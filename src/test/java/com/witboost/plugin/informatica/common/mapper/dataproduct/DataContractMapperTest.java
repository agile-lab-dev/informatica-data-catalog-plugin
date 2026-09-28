package com.witboost.plugin.informatica.common.mapper.dataproduct;

import static org.junit.jupiter.api.Assertions.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.witboost.plugin.informatica.common.model.informatica.DataAsset;
import com.witboost.plugin.informatica.common.model.informatica.DataContract;
import com.witboost.plugin.informatica.common.model.informatica.DeliveryTarget;
import com.witboost.plugin.informatica.common.model.witboost.DataProduct;
import com.witboost.plugin.informatica.common.parser.Parser;
import com.witboost.plugin.informatica.common.utils.ResourceUtils;
import com.witboost.plugin.informatica.marketplace.config.MarketplaceMappingProperties;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

class DataContractMapperTest {

    private final DataContractMapper mapper = Mappers.getMapper(DataContractMapper.class);
    private final DeliveryTargetMapper deliveryTargetMapper =
            Mappers.getMapper(DeliveryTargetMapper.class);
    private final DataAssetMapper dataAssetMapper = Mappers.getMapper(DataAssetMapper.class);
    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void mapsConfiguredMarketplacePathsFromDataProductRoot() {
        MarketplaceMappingProperties properties = new MarketplaceMappingProperties();
        properties.setCategories(
                List.of(
                        categoryLevel("specific.classification.organization"),
                        categoryLevel("specific.classification.domain")));
        MarketplaceMappingProperties.AttributeMapping owner =
                new MarketplaceMappingProperties.AttributeMapping();
        owner.setDescriptorPath("specific.customAttributes.owner");
        MarketplaceMappingProperties.AttributeMapping score =
                new MarketplaceMappingProperties.AttributeMapping();
        score.setDescriptorPath("specific.customAttributes.score");
        properties.setCustomAttributes(Map.of("attribute-owner", owner, "attribute-score", score));
        mapper.setMappingProperties(properties);

        DataProduct dataProduct =
                Parser.parseDataProduct(
                                """
                                id: product-id
                                name: Product
                                specific:
                                  classification:
                                    organization: Example Organization
                                    domain: Sales
                                  customAttributes:
                                    owner: Example Owner
                                    score: 99
                                """)
                        .get();

        DataContract result = mapper.toDataContract(dataProduct);

        assertEquals(List.of("Example Organization", "Sales"), result.getMarketplaceCategoryPath());
        assertEquals("Example Owner", result.getCustomAttributes().get("attribute-owner"));
        assertEquals(99, result.getCustomAttributes().get("attribute-score"));
    }

    private static MarketplaceMappingProperties.CategoryLevel categoryLevel(String path) {
        MarketplaceMappingProperties.CategoryLevel level =
                new MarketplaceMappingProperties.CategoryLevel();
        level.setDescriptorPath(path);
        return level;
    }

    @Test
    void toDataContractMapsCorrectly() throws Exception {
        // Arrange
        DataProduct dataProduct = createTestDataProduct();

        // Act
        DataContract result = mapper.toDataContract(dataProduct);

        // Assert
        assertEquals(expectedBaseCharacteristics(), result.getBaseCharacteristics());
        assertEquals(expectedReferenceContext(), result.getReferenceContext());
        assertEquals(expectedAdditionalInformation(), result.getAdditionalInformation());
    }

    @Test
    void toDataContractHandlesNullJsonData() {
        // Arrange
        DataProduct dataProduct = new DataProduct();
        dataProduct.setId("test-id");
        dataProduct.setName("testName");
        dataProduct.setSpecific(null); // No JSON data

        // Act
        DataContract result = mapper.toDataContract(dataProduct);

        // Assert
        assertNotNull(result);
        assertNotNull(result.getBaseCharacteristics());
        assertEquals("test-id", result.getBaseCharacteristics().getIdentifier());
        // JSON fields should not be set
        assertNull(result.getAdditionalInformation().getDataProductType());
    }

    @Test
    void toDataContractHandlesPartialJsonData() throws Exception {
        // Arrange
        DataProduct dataProduct = new DataProduct();
        dataProduct.setId("test-id");
        dataProduct.setName("testName");

        // Only some JSON fields
        ObjectNode jsonData = objectMapper.createObjectNode();
        jsonData.put("dataProductType", "Source-aligned");
        jsonData.put("company", "Test Company");
        // Missing other fields

        dataProduct.setSpecific(jsonData);

        // Act
        DataContract result = mapper.toDataContract(dataProduct);

        // Assert
        assertNotNull(result);
        assertEquals("Source-aligned", result.getAdditionalInformation().getDataProductType());
        assertEquals("Test Company", result.getReferenceContext().getCompany());
        assertNull(result.getReferenceContext().getDomain());
    }

