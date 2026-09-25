package com.witboost.plugin.informatica.marketplace.service;

import com.witboost.plugin.informatica.common.mapper.naming.NameBuilder;
import com.witboost.plugin.informatica.common.model.informatica.DataContract;
import com.witboost.plugin.informatica.common.model.informatica.DeliveryTarget;
import com.witboost.plugin.informatica.datacatalog.config.DataCatalogUiConfig;
import com.witboost.plugin.informatica.datacatalog.service.client.AssetApiUtil;
import com.witboost.plugin.informatica.marketplace.model.*;
import com.witboost.plugin.informatica.marketplace.service.client.DataAssetApiClient;
import com.witboost.plugin.informatica.marketplace.service.client.DataCollectionApiClient;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DataAssetService {

    private final DataAssetApiClient dataAssetApiClient;

    private final DataCollectionApiClient dataCollectionApiClient;

    private final AssetApiUtil assetApiUtil;

    private final DataCatalogUiConfig uiConfig;

    public DataAssetService(
            DataAssetApiClient dataAssetApiClient,
            DataCollectionApiClient dataCollectionApiClient,
            AssetApiUtil assetApiUtil,
            DataCatalogUiConfig uiConfig) {
        this.dataAssetApiClient = dataAssetApiClient;
        this.dataCollectionApiClient = dataCollectionApiClient;
        this.assetApiUtil = assetApiUtil;
        this.uiConfig = uiConfig;
    }

    /**
     * Creates Data Assets in Informatica Data Marketplace for each Delivery Target.
     *
     * @param dataContract The DataContract containing Delivery Targets
     * @return List of created Data Asset IDs
     */
    public List<String> createDataAssets(DataContract dataContract) {
        String productName = dataContract.getBaseCharacteristics().getName();
        log.info("Creating data assets for data contract: {}", productName);

        List<String> createdAssetIds = new ArrayList<>();

        dataContract
                .getDeliveryTargets()
                .forEach(
                        dt -> {
                            try {
                                var createdAssetId = createDataAsset(dataContract, dt);
                                createdAssetIds.add(createdAssetId);

                            } catch (Exception e) {
                                log.error(
                                        "Failed to create data asset for delivery target: {}. Error: {}",
                                        dt.getBaseCharacteristics().getPortName(),
                                        e.getMessage(),
                                        e);
                            }
                        });

        log.info(
                "Created {} data asset(s) for data contract: {}",
                createdAssetIds.size(),
                productName);
        return createdAssetIds;
    }

    private String createDataAsset(
            DataContract dataContract,
            com.witboost.plugin.informatica.common.model.informatica.DeliveryTarget dt) {
        String dataAssetName = NameBuilder.getDataMarketplaceDataAssetName(dataContract, dt);

        log.debug("Retrieving system from Data Catalog with name '{}'", dataAssetName);
        var foundSystem = assetApiUtil.getAssetByName("system", dataAssetName);
        if (!foundSystem.hasValidHits()) {
            throw new IllegalStateException(
                    String.format(
                            "Cannot find system with name '%s' in Data Catalog", dataAssetName));
        }
        String systemCoreIdentity = foundSystem.getHits().get(0).getCoreIdentity();
        log.debug("Retrieved system with core identity '{}'", systemCoreIdentity);

        log.debug("Creating data asset: {}", dataAssetName);

        var request = new CreateDataAssetRequest();
        request.setName(dataAssetName);
        request.setDescription(buildDataAssetDescriptionValue(dataContract));
        request.setSource(buildDataAssetSourceValue(dt));
        request.setType("Output Port");
        request.setRefLink(uiConfig.baseUrl() + "/asset/" + systemCoreIdentity);

        var response = dataAssetApiClient.createDataAsset(request);
        var createdAssetId = response.getObjects().get(0).getId();
        log.info("✓ Created data asset - ID: {}, Name: {}", createdAssetId, dataAssetName);
        return createdAssetId;
    }

    /**
     * Finds IDs of Data Asset objects for output ports of a data contract
     *
     * @param dataContract data contract object
     * @return List of IDs
     * @throws IllegalStateException if not exactly one data asset for a given name
     */
    public List<String> getDataAssetsIds(DataContract dataContract) {
        Set<String> dataAssetNames =
                dataContract.getDeliveryTargets().stream()
                        .map(dt -> NameBuilder.getDataMarketplaceDataAssetName(dataContract, dt))
                        .collect(Collectors.toSet());
        var response = dataAssetApiClient.getAllDataAssetsPaginated(new GetDataAssetsRequest());
        Map<String, List<GetDataAssetsResponse.DataAssetObject>> assetsByName =
                response.stream()
                        .collect(
                                Collectors.groupingBy(
                                        GetDataAssetsResponse.DataAssetObject::getName));
        return dataAssetNames.stream()
                .map(
                        name -> {
                            List<GetDataAssetsResponse.DataAssetObject> matching =
                                    assetsByName.getOrDefault(name, List.of());
                            if (matching.size() != 1) {
                                throw new IllegalStateException(
                                        "Expected exactly 1 data asset with name '"
                                                + name
                                                + "', but found "
                                                + matching.size());
                            }
                            return matching.get(0).getId();
                        })
                .toList();
    }

    /**
     * Deletes a data asset from Informatica Marketplace.
     *
     * @param dataAssetId The ID of the data asset to delete
     */
    public void deleteDataAsset(String dataAssetId) {
        dataAssetApiClient.deleteDataAsset(dataAssetId);
    }

    public void deleteDataProductAssets(DataContract dataContract) {
        var dataProductName = dataContract.getBaseCharacteristics().getName();
        var dataCollection = dataCollectionApiClient.getCollectionByName(dataProductName);
        if (dataCollection == null) {
            throw new IllegalStateException("Collection does not exist. Name: " + dataProductName);
        }
        var dataAssets =
                dataCollectionApiClient.retrieveDataAssetsForDataCollectionPaginated(
                        dataCollection.getId());
        for (var dataAsset : dataAssets) {
            String dataAssetId = dataAsset.getId();
            log.debug(
                    "Deleting data asset ID '{}' for data product '{}'",
                    dataAssetId,
                    dataProductName);
            deleteDataAsset(dataAssetId);
        }
    }

    public void updateDataProductAssets(String dataCollectionId, DataContract dataContract) {
        var foundDeliveryTargets =
                dataCollectionApiClient.retrieveDataAssetsForDataCollectionPaginated(
                        dataCollectionId);
        Map<String, GetDataAssetsDataCollectionResponse.DataAssetItem> currentDataAssetsByName =
                foundDeliveryTargets.stream()
                        .collect(
                                Collectors.toMap(
                                        GetDataAssetsDataCollectionResponse.DataAssetItem::getName,
                                        Function.identity()));
        // Delete obsolete delivery targets
        Set<String> toProvisionDataAssetNames =
                dataContract.getDeliveryTargets().stream()
                        .map(dt -> NameBuilder.getDataMarketplaceDataAssetName(dataContract, dt))
                        .collect(Collectors.toSet());
        List<String> toDeleteDataAssetNames =
                currentDataAssetsByName.keySet().stream()
                        .filter(name -> !toProvisionDataAssetNames.contains(name))
                        .toList();
        if (!toDeleteDataAssetNames.isEmpty()) {
            log.debug("The following data assets will be deleted: {}", toDeleteDataAssetNames);
            for (var toDeleteName : toDeleteDataAssetNames) {
                var assetId = currentDataAssetsByName.get(toDeleteName).getId();
                dataCollectionApiClient.removeDataAssetFromCollection(dataCollectionId, assetId);
                deleteDataAsset(assetId);
            }
        }
        // For each data asset to provision, update existing or create new
        List<String> newlyCreatedDataAssetIds = new ArrayList<>();
        for (var dt : dataContract.getDeliveryTargets()) {
            var dataAssetName = NameBuilder.getDataMarketplaceDataAssetName(dataContract, dt);
            if (currentDataAssetsByName.containsKey(dataAssetName)) {
                // UPDATE
                var apiObj = currentDataAssetsByName.get(dataAssetName);
                updateDataAsset(dataContract, dt, apiObj);
            } else {
                // CREATE
                newlyCreatedDataAssetIds.add(createDataAsset(dataContract, dt));
            }
        }

        // The create-data-asset API does not associate the asset with the collection;
        // it has to be linked via a separate PATCH /data-collections/{id}/data-assets.
        for (var dataAssetId : newlyCreatedDataAssetIds) {
            log.debug(
                    "Linking newly created data asset '{}' to data collection '{}'",
                    dataAssetId,
                    dataCollectionId);
            dataCollectionApiClient.addDataAssetToCollection(dataCollectionId, dataAssetId);
        }
    }

    private String updateDataAsset(
            DataContract dataContract,
            DeliveryTarget deliveryTarget,
            GetDataAssetsDataCollectionResponse.DataAssetItem apiDataAsset) {
        String toProvisionDataAssetName =
                NameBuilder.getDataMarketplaceDataAssetName(dataContract, deliveryTarget);

        // In case the name was changed, the existing system name in Data Catalog must match the
        // "new" port name
        log.debug("Retrieving system from Data Catalog with name '{}'", toProvisionDataAssetName);
        var foundSystem = assetApiUtil.getAssetByName("system", toProvisionDataAssetName);
        if (!foundSystem.hasValidHits()) {
            throw new IllegalStateException(
                    String.format(
                            "Cannot find system with name '%s' in Data Catalog",
                            toProvisionDataAssetName));
        }
        String systemCoreIdentity = foundSystem.getHits().get(0).getCoreIdentity();
        log.debug("Retrieved system with core identity '{}'", systemCoreIdentity);

        log.debug(
                "Updating data asset with name '{}' for ID '{}'",
                toProvisionDataAssetName,
                apiDataAsset.getId());

        var request = new UpdateDataAssetRequest();
        request.setId(apiDataAsset.getId());
        request.setName(toProvisionDataAssetName);
        request.setDescription(buildDataAssetDescriptionValue(dataContract));
        request.setType("Output Port");
        request.setSource(buildDataAssetSourceValue(deliveryTarget));
        request.setRefLink(uiConfig.baseUrl() + "/asset/" + systemCoreIdentity);

        var response = dataAssetApiClient.updateDataAsset(request);
        var updatedAssetId = response.getObjects().get(0).getId();
        log.info(
                "✓ Updated data asset - ID: {}, Name: {}",
                updatedAssetId,
                toProvisionDataAssetName);
        return updatedAssetId;
    }

    private static String buildDataAssetSourceValue(
            com.witboost.plugin.informatica.common.model.informatica.DeliveryTarget dt) {
        var dtTech = dt.getBaseCharacteristics().getPortTechnology();
        return dtTech.substring(0, 1).toUpperCase() + dtTech.substring(1);
    }

    private static String buildDataAssetDescriptionValue(DataContract dataContract) {
        String productName = dataContract.getBaseCharacteristics().getName();
        return String.format(
                "Output port del Data Product '%s' su tecnologia Snowflake", productName);
    }
}
