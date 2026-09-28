package com.witboost.plugin.informatica.marketplace.service;

import static org.junit.jupiter.api.Assertions.*;

import com.witboost.plugin.informatica.common.exceptions.ApiCallException;
import com.witboost.plugin.informatica.common.model.informatica.DataContract;
import com.witboost.plugin.informatica.marketplace.common.Constants;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

/**
 * Integration tests for DataCollectionService with real Informatica API interactions.
 *
 * <p>These tests interact with the actual Informatica Marketplace API to verify the complete flow
 * of creating and updating data collections including custom attributes.
 *
 * <p><b>Prerequisites:</b>
 *
 * <ul>
 *   <li>Valid Informatica credentials configured in application-test.yml
 *   <li>Network access to Informatica Marketplace API
 *   <li>Test environment with proper permissions
 * </ul>
 *
 * <p><b>Note:</b> These tests should be run manually or in a dedicated integration test phase, not
 * in the standard unit test suite.
 */
@Slf4j
@SpringBootTest
@ActiveProfiles("test")
@Disabled("Integration tests - requires real Informatica API credentials and network access")
class DataCollectionServiceIntegrationTest {

    @Autowired private DataCollectionService dataCollectionService;

    @Autowired private CustomAttributeService customAttributeService;

    private DataContract testDataContract;
    private Map<String, String> customAttributesMap;

    @BeforeEach
    void setUp() {
        log.info("Setting up integration test");

        // Load custom attributes map from Informatica
        customAttributesMap = customAttributeService.getDataCollectionCustomAttributesNameId();
        assertNotNull(customAttributesMap, "Custom attributes map should be loaded");
        assertFalse(customAttributesMap.isEmpty(), "Custom attributes map should not be empty");

        log.info("Loaded {} custom attributes from Informatica", customAttributesMap.size());
    }

    @Test
    void createCollection() {
        testDataContract = createCompleteDataContractForCreate();
        assertDoesNotThrow(
                () -> dataCollectionService.createCollection(testDataContract),
                "Collection creation should not throw any exception");
    }

    /**
     * Integration Test: Create a new collection in Informatica Marketplace
     *
     * <p>This test creates a complete data collection with all custom attributes and verifies that
     * it was successfully created in Informatica.
     *
     * <p><b>Test Steps:</b>
     *
     * <ol>
     *   <li>Create a complete DataContract with all required fields
     *   <li>Call createCollection to create it in Informatica
     *   <li>Verify that the collection exists using collectionExists
     *   <li>Clean up: Delete the test collection (if needed)
     * </ol>
     */
    @Test
    void testCreateCollection_WithCompleteDataContract_ShouldSucceedInInformatica() {
        // Arrange
        testDataContract = createCompleteDataContractForCreate();

        String collectionName = testDataContract.getBaseCharacteristics().getName();

        // Ensure collection doesn't exist before test
        if (dataCollectionService.collectionExists(collectionName)) {
            log.warn("Collection '{}' already exists, skipping creation test", collectionName);
            return;
        }

        try {
            // Act
            log.info("Creating collection '{}' in Informatica", collectionName);
            var id = dataCollectionService.createCollection(testDataContract);

            // Assert
            assertNotNull(id, "Collection should be created successfully");
            assertTrue(
                    dataCollectionService.collectionExists(collectionName),
                    "Collection should exist in Informatica after creation");

            log.info("✓ Collection '{}' created successfully in Informatica", collectionName);

        } catch (Exception e) {
            log.error("Failed to create collection: {}", e.getMessage(), e);
            fail("Collection creation failed: " + e.getMessage());
        }
    }

    /**
     * Integration Test: Update an existing collection in Informatica Marketplace
     *
     * <p>This test updates a data collection with new custom attribute values and verifies that the
     * changes are persisted in Informatica.
     *
     * <p><b>Test Steps:</b>
     *
     * <ol>
     *   <li>Create a complete DataContract with all fields populated
     *   <li>Ensure the collection exists in Informatica (or create it)
     *   <li>Call updateCollection to update summary and custom attributes
     *   <li>Verify that the update was successful
     *   <li>Optionally: Retrieve and verify the updated values
     * </ol>
     */
    @Test
    void testUpdateCollection_WithCompleteDataContract_ShouldSucceedInInformatica() {
        // Arrange
        testDataContract = createCompleteDataContractForUpdate();
        String collectionName = testDataContract.getBaseCharacteristics().getName();

        // Ensure collection exists before update
        if (!dataCollectionService.collectionExists(collectionName)) {
            log.info("Collection '{}' doesn't exist, creating it first", collectionName);
            var id = dataCollectionService.createCollection(testDataContract);
            assertNotNull(id, "Collection should be created before update test");
        }

        try {
            // Act
            log.info("Updating collection '{}' in Informatica", collectionName);
            assertDoesNotThrow(
                    () -> dataCollectionService.updateCollection(testDataContract),
                    "Update should not throw any exception");

            log.info("✓ Collection '{}' updated successfully in Informatica", collectionName);

            // Verify the update was successful
            assertTrue(
                    dataCollectionService.collectionExists(collectionName),
                    "Collection should still exist after update");

        } catch (Exception e) {
            log.error("Failed to update collection: {}", e.getMessage(), e);
            fail("Collection update failed: " + e.getMessage());
        }
    }

