package com.witboost.plugin.informatica.datacatalog.config;

import jakarta.validation.constraints.NotBlank;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@ConfigurationProperties(prefix = "informatica.data-catalog.ui")
@Validated
public record DataCatalogUiConfig(
        @NotBlank(message = "informatica.data-catalog.ui.base-url is required") String baseUrl) {}
