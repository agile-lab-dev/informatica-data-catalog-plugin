package com.witboost.plugin.informatica.common.mapper.dataproduct;

import static org.junit.jupiter.api.Assertions.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.witboost.plugin.informatica.common.model.informatica.DataAsset;
import com.witboost.plugin.informatica.common.model.witboost.Column;
import com.witboost.plugin.informatica.common.model.witboost.DataContract;
import com.witboost.plugin.informatica.common.model.witboost.DataProduct;
import com.witboost.plugin.informatica.common.model.witboost.OutputPort;
import com.witboost.plugin.informatica.common.model.witboost.Specific;
import com.witboost.plugin.informatica.common.parser.Parser;
import com.witboost.plugin.informatica.common.utils.ResourceUtils;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

class DataAssetMapperTest {

    private final DataAssetMapper mapper = Mappers.getMapper(DataAssetMapper.class);
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void toDataAssetMapsEntityInfoFromOutputPort() {
        // Arrange
        OutputPort<Specific> outputPort =
                createTestOutputPort("TestEntity", "Test entity description");
        ObjectNode specific = objectMapper.createObjectNode();
        specific.put("entityName", "TestEntity");
        outputPort.setSpecific(specific);

        // Act
        DataAsset result = mapper.toDataAsset(outputPort);

        // Assert
        assertNotNull(result);
        assertNotNull(result.getEntityInfo());
        assertEquals("TestEntity", result.getEntityInfo().getEntityName());
        assertEquals("Test entity description", result.getEntityInfo().getEntityDescription());
    }

    @Test
    void toDataAssetMapsSystemInfoFromSpecific() {
        // Arrange
        OutputPort<Specific> outputPort = createTestOutputPort("TestEntity", "Description");

        ObjectNode specific = objectMapper.createObjectNode();
        specific.put("systemName", "TestSystem");
        specific.put("serverName", "test-server.example.com");
        specific.put("databaseName", "test_database");
        specific.put("schemaName", "test_schema");
        outputPort.setSpecific(specific);

        // Act
        DataAsset result = mapper.toDataAsset(outputPort);

        // Assert
        assertNotNull(result.getSystemInfo());
        assertEquals("TestSystem", result.getSystemInfo().getSystemName());
        assertEquals("test-server.example.com", result.getSystemInfo().getServerName());
        assertEquals("test_database", result.getSystemInfo().getDatabaseName());
        assertEquals("test_schema", result.getSystemInfo().getSchemaName());
    }

    @Test
    void toDataAssetMapsEntityInfoFromSpecific() {
        // Arrange
        OutputPort<Specific> outputPort = createTestOutputPort("TestEntity", "Description");

        ObjectNode specific = objectMapper.createObjectNode();
        specific.put("entityType", "Table");
        specific.put("feedingFrequency", "Giornaliero");
        specific.put("feedingType", "PUSH");
        specific.put("loadingMode", "Full");
        specific.put("manualProcess", true);
        specific.put("historicized", true);
        specific.put("retentionInfo", "5 years");
        specific.put("sensitiveData", true);
        ObjectNode slaNode = objectMapper.createObjectNode();
        slaNode.put("refreshRate", "Giornaliero");
        slaNode.put("retentionRate", "99.9%");
        specific.set("sla", slaNode);
        specific.put("managedData", "Company1, Domain1");
        specific.put("managedCompanies", "Company1");
        specific.put("semanticLinks", "term1, term2");
        outputPort.setSpecific(specific);

        // Act
        DataAsset result = mapper.toDataAsset(outputPort);

        // Assert
        DataAsset.EntityInfo entityInfo = result.getEntityInfo();
        assertEquals("Table", entityInfo.getEntityType());
        assertEquals("Giornaliero", entityInfo.getFeedingFrequency());
        assertEquals("PUSH", entityInfo.getFeedingType());
        assertEquals("Full", entityInfo.getLoadingMode());
        assertEquals(true, entityInfo.getManualProcess());
        assertEquals(true, entityInfo.getHistoricized());
        assertEquals("5 years", entityInfo.getRetentionInfo());
        assertEquals(true, entityInfo.getSensitiveData());
        assertEquals(
                DataAsset.SLA.builder().refreshRate("Giornaliero").retentionRate("99.9%").build(),
                entityInfo.getSla());
        assertEquals("term1, term2", entityInfo.getSemanticLinks());
    }

