package com.witboost.plugin.informatica.common.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.witboost.plugin.informatica.common.config.DescriptorStructureProperties;
import org.junit.jupiter.api.Test;

class DescriptorTreeParserTest {

    @Test
    void parsesFlatOutputPortAsOutputPortWithSchemaFields() throws Exception {
        DescriptorTreeNode root = parser().parse(flatDescriptor());

        assertEquals(DescriptorTreeNode.Type.DATA_PRODUCT, root.getType());
        assertTrue(root.isPublishEnabled());
        assertEquals(1, root.getChildren().size());
        DescriptorTreeNode outputPort = root.getChildren().get(0);
        assertEquals(DescriptorTreeNode.Type.OUTPUT_PORT, outputPort.getType());
        assertEquals(DescriptorTreeNode.CatalogAssetType.DATASET, outputPort.getCatalogAssetType());
        assertEquals("op-flat", outputPort.getId());
        assertEquals(1, outputPort.getSchemaFields().size());
        assertTrue(outputPort.getChildren().isEmpty());
    }

    @Test
    void parsesNestedOutputPortAndAppliesLocalPublishOptOut() throws Exception {
        DescriptorTreeNode root = parser().parse(nestedDescriptor());

        DescriptorTreeNode outputPort = root.getChildren().get(0);
        assertEquals(DescriptorTreeNode.Type.OUTPUT_PORT, outputPort.getType());
        assertEquals(DescriptorTreeNode.CatalogAssetType.SYSTEM, outputPort.getCatalogAssetType());
        assertEquals(1, outputPort.getChildren().size());
        DescriptorTreeNode subcomponent = outputPort.getChildren().get(0);
        assertEquals(DescriptorTreeNode.Type.SUBCOMPONENT, subcomponent.getType());
        assertEquals(outputPort, subcomponent.getParent());
        assertFalse(subcomponent.isPublishEnabled());
        assertEquals(
                DescriptorTreeNode.CatalogAssetType.DATASET, subcomponent.getCatalogAssetType());
        assertEquals(1, subcomponent.getSchemaFields().size());
    }

    private static DescriptorTreeParser parser() {
        DescriptorStructureProperties properties = new DescriptorStructureProperties();
        properties.getDataProduct().setChildrenPath("components");
        properties.getOutputPort().setChildrenPath("components");
        properties.getSubcomponent().setChildrenPath("components");
        properties.getOutputPort().setKindValue("outputport");
        properties.getSubcomponent().setKindValue("outputport");
        properties.getSchema().setPath("dataContract.schema");
        return new DescriptorTreeParser(properties);
    }

    private static String flatDescriptor() {
        return """
                specific:
                  publishToInformatica: true
                components:
                  - kind: workload
                    id: ignored-workload
                  - kind: outputport
                    id: op-flat
                    dataContract:
                      schema:
                        - name: customer_id
                """;
    }

    private static String nestedDescriptor() {
        return """
                specific:
                  publishToInformatica: true
                components:
                  - kind: outputport
                    id: op-container
                    components:
                      - kind: outputport
                        id: sub-hidden
                        specific:
                          publishToInformatica: false
                        dataContract:
                          schema:
                            - name: customer_id
                """;
    }
}
