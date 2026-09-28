package com.witboost.plugin.informatica.marketplace.mapper.datacontract;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.witboost.plugin.informatica.common.model.informatica.DataContract;
import com.witboost.plugin.informatica.marketplace.model.CreateDataCollectionRequest;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

class CreateCollectionMapperTest {

    private final CreateCollectionMapper mapper = Mappers.getMapper(CreateCollectionMapper.class);

    @Test
    void mapsDescriptorCustomAttributesUsingTechnicalIdKeys() {
        DataContract contract = new DataContract();
        contract.getBaseCharacteristics().setName("Example Collection");
        contract.getBaseCharacteristics().setDescription("Example description");
        contract.setCustomAttributes(
                Map.of("attribute-owner", "Example Owner", "attribute-quality", 99));

        CreateDataCollectionRequest result =
                mapper.mapToCreateDataCollectionRequest(contract, "category-id");

        assertNotNull(result);
        assertEquals("Example Collection", result.getName());
        assertEquals("category-id", result.getCategoryId());
        assertEquals(2, result.getCustomAttributes().size());
        Map<String, Object> values =
                result.getCustomAttributes().stream()
                        .collect(
                                java.util.stream.Collectors.toMap(
                                        CreateDataCollectionRequest.CustomAttribute::getId,
                                        CreateDataCollectionRequest.CustomAttribute::getValue));
        assertEquals("Example Owner", values.get("attribute-owner"));
        assertEquals(99, values.get("attribute-quality"));
    }
}
