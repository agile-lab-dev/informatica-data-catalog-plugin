package com.witboost.plugin.informatica.datacatalog.service.client;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

/**
 * Integration tests for CatalogSourceApiClient. Configuration is loaded from application-test.yml
 * via @ActiveProfiles("test").
 *
 * <p>These tests are disabled by default as they require real Informatica API credentials.
 *
 * <p>To run these tests: 1. Set up real credentials in application-test.yml or environment
 * variables 2. Remove the @Disabled annotation 3. Run the tests
 */
@SpringBootTest
@ActiveProfiles("test")
@Disabled("Integration tests - requires real Informatica API credentials and network access")
public class CatalogSourceApiClientTest {

    @Autowired private CatalogSourceApiClient client;

    @Test
    public void shouldSyncCatalogSource() {
        var res = client.syncCatalogSource("00000000-0000-0000-0000-000000000001");
        System.out.println(res);
    }
}
