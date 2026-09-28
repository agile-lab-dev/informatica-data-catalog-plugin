package com.witboost.plugin.informatica.marketplace.config;

import jakarta.validation.constraints.NotBlank;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

/**
 * Configuration properties for Informatica Marketplace API.
 *
 * <p>This configuration class provides connection details and endpoint definitions for interacting
 * with the Informatica Marketplace API.
 *
 * <p>Configuration example in application.yml:
 *
 * <pre>{@code
 * informatica:
 *   marketplace:
 *     api:
 *       base-url: https://marketplace.informaticacloud.com
 *       endpoints:
 *         data-collections: /api/v2/data-collections
 * }</pre>
 */
@ConfigurationProperties(prefix = "informatica.marketplace.api")
@Validated
public record MarketplaceApiConfig(
        @NotBlank(message = "informatica.marketplace.api.base-url is required") String baseUrl,
        @NotBlank(message = "informatica.marketplace.api.wrapper-base-url is required")
                String wrapperBaseUrl,
        @jakarta.validation.Valid Endpoints endpoints) {
    /** Configuration for Marketplace API endpoints. */
    public record Endpoints(
            @NotBlank(
                            message =
                                    "informatica.marketplace.api.endpoints.data-collections is required")
                    String dataCollections,
            @NotBlank(message = "informatica.marketplace.api.endpoints.categories is required")
                    String categories,
            @NotBlank(
                            message =
                                    "informatica.marketplace.api.endpoints.delivery-targets is required")
                    String deliveryTargets,
            @NotBlank(
                            message =
                                    "informatica.marketplace.api.endpoints.delivery-templates is required")
                    String deliveryTemplates,
            @NotBlank(message = "informatica.marketplace.api.endpoints.data-assets is required")
                    String dataAssets,
            @NotBlank(
                            message =
                                    "informatica.marketplace.api.endpoints.create-data-assets is required")
                    String createDataAssets,
            @NotBlank(
                            message =
                                    "informatica.marketplace.api.endpoints.update-data-assets is required")
                    String updateDataAssets,
            @NotBlank(
                            message =
                                    "informatica.marketplace.api.endpoints.delete-data-assets is required")
                    String deleteDataAssets) {}

    /**
     * Gets the complete URL for the data collections endpoint.
     *
     * @return The full URL including base URL and data collections endpoint
     */
    public String getDataCollectionsUrl() {
        return baseUrl() + endpoints().dataCollections();
    }

    /**
     * Gets the complete URL for the categories endpoint.
     *
     * @return The full URL including base URL and categories endpoint
     */
    public String getCategoriesUrl() {
        return baseUrl() + endpoints().categories();
    }

    /**
     * Gets the complete URL for the delivery targets endpoint.
     *
     * @return The full URL including base URL and delivery targets endpoint
     */
    public String getDeliveryTargetsUrl() {
        return baseUrl() + endpoints().deliveryTargets();
    }

    /**
     * Gets the complete URL for the delivery templates endpoint.
     *
     * @return The full URL including base URL and delivery templates endpoint
     */
    public String getDeliveryTemplatesUrl() {
        return baseUrl() + endpoints().deliveryTemplates();
    }

    /**
     * Gets the complete URL for the data assets endpoint.
     *
     * @return The full URL including base URL and data assets endpoint
     */
    public String getDataAssetsUrl() {
        return baseUrl() + endpoints().dataAssets();
    }

    /**
     * Gets the complete URL for the data assets create endpoint.
     *
     * @return The full URL including base URL and create data assets endpoint
     */
    public String getCreateDataAssetsUrl() {
        return wrapperBaseUrl() + endpoints().createDataAssets();
    }

    /**
     * Gets the complete URL for the data assets update endpoint.
     *
     * @return The full URL including base URL and update data assets endpoint
     */
    public String getUpdateDataAssetsUrl() {
        return wrapperBaseUrl() + endpoints().updateDataAssets();
    }

    /**
     * Gets the complete URL for the data assets delete endpoint.
     *
     * @return The full URL including base URL and delete data assets endpoint
     */
    public String getDeleteDataAssetsUrl() {
        return wrapperBaseUrl() + endpoints().deleteDataAssets();
    }
}
