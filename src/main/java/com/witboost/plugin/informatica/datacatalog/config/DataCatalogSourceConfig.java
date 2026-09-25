package com.witboost.plugin.informatica.datacatalog.config;

import jakarta.validation.constraints.NotNull;
import java.util.Map;
import java.util.Optional;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@ConfigurationProperties(prefix = "informatica.data-catalog.catalog-source")
@Validated
public record DataCatalogSourceConfig(
        @NotNull(message = "Metadata sync configuration is required") Boolean enableMetadataSync,
        @NotNull(message = "Catalog sources configuration is required")
                Map<String, String> sources) {
    public String getCatalogSourceByTechnology(String technology) {
        return findCatalogSourceByTechnology(technology)
                .orElseThrow(
                        () ->
                                new IllegalArgumentException(
                                        "No catalog source configured for technology "
                                                + technology));
    }

    public Optional<String> findCatalogSourceByTechnology(String technology) {
        if (technology == null) return Optional.empty();
        return Optional.ofNullable(sources.get(technology.toLowerCase()))
                .filter(source -> !source.isBlank());
    }
}
