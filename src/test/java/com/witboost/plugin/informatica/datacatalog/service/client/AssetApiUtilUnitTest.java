package com.witboost.plugin.informatica.datacatalog.service.client;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import com.witboost.plugin.informatica.datacatalog.model.AssetGetResponse;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AssetApiUtilUnitTest {

    @Mock private DataCatalogAssetApiClient assetApiClient;

    @InjectMocks private AssetApiUtil assetApiUtil;

    private static final String CATALOG_SOURCE_NAME = "SNOWFLAKE_CATALOG_SRC";
    private static final String CATALOG_SOURCE_UUID = "TEST-CATALOG-SRC-UUID";
    private static final String EXTERNAL_ID =
            CATALOG_SOURCE_UUID + "://" + CATALOG_SOURCE_UUID + "~core.Resource";

    private AssetGetResponse responseWithHit(String externalId) {
        AssetGetResponse response = new AssetGetResponse();
        AssetGetResponse.Hit hit = new AssetGetResponse.Hit();
        hit.setExternalIdentity(externalId);
        response.setHits(List.of(hit));
        return response;
    }

    private AssetGetResponse responseWithoutHits() {
        AssetGetResponse response = new AssetGetResponse();
        response.setHits(new ArrayList<>());
        return response;
    }

    @Test
    void getCatalogSourceExternalId_whenNotFound_throwsWithCatalogSourceName() {
        when(assetApiClient.queryAssets(anyString(), anyString()))
                .thenReturn(responseWithoutHits());

        IllegalStateException ex =
                assertThrows(
                        IllegalStateException.class,
                        () -> assetApiUtil.getCatalogSourceExternalId(CATALOG_SOURCE_NAME));

        assertTrue(
                ex.getMessage().contains(CATALOG_SOURCE_NAME),
                "Exception message should name the missing catalog source, but was: "
                        + ex.getMessage());
    }

    @Test
    void getCatalogSourceExternalId_whenFound_returnsExternalIdentity() {
        when(assetApiClient.queryAssets(anyString(), anyString()))
                .thenReturn(responseWithHit(EXTERNAL_ID));

        assertEquals(EXTERNAL_ID, assetApiUtil.getCatalogSourceExternalId(CATALOG_SOURCE_NAME));
    }

    @Test
    void getCatalogSourceUUID_whenFound_returnsUuidPrefix() {
        when(assetApiClient.queryAssets(anyString(), anyString()))
                .thenReturn(responseWithHit(EXTERNAL_ID));

        assertEquals(CATALOG_SOURCE_UUID, assetApiUtil.getCatalogSourceUUID(CATALOG_SOURCE_NAME));
    }

    @Test
    void getCatalogSourceUUID_whenNotFound_throwsWithCatalogSourceName() {
        when(assetApiClient.queryAssets(anyString(), anyString()))
                .thenReturn(responseWithoutHits());

        IllegalStateException ex =
                assertThrows(
                        IllegalStateException.class,
                        () -> assetApiUtil.getCatalogSourceUUID(CATALOG_SOURCE_NAME));

        assertTrue(ex.getMessage().contains(CATALOG_SOURCE_NAME));
    }
}
