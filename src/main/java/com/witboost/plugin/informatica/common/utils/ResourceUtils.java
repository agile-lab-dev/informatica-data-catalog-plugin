package com.witboost.plugin.informatica.common.utils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public final class ResourceUtils {

    private ResourceUtils() {}

    /**
     * Reads resource content from the classpath as UTF-8 string.
     *
     * @param resourcePath path to resource, absolute (starting with '/') or relative to this
     *     class's package
     * @return content of the resource
     * @throws IOException when resource is not found or an I/O error occurs
     */
    public static String getContentFromResource(String resourcePath) throws IOException {
        Objects.requireNonNull(resourcePath, "resourcePath must not be null");
        try (InputStream in = ResourceUtils.class.getResourceAsStream(resourcePath)) {
            if (in == null) {
                throw new IOException("Resource not found: " + resourcePath);
            }
            return new String(in.readAllBytes(), StandardCharsets.UTF_8);
        }
    }
}
