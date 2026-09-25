package com.witboost.plugin.informatica.marketplace.service;

import static org.junit.jupiter.api.Assertions.*;

import com.witboost.plugin.informatica.common.utils.ResourceUtils;
import com.witboost.plugin.informatica.marketplace.openapi.model.ProvisioningResultRequest;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@Slf4j
@SpringBootTest
@ActiveProfiles("test")
@Disabled("Integration tests - requires real Informatica API credentials and network access")
public class MarketplaceServiceIntegrationTest {

    @Autowired private MarketplaceService marketplaceService;

    private ProvisioningResultRequest provisioningResultRequest;

    @BeforeEach
    void setUp() throws IOException {
        log.info("Setting up integration test for DataCatalogService");
        provisioningResultRequest = new ProvisioningResultRequest();
    }

    @Test
    @DisplayName(
            "insertProvisioningResults() should complete successfully with valid data product descriptor")
    void testInsertProvisioning_WithValidDescriptor_ShouldCompleteSuccessfully()
            throws IOException {
        log.info("Loading test data product descriptor");
        String testDescriptorName = System.getenv("TEST_DESCRIPTOR_NAME");
        if (testDescriptorName == null) {
            testDescriptorName = "descriptor_integration_test.yml";
        }
        log.info("Loading test data product descriptor " + testDescriptorName);
        String descriptorContent =
                ResourceUtils.getContentFromResource("/descriptors/" + testDescriptorName);
        provisioningResultRequest.setDescriptor(descriptorContent);

        log.info("Descriptor loaded, provisioning length: {} bytes", descriptorContent.length());

        var status = marketplaceService.insertProvisioningResults(provisioningResultRequest);

        assertNotNull(status, "ProvisioningStatus should not be null");
        assertNotNull(status.getStatus(), "Status should not be null");
        assertEquals("COMPLETED", status.getStatus());

        System.out.println(status);
    }

    @Test
    @DisplayName("delete() should complete successfully with valid data product descriptor")
    void testDelete_WithValidDescriptor_ShouldCompleteSuccessfully() throws IOException {
        log.info("Loading test data product descriptor");
        String testDescriptorName = System.getenv("TEST_DESCRIPTOR_NAME");
        if (testDescriptorName == null) {
            testDescriptorName = "descriptor_integration_test.yml";
        }
        log.info("Loading test data product descriptor " + testDescriptorName);
        String descriptorContent =
                ResourceUtils.getContentFromResource("/descriptors/" + testDescriptorName);
        provisioningResultRequest.setDescriptor(descriptorContent);

        log.info("Descriptor loaded, provisioning length: {} bytes", descriptorContent.length());

        var status = marketplaceService.delete(provisioningResultRequest);

        assertNotNull(status, "ProvisioningStatus should not be null");

        System.out.println(status);
    }
}
