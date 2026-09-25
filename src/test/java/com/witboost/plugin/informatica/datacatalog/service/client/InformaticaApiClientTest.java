package com.witboost.plugin.informatica.datacatalog.service.client;

import static org.junit.jupiter.api.Assertions.*;

import com.witboost.plugin.informatica.common.client.InformaticaApiClient;
import com.witboost.plugin.informatica.datacatalog.config.DataCatalogApiConfig;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

/**
 * Integration tests for InformaticaApiClient. Configuration is loaded from application-test.yml
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
class InformaticaApiClientTest {

    @Autowired private DataCatalogApiConfig config;

    @Autowired private InformaticaApiClient client;

    @Test
    @DisplayName("Should login and cache session and org ids")
    void shouldLoginAndCache() {
        // When
        String session = client.getSessionId();
        String org = client.getOrgId();

        // Then
        assertNotNull(session, "Session ID should not be null");
        assertNotNull(org, "Org ID should not be null");
        assertFalse(session.isEmpty(), "Session ID should not be empty");
        assertFalse(org.isEmpty(), "Org ID should not be empty");

        // Verify caching - calling again should return the same session without re-login
        String session2 = client.getSessionId();
        String org2 = client.getOrgId();
        assertEquals(session, session2, "Cached session ID should be returned");
        assertEquals(org, org2, "Cached org ID should be returned");
    }

    @Test
    @DisplayName("Should generate and cache JWT token")
    void shouldReturnCachedJwtToken() {
        // When
        String jwtToken = client.getJwtToken();

        // Then
        assertNotNull(jwtToken, "JWT token should not be null");
        assertFalse(jwtToken.isEmpty(), "JWT token should not be empty");

        // Verify caching - calling again should return the same token
        String jwtToken2 = client.getJwtToken();
        assertEquals(jwtToken, jwtToken2, "Cached JWT token should be returned");
    }

    @Test
    @DisplayName("Should initialize HTTP client successfully")
    void shouldInitializeHttpClientSuccessfully() {
        // Given - client is already initialized via @Autowired and constructor

        // Then
        assertNotNull(client, "Client should be autowired");
        assertNotNull(client.getHttpClient(), "HTTP client should be initialized");
    }
}
