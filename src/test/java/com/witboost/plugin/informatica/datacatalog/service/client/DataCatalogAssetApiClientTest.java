package com.witboost.plugin.informatica.datacatalog.service.client;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.witboost.plugin.informatica.datacatalog.common.Constants;
import com.witboost.plugin.informatica.datacatalog.model.AssetGetResponse;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

/**
 * Integration tests for DataCatalogAssetApiClient. Configuration is loaded from
 * application-test.yml via @ActiveProfiles("test").
 *
 * <p>These tests are disabled by default as they require real Informatica API credentials.
 *
 * <p>To run these tests: 1. Set up real credentials in application-test.yml or environment
 * variables 2. Remove the @Disabled annotation 3. Run the tests
 */
@SpringBootTest
@ActiveProfiles("test")
@Disabled("Integration tests - requires real Informatica API credentials and network access")
class DataCatalogAssetApiClientTest {

    @Autowired private DataCatalogAssetApiClient client;

    @Test
    void shouldQueryColumns() {
        String query =
                "columns related to technical dataset 'example_dataset'"
                        + " and in (source 'EXAMPLE_CATALOG_SOURCE')"
                        + " and in (database 'example_database')"
                        + " and in (schema 'example_schema')";
        String body = "{}";

        AssetGetResponse response = client.pollForResults(query);
        assertNotNull(response);
        assertNotNull(response.getHits());
        System.out.println(response);
    }

    @Test
    void shouldGetNeighbors() {
        var response = client.getAssetNeighbors("EXAMPLE_SYSTEM", "core.Resource");
        assertNotNull(response);
        assertNotNull(response.getCoreExternalId());
        assertNotNull(response.getCoreIdentity());
        System.out.println(response);
    }

    @Test
    void shouldHandleRelationship() {
        client.handleRelationship(
                "add",
                Constants.AssociationTypes.RESOURCE_TO_SYSTEM.getAssociationId(),
                "EXAMPLE_SYSTEM",
                "00000000-0000-0000-0000-000000000001://00000000-0000-0000-0000-000000000001~core.Resource");
    }

    @Test
    @DisplayName("Should handle empty query results")
    void shouldHandleEmptyQueryResults() {
        // Given - Query that should return no results
        String query = "asset.name:\"ThisAssetDefinitelyDoesNotExist123456789\"";
        String body = "{}";

        // When
        AssetGetResponse response = client.queryAssets(body, query);

        // Then
        assertNotNull(response, "Response should not be null");
        if (response.getHits() != null) {
            assertEquals(0, response.getHits().size(), "Should return empty results");
        }
    }

    @Test
    @DisplayName("Should poll for all results with custom page size")
    void shouldPollForAllResultsWithCustomPageSize() {
        // Given
        String query = "Business Terms";
        int customPageSize = 50;

        // When
        AssetGetResponse response = client.pollForResults(query, customPageSize);

        // Then
        assertNotNull(response, "Response should not be null");
        assertNotNull(response.getHits(), "Hits should not be null");

        System.out.println(
                "Poll with custom page size ("
                        + customPageSize
                        + ") returned "
                        + response.getHits().size()
                        + " hits");
    }

    @Test
    @DisplayName("Should cache session across multiple queries")
    void shouldCacheSessionAcrossMultipleQueries() {
        // Given
        String query = "Business Terms";
        String body = "{}";
        // When - Make first query
        String sessionId1 = client.getInformaticaApiClient().getSessionId();
        client.queryAssets(body, query);

        // Make second query
        String sessionId2 = client.getInformaticaApiClient().getSessionId();
        client.queryAssets(body, query);

        // Then - Session should be cached
        assertEquals(sessionId1, sessionId2, "Session ID should be cached and reused");
        System.out.println("Session caching verified - same session used for multiple queries");
    }
}