    @Test
    void normalizesEntityVocabulariesCaseInsensitively() {
        OutputPort<Specific> outputPort = createTestOutputPort("TestEntity", "Description");

        ObjectNode specific = objectMapper.createObjectNode();
        specific.put("feedingFrequency", "giornaliero");
        specific.put("feedingType", "pull");
        specific.put("loadingMode", "full");
        ObjectNode slaNode = objectMapper.createObjectNode();
        slaNode.put("refreshRate", "ONESHOT");
        specific.set("sla", slaNode);
        outputPort.setSpecific(specific);

        DataAsset.EntityInfo entityInfo = mapper.toDataAsset(outputPort).getEntityInfo();

        assertEquals("Giornaliero", entityInfo.getFeedingFrequency());
        assertEquals("PULL", entityInfo.getFeedingType());
        assertEquals("Full", entityInfo.getLoadingMode());
        assertEquals("Oneshot", entityInfo.getSla().getRefreshRate());
    }

    @Test
    void toDataAssetMapsAttributesFromSchema() {
        // Arrange
        OutputPort<Specific> outputPort = createTestOutputPort("TestEntity", "Description");

        DataContract dataContract = new DataContract();
        Column column1 = new Column();
        column1.setName("id");
        column1.setDescription("Primary key");
        column1.setDataType("INTEGER");
        column1.setDataLength("10");

        Column column2 = new Column();
        column2.setName("name");
        column2.setDescription("Name field");
        column2.setDataType("VARCHAR");
        column2.setDataLength("255");

        dataContract.setSchema(List.of(column1, column2));
        outputPort.setDataContract(dataContract);

        // Act
        DataAsset result = mapper.toDataAsset(outputPort);

        // Assert - Build expected attributes
        DataAsset.AttributeInfo expectedAttr1 =
                DataAsset.AttributeInfo.builder()
                        .attributeName("id")
                        .attributeDescription("Primary key")
                        .attributeDomain("INTEGER")
                        .length(10)
                        .position(1)
                        .mandatory(false)
                        .primaryKey(false)
                        .sensitiveData(false)
                        .semanticLinks(null)
                        .qualityControlLinks(null)
                        .build();

        DataAsset.AttributeInfo expectedAttr2 =
                DataAsset.AttributeInfo.builder()
                        .attributeName("name")
                        .attributeDescription("Name field")
                        .attributeDomain("VARCHAR")
                        .length(255)
                        .position(2)
                        .mandatory(false)
                        .primaryKey(false)
                        .sensitiveData(false)
                        .semanticLinks(null)
                        .qualityControlLinks(null)
                        .build();

        assertEquals(2, result.getAttributes().size());
        assertEquals(expectedAttr1, result.getAttributes().get(0));
        assertEquals(expectedAttr2, result.getAttributes().get(1));
    }

    @Test
    void toDataAssetMapsAllSystemAndEntityFields() {
        // Arrange
        OutputPort<Specific> outputPort =
                createTestOutputPort("CompleteEntity", "Complete entity description");

        ObjectNode specific = objectMapper.createObjectNode();
        specific.put("systemName", "Production System");
        specific.put("serverName", "prod-server.example.com");
        specific.put("databaseName", "prod_database");
        specific.put("schemaName", "prod_schema");
        specific.put("entityName", "CompleteEntity");
        specific.put("entityType", "Table");
        specific.put("feedingFrequency", "Giornaliero");
        specific.put("feedingType", "PUSH");
        specific.put("loadingMode", "Incremental");
        specific.put("manualProcess", true);
        specific.put("historicized", true);
        specific.put("retentionInfo", "7 years retention policy");
        specific.put("sensitiveData", true);
        ObjectNode slaNode2 = objectMapper.createObjectNode();
        slaNode2.put("refreshRate", "Giornaliero");
        slaNode2.put("retentionRate", "99.99% availability");
        specific.set("sla", slaNode2);
        specific.put("managedData", "Example, Finance, Accounting");
        specific.put("managedCompanies", "Example Organization, Example Company");
        specific.put("semanticLinks", "customer, account, transaction");
        outputPort.setSpecific(specific);

        // Act
        DataAsset result = mapper.toDataAsset(outputPort);

        // Assert - Build expected objects
        DataAsset.SystemInfo expectedSystemInfo =
                DataAsset.SystemInfo.builder()
                        .systemName("Production System")
                        .serverName("prod-server.example.com")
                        .databaseName("prod_database")
                        .schemaName("prod_schema")
                        .build();

        DataAsset.EntityInfo expectedEntityInfo =
                DataAsset.EntityInfo.builder()
                        .entityName("CompleteEntity")
                        .entityDescription("Complete entity description")
                        .entityType("Table")
                        .feedingFrequency("Giornaliero")
                        .feedingType("PUSH")
                        .loadingMode("Incremental")
                        .manualProcess(true)
                        .historicized(true)
                        .retentionInfo("7 years retention policy")
                        .sensitiveData(true)
                        .sla(
                                DataAsset.SLA
                                        .builder()
                                        .refreshRate("Giornaliero")
                                        .retentionRate("99.99% availability")
                                        .build())
                        .semanticLinks("customer, account, transaction")
                        .build();

        assertEquals(expectedSystemInfo, result.getSystemInfo());
        assertEquals(expectedEntityInfo, result.getEntityInfo());
    }

