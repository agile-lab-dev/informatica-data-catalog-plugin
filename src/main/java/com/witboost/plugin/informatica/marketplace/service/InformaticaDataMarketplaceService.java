package com.witboost.plugin.informatica.marketplace.service;

import com.witboost.plugin.informatica.common.mapper.naming.NameBuilder;
import com.witboost.plugin.informatica.common.model.informatica.DataContract;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class InformaticaDataMarketplaceService {

    private final DataCollectionService dataCollectionService;
    private final DeliveryTargetService deliveryTargetService;
    private final DataAssetService dataAssetService;

    public InformaticaDataMarketplaceService(
            DataCollectionService dataCollectionService,
            DeliveryTargetService deliveryTargetService,
            DataAssetService dataAssetService) {
        this.dataCollectionService = dataCollectionService;
        this.deliveryTargetService = deliveryTargetService;
        this.dataAssetService = dataAssetService;
    }

    public void upsertProductToDataMarketplace(DataContract dataContract) {
        String productName = dataContract.getBaseCharacteristics().getName();

        log.debug("Checking existence of Data Product '{}' in Data Catalog", productName);
        boolean productExists = isProductInDataMarketplace(productName);

        if (productExists) {
            log.info("Data Product '{}' already exists in Data Catalog. Updating...", productName);
            updateProductToDataMarketplace(dataContract);
        } else {
            log.info("Data Product '{}' does not exist in Data Catalog. Creating...", productName);
            insertProductToDataMarketplace(dataContract);
        }
    }

    public boolean isProductInDataMarketplace(String name) {
        log.debug("Checking if product '{}' exists", name);

        if (name == null || name.isBlank()) {
            log.warn("Product name is null or blank");
            return false;
        }

        boolean exists = this.dataCollectionService.collectionExists(name);

        if (exists) {
            log.info("Product '{}' exists in Informatica Marketplace", name);
        } else {
            log.debug("Product '{}' does not exist in Informatica Marketplace", name);
        }

        return exists;
    }

    public String updateProductToDataMarketplace(DataContract dataContract) {
        return dataCollectionService.updateCollection(dataContract);
    }

    /**
     * Inserts a new Data Product into Informatica Data Marketplace.
     *
     * <p>This method performs the following steps:
     *
     * <ol>
     *   <li>Creates a Data Collection
     *   <li>Creates Delivery Targets (Output Ports) for the collection
     *   <li>Creates Data Assets and links them to the collection
     * </ol>
     *
     * @param dataContract The DataContract to insert
     * @return The ID of the created Data Collection
     * @throws RuntimeException if any step fails
     */
    public String insertProductToDataMarketplace(DataContract dataContract) {
        String productName = dataContract.getBaseCharacteristics().getName();
        log.info("Inserting Data Product '{}' into Data Marketplace", productName);

        try {
            // Step 1: Create Data Collection with summary and custom attributes
            log.debug("Step 1/3: Creating data collection...");
            String collectionId = dataCollectionService.createCollection(dataContract);
            log.info("✓ Data Collection created with ID: {}", collectionId);

            // Step 2: Create Delivery Targets for each Output Port
            log.debug(
                    "Step 2/3: Creating {} delivery target(s)...",
                    dataContract.getDeliveryTargets().size());

            dataContract
                    .getDeliveryTargets()
                    .forEach(
                            dt -> {
                                try {
                                    deliveryTargetService.createDeliveryTarget(
                                            collectionId, dataContract, dt);
                                    log.debug(
                                            "✓ Delivery Target created: {}",
                                            dt.getBaseCharacteristics().getPortName());
                                } catch (Exception e) {
                                    log.error(
                                            "Failed to create Delivery Target for port: {}. Error: {}",
                                            dt.getBaseCharacteristics().getPortName(),
                                            e.getMessage(),
                                            e);
                                    throw new RuntimeException(
                                            "Failed to create Delivery Target", e);
                                }
                            });

            log.info("✓ All Delivery Targets created successfully");

            // Step 3: Create Data Assets and link them to the collection
            log.debug("Step 3/3: Creating data assets...");
            var createdDataAssetIds = dataAssetService.createDataAssets(dataContract);
            log.info("✓ Created {} data asset(s)", createdDataAssetIds.size());

            // Step 4: Link Data Assets to the Data Collection
            if (!createdDataAssetIds.isEmpty()) {
                log.debug(
                        "Linking {} data asset(s) to collection '{}'...",
                        createdDataAssetIds.size(),
                        collectionId);
                dataCollectionService.addDataAssetsToCollection(collectionId, createdDataAssetIds);
                log.warn("Data Assets linking not yet implemented - skipping for now");
            } else {
                log.warn("No data assets were created to link to collection");
            }

            log.info(
                    "✓ Data Product '{}' inserted successfully into Data Marketplace", productName);
            return collectionId;

        } catch (Exception e) {
            log.error(
                    "Failed to insert Data Product '{}' into Data Marketplace. Error: {}",
                    productName,
                    e.getMessage(),
                    e);
            throw new RuntimeException(
                    String.format("Failed to insert Data Product '%s'", productName), e);
        }
    }

    public String deleteProduct(DataContract dataContract) {
        String dataProductName = dataContract.getBaseCharacteristics().getName();
        log.info("Deleting product: {}", dataProductName);

        // Step 1: Delete data assets associated with the collection
        // Note: Data assets should be deleted first to avoid orphaned assets
        // Currently dataAssetService may need to be extended to support bulk deletion by collection
        // ID
        log.debug("Deleting data assets for data product '{}'...", dataProductName);
        dataAssetService.deleteDataProductAssets(dataContract);

        // Step 2: Delete delivery targets associated with the collection
        // Note: Delivery targets depend on the collection, so they must be deleted before the
        // collection
        // Currently deliveryTargetService may need to be extended to support bulk deletion by
        // collection ID
        log.debug("Deleting delivery targets for collection '{}'...", dataProductName);
        var deliveryTargetNames =
                dataContract.getDeliveryTargets().stream()
                        .map(dt -> NameBuilder.getDataMarketplaceOutputPortName(dataContract, dt))
                        .toList();

        deliveryTargetService.deleteDeliveryTargetsByName(deliveryTargetNames);

        // Step 3: Delete the data collection itself
        log.debug("Deleting data collection '{}'...", dataProductName);
        return dataCollectionService.deleteCollection(dataContract);
    }
}
