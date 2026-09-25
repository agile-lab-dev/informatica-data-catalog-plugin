package com.witboost.plugin.informatica.common.model.informatica;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.Map;
import org.junit.jupiter.api.Test;

class DataContractCustomAttributeDateTest {

    @Test
    void descriptorCustomAttributesAreMappedByConfiguredName() {
        Map<String, String> attributeIds =
                Map.of("businessOwner", "id-owner", "qualityScore", "id-quality");

        DataContract dataContract =
                DataContract.builder()
                        .customAttributes(
                                Map.of("businessOwner", "Example Owner", "qualityScore", 99))
                        .build();

        Map<String, Object> values = dataContract.getCustomAttributeValues(attributeIds);

        assertEquals("Example Owner", values.get("id-owner"));
        assertEquals(99, values.get("id-quality"));
        assertFalse(values.containsKey("missing"));
    }
}
