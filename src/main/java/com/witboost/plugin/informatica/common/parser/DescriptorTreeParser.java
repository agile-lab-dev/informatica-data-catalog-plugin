package com.witboost.plugin.informatica.common.parser;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.witboost.plugin.informatica.common.config.DescriptorStructureProperties;
import java.util.ArrayList;
import java.util.List;

/** Builds the Catalog and Marketplace-neutral tree from a raw YAML descriptor. */
public final class DescriptorTreeParser {

    private static final String COMPONENTS = "components";

    private final ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
    private final DescriptorStructureProperties properties;

    public DescriptorTreeParser(DescriptorStructureProperties properties) {
        this.properties = properties;
    }

    public DescriptorTreeNode parse(String yamlDescriptor) throws Exception {
        return parse(objectMapper.readTree(yamlDescriptor));
    }

    public DescriptorTreeNode parse(JsonNode descriptor) {
        if (descriptor == null || !descriptor.isObject()) {
            throw new IllegalArgumentException("Descriptor root must be a JSON/YAML object");
        }
        return parseNode(
                DescriptorTreeNode.Type.DATA_PRODUCT,
                descriptor,
                null,
                properties.getDataProduct(),
                properties.getOutputPort());
    }

    private DescriptorTreeNode parseNode(
            DescriptorTreeNode.Type type,
            JsonNode node,
            DescriptorTreeNode parent,
            DescriptorStructureProperties.NodeProperties nodeProperties,
            DescriptorStructureProperties.NodeProperties childProperties) {
        boolean publishEnabled =
                parent == null
                        ? readRequiredBoolean(node, "specific.publishToInformatica", false)
                        : parent.isPublishEnabled()
                                && readOptOutBoolean(node, nodeProperties.getPublishPath());
        String id = readText(node, nodeProperties.getIdPath());
        DescriptorTreeNode treeNode =
                new DescriptorTreeNode(
                        type,
                        node,
                        parent,
                        id,
                        publishEnabled,
                        readBoolean(node, nodeProperties.getShoppablePath(), false),
                        readArray(node, properties.getSchema().getPath()));
        List<JsonNode> childNodes = readArray(node, COMPONENTS);
        for (JsonNode child : childNodes) {
            if (!matchesKind(child, childProperties)) {
                continue;
            }
            DescriptorTreeNode.Type childType = childType(type);
            DescriptorStructureProperties.NodeProperties childNodeProperties =
                    type == DescriptorTreeNode.Type.OUTPUT_PORT
                            ? properties.getSubcomponent()
                            : childProperties;
            DescriptorTreeNode childTreeNode =
                    parseNode(
                            childType,
                            child,
                            treeNode,
                            childNodeProperties,
                            properties.getSubcomponent());
            treeNode.addChild(childTreeNode);
        }
        return treeNode;
    }

    private boolean matchesKind(
            JsonNode node, DescriptorStructureProperties.NodeProperties childProperties) {
        String expectedKind = childProperties.getKindValue();
        return expectedKind == null
                || expectedKind.isBlank()
                || expectedKind.equals(readText(node, childProperties.getKindPath()));
    }

    private DescriptorTreeNode.Type childType(DescriptorTreeNode.Type parentType) {
        return parentType == DescriptorTreeNode.Type.DATA_PRODUCT
                ? DescriptorTreeNode.Type.OUTPUT_PORT
                : DescriptorTreeNode.Type.SUBCOMPONENT;
    }

    private List<JsonNode> readArray(JsonNode node, String path) {
        JsonNode value = readPath(node, path);
        if (value == null || value.isNull()) return List.of();
        if (!value.isArray()) {
            throw new IllegalArgumentException(
                    "Configured descriptor path is not an array: " + path);
        }
        List<JsonNode> values = new ArrayList<>();
        value.elements().forEachRemaining(values::add);
        return values;
    }

    private boolean readRequiredBoolean(JsonNode node, String path, boolean defaultValue) {
        JsonNode value = readPath(node, path);
        if (value == null || value.isNull()) {
            throw new IllegalArgumentException("Required descriptor flag is missing: " + path);
        }
        return value.asBoolean(defaultValue);
    }

    private boolean readOptOutBoolean(JsonNode node, String path) {
        JsonNode value = readPath(node, path);
        return value == null || value.isNull() || value.asBoolean(true);
    }

    private boolean readBoolean(JsonNode node, String path, boolean defaultValue) {
        JsonNode value = readPath(node, path);
        return value == null || value.isNull() ? defaultValue : value.asBoolean(defaultValue);
    }

    private String readText(JsonNode node, String path) {
        JsonNode value = readPath(node, path);
        return value == null || value.isNull() ? null : value.asText();
    }

    private JsonNode readPath(JsonNode node, String path) {
        return DescriptorPathResolver.read(node, path);
    }
}
