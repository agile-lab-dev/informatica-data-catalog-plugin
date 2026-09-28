package com.witboost.plugin.informatica.marketplace.mapper;

import com.witboost.plugin.informatica.common.model.informatica.DataContract;
import com.witboost.plugin.informatica.marketplace.config.MarketplaceMappingProperties;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/** Applies configured defaults and transformations to ID-keyed Marketplace attributes. */
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

    public Map<String, Object> map(DataContract dataContract) {
        Map<String, Object> result = new LinkedHashMap<>();
        properties
                .getCustomAttributes()
                .forEach(
                        (attributeId, mapping) -> {
                            Object value = dataContract.getCustomAttributes().get(attributeId);
                            if (value == null) value = mapping.getDefaultValue();
                            if (mapping.isRequired() && value == null) {
                                throw new IllegalArgumentException(
                                        "Required Marketplace attribute is missing: "
                                                + attributeId
                                                + " (descriptor path: "
                                                + mapping.getDescriptorPath()
                                                + ")");
                            }
                            if (value != null) {
                                result.put(attributeId, transform(attributeId, value));
                            }
                        });
        return result;
    }

    private Object transform(String name, Object value) {
        MarketplaceMappingProperties.AttributeMapping mapping =
                properties.getCustomAttributes().get(name);
        if (mapping == null || mapping.getTransformer() == null) return value;
        DescriptorValueTransformer transformer = transformers.get(mapping.getTransformer());
        if (transformer == null)
            return unknownTransformer(mapping.getTransformer()).transform(value);
        return transformer.transform(value);
    }

    private DescriptorValueTransformer unknownTransformer(String name) {
        throw new IllegalArgumentException("Unknown Marketplace value transformer: " + name);
    }
}
