package com.witboost.plugin.informatica.common.model.informatica;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.Map;
import org.junit.jupiter.api.Test;

class DataContractCustomAttributeDateTest {

    @Test
    void descriptorCustomAttributesAreKeyedByTechnicalId() {
        DataContract dataContract =
                DataContract.builder()
                        .customAttributes(Map.of("id-owner", "Example Owner", "id-quality", 99))
                        .build();

        Map<String, Object> values = dataContract.getCustomAttributes();

        assertEquals("Example Owner", values.get("id-owner"));
        assertEquals(99, values.get("id-quality"));
        assertFalse(values.containsKey("missing"));
    }
}