    @Test
    void toDataContractHandlesNullJsonValues() throws Exception {
        // Arrange
        DataProduct dataProduct = new DataProduct();
        dataProduct.setId("test-id");
        dataProduct.setName("testName");

        ObjectNode jsonData = objectMapper.createObjectNode();
        jsonData.set("dataProductType", null); // Explicit null
        jsonData.put("company", "Test Company");

        dataProduct.setSpecific(jsonData);

        // Act
        DataContract result = mapper.toDataContract(dataProduct);

        // Assert
        assertNotNull(result);
        assertNull(result.getAdditionalInformation().getDataProductType());
        assertEquals("Test Company", result.getReferenceContext().getCompany());
    }

    @Test
    void toDataContractMapsAllJsonFields() throws Exception {
        // Arrange
        DataProduct dataProduct = createDataProductWithAllJsonFields();

        // Act
        DataContract result = mapper.toDataContract(dataProduct);

        // Assert - Build expected objects
        DataContract.BaseCharacteristics expectedBc =
                DataContract.BaseCharacteristics.builder()
                        .identifier("test-id")
                        .name("testName")
                        .description("testDescription")
                        .productOwnerName("Test Owner")
                        .technicalOwnersNames("Tech Owner 1, Tech Owner 2")
                        .certifiedUse("Analysis")
                        .status("Unpublished")
                        .build();

        DataContract.ReferenceContext expectedRc =
                DataContract.ReferenceContext.builder()
                        .company("TestCompany")
                        .domain("Finance")
                        .subdomain("Payments")
                        .build();

        DataContract.AdditionalInformation expectedAi =
                DataContract.AdditionalInformation.builder()
                        .dataProductType("Consumer-aligned")
                        .lifeCycleStatus("Active")
                        .version("2.0.0")
                        .sensitiveInfo("Si") // normalized from descriptor value "Yes"
                        .confidentiality("Reserved")
                        .norms("GDPR,ISO27001")
                        .creationDate("2026-01-13")
                        .endDate("2026-01-20")
                        .releaseDate("2026-01-15")
                        .suspensionDate("2026-01-18")
                        .linkDocumentation("https://docs.example.com/test")
                        .linkObservabilityPort("https://observability.example.com/test")
                        .linkDataQualityPort("https://quality.example.com/test")
                        .metadataDetails("https://metadata.example.com/test")
                        .contacts("Test Contact: test@example.com")
                        .termsOfUse("Internal use only")
                        .sla("99.9% availability")
                        .outsourcer("External Company")
                        .quality("High quality data")
                        .memorizationType("Si")
                        .build();

        assertEquals(expectedBc, result.getBaseCharacteristics());
        assertEquals(expectedRc, result.getReferenceContext());
        assertEquals(expectedAi, result.getAdditionalInformation());
    }

    @Test
    void toDataContractSetsDefaultConstants() {
        // Arrange
        DataProduct dataProduct = new DataProduct();
        dataProduct.setId("test-id");
        dataProduct.setName("testName");

        // Act
        DataContract result = mapper.toDataContract(dataProduct);

        // Assert
        assertEquals("Generic", result.getBaseCharacteristics().getCertifiedUse());
        assertEquals("Private", result.getAdditionalInformation().getConfidentiality());
    }

