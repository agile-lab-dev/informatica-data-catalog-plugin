package com.witboost.plugin.informatica.marketplace.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.witboost.plugin.informatica.common.model.informatica.DataContract;
import com.witboost.plugin.informatica.marketplace.config.MarketplaceMappingProperties;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;

class MarketplaceAttributeMapperTest {

    @Test
    void usesConfiguredMapKeyAsTechnicalId() {
        MarketplaceMappingProperties properties = new MarketplaceMappingProperties();
        MarketplaceMappingProperties.AttributeMapping mapping =
                new MarketplaceMappingProperties.AttributeMapping();
        mapping.setDescriptorPath("specific.owner");
        properties.setCustomAttributes(Map.of("com.infa.odin.models.custom.ca_owner", mapping));

        MarketplaceAttributeMapper mapper = new MarketplaceAttributeMapper(properties, List.of());
        DataContract contract = new DataContract();
        contract.setCustomAttributes(
                Map.of("com.infa.odin.models.custom.ca_owner", "Example Owner"));

        Map<String, Object> result = mapper.map(contract);

        assertEquals(Map.of("com.infa.odin.models.custom.ca_owner", "Example Owner"), result);
    }
}
