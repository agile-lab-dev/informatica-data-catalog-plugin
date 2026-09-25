package com.witboost.plugin.informatica.common.client;

import jakarta.annotation.PostConstruct;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.time.Duration;
import lombok.extern.slf4j.Slf4j;

/**
 * Base client for HTTP operations. Provides a configured HttpClient with appropriate timeout and
 * connection settings.
 *
 * <p>Subclasses should extend this class to inherit HTTP client functionality. Note: Do not
 * add @Service to this base class - add it to concrete implementations.
 */
@Slf4j
public abstract class BaseClient {

    protected static final Duration DEFAULT_TIMEOUT = Duration.ofSeconds(30);
    protected static final Duration DEFAULT_CONNECT_TIMEOUT = Duration.ofSeconds(10);
    private static final String AUTH_TUNNELING_PROPERTY = "jdk.http.auth.tunneling.disabledSchemes";

    // HTTP Constants
    protected static final String CONTENT_TYPE_JSON = "application/json";
    protected static final int HTTP_OK = 200;
    protected static final int HTTP_CREATED = 201;

    protected HttpClient httpClient;

    /**
     * Gets the configured HTTP client instance.
     *
     * @return HttpClient instance, or null if not yet initialized
     */
    public HttpClient getHttpClient() {
        return this.httpClient;
    }

    /**
     * Initializes the HTTP client with appropriate timeout and connection settings. This method is
     * automatically called after bean construction via @PostConstruct.
     *
     * <p>Configuration includes: - Connection timeout: 10 seconds - Request timeout: 30 seconds -
     * Automatic redirect following - HTTP/2 support
     *
     * @throws RuntimeException if HTTP client initialization fails
     */
    @PostConstruct
    public void initHttpClient() {
        log.info("Initializing HTTP client for {}", this.getClass().getSimpleName());

        try {
            // Enable HTTP tunneling through proxy if needed
            String originalValue = System.getProperty(AUTH_TUNNELING_PROPERTY);
            System.setProperty(AUTH_TUNNELING_PROPERTY, "");
            log.debug(
                    "Set system property {}='' (original value: {})",
                    AUTH_TUNNELING_PROPERTY,
                    originalValue);

            // Build HTTP client with proper configuration
            this.httpClient =
                    HttpClient.newBuilder()
                            .version(HttpClient.Version.HTTP_2)
                            .followRedirects(HttpClient.Redirect.NORMAL)
                            .connectTimeout(DEFAULT_CONNECT_TIMEOUT)
                            .build();

            log.info(
                    "HTTP client initialized successfully for {}", this.getClass().getSimpleName());

        } catch (Exception e) {
            String errorMessage =
                    String.format(
                            "Failed to initialize HTTP client for %s: %s",
                            this.getClass().getSimpleName(), e.getMessage());
            log.error(errorMessage, e);
            throw new RuntimeException(errorMessage, e);
        }
    }

    /**
     * Creates a new HTTP request builder with default timeout settings.
     *
     * @return HttpRequest.Builder configured with default timeout
     */
    public HttpRequest.Builder getHttpRequestBuilder() {
        return HttpRequest.newBuilder().timeout(DEFAULT_TIMEOUT);
    }

    /**
     * Creates a new HTTP request builder with custom timeout.
     *
     * @param timeout Custom timeout duration
     * @return HttpRequest.Builder configured with custom timeout
     */
    public HttpRequest.Builder getHttpRequestBuilder(Duration timeout) {
        if (timeout == null) {
            log.warn("Null timeout provided, using default: {}", DEFAULT_TIMEOUT);
            return getHttpRequestBuilder();
        }
        return HttpRequest.newBuilder().timeout(timeout);
    }

    /**
     * Checks if the HTTP client is initialized and ready to use.
     *
     * @return true if HTTP client is initialized, false otherwise
     */
    public boolean isInitialized() {
        return this.httpClient != null;
    }
}
