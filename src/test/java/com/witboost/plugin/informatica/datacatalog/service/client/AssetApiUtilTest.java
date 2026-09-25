package com.witboost.plugin.informatica.datacatalog.service.client;

import static org.junit.jupiter.api.Assertions.*;

import com.witboost.plugin.informatica.datacatalog.config.DataCatalogApiConfig;
import com.witboost.plugin.informatica.datacatalog.model.AssetGetResponse;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
@Disabled("Integration tests - requires real Informatica API credentials and network access")
class AssetApiUtilTest {

    @Autowired private DataCatalogApiConfig config;

    @Autowired private AssetApiUtil client;

    @Test
    void getSystemByName() {
        AssetGetResponse system =
                client.getAssetByName(
                        "system", "DP_DATA_PRODUCT_TEST_METADATA_3_SWF_STANDARD_MODE");
        System.out.println(system);
    }

    @Test
    void isSystemExisting() {
        assertTrue(client.isAssetExisting("system", "System-Test"));
        assertFalse(client.isAssetExisting("system", "NonExistingSystem-XYZ"));
    }
}
