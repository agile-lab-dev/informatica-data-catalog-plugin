package com.witboost.plugin.informatica.common.parser;

import com.fasterxml.jackson.databind.JsonNode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/** A descriptor node with its raw payload and resolved publication state. */
public final class DescriptorTreeNode {

    public enum Type {
        DATA_PRODUCT,
        OUTPUT_PORT,
        SUBCOMPONENT
    }

    public enum CatalogAssetType {
        SYSTEM,
        DATASET,
        ELEMENT
    }

    private final Type type;
    private final JsonNode rawNode;
    private final DescriptorTreeNode parent;
    private final String id;
    private final boolean publishEnabled;
    private final boolean shoppable;
    private final List<DescriptorTreeNode> children = new ArrayList<>();
    private final List<JsonNode> schemaFields;

    DescriptorTreeNode(
            Type type,
            JsonNode rawNode,
            DescriptorTreeNode parent,
            String id,
            boolean publishEnabled,
            boolean shoppable,
            List<JsonNode> schemaFields) {
        this.type = type;
        this.rawNode = rawNode;
        this.parent = parent;
        this.id = id;
        this.publishEnabled = publishEnabled;
        this.shoppable = shoppable;
        this.schemaFields = List.copyOf(schemaFields);
    }

    public Type getType() {
        return type;
    }

    public JsonNode getRawNode() {
        return rawNode;
    }

    public DescriptorTreeNode getParent() {
        return parent;
    }

    public String getId() {
        return id;
    }

    public boolean isPublishEnabled() {
        return publishEnabled;
    }

    public boolean isShoppable() {
        return shoppable;
    }

    public List<DescriptorTreeNode> getChildren() {
        return Collections.unmodifiableList(children);
    }

    public List<JsonNode> getSchemaFields() {
        return schemaFields;
    }

    public CatalogAssetType getCatalogAssetType() {
        if (type == Type.DATA_PRODUCT || (type == Type.OUTPUT_PORT && !children.isEmpty())) {
            return CatalogAssetType.SYSTEM;
        }
        return CatalogAssetType.DATASET;
    }

    void addChild(DescriptorTreeNode child) {
        children.add(child);
    }
}