    /**
     * Integration Test: Update custom attributes only
     *
     * <p>This test verifies that custom attributes can be updated independently without changing
     * summary information.
     */
    @Test
    void testUpdateCollection_CustomAttributesOnly_ShouldSucceedInInformatica() {
        // Arrange
        testDataContract = createCompleteDataContractForUpdate();

        // Modify only custom attribute values
        testDataContract.getAdditionalInformation().setVersion("3.0.0");
        testDataContract.getAdditionalInformation().setLifeCycleStatus("In Development");
        testDataContract
                .getAdditionalInformation()
                .setQuality(
                        "<p>Completeness: 99%</p><p>Accuracy: 99.5%</p><p>Timeliness: Real-time</p>");

        String collectionName = testDataContract.getBaseCharacteristics().getName();

        try {
            // Act
            log.info("Updating custom attributes for collection '{}'", collectionName);
            assertDoesNotThrow(() -> dataCollectionService.updateCollection(testDataContract));

            log.info("✓ Custom attributes updated successfully");

        } catch (Exception e) {
            log.error("Failed to update custom attributes: {}", e.getMessage(), e);
            fail("Custom attributes update failed: " + e.getMessage());
        }
    }

    /**
     * Integration Test: Verify custom attribute values mapping
     *
     * <p>This test verifies that the DataContract.getCustomAttributeValues method correctly maps
     * field values to Informatica custom attribute IDs.
     */
    @Test
    void testGetCustomAttributeValues_WithCompleteDataContract_ShouldReturnCorrectMapping() {
        // Arrange
        testDataContract = createCompleteDataContractForUpdate();

        // Act
        Map<String, Object> customAttributeValues =
                testDataContract.getCustomAttributeValues(customAttributesMap);

        // Assert
        assertNotNull(customAttributeValues, "Custom attribute values map should not be null");
        assertFalse(
                customAttributeValues.isEmpty(), "Custom attribute values map should not be empty");

        log.info("Mapped {} custom attribute values", customAttributeValues.size());

        // Verify some key mappings
        assertTrue(
                customAttributeValues.containsKey(customAttributesMap.get("Product Owner")),
                "Should contain Product Owner mapping");
        assertTrue(
                customAttributeValues.containsKey(customAttributesMap.get("Versione")),
                "Should contain Versione mapping");
        assertTrue(
                customAttributeValues.containsKey(customAttributesMap.get("Life cycle status")),
                "Should contain Life cycle status mapping");

        // Verify values are correct
        assertEquals(
                "Example Owner",
                customAttributeValues.get(customAttributesMap.get("Product Owner")));
        assertEquals("2.1.0", customAttributeValues.get(customAttributesMap.get("Versione")));
        assertEquals(
                "Active", customAttributeValues.get(customAttributesMap.get("Life cycle status")));

        log.info("✓ Custom attribute values mapping verified successfully");
    }

    /** Integration Test: Verify null values are excluded from custom attributes */
    @Test
    void testGetCustomAttributeValues_WithNullValues_ShouldExcludeNulls() {
        // Arrange
        testDataContract = createMinimalDataContract();

        // Act
        Map<String, Object> customAttributeValues =
                testDataContract.getCustomAttributeValues(customAttributesMap);

        // Assert
        assertNotNull(customAttributeValues);

        // Verify that null values are not included
        customAttributeValues.forEach(
                (id, value) -> {
                    assertNotNull(value, "Custom attribute values should not contain null values");
                });

        log.info("✓ Null values correctly excluded from custom attributes mapping");
    }

    /** Integration Test: Verify error handling for invalid collection name */
    @Test
    void testUpdateCollection_WithInvalidIdentifier_ShouldThrowException() {
        // Arrange
        testDataContract = createCompleteDataContractForUpdate();
        testDataContract.getBaseCharacteristics().setIdentifier("invalid-non-existing-id-12345");

        // Act & Assert
        assertThrows(
                ApiCallException.class,
                () -> {
                    dataCollectionService.updateCollection(testDataContract);
                },
                "Should throw ApiCallException for invalid collection identifier");

        log.info("✓ Error handling verified for invalid identifier");
    }

    // ==================== Helper Methods ====================

