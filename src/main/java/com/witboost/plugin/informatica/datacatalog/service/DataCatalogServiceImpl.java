package com.witboost.plugin.informatica.datacatalog.service;

import com.witboost.plugin.informatica.common.exceptions.FailedOperation;
import com.witboost.plugin.informatica.common.exceptions.Problem;
import com.witboost.plugin.informatica.common.mapper.dataproduct.DataAssetMapper;
import com.witboost.plugin.informatica.common.mapper.dataproduct.DataContractMapper;
import com.witboost.plugin.informatica.common.mapper.dataproduct.DeliveryTargetMapper;
import com.witboost.plugin.informatica.common.model.ValidationLevel;
import com.witboost.plugin.informatica.common.model.informatica.DataAsset;
import com.witboost.plugin.informatica.common.model.informatica.DataContract;
import com.witboost.plugin.informatica.common.model.informatica.DeliveryTarget;
import com.witboost.plugin.informatica.common.model.informatica.TechnicalDataElement;
import com.witboost.plugin.informatica.common.model.witboost.DataProduct;
import com.witboost.plugin.informatica.common.parser.Parser;
import com.witboost.plugin.informatica.datacatalog.common.exceptions.DataCatalogPluginValidationException;
import com.witboost.plugin.informatica.datacatalog.config.DataCatalogSourceConfig;
import com.witboost.plugin.informatica.datacatalog.config.DataCatalogUiConfig;
import com.witboost.plugin.informatica.datacatalog.config.ValidationLevelConfig;
import com.witboost.plugin.informatica.datacatalog.openapi.model.*;
import com.witboost.plugin.informatica.datacatalog.service.client.AssetApiUtil;
import io.vavr.control.Try;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class DataCatalogServiceImpl implements DataCatalogService {

    private final String publishAllowedEnvironment;
    private final ValidatorService<DataProduct> dataProductValidator;
    private final ValidatorService<DataContract> dataContractValidator;
    private final DataContractMapper dataContractMapper;
    private final DeliveryTargetMapper deliveryTargetMapper;
    private final DataAssetMapper dataAssetMapper;
    private final InformaticaDataCatalogService informaticaDataCatalogService;
    private final DataCatalogUiConfig uiConfig;
    private final AssetApiUtil assetApiUtil;
    private final DataCatalogSourceConfig dataCatalogSourceConfig;
    private final ValidationLevelConfig validationLevelConfig;
    private final TechnicalElementService technicalElementService;

    private final Logger logger = LoggerFactory.getLogger(DataCatalogServiceImpl.class);

    public DataCatalogServiceImpl(
            ValidatorService<DataProduct> dataProductValidator,
            ValidatorService<DataContract> dataContractValidator,
            DataContractMapper dataContractMapper,
            DeliveryTargetMapper deliveryTargetMapper,
            DataAssetMapper dataAssetMapper,
            InformaticaDataCatalogService informaticaDataCatalogService,
            DataCatalogUiConfig uiConfig,
            AssetApiUtil assetApiUtil,
            DataCatalogSourceConfig dataCatalogSourceConfig,
            ValidationLevelConfig validationLevelConfig,
            TechnicalElementService technicalElementService,
            @Value("${informatica.publish_allowed_environment}") String publishAllowedEnvironment) {
        this.dataProductValidator = dataProductValidator;
        this.dataContractValidator = dataContractValidator;
        this.dataContractMapper = dataContractMapper;
        this.deliveryTargetMapper = deliveryTargetMapper;
        this.dataAssetMapper = dataAssetMapper;
        this.informaticaDataCatalogService = informaticaDataCatalogService;
        this.uiConfig = uiConfig;
        this.assetApiUtil = assetApiUtil;
        this.dataCatalogSourceConfig = dataCatalogSourceConfig;
        this.validationLevelConfig = validationLevelConfig;
        this.technicalElementService = technicalElementService;
        this.publishAllowedEnvironment = publishAllowedEnvironment;
    }

    @Override
    public EntityReference getEntityReference(String componentId) {
        return null;
    }

    @Override
    public ValidationResult validate(ProvisioningRequest provisioningRequest) {
        try {
            var dataProduct = parseDataProduct(provisioningRequest);
            var publishDecision =
                    Parser.evaluatePublishToInformatica(
                            provisioningRequest.getDescriptor(), publishAllowedEnvironment);
            if (!publishDecision.shouldPublish()) {
                logger.info("Skipping validation. {}", publishDecision.reason());
                return new ValidationResult(true);
            }
            mapAndValidate(dataProduct, provisioningRequest);
            return new ValidationResult(true);
        } catch (DataCatalogPluginValidationException ex) {
            List<String> errors =
                    ex.getFailedOperation().problems().stream().map(Problem::getMessage).toList();
            logger.error("Validation failed with {} error(s): {}", errors.size(), errors);
            return new ValidationResult(false).error(new ValidationError(errors));
        }
    }

    @Override
    public ProvisioningStatus provision(ProvisioningRequest provisioningRequest) {
        return runOperation(
                provisioningRequest,
                "Provisioning",
                dataContract -> {
                    var operation =
                            informaticaDataCatalogService.upsertProductToDataCatalog(dataContract);
                    var dataProductAsset =
                            assetApiUtil.getAssetByName("system", operation.dataProductAssetName());
                    if (!dataProductAsset.hasValidHits()) {
                        throw new IllegalStateException(
                                "Cannot retrieve created/updated data product system assset");
                    }
                    String dataProductUrl =
                            uiConfig.baseUrl()
                                    + "/asset/"
                                    + dataProductAsset.getHits().get(0).getCoreIdentity();
                    return new ProvisioningStatus()
                            .status(ProvisioningStatus.StatusEnum.COMPLETED)
                            .result("Provisioning completed successfully")
                            .info(new Info().publicInfo(dataProductUrl));
                });
    }

    @Override
    public ProvisioningStatus unprovision(ProvisioningRequest provisioningRequest) {
        return runOperation(
                provisioningRequest,
                "Unprovisioning",
                dataContract -> {
                    informaticaDataCatalogService.deleteProductFromDataCatalog(dataContract);
                    return new ProvisioningStatus()
                            .status(ProvisioningStatus.StatusEnum.COMPLETED)
                            .result("Unprovisioning completed successfully");
                });
    }

    private ProvisioningStatus runOperation(
            ProvisioningRequest req,
            String label,
            Function<DataContract, ProvisioningStatus> action) {
        var publishDecision =
                Parser.evaluatePublishToInformatica(req.getDescriptor(), publishAllowedEnvironment);
        if (!publishDecision.shouldPublish()) {
            logger.info("{}", publishDecision.reason());
            return new ProvisioningStatus()
                    .status(ProvisioningStatus.StatusEnum.COMPLETED)
                    .result(publishDecision.reason());
        }
        return Try.of(
                        () -> {
                            var dataContract = parseMapAndValidate(req);
                            logger.info(
                                    "{} Data Product '{}' to Informatica Data Catalog",
                                    label,
                                    dataContract.getBaseCharacteristics().getName());
                            return action.apply(dataContract);
                        })
                .recover(
                        DataCatalogPluginValidationException.class,
                        ex -> {
                            var errors =
                                    String.join(
                                            "; ",
                                            ex.getFailedOperation().problems().stream()
                                                    .map(Problem::getMessage)
                                                    .toList());
                            logger.error("{} request validation failed: {}", label, errors);
                            return new ProvisioningStatus()
                                    .status(ProvisioningStatus.StatusEnum.FAILED)
                                    .result(label + " request validation failed: " + errors);
                        })
                .getOrElseGet(
                        t -> {
                            logger.error("Unexpected error during {}", label.toLowerCase(), t);
                            return new ProvisioningStatus()
                                    .status(ProvisioningStatus.StatusEnum.FAILED)
                                    .result(t.getMessage());
                        });
    }

    private DataContract parseMapAndValidate(ProvisioningRequest provisioningRequest) {
        var dataProduct = parseDataProduct(provisioningRequest);
        return mapAndValidate(dataProduct, provisioningRequest);
    }

    private DataContract mapAndValidate(
            DataProduct dataProduct, ProvisioningRequest provisioningRequest) {
        ValidationLevel level = resolveValidationLevel(provisioningRequest, dataProduct);
        logger.info(
                "Validation level resolved to {} (environment '{}')",
                level,
                dataProduct.getEnvironment());

        // LOW: structural / parameter validation (offline)
        if (level.atLeast(ValidationLevel.LOW)) {
            validateDataProduct(dataProduct);
        }

        var dataContract = mapDataContract(dataProduct);
        dataContract.setValidationLevel(level);

        if (level.atLeast(ValidationLevel.LOW)) {
            validateDataContract(dataContract);
        }
        // MID: live checks against Informatica, collected cumulatively so all issues surface at
        // once.
        // HIGH: also descriptor-vs-catalog column match.
        if (level.atLeast(ValidationLevel.MID)) {
            logger.info(
                    "Running live Informatica validation checks (catalog source) at level {}",
                    level);
            logger.info("Syncing catalog source metadata before live validation checks");
            informaticaDataCatalogService.syncCatalogSourcesMetadata();
            List<Problem> problems = checkCatalogSourcesAndTables(dataContract, level);
            if (!problems.isEmpty()) {
                throw new DataCatalogPluginValidationException(
                        "Validation against Informatica failed. See error details for more information",
                        new FailedOperation(problems));
            }
            logger.info("Live Informatica validation checks passed");
        } else {
            logger.info(
                    "Skipping live Informatica validation checks (catalog source): validation level is {}",
                    level);
        }
        return dataContract;
    }

    private ValidationLevel resolveValidationLevel(
            ProvisioningRequest provisioningRequest, DataProduct dataProduct) {
        ValidationLevel descriptorOverride =
                Parser.readValidationLevel(provisioningRequest.getDescriptor())
                        .map(this::parseLevelOrNull)
                        .orElse(null);
        return validationLevelConfig.resolve(dataProduct.getEnvironment(), descriptorOverride);
    }

    private ValidationLevel parseLevelOrNull(String raw) {
        try {
            return ValidationLevel.valueOf(raw);
        } catch (IllegalArgumentException e) {
            logger.warn("Unknown validationLevel '{}' in descriptor, ignoring override", raw);
            return null;
        }
    }

    private DataProduct parseDataProduct(ProvisioningRequest provisioningRequest) {
        logger.debug("Parsing Data Product descriptor");
        var eitherDataProduct = Parser.parseDataProduct(provisioningRequest.getDescriptor());
        if (eitherDataProduct.isLeft()) {
            // Logged once at the operation boundary (validate()/runOperation()); see
            // validateDataContract.
            throw new DataCatalogPluginValidationException(
                    "Descriptor parse failed. See error details for more information",
                    eitherDataProduct.getLeft());
        }
        var dataProduct = eitherDataProduct.get();
        logger.info("Parsed Data Product descriptor for '{}'", dataProduct.getName());
        return dataProduct;
    }

    private void validateDataProduct(DataProduct dataProduct) {
        logger.debug("Validating Data Product descriptor");
        dataProductValidator
                .validate(dataProduct)
                .ifPresent(
                        failedOperation -> {
                            // Logged once at the operation boundary (validate()/runOperation());
                            // see validateDataContract.
                            throw new DataCatalogPluginValidationException(
                                    "Descriptor validation failed. See error details for more information",
                                    failedOperation);
                        });
    }

    private DataContract mapDataContract(DataProduct dataProduct) {
        logger.info("Mapping Data Product to Data Contract");
        var dataContract =
                dataContractMapper.toDataContractWithOutputPorts(
                        dataProduct, deliveryTargetMapper, dataAssetMapper);
        logger.info(
                "Mapped Data Product '{}' to Data Contract '{}'",
                dataProduct.getName(),
                dataContract.getBaseCharacteristics().getName());
        return dataContract;
    }

    private void validateDataContract(DataContract dataContract) {
        dataContractValidator
                .validate(dataContract)
                .ifPresent(
                        failedOperation -> {
                            // Logged once at the operation boundary: validate() logs the messages
                            // on the caught
                            // DataCatalogPluginValidationException, runOperation() logs them in its
                            // recover branch.
                            throw new DataCatalogPluginValidationException(
                                    "Data Contract validation failed. See error details for more information",
                                    failedOperation);
                        });
    }

    private List<Problem> checkCatalogSourcesAndTables(
            DataContract dataContract, ValidationLevel level) {
        List<Problem> problems = new ArrayList<>();
        Set<String> checkedSources = new HashSet<>();
        Set<String> missingSources = new HashSet<>();

        for (var dt : dataContract.getDeliveryTargets()) {
            var catalogSourceOpt = resolveCatalogSourceName(dt);
            if (catalogSourceOpt.isEmpty())
                continue; // unsupported tech already reported by DataContractValidatorService

            String catalogSourceName = catalogSourceOpt.get();
            String portTech = dt.getBaseCharacteristics().getPortTechnology();
            // Existence is checked once per source; subsequent delivery targets reuse the result.
            if (checkedSources.add(catalogSourceName)) {
                logger.info(
                        "Checking catalog source '{}' (technology '{}') exists in Informatica",
                        catalogSourceName,
                        portTech);
                if (!assetApiUtil.isAssetExisting("catalog source", catalogSourceName)) {
                    logger.error(
                            "Catalog source '{}' for technology '{}' not found in Informatica",
                            catalogSourceName,
                            portTech);
                    problems.add(
                            new Problem(
                                    "Catalog source '"
                                            + catalogSourceName
                                            + "' for technology '"
                                            + portTech
                                            + "' not found in Informatica Data Catalog"));
                    missingSources.add(catalogSourceName);
                } else {
                    logger.info("Catalog source '{}' exists", catalogSourceName);
                }
            }
            if (missingSources.contains(catalogSourceName)) {
                continue; // skip table checks for a missing catalog source (across all sharing
                // delivery targets)
            }

            for (var dataAsset : dt.getDataAssets()) {
                String dbName = dataAsset.getSystemInfo().getDatabaseName();
                String schemaName = dataAsset.getSystemInfo().getSchemaName();
                String entityName = dataAsset.getEntityInfo().getEntityName();
                if (level.atLeast(ValidationLevel.HIGH)) {
                    logger.info(
                            "Checking columns for table '{}' (database '{}', schema '{}') against catalog source '{}'",
                            entityName,
                            dbName,
                            schemaName,
                            catalogSourceName);
                    checkColumnMatch(
                            catalogSourceName, dbName, schemaName, entityName, dataAsset, problems);
                } else {
                    logger.info(
                            "Checking table '{}' (database '{}', schema '{}') exists in catalog source '{}'",
                            entityName,
                            dbName,
                            schemaName,
                            catalogSourceName);
                    checkTableExists(catalogSourceName, dbName, schemaName, entityName, problems);
                }
            }
        }

        return problems;
    }

    /** MID: the table must have at least one technical element in the catalog source. */
    private void checkTableExists(
            String catalogSourceName,
            String dbName,
            String schemaName,
            String entityName,
            List<Problem> problems) {
        boolean found;
        try {
            found =
                    technicalElementService.hasDataElements(
                            catalogSourceName, dbName, schemaName, entityName);
        } catch (Exception e) {
            logger.warn(
                    "Technical element existence check failed for table '{}': {}",
                    entityName,
                    e.getMessage());
            found = false;
        }
        if (!found) {
            problems.add(
                    new Problem(
                            noElementsMessage(catalogSourceName, dbName, schemaName, entityName)));
        } else {
            logger.info("Table '{}' found in catalog source '{}'", entityName, catalogSourceName);
        }
    }

    /**
     * HIGH: every column declared in the descriptor must exist among the catalog's technical
     * elements.
     */
    private void checkColumnMatch(
            String catalogSourceName,
            String dbName,
            String schemaName,
            String entityName,
            DataAsset dataAsset,
            List<Problem> problems) {
        List<TechnicalDataElement> catalogColumns;
        try {
            catalogColumns =
                    technicalElementService.searchDataElements(
                            catalogSourceName, dbName, schemaName, entityName);
        } catch (Exception e) {
            logger.warn("Column resolution failed for table '{}': {}", entityName, e.getMessage());
            problems.add(
                    new Problem(
                            noElementsMessage(catalogSourceName, dbName, schemaName, entityName)));
            return;
        }

        Set<String> catalogNames =
                catalogColumns.stream()
                        .map(TechnicalDataElement::getCoreName)
                        .filter(Objects::nonNull)
                        .map(String::toUpperCase)
                        .collect(Collectors.toSet());

        List<String> missing =
                dataAsset.getAttributes().stream()
                        .map(DataAsset.AttributeInfo::getAttributeName)
                        .filter(Objects::nonNull)
                        .filter(name -> !catalogNames.contains(name.toUpperCase()))
                        .toList();

        if (!missing.isEmpty()) {
            problems.add(
                    new Problem(
                            String.format(
                                    "Columns declared in descriptor but not found in catalog source '%s' for table '%s' (database '%s', schema '%s'): %s",
                                    catalogSourceName,
                                    entityName,
                                    dbName,
                                    schemaName,
                                    String.join(", ", missing))));
        } else {
            logger.info(
                    "All {} declared column(s) matched for table '{}' in catalog source '{}'",
                    dataAsset.getAttributes().size(),
                    entityName,
                    catalogSourceName);
        }
    }

    private String noElementsMessage(
            String catalogSourceName, String dbName, String schemaName, String entityName) {
        return String.format(
                "No technical elements found in catalog source '%s' for database '%s', schema '%s', table '%s'. "
                        + "Check that database, schema and table name are correct and the catalog source is synced.",
                catalogSourceName, dbName, schemaName, entityName);
    }

    private Optional<String> resolveCatalogSourceName(DeliveryTarget dt) {
        return Optional.ofNullable(dt.getBaseCharacteristics())
                .map(DeliveryTarget.BaseCharacteristics::getPortTechnology)
                .flatMap(
                        technology ->
                                dataCatalogSourceConfig.findCatalogSourceByTechnology(
                                        technology.toLowerCase()));
    }
}
