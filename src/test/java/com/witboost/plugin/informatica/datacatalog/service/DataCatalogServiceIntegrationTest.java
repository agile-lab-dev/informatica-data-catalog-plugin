package com.witboost.plugin.informatica.datacatalog.service;

import static org.junit.jupiter.api.Assertions.*;

import com.witboost.plugin.informatica.common.utils.ResourceUtils;
import com.witboost.plugin.informatica.datacatalog.openapi.model.ProvisioningRequest;
import com.witboost.plugin.informatica.datacatalog.openapi.model.ProvisioningStatus;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

/**
 * Integration tests for DataCatalogServiceImpl with real Informatica API interactions.
 *
 * <p>These tests interact with the actual Informatica Data Catalog API to verify the complete flow
 * of provisioning data products including:
 *
 * <ul>
 *   <li>Data product descriptor parsing and validation
 *   <li>Data contract mapping from data product
 *   <li>System and dataset creation in Informatica Data Catalog
 *   <li>Technical data element linking
 * </ul>
 *
 * <p><b>Prerequisites:</b>
 *
 * <ul>
 *   <li>Valid Informatica credentials configured in application-test.yml
 *   <li>Network access to Informatica Data Catalog API
 *   <li>Test environment with proper permissions
 *   <li>Sample data product descriptors in test resources
 * </ul>
 *
 * <p><b>Note:</b> These tests should be run manually or in a dedicated integration test phase, not
 * in the standard unit test suite. Tests are disabled by default and must be explicitly enabled by
 * removing the @Disabled annotation when running integration tests.
 *
 * <p><b>To run integration tests:</b>
 *
 * <pre>
 * mvn test -Dtest=DataCatalogServiceIntegrationTest
 * </pre>
 */
@Slf4j
@SpringBootTest
@ActiveProfiles("test")
@Disabled("Integration tests - requires real Informatica API credentials and network access")
class DataCatalogServiceIntegrationTest {

    @Autowired private DataCatalogServiceImpl dataCatalogService;

    private ProvisioningRequest provisioningRequest;

    @BeforeEach
    void setUp() throws IOException {
        log.info("Setting up integration test for DataCatalogService");
        provisioningRequest = new ProvisioningRequest();
    }

    /**
     * Integration Test: Provision a data product with valid descriptor
     *
     * <p>This test verifies the complete provisioning flow:
     *
     * <ol>
     *   <li>Parse a valid data product descriptor
     *   <li>Validate the parsed data product
     *   <li>Map the data product to a data contract
     *   <li>Create systems and datasets in Informatica Data Catalog
     *   <li>Link technical data elements
     *   <li>Verify the provisioning completed successfully
     * </ol>
     *
     * @throws IOException if unable to read test descriptor file
     */
    @Test
    @DisplayName("provision() should complete successfully with valid data product descriptor")
    void testProvision_WithValidDescriptor_ShouldCompleteSuccessfully() throws IOException {
        // Arrange
        String testDescriptorName = System.getenv("TEST_DESCRIPTOR_NAME");
        if (testDescriptorName == null) {
            testDescriptorName = "descriptor_integration_test.yml";
        }
        log.info("Loading test data product descriptor " + testDescriptorName);
        String descriptorContent =
                ResourceUtils.getContentFromResource("/descriptors/" + testDescriptorName);
        provisioningRequest.setDescriptor(descriptorContent);

        log.info("Descriptor loaded, provisioning length: {} bytes", descriptorContent.length());

        // Act
        log.info("Starting provision operation");
        ProvisioningStatus status = dataCatalogService.provision(provisioningRequest);

        // Assert
        assertNotNull(status, "ProvisioningStatus should not be null");
        assertNotNull(status.getStatus(), "Status should not be null");

        if (status.getStatus() == ProvisioningStatus.StatusEnum.COMPLETED) {
            log.info("✓ Provisioning completed successfully");
            log.info("Provisioning result: {}", status.getResult());
            assertEquals(
                    ProvisioningStatus.StatusEnum.COMPLETED,
                    status.getStatus(),
                    "Provisioning status should be COMPLETED");
        } else if (status.getStatus() == ProvisioningStatus.StatusEnum.FAILED) {
            log.error("✗ Provisioning failed with status: {}", status.getResult());
            fail(
                    "Provisioning should complete successfully but failed with message: "
                            + status.getResult());
        } else {
            log.warn("Provisioning status: {}", status.getStatus());
        }
    }

    /**
     * Integration Test: Unprovision a data product with valid descriptor
     *
     * @throws IOException if unable to read test descriptor file
     */
    @Test
    @DisplayName("unprovision() should complete successfully with valid data product descriptor")
    void testUnprovision_WithValidDescriptor_ShouldCompleteSuccessfully() throws IOException {
        // Arrange
        String testDescriptorName = System.getenv("TEST_DESCRIPTOR_NAME");
        if (testDescriptorName == null) {
            testDescriptorName = "descriptor_integration_test.yml";
        }
        log.info("Loading test data product descriptor " + testDescriptorName);
        String descriptorContent =
                ResourceUtils.getContentFromResource("/descriptors/" + testDescriptorName);
        provisioningRequest.setDescriptor(descriptorContent);

        log.info("Descriptor loaded, unprovisioning length: {} bytes", descriptorContent.length());

        // Act
        log.info("Starting unprovision operation");
        ProvisioningStatus status = dataCatalogService.unprovision(provisioningRequest);

        // Assert
        assertNotNull(status, "ProvisioningStatus should not be null");
        assertNotNull(status.getStatus(), "Status should not be null");

        if (status.getStatus() == ProvisioningStatus.StatusEnum.COMPLETED) {
            log.info("✓ Unprovisioning completed successfully");
            log.info("Unprovisioning result: {}", status.getResult());
            assertEquals(
                    ProvisioningStatus.StatusEnum.COMPLETED,
                    status.getStatus(),
                    "Unprovisioning status should be COMPLETED");
        } else if (status.getStatus() == ProvisioningStatus.StatusEnum.FAILED) {
            log.error("✗ Unprovisioning failed with status: {}", status.getResult());
            fail(
                    "Unprovisioning should complete successfully but failed with message: "
                            + status.getResult());
        } else {
            log.warn("Unprovisioning status: {}", status.getStatus());
        }
    }
}
