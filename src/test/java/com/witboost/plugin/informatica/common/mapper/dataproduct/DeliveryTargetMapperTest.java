package com.witboost.plugin.informatica.common.mapper.dataproduct;

import static org.junit.jupiter.api.Assertions.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.witboost.plugin.informatica.common.model.informatica.DataAsset;
import com.witboost.plugin.informatica.common.model.informatica.DeliveryTarget;
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

class DeliveryTargetMapperTest {

    private final DeliveryTargetMapper mapper = Mappers.getMapper(DeliveryTargetMapper.class);
    private final DataAssetMapper dataAssetMapper = Mappers.getMapper(DataAssetMapper.class);
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void toOutputPortMapsBasicFieldsCorrectly() {
        // Arrange
        OutputPort<Specific> witboostOutputPort =
                createTestOutputPort("Test Port", "Test Description", "1.0.0", "Snowflake");

        // Act
        DeliveryTarget result = mapper.toOutputPort(witboostOutputPort);

        // Assert
        assertNotNull(result);
        assertNotNull(result.getBaseCharacteristics());
        assertEquals("Test Port", result.getBaseCharacteristics().getPortName());
        assertEquals("Test Description", result.getBaseCharacteristics().getDescription());
        assertEquals("1.0.0", result.getBaseCharacteristics().getVersion());
        assertEquals("Snowflake", result.getBaseCharacteristics().getPortTechnology());
    }

    @Test
    void toOutputPortMapsAllFieldsFromJson() {
        // Arrange
        OutputPort<Specific> witboostOutputPort =
                createTestOutputPort("Test Port", "Test Description", "1.0.0", "Snowflake");

        ObjectNode specific = objectMapper.createObjectNode();
        specific.put("managedCompanies", "Company1, Company2");
        specific.put("managedData", "Data1, Data2");
        specific.put("adGroupName", "AD-Group-Test");
        specific.put("isPii", true);
        specific.put("modificationDate", "2026-01-15");
        specific.put("suspensionDate", "2026-06-01");
        specific.put("deprecationDate", "2027-01-01");
        specific.put("qualityExpectations", "High Quality Standards");
        specific.put("securityConsiderations", "TLS 1.3, OAuth 2.0");
        specific.put("technicalSpecifications", "REST API, JDBC, Snowflake View");
        witboostOutputPort.setSpecific(specific);

        // Act
        DeliveryTarget result = mapper.toOutputPort(witboostOutputPort);

        // Assert - Build expected BaseCharacteristics
        DeliveryTarget.BaseCharacteristics expected =
                DeliveryTarget.BaseCharacteristics.builder()
                        .portName("Test Port")
                        .description("Test Description")
                        .version("1.0.0")
                        .portTechnology("Snowflake")
                        .creationDate("2026-01-01")
                        .isPii(true)
                        .modificationDate("2026-01-15")
                        .suspensionDate("2026-06-01")
                        .deprecationDate("2027-01-01")
                        .qualityExpectations("High Quality Standards")
                        .securityConsiderations("TLS 1.3, OAuth 2.0")
                        .technicalSpecifications("REST API, JDBC, Snowflake View")
                        .build();

        assertEquals(expected, result.getBaseCharacteristics());
    }

    @Test
    void toOutputPortsGroupedByTechnologyGroupsCorrectly() {
        // Arrange - All ports with same Snowflake technology
        OutputPort<Specific> port1 = createTestOutputPort("Port1", "Desc1", "1.0", "Snowflake");
        OutputPort<Specific> port2 = createTestOutputPort("Port2", "Desc2", "1.0", "Snowflake");
        OutputPort<Specific> port3 = createTestOutputPort("Port3", "Desc3", "1.0", "Snowflake");

        List<OutputPort<?>> outputPorts = List.of(port1, port2, port3);

        // Act
        List<DeliveryTarget> result =
                mapper.toOutputPortsGroupedByTechnology(outputPorts, dataAssetMapper);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size()); // All ports grouped under Snowflake