    @Test
    void toDataAssetHandlesEmptySchema() {
        // Arrange
        OutputPort<Specific> outputPort = createTestOutputPort("TestEntity", "Description");
        DataContract dataContract = new DataContract();
        dataContract.setSchema(List.of());
        outputPort.setDataContract(dataContract);

        // Act
        DataAsset result = mapper.toDataAsset(outputPort);

        // Assert
        assertNotNull(result.getAttributes());
        assertTrue(result.getAttributes().isEmpty());
    }

    @Test
    void toDataAssetHandlesNullDataContract() {
        // Arrange
        OutputPort<Specific> outputPort = createTestOutputPort("TestEntity", "Description");
        outputPort.setDataContract(null);

        // Act
        DataAsset result = mapper.toDataAsset(outputPort);

        // Assert
        assertNotNull(result.getAttributes());
        assertTrue(result.getAttributes().isEmpty());
    }

    @Test
    void toDataAssetSetsDefaultBooleanValues() {
        // Arrange
        OutputPort<Specific> outputPort = createTestOutputPort("TestEntity", "Description");

        // Act
        DataAsset result = mapper.toDataAsset(outputPort);

        // Assert - defaults for boolean fields
        assertEquals(false, result.getEntityInfo().getHistoricized());
        assertEquals(false, result.getEntityInfo().getSensitiveData());
    }

    @Test
    void toDataAssetFromYamlDescriptor() throws Exception {
        // Arrange
        String yamlContent =
                ResourceUtils.getContentFromResource("/parser/descriptors/sample_descriptor.yml");
        DataProduct dataProduct = Parser.parseDataProduct(yamlContent).get();
        List<OutputPort<Specific>> outputPorts = dataProduct.extractOutputPorts();

        assertFalse(outputPorts.isEmpty());
        assertEquals(2, outputPorts.size());
        OutputPort<Specific> firstOutputPort = outputPorts.get(0);

        // Act
        DataAsset result = mapper.toDataAsset(firstOutputPort);

        // Assert - Build expected SystemInfo
        DataAsset.SystemInfo expectedSystemInfo =
                DataAsset.SystemInfo.builder()
                        .systemName("Example Data System")
                        .serverName("example-snowflake.example.com")
                        .databaseName("example_analytics")
                        .schemaName("curated")
                        .build();

        // Assert - Build expected EntityInfo
        DataAsset.EntityInfo expectedEntityInfo =
                DataAsset.EntityInfo.builder()
                        .entityName("contratto")
                        .entityDescription(
                                " Tabella che contiene i contratti in vigore ed altre informazioni riguardanti il folder. Chiave compagnia/archivio/appendice (id_contratto_dvi)")
                        .entityType("View")
                        .feedingFrequency("Giornaliero")
                        .feedingType("PUSH")
                        .loadingMode("Full")
                        .manualProcess(false)
                        .historicized(true)
                        .retentionInfo("5 anni")
                        .sensitiveData(false)
                        .sla(
                                DataAsset.SLA
                                        .builder()
                                        .refreshRate("Giornaliero")
                                        .retentionRate("Disponibilità: 99.9%")
                                        .build())
                        .semanticLinks(null)
                        .build();

        assertEquals(expectedSystemInfo, result.getSystemInfo());
        assertEquals(expectedEntityInfo, result.getEntityInfo());
        // The Witboost component id/urn is carried so validation errors can point at the component
        assertEquals(
                "urn:example:component:analytics:orders",
                result.getComponentId());

        // Assert - Build expected Attributes (first two)
        DataAsset.AttributeInfo expectedAttr1 =
                DataAsset.AttributeInfo.builder()
                        .attributeName("id_contratto_dvi")
                        .attributeDescription(
                                "Identificativo Univoco Contratto Compagnia|Archivio|Posizione")
                        .attributeDomain("text")
                        .length(50)
                        .position(1)
                        .mandatory(false)
                        .primaryKey(false)
                        .sensitiveData(false)
                        .semanticLinks(null)
                        .qualityControlLinks(null)
                        .build();

        DataAsset.AttributeInfo expectedAttr2 =
                DataAsset.AttributeInfo.builder()
                        .attributeName("cd_compagnia")
                        .attributeDescription("Codice Compagnia (1=Example Organization)")
                        .attributeDomain("text")
                        .length(10)
                        .position(2)
                        .mandatory(false)
                        .primaryKey(false)
                        .sensitiveData(false)
                        .semanticLinks(null)
                        .qualityControlLinks(null)
                        .build();

        assertTrue(result.getAttributes().size() >= 6);
        assertEquals(expectedAttr1, result.getAttributes().get(0));
        assertEquals(expectedAttr2, result.getAttributes().get(1));
    }