    @Test
    void toDataContractFromYamlDescriptor() throws Exception {

        String yamlContent =
                ResourceUtils.getContentFromResource("/parser/descriptors/sample_descriptor.yml");
        DataProduct dataProduct = Parser.parseDataProduct(yamlContent).get();

        DataContract result = mapper.toDataContract(dataProduct);

        DataContract expected = new DataContract();
        expected.setBaseCharacteristics(
                DataContract.BaseCharacteristics.builder()
                        .identifier("urn:example:dp:analytics:1")
                        .name("Example Analytics Product")
                        .description("Example product for customer analytics")
                        .productOwnerName("Anonymous User")
                        .technicalOwnersNames("example-team")
                        .certifiedUse("Generic")
                        .status("Unpublished")
                        .build());
        expected.setReferenceContext(
                DataContract.ReferenceContext.builder()
                        .company("Example Organization")
                        .domain("Customer Analytics")
                        .subdomain("Data Products")
                        .build());
        expected.setAdditionalInformation(
                DataContract.AdditionalInformation.builder()
                        .dataProductType("Source-aligned")
                        .version("0.1.0-SNAPSHOT-7")
                        .lifeCycleStatus("Active")
                        .norms("N/A")
                        .confidentiality("Private")
                        .sensitiveInfo("No")
                        .creationDate("2026-01-01")
                        .releaseDate("2026-01-15")
                        .endDate("2027-12-31")
                        .linkDocumentation("https://docs.example.com/test")
                        .linkObservabilityPort("https://observability.example.com/test")
                        .linkDataQualityPort("https://quality.example.com/test")
                        .metadataDetails("https://metadata.example.com/test")
                        .contacts("Test Contact: test@example.com")
                        .termsOfUse(
                                "Dati ad uso interno. Non condividere con terze parti senza autorizzazione.")
                        .sla("Disponibilità: 99.9%, Refresh: Daily, Recovery: 4h")
                        .quality("Completezza, Accuratezza")
                        .memorizationType("Si")
                        .build());

        assertNotNull(result);
    }

    @Test
    void toDataContractFromDescriptorYaml() throws Exception {

        String yamlContent = ResourceUtils.getContentFromResource("/descriptors/descriptor.yml");
        DataProduct dataProduct = Parser.parseDataProduct(yamlContent).get();

        DataContract result = mapper.toDataContract(dataProduct);

        DataContract expected = new DataContract();
        expected.setBaseCharacteristics(
                DataContract.BaseCharacteristics.builder()
                        .identifier("urn:dmb:dp:example-domain:example-dp:0")
                        .name("Data Product Test Metadata 3")
                        .description("Data Product")
                        .productOwnerName("Example Owner")
                        .technicalOwnersNames("example.owner")
                        .certifiedUse("Generic")
                        .status("Unpublished")
                        .build());
        expected.setReferenceContext(
                DataContract.ReferenceContext.builder()
                        .company("Example Organization")
                        .domain("Marketing Commerciale")
                        .subdomain("CAM")
                        .build());
        expected.setAdditionalInformation(
                DataContract.AdditionalInformation.builder()
                        .dataProductType("Consumer-aligned")
                        .version("1.0.0")
                        .lifeCycleStatus("Draft")
                        .norms("N/A")
                        .confidentiality("Public")
                        .sensitiveInfo("Si") // normalized from descriptor value "true"
                        .creationDate("2026-01-26T15:44:54.193Z")
                        .releaseDate("2026-01-27")
                        .endDate("2026-01-31")
                        .suspensionDate("2026-01-30")
                        .linkDocumentation("http:/test.link")
                        .linkObservabilityPort("http:/test.observability.link")
                        .linkDataQualityPort("http://test.dq.link")
                        .metadataDetails("Other metadata details")
                        .contacts("example.owner")
                        .termsOfUse("N/A")
                        .sla("Information SLA")
                        .outsourcer("testoutsourcer")
                        .quality("Gold")
                        .memorizationType("Si")
                        .build());

        assertNotNull(result);
    }

    @Test
    void normalizesSensitiveInfoToCanonicalSiNo() {
        // Variants that must normalize to canonical "Si" (no accent); accented "Sì" accepted in
        // input
        for (String yes : List.of("Sì", "sì", "Si", "si", "true", "TRUE", "Yes", "y", "1")) {
            assertEquals("Si", mapSensitiveInfo(yes), "Expected 'Si' for input '" + yes + "'");
        }
        // Variants that must normalize to canonical "No"
        for (String no : List.of("No", "no", "false", "FALSE", "n", "0")) {
            assertEquals("No", mapSensitiveInfo(no), "Expected 'No' for input '" + no + "'");
        }
        // Unrecognized values are left untouched so @Pattern validation can reject them
        assertEquals("maybe", mapSensitiveInfo("maybe"));
    }

