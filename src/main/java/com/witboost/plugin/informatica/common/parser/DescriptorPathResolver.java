package com.witboost.plugin.informatica.common.parser;

import com.fasterxml.jackson.databind.JsonNode;

/** Resolves dot-separated descriptor paths relative to the supplied descriptor node. */
public final class DescriptorPathResolver {

    private DescriptorPathResolver() {}

    public static JsonNode read(JsonNode node, String path) {
        if (path == null || path.isBlank()) {
            throw new IllegalArgumentException("Descriptor path must not be blank");
        }

        JsonNode current = node;
        for (String segment : path.split("\\.", -1)) {
            if (segment.isBlank()) {
                throw new IllegalArgumentException("Invalid descriptor path: " + path);
            }
            if (current == null || !current.isObject()) {
                return null;
            }
            current = current.get(segment);
        }
        return current;
    }
}