package com.witboost.plugin.informatica.marketplace.mapper;

import com.witboost.plugin.informatica.common.model.informatica.DataContract;
import com.witboost.plugin.informatica.marketplace.config.MarketplaceMappingProperties;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/** Maps descriptor-provided custom values to runtime-resolved Informatica attribute IDs. */
@Component
public class MarketplaceAttributeMapper {

    private final MarketplaceMappingProperties properties;
    private final Map<String, DescriptorValueTransformer> transformers;

    @Autowired
    public MarketplaceAttributeMapper(
            MarketplaceMappingProperties properties,
            List<DescriptorValueTransformer> transformers) {
        this.properties = properties;
        this.transformers =
                transformers.stream()
                        .collect(
                                java.util.stream.Collectors.toMap(
                                        DescriptorValueTransformer::name, t -> t));
    }

    public Map<String, Object> map(
            DataContract dataContract, Map<String, String> attributeNamesToIds) {
        Map<String, Object> values = new LinkedHashMap<>(dataContract.getCustomAttributes());
        properties
                .getCustomAttributes()
                .forEach(
                        (name, mapping) -> {
                            Object value = values.get(mapping.getDescriptorPath());
                            if (value == null) value = mapping.getDefaultValue();
                            if (mapping.isRequired() && value == null) {
                                throw new IllegalArgumentException(
                                        "Required Marketplace attribute is missing: " + name);
                            }
                            if (value != null) values.put(name, value);
                        });
        Map<String, Object> result = new LinkedHashMap<>();
        values.forEach(
                (name, value) -> {
                    MarketplaceMappingProperties.AttributeMapping mapping =
                            properties.getCustomAttributes().get(name);
                    String id =
                            mapping != null && mapping.getInformaticaId() != null
                                    ? mapping.getInformaticaId()
                                    : attributeNamesToIds.get(name);
                    if (id != null) result.put(id, transform(name, value));
                });
        return result;
    }

    private Object transform(String name, Object value) {
        MarketplaceMappingProperties.AttributeMapping mapping =
                properties.getCustomAttributes().get(name);
        return mapping == null || mapping.getTransformer() == null
                ? value
                : transformers
                        .getOrDefault(
                                mapping.getTransformer(),
                                unknownTransformer(mapping.getTransformer()))
                        .transform(value);
    }

    private DescriptorValueTransformer unknownTransformer(String name) {
        throw new IllegalArgumentException("Unknown Marketplace value transformer: " + name);
    }
}