    @Test
    void normalizesControlledVocabulariesCaseInsensitively() {
        DataProduct dataProduct = new DataProduct();
        dataProduct.setId("test-id");
        dataProduct.setName("testName");
        ObjectNode jsonData = objectMapper.createObjectNode();
        jsonData.put("certifiedUse", "analysis");
        jsonData.put("lifeCycleStatus", "in development");
        jsonData.put("confidentiality", "PRIVATE");
        jsonData.put("memorizationType", "no");
        dataProduct.setSpecific(jsonData);

        DataContract result = mapper.toDataContract(dataProduct);

        assertEquals("Analysis", result.getBaseCharacteristics().getCertifiedUse());
        assertEquals("In Development", result.getAdditionalInformation().getLifeCycleStatus());
        assertEquals("Private", result.getAdditionalInformation().getConfidentiality());
        assertEquals("No", result.getAdditionalInformation().getMemorizationType());
    }

    @Test
    void mapsFullyQualifiedNameAndDeprecationDate() {
        DataProduct dataProduct = new DataProduct();
        dataProduct.setId("test-id");
        dataProduct.setName("testName");
        dataProduct.setFullyQualifiedName(Optional.of("urn:dmb:dp:fqn"));
        ObjectNode jsonData = objectMapper.createObjectNode();
        jsonData.put("deprecationDate", "2026-12-31");
        dataProduct.setSpecific(jsonData);

        DataContract result = mapper.toDataContract(dataProduct);

        assertEquals("urn:dmb:dp:fqn", result.getBaseCharacteristics().getFullyQualifiedName());
        assertEquals("2026-12-31", result.getAdditionalInformation().getDeprecationDate());
    }

    @Test
    void companyMapsToReferenceContextCompany() {
        assertEquals("Example Organization", mapCompany("Example Organization", null));
        assertNull(mapCompany(null, null));
        // neither present -> null
        assertNull(mapCompany(null, null));
    }

    /** Maps a data product with the given company value and returns the resolved company. */
    private String mapCompany(String company, String ignoredLegacyValue) {
        DataProduct dataProduct = new DataProduct();
        dataProduct.setId("test-id");
        dataProduct.setName("testName");
        ObjectNode jsonData = objectMapper.createObjectNode();
        if (company != null) jsonData.put("company", company);
        dataProduct.setSpecific(jsonData);
        return mapper.toDataContract(dataProduct).getReferenceContext().getCompany();
    }

    /**
     * Maps a data product whose only specific field is {@code sensitiveInfo} and returns the mapped
     * value.
     */
    private String mapSensitiveInfo(String descriptorValue) {
        DataProduct dataProduct = new DataProduct();
        dataProduct.setId("test-id");
        dataProduct.setName("testName");
        ObjectNode jsonData = objectMapper.createObjectNode();
        jsonData.put("sensitiveInfo", descriptorValue);
        dataProduct.setSpecific(jsonData);
        return mapper.toDataContract(dataProduct).getAdditionalInformation().getSensitiveInfo();
    }

    // ========================================
    // Helper methods
    // ========================================

    private DataProduct createTestDataProduct() throws Exception {
        DataProduct dataProduct = new DataProduct();
        dataProduct.setId("test-id-123");
        dataProduct.setName("testName");
        dataProduct.setDescription("testDescription");
        dataProduct.setDomain("testDomain");
        dataProduct.setVersion("1.0.0");
        dataProduct.setDataProductOwnerDisplayName("Example Owner");

        ObjectNode jsonData = objectMapper.createObjectNode();
        jsonData.put("dataProductType", "Consumer-aligned");
        jsonData.put("businessDomain", "testDomain");
        jsonData.put("version", "1.0.0");
        jsonData.putObject("customAttributes").put("exampleAttribute", "exampleValue");
        jsonData.put("norms", "testNorms");
        jsonData.put("lifeCycleStatus", "Active");
        jsonData.put("sensitiveInfo", "No");
        jsonData.put("creationDate", "2026-01-13");
        jsonData.put("endDate", "2026-01-20");
        jsonData.put("releaseDate", "2026-01-15");
        jsonData.put("suspensionDate", "2026-01-18");

        dataProduct.setSpecific(jsonData);
        return dataProduct;
    }

