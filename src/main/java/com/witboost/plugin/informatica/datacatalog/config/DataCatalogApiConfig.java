package com.witboost.plugin.informatica.datacatalog.config;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@ConfigurationProperties(prefix = "informatica.data-catalog.api")
@Validated
public record DataCatalogApiConfig(
        @NotBlank(message = "informatica.data-catalog.api.base-url is required") String baseUrl,
        @jakarta.validation.Valid Endpoints endpoints,
        @jakarta.validation.Valid Settings settings) {
    public record Endpoints(
            @NotBlank(message = "informatica.data-catalog.api.manage is required")
                    String assetManage,
            @NotBlank(message = "informatica.data-catalog.api.list is required") String assetList,
            @NotBlank(message = "informatica.data-catalog.api.import is required")
                    String assetImport,
            @NotBlank(message = "informatica.data-catalog.api.jobs is required") String jobs,
            @NotBlank(message = "informatica.data-catalog.api.exec-catalog-source-job is required")
                    String execCatalogSourceJob) {}

    /** Configuration for API behavior settings. */
    public record Settings(
            @NotNull(
                            message =
                                    "informatica.data-catalog.api.settings.polling-interval-millis is required")
                    @Min(
                            value = 1000,
                            message =
                                    "informatica.data-catalog.api.settings.polling-interval-millis must be at least 1000ms (1 second)")
                    Long pollingIntervalMillis,
            @NotNull(
                            message =
                                    "informatica.data-catalog.api.settings.polling-timeout-millis is required")
                    @Min(
                            value = 1000,
                            message =
                                    "informatica.data-catalog.api.settings.polling-timeout-millis must be at least 1000ms (1 second)")
                    Long pollingTimeoutMillis,
            @NotNull(
                            message =
                                    "informatica.data-catalog.api.settings.access-token-expire-seconds is required")
                    @Min(
                            value = 60,
                            message =
                                    "informatica.data-catalog.api.settings.access-token-expire-seconds must be at least 60 seconds")
                    Long accessTokenExpireSeconds) {}

    public String getJobsUrl() {
        return baseUrl() + endpoints().jobs();
    }

    public String getAssetManageUrl() {
        return baseUrl() + endpoints().assetManage();
    }

    public String getAssetListUrl() {
        return baseUrl() + endpoints().assetList();
    }

    public String getAssetImportUrl() {
        return baseUrl() + endpoints().assetImport();
    }

    public String getExecCatalogSourceJobUrl() {
        return baseUrl() + endpoints().execCatalogSourceJob();
    }
}