        // Find Snowflake group
        DeliveryTarget snowflakeGroup = result.get(0);
        assertNotNull(snowflakeGroup);
        assertEquals("Snowflake", snowflakeGroup.getBaseCharacteristics().getPortTechnology());
        assertEquals(3, snowflakeGroup.getDataAssets().size()); // Three ports grouped together
    }

    @Test
    void toOutputPortsGroupedByTechnologyAggregatesNameAndDescription() {
        // Arrange - Multiple ports with same technology
        OutputPort<Specific> port1 = createTestOutputPort("Port1", "Desc1", "1.0", "Snowflake");
        OutputPort<Specific> port2 = createTestOutputPort("Port2", "Desc2", "1.0", "Snowflake");

        List<OutputPort<?>> outputPorts = List.of(port1, port2);

        // Act
        List<DeliveryTarget> result =
                mapper.toOutputPortsGroupedByTechnology(outputPorts, dataAssetMapper);

        // Assert
        assertEquals(1, result.size());
        DeliveryTarget snowflakeGroup = result.get(0);

        // When multiple ports are grouped, name and description are aggregated
        assertEquals(
                "Snowflake Output Port", snowflakeGroup.getBaseCharacteristics().getPortName());
        assertTrue(
                snowflakeGroup.getBaseCharacteristics().getDescription().contains("2 data assets"));
    }

    @Test
    void toOutputPortsGroupedByTechnologyHandlesEmptyList() {
        // Act
        List<DeliveryTarget> result =
                mapper.toOutputPortsGroupedByTechnology(List.of(), dataAssetMapper);

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void toOutputPortsGroupedByTechnologyHandlesNullList() {
        // Act
        List<DeliveryTarget> result =
                mapper.toOutputPortsGroupedByTechnology(null, dataAssetMapper);

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void toOutputPortsGroupedByTechnologyHandlesNullTechnology() {
        // Arrange
        OutputPort<Specific> port1 = createTestOutputPort("Port1", "Desc1", "1.0", null);

        List<OutputPort<?>> outputPorts = List.of(port1);

        // Act
        List<DeliveryTarget> result =
                mapper.toOutputPortsGroupedByTechnology(outputPorts, dataAssetMapper);

        // Assert
        assertEquals(1, result.size());
        // Null technology should be treated as "unknown"
        assertNull(result.get(0).getBaseCharacteristics().getPortTechnology());
    }

    @Test
    void toOutputPortFromYamlDescriptor() throws Exception {
        // Arrange
        String yamlContent =
                ResourceUtils.getContentFromResource("/parser/descriptors/sample_descriptor.yml");
        DataProduct dataProduct = Parser.parseDataProduct(yamlContent).get();
        List<OutputPort<Specific>> outputPorts = dataProduct.extractOutputPorts();
        assertEquals(2, outputPorts.size());

        // Act
        List<OutputPort<?>> castedList = new java.util.ArrayList<>(outputPorts);
        List<DeliveryTarget> result =
                mapper.toOutputPortsGroupedByTechnology(castedList, dataAssetMapper);

        // Assert
        assertEquals(1, result.size());
        DeliveryTarget snowflakeTarget = result.get(0);
        assertEquals(2, snowflakeTarget.getDataAssets().size());

        // Expected DeliveryTarget.BaseCharacteristics
        DeliveryTarget.BaseCharacteristics expectedBc =
                DeliveryTarget.BaseCharacteristics.builder()
                        .portName("Snowflake Output Port")
                        .portTechnology("Snowflake")
                        .description("Output port for Snowflake containing 2 data assets")
                        .version("1.0.0")
                        .creationDate("2026-01-01")
                        .isPii(false)
                        .modificationDate("2026-01-15")
                        .qualityExpectations("Completeness above 99%")
                        .securityConsiderations("TLS 1.3 and group-based access")
                        .technicalSpecifications("JDBC connection with view-based access")
                        .build();
        assertEquals(expectedBc, snowflakeTarget.getBaseCharacteristics());

        // Expected first DataAsset
        DataAsset firstAsset = snowflakeTarget.getDataAssets().get(0);

        DataAsset.SystemInfo expectedSystemInfo =
                DataAsset.SystemInfo.builder()
                        .systemName("Example Data System")
                        .serverName("example-snowflake.example.com")
                        .databaseName("example_analytics")
                        .schemaName("curated")
                        .build();
        assertEquals(expectedSystemInfo, firstAsset.getSystemInfo());

        DataAsset.EntityInfo expectedEntityInfo =
                DataAsset.EntityInfo.builder()
                        .entityName("orders")
                        .entityDescription("Output port for the orders dataset")
                        .entityType("View")
                        .feedingFrequency("Giornaliero")
                        .feedingType("PUSH")
                        .loadingMode("Full")
                        .manualProcess(false)
                        .historicized(true)
                        .retentionInfo("5 years")
                        .sensitiveData(false)
                        .sla(
                                DataAsset.SLA
                                        .builder()
                                        .refreshRate("Giornaliero")
                                        .retentionRate("Availability: 99.9%")
                                        .build())
                        .semanticLinks(null)
                        .build();
        assertEquals(expectedEntityInfo, firstAsset.getEntityInfo());

        // Verify first DataAsset - Attributes
        DataAsset.AttributeInfo expectedFirstAttr =
                DataAsset.AttributeInfo.builder()
                        .attributeName("order_id")
                        .attributeDescription("Stable order identifier")
                        .attributeDomain("TEXT")
                        .length(50)
                        .position(1)
                        .mandatory(false)
                        .primaryKey(false)
                        .sensitiveData(false)
                        .build();
        assertFalse(firstAsset.getAttributes().isEmpty());
        assertEquals(expectedFirstAttr, firstAsset.getAttributes().get(0));
    }

    @Test
    void toOutputPortFromDescriptorYaml() throws Exception {
        // Arrange
        String yamlContent = ResourceUtils.getContentFromResource("/descriptors/descriptor.yml");
        DataProduct dataProduct = Parser.parseDataProduct(yamlContent).get();
        List<OutputPort<Specific>> outputPorts = dataProduct.extractOutputPorts();
        assertEquals(1, outputPorts.size());

        // Act
        List<OutputPort<?>> castedList = new java.util.ArrayList<>(outputPorts);
        List<DeliveryTarget> result =
                mapper.toOutputPortsGroupedByTechnology(castedList, dataAssetMapper);

        // Assert
        assertEquals(1, result.size());
        DeliveryTarget snowflakeTarget = result.get(0);
        assertEquals(1, snowflakeTarget.getDataAssets().size());

        // Expected DeliveryTarget.BaseCharacteristics
        DeliveryTarget.BaseCharacteristics expectedBc =
                DeliveryTarget.BaseCharacteristics.builder()
                        .portName("MyFirstOuputPort")
                        .portTechnology("snowflake")
                        .description("MyFirstOuputPort")
                        .version("0.0.0")
                        .isPii(true)
                        .modificationDate("2026-01-27")
                        .suspensionDate("2026-01-28")
                        .deprecationDate("2026-01-31")
                        .qualityExpectations("TestQualityExpectation")
                        .securityConsiderations("TestSecurityConsiderations")
                        .technicalSpecifications("TestTechnicalSpecifications")
                        .build();
        assertEquals(expectedBc, snowflakeTarget.getBaseCharacteristics());

        // Expected DataAsset
        DataAsset firstAsset = snowflakeTarget.getDataAssets().get(0);

        DataAsset.SystemInfo expectedSystemInfo =
                DataAsset.SystemInfo.builder()
                        .systemName("TestSystemName")
                        .serverName("TestServerName")
                        .databaseName("TestDBName")
                        .schemaName("TestSthatmaName")
                        .build();
        assertEquals(expectedSystemInfo, firstAsset.getSystemInfo());

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
        assertEquals(expectedEntityInfo, firstAsset.getEntityInfo());

        // Verify DataAsset - Attributes
        DataAsset.AttributeInfo expectedFirstAttr =
                DataAsset.AttributeInfo.builder()
                        .attributeName("ID")
                        .attributeDescription(null)
                        .attributeDomain("TEXT")
                        .length(16777216)
                        .position(1)
                        .mandatory(false)
                        .primaryKey(false)
                        .sensitiveData(false)
                        .build();
        assertFalse(firstAsset.getAttributes().isEmpty());
        assertEquals(expectedFirstAttr, firstAsset.getAttributes().get(0));
    }

    // ========================================
    // Helper methods
    // ========================================

    private OutputPort<Specific> createTestOutputPort(
            String name, String description, String version, String technology) {
        OutputPort<Specific> outputPort = new OutputPort<>();
        outputPort.setName(name);
        outputPort.setDescription(description);
        outputPort.setVersion(version);
        outputPort.setTechnology(technology != null ? Optional.of(technology) : Optional.empty());
        outputPort.setCreationDate(Optional.of("2026-01-01"));
        outputPort.setDataContract(new DataContract());
        return outputPort;
    }
}
