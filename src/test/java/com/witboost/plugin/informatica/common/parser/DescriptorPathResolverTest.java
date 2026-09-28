package com.witboost.plugin.informatica.common.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

class DescriptorPathResolverTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void resolvesPathRelativeToSuppliedNode() throws Exception {
        var root = objectMapper.readTree("{\"specific\":{\"owner\":\"Example Owner\"}}");

        assertEquals("Example Owner", DescriptorPathResolver.read(root, "specific.owner").asText());
        assertNull(DescriptorPathResolver.read(root.get("specific"), "specific.owner"));
    }

    @Test
    void rejectsBlankAndMalformedPaths() throws Exception {
        var root = objectMapper.readTree("{} ");

        assertThrows(IllegalArgumentException.class, () -> DescriptorPathResolver.read(root, ""));
        assertThrows(
                IllegalArgumentException.class,
                () -> DescriptorPathResolver.read(root, "specific..owner"));
    }
}
