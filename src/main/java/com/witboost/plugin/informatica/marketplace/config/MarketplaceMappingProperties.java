package com.witboost.plugin.informatica.marketplace.config;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

/** Declarative Marketplace category and custom-attribute mapping. */
@ConfigurationProperties(prefix = "informatica.marketplace.mapping")
@Validated
public class MarketplaceMappingProperties {

    @Valid @NotEmpty private List<CategoryLevel> categories = new ArrayList<>();

    @Valid private Map<@NotBlank String, AttributeMapping> customAttributes = new LinkedHashMap<>();

    public List<CategoryLevel> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryLevel> categories) {
        this.categories = categories;
    }

    public Map<String, AttributeMapping> getCustomAttributes() {
        return customAttributes;
    }

    public void setCustomAttributes(Map<String, AttributeMapping> customAttributes) {
        this.customAttributes = customAttributes;
    }

    public static class CategoryLevel {
        @NotBlank private String descriptorPath;

        public String getDescriptorPath() {
            return descriptorPath;
        }

        public void setDescriptorPath(String descriptorPath) {
            this.descriptorPath = descriptorPath;
        }
    }

    public static class AttributeMapping {
        @NotBlank private String descriptorPath;
        private boolean required;
        private String defaultValue;
        private String transformer;

        public String getDescriptorPath() {
            return descriptorPath;
        }

        public void setDescriptorPath(String descriptorPath) {
            this.descriptorPath = descriptorPath;
        }

        public boolean isRequired() {
            return required;
        }

        public void setRequired(boolean required) {
            this.required = required;
        }

        public String getDefaultValue() {
            return defaultValue;
        }

        public void setDefaultValue(String defaultValue) {
            this.defaultValue = defaultValue;
        }

        public String getTransformer() {
            return transformer;
        }

        public void setTransformer(String transformer) {
            this.transformer = transformer;
        }
    }
}