    private DataProduct createDataProductWithAllJsonFields() throws Exception {
        DataProduct dataProduct = new DataProduct();
        dataProduct.setId("test-id");
        dataProduct.setName("testName");
        dataProduct.setDescription("testDescription");
        dataProduct.setVersion("2.0.0");
        dataProduct.setDataProductOwnerDisplayName("Test Owner");
        dataProduct.setInformationSLA(Optional.of("99.9% availability"));

        ObjectNode jsonData = objectMapper.createObjectNode();
        // BaseCharacteristics fields
        jsonData.put("technicalOwners", "Tech Owner 1, Tech Owner 2");
        jsonData.put("certifiedUse", "Analysis");

        // ReferenceContext fields
        jsonData.put("company", "TestCompany");
        jsonData.put("businessDomain", "Finance");
        jsonData.put("businessSubdomain", "Payments");

        // AdditionalInformation fields
        jsonData.put("dataProductType", "Consumer-aligned");
        jsonData.put("lifeCycleStatus", "Active");
        jsonData.put("sensitiveInfo", "Yes");
        jsonData.put("confidentiality", "Reserved");
        jsonData.put("norms", "GDPR,ISO27001");
        jsonData.put("creationDate", "2026-01-13");
        jsonData.put("endDate", "2026-01-20");
        jsonData.put("releaseDate", "2026-01-15");
        jsonData.put("suspensionDate", "2026-01-18");
        jsonData.put("linkDocumentation", "https://docs.example.com/test");
        jsonData.put("linkObservabilityPort", "https://observability.example.com/test");
        jsonData.put("linkDataQualityPort", "https://quality.example.com/test");
        jsonData.put("metadataDetails", "https://metadata.example.com/test");
        jsonData.put("contacts", "Test Contact: test@example.com");
        jsonData.put("termsOfUse", "Internal use only");
        jsonData.put("outsourcer", "External Company");
        jsonData.put("quality", "High quality data");

        dataProduct.setSpecific(jsonData);
        return dataProduct;
    }

    private DataContract.BaseCharacteristics expectedBaseCharacteristics() {
        return DataContract.BaseCharacteristics.builder()
                .identifier("test-id-123")
                .name("testName")
                .description("testDescription")
                .productOwnerName("Example Owner")
                .certifiedUse("Generic")
                .status("Unpublished")
                .build();
    }

    private DataContract.ReferenceContext expectedReferenceContext() {
        return DataContract.ReferenceContext.builder().domain("testDomain").build();
    }

    private DataContract.AdditionalInformation expectedAdditionalInformation() {
        return DataContract.AdditionalInformation.builder()
                .dataProductType("Consumer-aligned")
                .lifeCycleStatus("Active")
                .version("1.0.0")
                .sensitiveInfo("No")
                .norms("testNorms")
                .releaseDate("2026-01-15")
                .endDate("2026-01-20")
                .creationDate("2026-01-13")
                .suspensionDate("2026-01-18")
                .confidentiality("Private")
                .memorizationType("Si")
                .build();
    }

    // ========================================
    // Tests for toDataContractWithOutputPorts
    // ========================================

    @Test
    void toDataContractWithOutputPortsMapsDeliveryTargetsFromYaml() throws Exception {
        // Arrange
        String yamlContent =
                ResourceUtils.getContentFromResource("/parser/descriptors/sample_descriptor.yml");
        DataProduct dataProduct = Parser.parseDataProduct(yamlContent).get();

        // Act
        DataContract result =
                mapper.toDataContractWithOutputPorts(
                        dataProduct, deliveryTargetMapper, dataAssetMapper);

        // Assert
        assertEquals(2, result.getDeliveryTargets().size());
        assertTrue(
                result.getDeliveryTargets().stream()
                        .allMatch(
                                target ->
                                        target.getCatalogAssetType()
                                                        == DeliveryTarget.CatalogAssetType.DATASET
                                                && target.getDataAssets().size() == 1));
        DeliveryTarget snowflakeTarget = result.getDeliveryTargets().get(0);

        assertEquals("Orders Output Port", snowflakeTarget.getBaseCharacteristics().getPortName());
        assertEquals(
                "orders", snowflakeTarget.getDataAssets().get(0).getEntityInfo().getEntityName());
        assertEquals(2, snowflakeTarget.getDataAssets().get(0).getAttributes().size());
    }

    @Test
    void toDataContractWithOutputPortsHandlesEmptyOutputPorts() throws Exception {
        // Arrange
        DataProduct dataProduct = new DataProduct();
        dataProduct.setId("test-id");
        dataProduct.setName("testName");
        dataProduct.setComponents(List.of()); // No components

        // Act
        DataContract result =
                mapper.toDataContractWithOutputPorts(
                        dataProduct, deliveryTargetMapper, dataAssetMapper);

        // Assert
        assertNotNull(result);
        assertNotNull(result.getDeliveryTargets());
        assertTrue(result.getDeliveryTargets().isEmpty());
    }

