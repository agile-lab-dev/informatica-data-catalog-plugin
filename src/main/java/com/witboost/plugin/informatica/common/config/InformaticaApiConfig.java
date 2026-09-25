package com.witboost.plugin.informatica.common.config;

import jakarta.validation.constraints.NotBlank;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

/**
 * Configuration properties for Informatica API authentication.
 *
 * <p>This configuration class provides common authentication details for interacting with
 * Informatica APIs (login and token endpoints).
 *
 * <p>Configuration example in application.yml:
 *
 * <pre>{@code
 * informatica:
 *   api:
 *     base-url: https://dm-em.informaticacloud.com
 *     login: /ma/api/v2/user/login
 *     token: /identity-service/api/v1/jwt/Token?client_id=idmc_api&nonce=1234
 *     username: your-username
 *     password: your-password
 * }</pre>
 */
@ConfigurationProperties(prefix = "informatica.api")
@Validated
public record InformaticaApiConfig(
        @NotBlank(message = "informatica.api.base-url is required") String baseUrl,
        @NotBlank(message = "informatica.api.login is required") String login,
        @NotBlank(message = "informatica.api.token is required") String token,
        @NotBlank(message = "informatica.api.username is required") String username,
        @NotBlank(message = "informatica.api.password is required") String password) {
    /**
     * Gets the complete URL for the login endpoint.
     *
     * @return The full URL including base URL and login endpoint
     */
    public String getLoginUrl() {
        return baseUrl() + login();
    }

    /**
     * Gets the complete URL for the token endpoint.
     *
     * @return The full URL including base URL and token endpoint
     */
    public String getTokenUrl() {
        return baseUrl() + token();
    }
}
