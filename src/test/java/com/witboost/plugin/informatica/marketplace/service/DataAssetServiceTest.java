package com.witboost.plugin.informatica.marketplace.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.witboost.plugin.informatica.common.model.informatica.DataContract;
import com.witboost.plugin.informatica.common.model.informatica.DeliveryTarget;
import com.witboost.plugin.informatica.datacatalog.config.DataCatalogUiConfig;
import com.witboost.plugin.informatica.datacatalog.model.AssetGetResponse;
import com.witboost.plugin.informatica.datacatalog.service.client.AssetApiUtil;
import com.witboost.plugin.informatica.marketplace.model.CreateDataAssetResponse;
import com.witboost.plugin.informatica.marketplace.service.client.DataAssetApiClient;
import com.witboost.plugin.informatica.marketplace.service.client.DataCollectionApiClient;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class DataAssetServiceTest {

    @Mock private DataAssetApiClient dataAssetApiClient;

    @Mock private DataCollectionApiClient dataCollectionApiClient;

    @Mock private AssetApiUtil assetApiUtil;

    @Mock private DataCatalogUiConfig uiConfig;

    @InjectMocks private DataAssetService dataAssetService;

    /**
     * Regression: in the update path, newly-created data assets must be linked to the collection
     * via {@code addDataAssetToCollection}. The create-data-asset API alone does not associate the
     * asset with any collection, leaving it orphan in the UI.
     */
    @Test
    void updateDataProductAssets_newlyCreatedAssetsAreLinkedToCollection() {
        String collectionId = "16d57cdd-a4f2-4501-a11f-c18816cc88e8";
        String newAssetId = "044f74fe-4b0c-4529-aab4-e2eebdb42319";

        DataContract dataContract = new DataContract();
        dataContract.getBaseCharacteristics().setName("datapr-strutturaagenzie");
        dataContract.setDeliveryTargets(
                List.of(deliveryTarget("Snowflake Output Port", "Snowflake")));

        when(dataCollectionApiClient.retrieveDataAssetsForDataCollectionPaginated(collectionId))
                .thenReturn(Collections.emptyList());

        AssetGetResponse catalogLookup = new AssetGetResponse();
        AssetGetResponse.Hit hit = new AssetGetResponse.Hit();
        hit.setCoreIdentity("core-identity-1");
        catalogLookup.setHits(List.of(hit));
        when(assetApiUtil.getAssetByName(eq("system"), any())).thenReturn(catalogLookup);

        when(uiConfig.baseUrl()).thenReturn("https://catalog.example");

        CreateDataAssetResponse createResp = new CreateDataAssetResponse();
        CreateDataAssetResponse.DataAssetObject obj = new CreateDataAssetResponse.DataAssetObject();
        obj.setId(newAssetId);
        createResp.setObjects(List.of(obj));
        when(dataAssetApiClient.createDataAsset(any())).thenReturn(createResp);

        dataAssetService.updateDataProductAssets(collectionId, dataContract);

        verify(dataCollectionApiClient, times(1))
                .addDataAssetToCollection(collectionId, newAssetId);
    }

    /**
     * Negative case: if the data asset already exists for the collection (UPDATE path, not CREATE),
     * no extra link call is issued.
     */
    @Test
    void updateDataProductAssets_existingAssetsAreNotRelinked() {
        String collectionId = "coll-1";

        DataContract dataContract = new DataContract();
        dataContract.getBaseCharacteristics().setName("dp-test");
        dataContract.setDeliveryTargets(
                List.of(deliveryTarget("Snowflake Output Port", "snowflake")));

        // Existing asset matches the name the NameBuilder will compute → goes through UPDATE branch
        String expectedAssetName =
                com.witboost.plugin.informatica.common.mapper.naming.NameBuilder
                        .getDataMarketplaceDataAssetName(
                                dataContract, dataContract.getDeliveryTargets().get(0));
        var existing =
                new com.witboost.plugin.informatica.marketplace.model
                        .GetDataAssetsDataCollectionResponse.DataAssetItem();
        existing.setId("existing-id");
        existing.setName(expectedAssetName);
        when(dataCollectionApiClient.retrieveDataAssetsForDataCollectionPaginated(collectionId))
                .thenReturn(List.of(existing));

        AssetGetResponse catalogLookup = new AssetGetResponse();
        AssetGetResponse.Hit hit = new AssetGetResponse.Hit();
        hit.setCoreIdentity("core-1");
        catalogLookup.setHits(List.of(hit));
        when(assetApiUtil.getAssetByName(eq("system"), any())).thenReturn(catalogLookup);
        when(uiConfig.baseUrl()).thenReturn("https://catalog.example");

        var updateResp =
                new com.witboost.plugin.informatica.marketplace.model.UpdateDataAssetResponse();
        var updatedObj =
                new com.witboost.plugin.informatica.marketplace.model.UpdateDataAssetResponse
                        .DataAssetObject();
        updatedObj.setId("existing-id");
        updateResp.setObjects(List.of(updatedObj));
        when(dataAssetApiClient.updateDataAsset(any())).thenReturn(updateResp);

        dataAssetService.updateDataProductAssets(collectionId, dataContract);

        verify(dataCollectionApiClient, never()).addDataAssetToCollection(any(), any());
    }

    /**
     * An output port opting out via {@code specific.publishToInformatica: false} produces no
     * delivery target, hence no data asset, in the DataContract. The data asset already linked to
     * the collection must then be unlinked and deleted as obsolete, rather than left orphan on the
     * Marketplace.
     */
    @Test
    void updateDataProductAssets_assetsMissingFromTheContractAreDeleted() {
        String collectionId = "coll-1";

        DataContract dataContract = new DataContract();
        dataContract.getBaseCharacteristics().setName("dp-test");
        // Only the Snowflake port survives the opt-out filter; the Databricks one is excluded
        dataContract.setDeliveryTargets(
                List.of(deliveryTarget("Snowflake Output Port", "snowflake")));

        String survivingAssetName =
                com.witboost.plugin.informatica.common.mapper.naming.NameBuilder
                        .getDataMarketplaceDataAssetName(
                                dataContract, dataContract.getDeliveryTargets().get(0));
        var surviving =
                new com.witboost.plugin.informatica.marketplace.model
                        .GetDataAssetsDataCollectionResponse.DataAssetItem();
        surviving.setId("surviving-id");
        surviving.setName(survivingAssetName);

        var obsolete =
                new com.witboost.plugin.informatica.marketplace.model
                        .GetDataAssetsDataCollectionResponse.DataAssetItem();
        obsolete.setId("obsolete-id");
        obsolete.setName("DP_TEST_DATABRICKS_READ");

        when(dataCollectionApiClient.retrieveDataAssetsForDataCollectionPaginated(collectionId))
                .thenReturn(List.of(surviving, obsolete));

        AssetGetResponse catalogLookup = new AssetGetResponse();
        AssetGetResponse.Hit hit = new AssetGetResponse.Hit();
        hit.setCoreIdentity("core-1");
        catalogLookup.setHits(List.of(hit));
        when(assetApiUtil.getAssetByName(eq("system"), any())).thenReturn(catalogLookup);
        when(uiConfig.baseUrl()).thenReturn("https://catalog.example");

        var updateResp =
                new com.witboost.plugin.informatica.marketplace.model.UpdateDataAssetResponse();
        var updatedObj =
                new com.witboost.plugin.informatica.marketplace.model.UpdateDataAssetResponse
                        .DataAssetObject();
        updatedObj.setId("surviving-id");
        updateResp.setObjects(List.of(updatedObj));
        when(dataAssetApiClient.updateDataAsset(any())).thenReturn(updateResp);

        dataAssetService.updateDataProductAssets(collectionId, dataContract);

        verify(dataCollectionApiClient).removeDataAssetFromCollection(collectionId, "obsolete-id");
        verify(dataAssetApiClient).deleteDataAsset("obsolete-id");
        verify(dataAssetApiClient, never()).deleteDataAsset("surviving-id");
    }

    private static DeliveryTarget deliveryTarget(String portName, String technology) {
        DeliveryTarget dt = new DeliveryTarget();
        dt.getBaseCharacteristics().setPortName(portName);
        dt.getBaseCharacteristics().setPortTechnology(technology);
        dt.getBaseCharacteristics().setDescription("desc");
        return dt;
    }

    private static <T> T eq(T value) {
        return org.mockito.ArgumentMatchers.eq(value);
    }
}