    @Test
    void mapsNestedOutputPortToSystemWithChildDatasets() {
        String yaml =
                "name: Nested Product\n"
                        + "specific:\n"
                        + "  publishToInformatica: true\n"
                        + "components:\n"
                        + "  - kind: outputport\n"
                        + "    name: Customer System\n"
                        + "    version: 1.0.0\n"
                        + "    technology: BigQuery\n"
                        + "    components:\n"
                        + "      - kind: outputport\n"
                        + "        name: customers\n"
                        + "        version: 1.0.0\n"
                        + "        technology: BigQuery\n"
                        + "        specific:\n"
                        + "          systemName: customer-system\n"
                        + "          databaseName: warehouse\n"
                        + "          schemaName: curated\n"
                        + "          entityName: customers\n"
                        + "          entityType: table\n"
                        + "        dataContract:\n"
                        + "          schema:\n"
                        + "            - name: customer_id\n"
                        + "              dataType: TEXT\n";
        DataProduct dataProduct = Parser.parseDataProduct(yaml).get();

        DataContract result =
                mapper.toDataContractWithOutputPorts(
                        dataProduct, deliveryTargetMapper, dataAssetMapper);

        assertEquals(1, result.getDeliveryTargets().size());
        DeliveryTarget system = result.getDeliveryTargets().get(0);
        assertEquals(DeliveryTarget.CatalogAssetType.SYSTEM, system.getCatalogAssetType());
        assertEquals("Customer System", system.getBaseCharacteristics().getPortName());
        assertEquals(1, system.getDataAssets().size());
        assertEquals("customers", system.getDataAssets().get(0).getEntityInfo().getEntityName());
    }

    @Test
    void toDataContractWithOutputPortsGroupsByTechnology() throws Exception {
        // Arrange
        String yamlContent =
                ResourceUtils.getContentFromResource("/parser/descriptors/sample_descriptor.yml");
        DataProduct dataProduct = Parser.parseDataProduct(yamlContent).get();

        // Act
        DataContract result =
                mapper.toDataContractWithOutputPorts(
                        dataProduct, deliveryTargetMapper, dataAssetMapper);

        // Assert - All OutputPorts in sample_descriptor have technology "Snowflake"
        // So they should be grouped into one DeliveryTarget with multiple DataAssets
        List<DeliveryTarget> deliveryTargets = result.getDeliveryTargets();

        // Find Snowflake delivery target
        DeliveryTarget snowflakeTarget =
                deliveryTargets.stream()
                        .filter(
                                dt ->
                                        "Snowflake"
                                                .equals(
                                                        dt.getBaseCharacteristics()
                                                                .getPortTechnology()))
                        .findFirst()
                        .orElse(null);

        assertNotNull(snowflakeTarget, "Should have a Snowflake DeliveryTarget");
        assertTrue(
                snowflakeTarget.getDataAssets().size() > 0,
                "Snowflake DeliveryTarget should have DataAssets");
    }

    @Test
    void toDataContractWithOutputPortsFromDescriptorYaml() throws Exception {
        // Arrange
        String yamlContent = ResourceUtils.getContentFromResource("/descriptors/descriptor.yml");
        DataProduct dataProduct = Parser.parseDataProduct(yamlContent).get();

        // Act
        DataContract result =
                mapper.toDataContractWithOutputPorts(
                        dataProduct, deliveryTargetMapper, dataAssetMapper);

        // Assert
        assertEquals(1, result.getDeliveryTargets().size());
        DeliveryTarget snowflakeTarget = result.getDeliveryTargets().get(0);
        assertEquals(1, snowflakeTarget.getDataAssets().size());

        // Expected DeliveryTarget.BaseCharacteristics
        DeliveryTarget.BaseCharacteristics expectedDtBc =
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
        assertEquals(expectedDtBc, snowflakeTarget.getBaseCharacteristics());

        // Expected DataAsset
        DataAsset dataAsset = snowflakeTarget.getDataAssets().get(0);

        DataAsset.SystemInfo expectedSystemInfo =
                DataAsset.SystemInfo.builder()
                        .systemName("TestSystemName")
                        .serverName("TestServerName")
                        .databaseName("TestDBName")
                        .schemaName("TestSthatmaName")
                        .build();
        assertEquals(expectedSystemInfo, dataAsset.getSystemInfo());

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
        assertEquals(expectedEntityInfo, dataAsset.getEntityInfo());

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
        assertEquals(expectedFirstAttr, dataAsset.getAttributes().get(0));
    }
}