    /**
     * Helper method to create a complete DataContract for creation testing. Includes all required
     * fields and custom attributes with correct default values.
     */
    private DataContract createCompleteDataContractForCreate() {
        DataContract dataContract = new DataContract();

        // Base Characteristics
        DataContract.BaseCharacteristics baseChars =
                DataContract.BaseCharacteristics.builder()
                        .identifier("test-collection-create-" + System.currentTimeMillis())
                        .name("Test Sales Data Product " + System.currentTimeMillis())
                        .description("Integration test data product for sales data aggregation")
                        .productOwnerName("Example Owner")
                        .technicalOwnersNames("Example Steward")
                        .certifiedUse("Generic")
                        .status(Constants.DATA_COLLECTION_PUBLISHED_STATUS)
                        .build();

        // Reference Context
        DataContract.ReferenceContext refContext =
                DataContract.ReferenceContext.builder()
                        .company("Example Organization")
                        .domain("Altro")
                        .subdomain("Generale")
                        .build();

        // Additional Information - Using correct default values
        DataContract.AdditionalInformation additionalInfo =
                DataContract.AdditionalInformation.builder()
                        .lifeCycleStatus(
                                "Draft") // Valid: Draft, Proposed, In Development, Active, Retired,
                        // Deprecated
                        .version("1.0.0")
                        .sensitiveInfo("No") // Valid: Sì, No
                        .confidentiality(
                                "Public") // Valid: Default (with space), Public, Private, Reserved
                        .creationDate("2025-01-01")
                        .memorizationType("Yes") // Valid: Yes, No, In identification
                        .dataProductType("Source-aligned") // Valid: Source-aligned, Aggregate,
                        // Consumer-aligned
                        .build();

        dataContract.setBaseCharacteristics(baseChars);
        dataContract.setReferenceContext(refContext);
        dataContract.setAdditionalInformation(additionalInfo);

        return dataContract;
    }

    /**
     * Helper method to create a complete DataContract for update testing. Includes all required
     * fields and custom attributes with correct default values.
     */
    private DataContract createCompleteDataContractForUpdate() {
        DataContract dataContract = new DataContract();

        // Base Characteristics
        DataContract.BaseCharacteristics baseChars =
                DataContract.BaseCharacteristics.builder()
                        .identifier("collection-123-abc")
                        .name("Sales Data Product")
                        .description(
                                "Comprehensive sales data aggregated from multiple sources including CRM, ERP, and legacy systems")
                        .productOwnerName("Example Owner")
                        .technicalOwnersNames("Example Owner, Example Steward")
                        .certifiedUse("Analysis")
                        .status(Constants.DATA_COLLECTION_PUBLISHED_STATUS)
                        .build();

        // Reference Context
        DataContract.ReferenceContext refContext =
                DataContract.ReferenceContext.builder()
                        .company("Example Organization")
                        .domain("Commerciale")
                        .subdomain("Vendite")
                        .build();

        // Additional Information - Using correct default values
        DataContract.AdditionalInformation additionalInfo =
                DataContract.AdditionalInformation.builder()
                        .lifeCycleStatus(
                                "Draft") // Valid: Draft, Proposed, In Development, Active, Retired,
                        // Deprecated
                        .version("1.0.0")
                        .sensitiveInfo("No") // Valid: Sì, No
                        .confidentiality(
                                "Public") // Valid: Default (with space), Public, Private, Reserved
                        .creationDate("2025-01-01")
                        .memorizationType("Yes") // Valid: Yes, No, In identification
                        .dataProductType("Source-aligned") // Valid: Source-aligned, Aggregate,
                        // Consumer-aligned
                        .build();

        dataContract.setBaseCharacteristics(baseChars);
        dataContract.setReferenceContext(refContext);
        dataContract.setAdditionalInformation(additionalInfo);

        return dataContract;
    }

    /**
     * Helper method to create a minimal DataContract for basic testing. Contains only mandatory
     * fields with correct default values.
     */
    private DataContract createMinimalDataContract() {
        DataContract dataContract = new DataContract();

        DataContract.BaseCharacteristics baseChars =
                DataContract.BaseCharacteristics.builder()
                        .identifier("minimal-123")
                        .name("Minimal Test Collection")
                        .description("Minimal data contract for basic testing")
                        .productOwnerName("Test Owner")
                        .technicalOwnersNames("Test Tech Owner")
                        .certifiedUse("Generic") // Default: Generic
                        .status(Constants.DATA_COLLECTION_PUBLISHED_STATUS)
                        .build();

        DataContract.ReferenceContext refContext =
                DataContract.ReferenceContext.builder()
                        .company("Test Company")
                        .domain("Test Domain")
                        .subdomain("Test Subdomain")
                        .build();

        DataContract.AdditionalInformation additionalInfo =
                DataContract.AdditionalInformation.builder()
                        .lifeCycleStatus(
                                "Draft") // Valid: Draft, Proposed, In Development, Active, Retired,
                        // Deprecated
                        .version("1.0.0")
                        .sensitiveInfo("No") // Valid: Sì, No
                        .confidentiality(
                                "Public") // Valid: Default (with space), Public, Private, Reserved
                        .creationDate("2025-01-01")
                        .memorizationType("Yes") // Valid: Yes, No, In identification
                        .dataProductType("Source-aligned") // Valid: Source-aligned, Aggregate,
                        // Consumer-aligned
                        .build();

        dataContract.setBaseCharacteristics(baseChars);
        dataContract.setReferenceContext(refContext);
        dataContract.setAdditionalInformation(additionalInfo);

        return dataContract;
    }
}
