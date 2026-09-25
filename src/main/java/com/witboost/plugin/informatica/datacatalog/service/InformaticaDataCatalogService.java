package com.witboost.plugin.informatica.datacatalog.service;

import com.witboost.plugin.informatica.common.exceptions.ApiCallException;
import com.witboost.plugin.informatica.common.mapper.datacontract.DataContractWorkbookMapper;
import com.witboost.plugin.informatica.common.mapper.datacontract.MappingContext;
import com.witboost.plugin.informatica.common.mapper.datacontract.Operation;
import com.witboost.plugin.informatica.common.mapper.naming.NameBuilder;
import com.witboost.plugin.informatica.common.model.ValidationLevel;
import com.witboost.plugin.informatica.common.model.informatica.DataContract;
import com.witboost.plugin.informatica.common.model.informatica.DeliveryTarget;
import com.witboost.plugin.informatica.common.model.informatica.TechnicalDataElement;
import com.witboost.plugin.informatica.datacatalog.common.Constants;
import com.witboost.plugin.informatica.datacatalog.config.DataCatalogSourceConfig;
import com.witboost.plugin.informatica.datacatalog.model.UpsertProductOperation;
import com.witboost.plugin.informatica.datacatalog.service.client.*;
import java.util.*;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class InformaticaDataCatalogService {

    private final AssetApiUtil assetApiUtil;

    private final DataCatalogAssetApiClient assetApiClient;

    private final ImportAssetApiClient importAssetApiClient;

    private final JobApiClient jobApiClient;

    private final CatalogSourceApiClient catalogSourceApiClient;

    private final TechnicalElementService technicalElementService;

    private final DataCatalogSourceConfig dataCatalogSourceConfig;

    private enum DataCatalogComponent {
        DATA_PRODUCT,
        OUTPUT_PORT,
        TECHNICAL_DATA_ELEMENT
    }

    public InformaticaDataCatalogService(
            AssetApiUtil assetApiUtil,
            DataCatalogAssetApiClient assetApiClient,
            ImportAssetApiClient importAssetApiClient,
            JobApiClient jobApiClient,
            CatalogSourceApiClient catalogSourceApiClient,
            TechnicalElementService technicalElementService,
            DataCatalogSourceConfig dataCatalogSourceConfig) {
        this.assetApiUtil = assetApiUtil;
        this.assetApiClient = assetApiClient;
        this.importAssetApiClient = importAssetApiClient;
        this.jobApiClient = jobApiClient;
        this.catalogSourceApiClient = catalogSourceApiClient;
        this.technicalElementService = technicalElementService;
        this.dataCatalogSourceConfig = dataCatalogSourceConfig;
    }

    /**
     * Checks if a data product already exists in the data catalog.
     *
     * @param dataProductSystemName the name of the data catalog SYS
     * @return true if existing, false otherwise
     */
    public boolean isProductInDataCatalog(String dataProductSystemName) {
        log.debug("Checking if product '{}' exists in Data Catalog", dataProductSystemName);
        return assetApiUtil.isAssetExisting("system", dataProductSystemName);
    }

    /**
     * Upserts a Data Product to Informatica Data Catalog.
     *
     * <p>This method automatically determines whether to create or update the Data Product based on
     * its existence in the catalog:
     *
     * <ul>
     *   <li>If the product exists → UPDATE operation
     *   <li>If the product does NOT exist → CREATE operation
     * </ul>
     *
     * @param dataContract The DataContract representing the Data Product to upsert
     * @return info record about upsert operation
     */
    public UpsertProductOperation upsertProductToDataCatalog(DataContract dataContract) {
        String productName = dataContract.getBaseCharacteristics().getName();
        String dataProductSystemName = NameBuilder.getDataCatalogDataProductName(dataContract);

        // At MID/HIGH the validation step has already synced the catalog source metadata,
        // so only sync here at LOW to avoid syncing twice for the same request.
        if (!dataContract.getValidationLevel().atLeast(ValidationLevel.MID)) {
            syncCatalogSourcesMetadata();
        }

        log.debug(
                "Checking existence of Data Product '{}' with SYSTEM name '{}' in Data Catalog",
                productName,
                dataProductSystemName);
        boolean productExists = isProductInDataCatalog(dataProductSystemName);

        if (productExists) {
            log.info("Data Product '{}' already exists in Data Catalog. Updating...", productName);
            updateProductToDataCatalog(dataContract);
        } else {
            log.info("Data Product '{}' does not exist in Data Catalog. Creating...", productName);
            insertProductToDataCatalog(dataContract);
        }
        return new UpsertProductOperation(productExists, dataProductSystemName);
    }

    public void updateProductToDataCatalog(DataContract dataContract) {
        String dataProductName = NameBuilder.getDataCatalogDataProductName(dataContract);
        var outputPortSystemsResponse =
                assetApiClient.pollForResults(
                        String.format("system in system \"%s\"", dataProductName));
        var existingOutputPorts =
                outputPortSystemsResponse.getHits().stream()
                        .map(
                                s ->
                                        s.getSummary()
                                                .getCoreName()
                                                .toUpperCase()) // TODO for some reason sometimes it
                        // is returned lowercase
                        .collect(Collectors.toSet());

        var toProvisionOutputPorts =
                dataContract.getDeliveryTargets().stream()
                        .map(dt -> NameBuilder.getDataCatalogOutputPortName(dataContract, dt))
                        .collect(Collectors.toSet());

        // Delete all assets related to obsolete output ports
        for (var existingOutputPort : existingOutputPorts) {
            if (!toProvisionOutputPorts.contains(existingOutputPort)) {
                // Find and delete all datasets in this output port system
                var dataSetsResponse =
                        assetApiClient.pollForResults(
                                String.format("dataset in system \"%s\"", existingOutputPort));
                for (var dataSet : dataSetsResponse.getHits()) {
                    invokeDeleteAssetApi("dataset", dataSet.getSummary().getCoreName());
                }
                // Delete output port system
                invokeDeleteAssetApi("system", existingOutputPort);
            }
        }

        // For each output port/dataset pick operation to perform
        // Map <asset name> -> CREATE/UPDATE
        Map<String, Operation> outputPortOperationsMap = new HashMap<>();
        Map<String, Operation> dataSetOperationsMap = new HashMap<>();

        for (var dt : dataContract.getDeliveryTargets()) {
            var toProvisionOutputPortName =
                    NameBuilder.getDataCatalogOutputPortName(dataContract, dt);
            if (!existingOutputPorts.contains(toProvisionOutputPortName)) {
                // CREATE output port and data sets
                outputPortOperationsMap.put(toProvisionOutputPortName, Operation.CREATE);
                for (var dataSet : dt.getDataAssets()) {
                    var toProvisionDataSetName = NameBuilder.getDataCatalogDataSetName(dt, dataSet);
                    dataSetOperationsMap.put(toProvisionDataSetName, Operation.CREATE);
                }
            } else {
                outputPortOperationsMap.put(toProvisionOutputPortName, Operation.UPDATE);
                var dataSetsResponse =
                        assetApiClient.pollForResults(
                                String.format(
                                        "dataset in system \"%s\"", toProvisionOutputPortName));
                var toProvisionDataSets =
                        dt.getDataAssets().stream()
                                .map(dataSet -> NameBuilder.getDataCatalogDataSetName(dt, dataSet))
                                .collect(Collectors.toSet());
                var existingDataSets =
                        dataSetsResponse.getHits().stream()
                                .map(ds -> ds.getSummary().getCoreName())
                                .collect(Collectors.toSet());
                for (var existingDataSetName : existingDataSets) {
                    if (toProvisionDataSets.contains(existingDataSetName)) {
                        // UPDATE
                        dataSetOperationsMap.put(existingDataSetName, Operation.UPDATE);
                    } else {
                        // DELETE
                        invokeDeleteAssetApi("dataset", existingDataSetName);
                    }
                }
                for (var toProvisionDataSetName : toProvisionDataSets) {
                    if (!existingDataSets.contains(toProvisionDataSetName)) {
                        // CREATE
                        dataSetOperationsMap.put(toProvisionDataSetName, Operation.CREATE);
                    }
                }
            }
        }

        String productName = dataContract.getBaseCharacteristics().getName();
        var firstStep =
                EnumSet.of(DataCatalogComponent.DATA_PRODUCT, DataCatalogComponent.OUTPUT_PORT);
        log.info("1 / 2: Importing components: {}", firstStep);
        executeImportJob(
                buildWorkbookForImport(
                        dataContract,
                        firstStep,
                        true,
                        outputPortOperationsMap,
                        dataSetOperationsMap),
                productName,
                "updated",
                "data product + output ports import for '" + productName + "'");

        var secondStep = EnumSet.of(DataCatalogComponent.TECHNICAL_DATA_ELEMENT);
        log.info("2 / 2: Importing components: {}", secondStep);
        executeTechnicalElementImport(
                buildWorkbookForImport(
                        dataContract,
                        secondStep,
                        true,
                        outputPortOperationsMap,
                        dataSetOperationsMap),
                productName,
                "updated",
                dataContract.getValidationLevel().atLeast(ValidationLevel.HIGH));

        log.info("Creating relationships between ports and sources");
        assureRelationshipFromPortsToSources(dataContract, Operation.CREATE);
    }

    public void deleteProductFromDataCatalog(DataContract dataContract) {
        String productName = dataContract.getBaseCharacteristics().getName();
        log.info("Deleting product '{}' from data catalog", productName);

        // Remove catalog source relationship
        log.info("Removing relationships between ports and sources");
        assureRelationshipFromPortsToSources(dataContract, Operation.DELETE);

        // Delete Dataset
        for (var deliveryTarget : dataContract.getDeliveryTargets()) {
            for (var dataSet : deliveryTarget.getDataAssets()) {
                invokeDeleteAssetApi(
                        "dataset", NameBuilder.getDataCatalogDataSetName(deliveryTarget, dataSet));
            }
        }
        // Delete Output Port
        for (var deliveryTarget : dataContract.getDeliveryTargets()) {
            invokeDeleteAssetApi(
                    "system",
                    NameBuilder.getDataCatalogOutputPortName(dataContract, deliveryTarget));
        }
        // Delete Data product
        invokeDeleteAssetApi("system", NameBuilder.getDataCatalogDataProductName(dataContract));

        // Note: delete of technical data elements association not supported/required
    }

    public void insertProductToDataCatalog(DataContract dataContract) {
        String productName = dataContract.getBaseCharacteristics().getName();
        var firstStep =
                EnumSet.of(DataCatalogComponent.DATA_PRODUCT, DataCatalogComponent.OUTPUT_PORT);
        log.info("1 / 2: Importing components: {}", firstStep);
        executeImportJob(
                buildWorkbookForImport(dataContract, firstStep, false, null, null),
                productName,
                "created",
                "data product + output ports import for '" + productName + "'");

        var secondStep = EnumSet.of(DataCatalogComponent.TECHNICAL_DATA_ELEMENT);
        log.info("2 / 2: Importing components: {}", secondStep);
        executeTechnicalElementImport(
                buildWorkbookForImport(dataContract, secondStep, false, null, null),
                productName,
                "created",
                dataContract.getValidationLevel().atLeast(ValidationLevel.HIGH));

        log.info("Creating relationships between ports and sources");
        assureRelationshipFromPortsToSources(dataContract, Operation.CREATE);
    }

    /**
     * For each output port, assures a relationship between the associated asset and the catalog
     * source for the output port technology.
     *
     * @param dataContract the data contract object
     * @param operation if {@code CREATE}, assure that the relationship exists, if {@code DELETE}
     *     assure that it the relationship does not exist
     */
    private void assureRelationshipFromPortsToSources(
            DataContract dataContract, Operation operation) {
        if (operation != Operation.CREATE && operation != Operation.DELETE) {
            throw new IllegalArgumentException("Unexpected operation " + operation);
        }
        for (var dt : dataContract.getDeliveryTargets()) {
            String deliveryTargetName = NameBuilder.getDataCatalogOutputPortName(dataContract, dt);
            var catalogSourceName =
                    dataCatalogSourceConfig.getCatalogSourceByTechnology(
                            dt.getBaseCharacteristics().getPortTechnology());
            var systemAssetResponse = assetApiUtil.getAssetByName("system", deliveryTargetName);
            var systemExternalId = systemAssetResponse.getHits().get(0).getExternalIdentity();
            var catalogSourceExternalId =
                    assetApiUtil.getCatalogSourceExternalId(catalogSourceName);
            log.debug(
                    "Handling link between catalog source '{}/{}' and output port '{}/{}' for operation '{}'",
                    catalogSourceName,
                    catalogSourceExternalId,
                    deliveryTargetName,
                    systemExternalId,
                    operation);
            var neighborsResponse =
                    assetApiClient.getAssetNeighbors(
                            systemExternalId, Constants.ClassTypes.CATALOG_SOURCE.getClassTypeId());
            boolean relationAlreadyExists = false;
            if (neighborsResponse.getNeighborhood() != null
                    && !neighborsResponse.getNeighborhood().isEmpty()) {
                var foundNeighbor =
                        neighborsResponse.getNeighborhood().get(0).getNeighbors().stream()
                                .filter(n -> Objects.equals(n.getNeighbor(), catalogSourceName))
                                .findFirst();
                relationAlreadyExists = foundNeighbor.isPresent();
            }
            String apiOperation = operation == Operation.CREATE ? "add" : "remove";
            boolean shouldPerformOperation =
                    (operation == Operation.CREATE && !relationAlreadyExists)
                            || (operation == Operation.DELETE && relationAlreadyExists);

            if (shouldPerformOperation) {
                log.info(
                        "Performing {} relationship between catalog source {} and system {}",
                        apiOperation,
                        catalogSourceName,
                        deliveryTargetName);
                assetApiClient.handleRelationship(
                        apiOperation,
                        Constants.AssociationTypes.RESOURCE_TO_SYSTEM.getAssociationId(),
                        systemExternalId,
                        catalogSourceExternalId);
            } else {
                log.debug(
                        "Skipping {} relationship between catalog source {} and system {}",
                        apiOperation,
                        catalogSourceName,
                        deliveryTargetName);
            }
        }
    }

    private void invokeDeleteAssetApi(String assetType, String assetName) {
        log.debug("Deleting {} by name '{}'", assetType, assetName);
        var asset = assetApiUtil.getAssetByName(assetType, assetName);
        if (!asset.hasValidHits()) {
            throw new IllegalStateException(
                    String.format(
                            "Cannot delete %s '%s': asset not found in Data Catalog",
                            assetType, assetName));
        }
        String externalId = asset.getHits().get(0).getExternalIdentity();
        log.debug("Deleting {} with external ID '{}'", assetType, externalId);
        assetApiClient.deleteAsset(externalId, AssetScheme.EXTERNAL);
    }

    public void syncCatalogSourcesMetadata() {
        if (!dataCatalogSourceConfig.enableMetadataSync()) {
            log.warn("Syncing catalog source metadata is disabled");
            return;
        }
        for (var catalogSourceName :
                new LinkedHashSet<>(dataCatalogSourceConfig.sources().values())) {
            if (catalogSourceName == null || catalogSourceName.isBlank()) continue;
            log.info("Syncing catalog source {}", catalogSourceName);
            var sourceUUID = assetApiUtil.getCatalogSourceUUID(catalogSourceName);
            try {
                var response = catalogSourceApiClient.syncCatalogSource(sourceUUID);
                var jobStatus =
                        jobApiClient.waitForJobCompletion(
                                response.getJobId(),
                                "catalog source metadata sync (" + catalogSourceName + ")");
                log.debug("Sync job completed with status '{}'", jobStatus);
            } catch (ApiCallException e) {
                log.warn("Syncing catalog source {} error: {}", catalogSourceName, e.getMessage());
            }
        }
    }

    /**
     * Executes the Informatica import asset job and waits for its completion.
     *
     * @param workbook The Excel workbook to import
     * @param productName The name of the Data Product (for logging)
     * @param action The action being performed ("created" or "updated")
     * @param jobDescription A human-readable label describing the job, used in polling logs
     */
    private void executeImportJob(
            Workbook workbook, String productName, String action, String jobDescription) {
        log.debug("Invoking import asset job for Data Product: {}", productName);
        String jobId = importAssetApiClient.invokeImportAssetJob(workbook);

        log.debug("Import job started with ID: {}. Waiting for completion...", jobId);
        jobApiClient.waitForJobCompletion(jobId, jobDescription);

        log.info("Successfully {} Data Product '{}' in Data Catalog", action, productName);
    }

    /**
     * Executes the technical data element import job (step 2/2), associating dataset columns to the
     * datasets created in step 1/2. In strict mode an import failure is fatal and propagates. In
     * non-strict mode the failure is logged as a warning and provisioning proceeds with systems and
     * datasets registered, but datasets remain without column associations. Column resolution
     * errors are never caught here: the workbook is built by the caller, so a misconfigured
     * database/schema/table stays fatal and surfaces as a provisioning error.
     */
    private void executeTechnicalElementImport(
            Workbook workbook, String productName, String action, boolean fatalOnFailure) {
        String jobDescription = "technical data element import (columns) for '" + productName + "'";
        if (fatalOnFailure) {
            executeImportJob(workbook, productName, action, jobDescription);
            return;
        }
        try {
            executeImportJob(workbook, productName, action, jobDescription);
        } catch (Exception e) {
            log.warn(
                    "2 / 2: Technical data element import failed for Data Product '{}'. Systems and datasets are "
                            + "registered, but column associations are missing. Cause: {}",
                    productName,
                    e.getMessage());
        }
    }

    /**
     * Builds the Excel workbook for importing/updating a Data Product in Informatica.
     *
     * @param dataContract The DataContract to process
     * @param componentSelection components to include in this import
     * @param isUpdate if false, data product does not exist and must be created, otherwise needs to
     *     be updated
     * @param outputPortOperations if isUpdate a map of operations for each output port, null
     *     otherwise
     * @param dataSetOperations if {@code isUpdate}, a map of operations for each dataset, null
     *     otherwise
     * @return The populated Excel workbook ready for import
     */
    private Workbook buildWorkbookForImport(
            DataContract dataContract,
            EnumSet<DataCatalogComponent> componentSelection,
            boolean isUpdate,
            Map<String, Operation> outputPortOperations,
            Map<String, Operation> dataSetOperations) {
        if (isUpdate && (outputPortOperations == null || dataSetOperations == null)) {
            throw new IllegalArgumentException("for UPDATE operation mappings are required");
        }
        String productName = dataContract.getBaseCharacteristics().getName();
        log.debug(
                "Building workbook:, productName: {}, components: {}, outputPortOperations: {}, dataSetOperations: {}",
                productName,
                componentSelection,
                outputPortOperations,
                dataSetOperations);

        var dataProductOperation = isUpdate ? Operation.UPDATE : Operation.CREATE;
        Map<String, Object> baseExtras = buildBaseExtras(dataContract, dataProductOperation);

        var mapper = new DataContractWorkbookMapper();

        // Add Data Product system
        if (componentSelection.contains(DataCatalogComponent.DATA_PRODUCT)) {
            mapper.addSystem(dataContract, dataProductOperation, baseExtras);
        }

        // Add Output port system and Data sets.
        if (componentSelection.contains(DataCatalogComponent.OUTPUT_PORT)) {
            for (var dt : dataContract.getDeliveryTargets()) {
                String deliveryTargetName =
                        NameBuilder.getDataCatalogOutputPortName(dataContract, dt);
                var deliveryTargetOperation = Operation.CREATE;
                if (outputPortOperations != null) {
                    deliveryTargetOperation = outputPortOperations.get(deliveryTargetName);
                    if (deliveryTargetOperation == null) {
                        throw new IllegalArgumentException(
                                "missing operation for delivery target " + deliveryTargetName);
                    }
                }
                addDeliveryTargetToWorkbook(
                        dataContract,
                        deliveryTargetOperation,
                        dataSetOperations,
                        mapper,
                        baseExtras,
                        dt);
            }
        }

        // Add technical data elements (columns)
        if (componentSelection.contains(DataCatalogComponent.TECHNICAL_DATA_ELEMENT)) {
            addDataSetColumnsToWorkbook(dataContract, mapper, baseExtras);
        }

        return mapper.getWorkbook();
    }

    /** Builds the base extras Map with common context information. */
    private Map<String, Object> buildBaseExtras(DataContract dataContract, Operation operation) {
        Map<String, Object> extras = new HashMap<>();
        extras.put(
                MappingContext.DATA_PRODUCT_NAME,
                NameBuilder.getDataCatalogDataProductName(dataContract));
        extras.put(
                MappingContext.DATA_PRODUCT_DOMAIN, dataContract.getReferenceContext().getDomain());
        extras.put(
                MappingContext.DATA_PRODUCT_SUBDOMAIN,
                dataContract.getReferenceContext().getSubdomain());

        // For UPDATE operations, add the Reference ID
        if (operation == Operation.UPDATE) {
            String dataProductSystemName = NameBuilder.getDataCatalogDataProductName(dataContract);
            String referenceId = retrieveReferenceId(dataProductSystemName, "Data Product");
            extras.put(MappingContext.DATA_PRODUCT_REFERENCE_ID, referenceId);
        }

        return extras;
    }

    /** Adds a single Delivery target (Output Port) */
    private void addDeliveryTargetToWorkbook(
            DataContract dataContract,
            Operation deliveryTargetOperation,
            Map<String, Operation> dataSetsOperations,
            DataContractWorkbookMapper mapper,
            Map<String, Object> baseExtras,
            DeliveryTarget dt) {
        // Prepare extras for Output Port
        Map<String, Object> outputPortExtras = new HashMap<>(baseExtras);
        String deliveryTargetName = NameBuilder.getDataCatalogOutputPortName(dataContract, dt);
        outputPortExtras.put(MappingContext.DELIVERY_TARGET_NAME, deliveryTargetName);

        // For UPDATE, retrieve the Output Port Reference ID
        if (deliveryTargetOperation == Operation.UPDATE) {
            String referenceId =
                    retrieveReferenceId(deliveryTargetName, "Delivery Target (Output Port)");
            outputPortExtras.put(MappingContext.DELIVERY_TARGET_REFERENCE_ID, referenceId);
        }

        // Add Output Port system and its Data Assets
        mapper.addSystem(dt, deliveryTargetOperation, outputPortExtras);
        mapper.addDataSets(dt, dataSetsOperations, outputPortExtras, assetApiClient);
    }

    /**
     * Retrieves the Reference ID (externalIdentity) for a system from Informatica.
     *
     * @param systemName The name of the system to retrieve
     * @param systemType The type of system (for error messages)
     * @return The Reference ID (externalIdentity)
     * @throws IllegalStateException if the system is not found
     */
    private String retrieveReferenceId(String systemName, String systemType) {
        log.debug("Retrieving Reference ID for {} '{}'", systemType, systemName);

        var existingSystem = assetApiUtil.getAssetByName("system", systemName);

        if (!existingSystem.hasValidHits()) {
            throw new IllegalStateException(
                    String.format(
                            "Cannot update %s '%s': system not found in Data Catalog",
                            systemType, systemName));
        }

        String referenceId = existingSystem.getHits().get(0).getExternalIdentity();
        log.debug("Retrieved Reference ID '{}' for {} '{}'", referenceId, systemType, systemName);

        return referenceId;
    }

    void addDataSetColumnsToWorkbook(
            DataContract dataContract,
            DataContractWorkbookMapper mapper,
            Map<String, Object> extras) {
        for (var deliveryTarget : dataContract.getDeliveryTargets()) {
            for (var dataSet : deliveryTarget.getDataAssets()) {
                String catalogSourceName =
                        resolveCatalogSourceName(deliveryTarget)
                                .orElseThrow(
                                        () ->
                                                new IllegalArgumentException(
                                                        "No catalog source configured for output port technology"));
                String dbName = dataSet.getSystemInfo().getDatabaseName();
                String schemaName = dataSet.getSystemInfo().getSchemaName();
                String dataSetName = NameBuilder.getDataCatalogDataSetName(deliveryTarget, dataSet);
                String entityName = dataSet.getEntityInfo().getEntityName();

                List<TechnicalDataElement> datasetColumns;
                try {
                    datasetColumns =
                            technicalElementService.searchDataElements(
                                    catalogSourceName, dbName, schemaName, entityName);
                } catch (Exception e) {
                    log.warn(
                            "Could not retrieve technical elements for entity '{}' (catalog src {}, db {}, schema {}); "
                                    + "skipping its columns in the workbook: {}",
                            entityName,
                            catalogSourceName,
                            dbName,
                            schemaName,
                            e.getMessage());
                    continue;
                }
                Map<String, Object> dataSetExtras = new HashMap<>(extras);
                dataSetExtras.put(MappingContext.DATA_ASSET_NAME, dataSetName);
                mapper.addDatasetColumns(datasetColumns, dataSetExtras);
            }
        }
    }

    private Optional<String> resolveCatalogSourceName(DeliveryTarget deliveryTarget) {
        return Optional.ofNullable(deliveryTarget.getBaseCharacteristics())
                .map(DeliveryTarget.BaseCharacteristics::getPortTechnology)
                .flatMap(
                        technology ->
                                dataCatalogSourceConfig.findCatalogSourceByTechnology(
                                        technology.toLowerCase()));
    }
}
