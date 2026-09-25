package com.witboost.plugin.informatica.marketplace.service;

import com.witboost.plugin.informatica.common.exceptions.ApiCallException;
import com.witboost.plugin.informatica.common.model.informatica.DataContract;
import com.witboost.plugin.informatica.marketplace.common.Constants;
import com.witboost.plugin.informatica.marketplace.mapper.datacontract.CreateCollectionMapper;
import com.witboost.plugin.informatica.marketplace.model.UpdateDataCollectionRequest;
import com.witboost.plugin.informatica.marketplace.service.client.DataCollectionApiClient;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DataCollectionService {

    private final DataCollectionApiClient dataCollectionApiClient;
    private final CategoryService categoryService;
    private final DeliveryTargetService deliveryTargetService;
    private final DataAssetService dataAssetService;
    private final CustomAttributeService customAttributeService;
    private final CreateCollectionMapper createCollectionMapper;
    private final DataContractResolverService dataContractResolverService;

    public DataCollectionService(
            DataCollectionApiClient dataCollectionApiClient,
            CategoryService categoryService,
            DeliveryTargetService deliveryTargetService,
            DataAssetService dataAssetService,
            CustomAttributeService customAttributeService,
            CreateCollectionMapper createCollectionMapper,
            DataContractResolverService dataContractResolverService) {
        this.dataCollectionApiClient = dataCollectionApiClient;
        this.categoryService = categoryService;
        this.deliveryTargetService = deliveryTargetService;
        this.dataAssetService = dataAssetService;
        this.customAttributeService = customAttributeService;
        this.createCollectionMapper = createCollectionMapper;
        this.dataContractResolverService = dataContractResolverService;
    }

    public boolean collectionExists(String name) {
        return dataCollectionApiClient.collectionExists(name);
    }

    public String createCollection(DataContract dataContract) {
        log.info("Starting collection creation process");

        try {
            String dataProductName = dataContract.getBaseCharacteristics().getName();
            log.debug("Creating collection for data product: {}", dataProductName);

            // Extract context information
            String company = dataContract.getReferenceContext().getCompany();
            String domain = dataContract.getReferenceContext().getDomain();
            String subDomain = dataContract.getReferenceContext().getSubdomain();
            log.debug("Context: company={}, domain={}, subdomain={}", company, domain, subDomain);

            // Retrieve category by company, domain, and subdomain
            log.debug(
                    "Retrieving category for: company={}, domain={}, subdomain={}",
                    company,
                    domain,
                    subDomain);
            var dataProductCategory =
                    categoryService.getCategoryByCompanyDomainSubdomain(company, domain, subDomain);
            log.info(
                    "Category resolved: ID={}, name={}",
                    dataProductCategory.getId(),
                    dataProductCategory.getName());

            // Get custom attributes map from Informatica
            log.debug("Retrieving custom attributes map from Informatica");
            var customAttributesName2Id =
                    customAttributeService.getDataCollectionCustomAttributesNameId();

            // Map DataContract to CreateDataCollectionRequest
            log.debug("Mapping DataContract to CreateDataCollectionRequest");
            var createCollectionRequest =
                    createCollectionMapper.mapToCreateDataCollectionRequest(
                            dataContract, dataProductCategory.getId(), customAttributesName2Id);
            log.debug(
                    "Mapped request: name={}, categoryId={}, status={}, customAttributes count={}",
                    createCollectionRequest.getName(),
                    createCollectionRequest.getCategoryId(),
                    createCollectionRequest.getStatus(),
                    createCollectionRequest.getCustomAttributes() != null
                            ? createCollectionRequest.getCustomAttributes().size()
                            : 0);

            // Create collection via API
            log.debug("Calling Informatica API to create collection: {}", dataProductName);
            var response = dataCollectionApiClient.createCollection(createCollectionRequest);
            log.info(
                    "Collection created successfully: name='{}', ID={}, externalId={}",
                    dataProductName,
                    response.getId(),
                    response.getExternalId());

            return response.getId();

        } catch (Exception e) {
            String errorMessage =
                    String.format(
                            "Unexpected error during collection creation for data product '%s': %s",
                            dataContract.getBaseCharacteristics().getName(), e.getMessage());
            log.error(errorMessage, e);
            throw new ApiCallException(errorMessage, e);
        }
    }

    public String updateCollection(DataContract dataContract) {
        log.info("Starting collection update process");

        try {
            String dataProductName = dataContract.getBaseCharacteristics().getName();
            log.debug("Updating collection for data product: {}", dataProductName);

            // Enrich data contract with identifier from Informatica
            log.debug("Enriching data contract with data from Informatica API");
            var dataContractEnriched =
                    dataContractResolverService.getDataContractEnriched(dataContract);
            var dataProductIdentifier =
                    dataContractEnriched.getBaseCharacteristics().getIdentifier();
            log.info("Data product identifier resolved: {}", dataProductIdentifier);

            // Update delivery targets (Output Ports)
            log.debug("Updating delivery targets for collection: {}", dataProductIdentifier);
            deliveryTargetService.updateDeliveryTargets(dataProductIdentifier, dataContract);

            // Update data asset (link to data catalog)
            log.debug("Updating data asset for collection: {}", dataProductIdentifier);
            dataAssetService.updateDataProductAssets(dataProductIdentifier, dataContract);

            // Prepare update requests
            log.debug("Preparing summary update request");
            UpdateDataCollectionRequest updateSummaryReq = getUpdateSummaryReq(dataContract);

            log.debug("Preparing custom attributes update request");
            UpdateDataCollectionRequest updateCustomAttributeReq =
                    getUpdateCustomAttributeReq(dataContract);

            // Update summary information
            log.debug("Updating collection summary for: {}", dataProductIdentifier);
            dataCollectionApiClient.updateCollection(dataProductIdentifier, updateSummaryReq);
            log.info("Successfully updated collection summary for: {}", dataProductIdentifier);

            // Update custom attributes
            log.debug("Updating collection custom attributes for: {}", dataProductIdentifier);
            dataCollectionApiClient.updateCollection(
                    dataProductIdentifier, updateCustomAttributeReq);
            log.info(
                    "Successfully updated collection custom attributes for: {}",
                    dataProductIdentifier);

            log.info("Collection update completed successfully for: {}", dataProductName);

            return dataProductIdentifier;

        } catch (Exception e) {
            String errorMessage =
                    String.format(
                            "Unexpected error during collection update for data product '%s': %s",
                            dataContract.getBaseCharacteristics().getName(), e.getMessage());
            log.error(errorMessage, e);
            throw new ApiCallException(errorMessage, e);
        }
    }

    private UpdateDataCollectionRequest getUpdateCustomAttributeReq(DataContract dataContract) {
        log.debug("Building custom attributes update request");

        // Get the map of custom attribute names -> IDs from Informatica
        Map<String, String> customAttributesMap =
                customAttributeService.getDataCollectionCustomAttributesNameId();

        // Get the map of custom attribute IDs -> values from the DataContract instance
        Map<String, Object> customAttributeValuesMap =
                dataContract.getCustomAttributeValues(customAttributesMap);

        // Convert to list of CustomAttribute objects
        List<UpdateDataCollectionRequest.CustomAttribute> customAttributes =
                customAttributeValuesMap.entrySet().stream()
                        .map(
                                entry ->
                                        UpdateDataCollectionRequest.CustomAttribute.builder()
                                                .id(entry.getKey())
                                                .value(entry.getValue())
                                                .build())
                        .toList();

        log.debug("Created {} custom attributes for update", customAttributes.size());
        return UpdateDataCollectionRequest.updateCustomAttributes(customAttributes);
    }

    private UpdateDataCollectionRequest getUpdateSummaryReq(DataContract dataContract) {
        return UpdateDataCollectionRequest.updateSummary(
                dataContract.getBaseCharacteristics().getName(),
                dataContract.getBaseCharacteristics().getDescription(),
                Constants.DATA_COLLECTION_PUBLISHED_STATUS);
    }

    /**
     * Deletes a data collection for a given data product.
     *
     * @param dataContract the contract of the data product
     * @return ID of the delete collection, null if collection not found
     */
    public String deleteCollection(DataContract dataContract) {
        var dataContractEnriched =
                dataContractResolverService.getDataContractEnriched(dataContract);

        var dataProductIdentifier = dataContractEnriched.getBaseCharacteristics().getIdentifier();

        dataCollectionApiClient.deleteCollection(dataProductIdentifier);

        return dataProductIdentifier;
    }

    public void addDataAssetsToCollection(String collectionId, List<String> createdDataAssetIds) {
        for (var dataAssetId : createdDataAssetIds) {
            log.debug(
                    "Adding data asset with ID '{}' to data collection with ID '{}'",
                    dataAssetId,
                    dataCollectionApiClient);
            dataCollectionApiClient.addDataAssetToCollection(collectionId, dataAssetId);
        }
    }
}
