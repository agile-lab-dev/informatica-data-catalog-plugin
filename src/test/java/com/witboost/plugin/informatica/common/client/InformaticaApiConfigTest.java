package com.witboost.plugin.informatica.common.client;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.witboost.plugin.informatica.common.config.InformaticaApiConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class InformaticaApiConfigTest {

    private static final String INFORMATICA_USERNAME = "sample-username";
    private static final String INFORMATICA_PASSWORD = "sample-password";
    private static final String BASE_URL = "https://test.informaticacloud.com";
    private static final String LOGIN_ENDPOINT = "/ma/api/v2/user/login";
    private static final String TOKEN_ENDPOINT =
            "/identity-service/api/v1/jwt/Token?client_id=idmc_api&nonce=1234";

    @Configuration
    @EnableConfigurationProperties(InformaticaApiConfig.class)
    static class TestConfig {}

    @DynamicPropertySource
    static void registerProps(DynamicPropertyRegistry registry) {
        registry.add("informatica.api.username", () -> INFORMATICA_USERNAME);
        registry.add("informatica.api.password", () -> INFORMATICA_PASSWORD);
        registry.add("informatica.api.base-url", () -> BASE_URL);
        registry.add("informatica.api.login", () -> LOGIN_ENDPOINT);
        registry.add("informatica.api.token", () -> TOKEN_ENDPOINT);
    }

    @Autowired private InformaticaApiConfig config;

    @Test
    void testInformaticaApiConfigProperties() {
        assertNotNull(config, "InformaticaApiConfig bean must not be null");

        // Test basic properties
        assertEquals(INFORMATICA_USERNAME, config.username(), "Username mismatch");
        assertEquals(INFORMATICA_PASSWORD, config.password(), "Password mismatch");
        assertEquals(BASE_URL, config.baseUrl(), "Base URL mismatch");
        assertEquals(LOGIN_ENDPOINT, config.login(), "Login endpoint mismatch");
        assertEquals(TOKEN_ENDPOINT, config.token(), "Token endpoint mismatch");

        // Test helper methods that compose full URLs
        assertEquals(BASE_URL + LOGIN_ENDPOINT, config.getLoginUrl(), "Login URL mismatch");
        assertEquals(BASE_URL + TOKEN_ENDPOINT, config.getTokenUrl(), "Token URL mismatch");
    }
}