    @Test
    void toDataAssetHandlesInvalidDataLength() {
        // Arrange
        OutputPort<Specific> outputPort = createTestOutputPort("TestEntity", "Description");

        DataContract dataContract = new DataContract();
        Column column = new Column();
        column.setName("test");
        column.setDescription("Test");
        column.setDataType("TEXT");
        column.setDataLength("invalid"); // Non-numeric

        dataContract.setSchema(List.of(column));
        outputPort.setDataContract(dataContract);

        // Act
        DataAsset result = mapper.toDataAsset(outputPort);

        // Assert - Should default to 0 for invalid length
        assertEquals(0, result.getAttributes().get(0).getLength());
    }

    @Test
    void toDataAssetHandlesNullDataLength() {
        // Arrange
        OutputPort<Specific> outputPort = createTestOutputPort("TestEntity", "Description");

        DataContract dataContract = new DataContract();
        Column column = new Column();
        column.setName("test");
        column.setDescription("Test");
        column.setDataType("TEXT");
        column.setDataLength(null);

        dataContract.setSchema(List.of(column));
        outputPort.setDataContract(dataContract);

        // Act
        DataAsset result = mapper.toDataAsset(outputPort);

        // Assert - Should default to 0 for null length
        assertEquals(0, result.getAttributes().get(0).getLength());
    }

    @Test
    void toDataAssetFromDescriptorYaml() throws Exception {
        // Arrange
        String yamlContent = ResourceUtils.getContentFromResource("/descriptors/descriptor.yml");
        DataProduct dataProduct = Parser.parseDataProduct(yamlContent).get();
        List<OutputPort<Specific>> outputPorts = dataProduct.extractOutputPorts();

        assertFalse(outputPorts.isEmpty());
        assertEquals(1, outputPorts.size());
        OutputPort<Specific> firstOutputPort = outputPorts.get(0);

        // Act
        DataAsset result = mapper.toDataAsset(firstOutputPort);

        // Assert - Build expected SystemInfo
        DataAsset.SystemInfo expectedSystemInfo =
                DataAsset.SystemInfo.builder()
                        .systemName("TestSystemName")
                        .serverName("TestServerName")
                        .databaseName("TestDBName")
                        .schemaName("TestSchemaName")
                        .build();

        // Assert - Build expected EntityInfo
        DataAsset.EntityInfo expectedEntityInfo =
                DataAsset.EntityInfo.builder()
                        .entityName("myfirstouputport")
                        .entityDescription("MyFirstOuputPort")
                        .entityType("TestEntityType")
                        .feedingFrequency("TestFrequency")
                        .feedingType("TestFeedingType")
                        .loadingMode("TetLoadingMode")
                        .manualProcess(true)
                        .historicized(true)
                        .retentionInfo("TestRetentionInfo")
                        .sensitiveData(true)
                        .sla(
                                DataAsset.SLA
                                        .builder()
                                        .refreshRate("TestRefreshRate")
                                        .retentionRate("TestRetentionRate")
                                        .build())
                        .semanticLinks("TestSemanticLinks")
                        .build();

        assertEquals(expectedSystemInfo, result.getSystemInfo());
        assertEquals(expectedEntityInfo, result.getEntityInfo());

        // Assert - Build expected Attributes
        DataAsset.AttributeInfo expectedAttr1 =
                DataAsset.AttributeInfo.builder()
                        .attributeName("ID")
                        .attributeDescription(null)
                        .attributeDomain("TEXT")
                        .length(16777216)
                        .position(1)
                        .mandatory(false)
                        .primaryKey(false)
                        .sensitiveData(false)
                        .semanticLinks(null)
                        .qualityControlLinks(null)
                        .build();

        DataAsset.AttributeInfo expectedAttr2 =
                DataAsset.AttributeInfo.builder()
                        .attributeName("NAME")
                        .attributeDescription(null)
                        .attributeDomain("TEXT")
                        .length(16777216)
                        .position(2)
                        .mandatory(false)
                        .primaryKey(false)
                        .sensitiveData(false)
                        .semanticLinks(null)
                        .qualityControlLinks(null)
                        .build();

        assertEquals(2, result.getAttributes().size());
        assertEquals(expectedAttr1, result.getAttributes().get(0));
        assertEquals(expectedAttr2, result.getAttributes().get(1));
    }

    // ========================================
    // Helper methods
    // ========================================

    private OutputPort<Specific> createTestOutputPort(String name, String description) {
        OutputPort<Specific> outputPort = new OutputPort<>();
        outputPort.setName(name);
        outputPort.setDescription(description);
        outputPort.setVersion("1.0.0");
        outputPort.setTechnology(Optional.of("Snowflake"));
        outputPort.setCreationDate(Optional.of("2026-01-01"));
        outputPort.setDataContract(new DataContract());
        return outputPort;
    }
}
