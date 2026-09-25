package com.witboost.plugin.informatica.marketplace.service.client;

import static org.junit.jupiter.api.Assertions.*;

import com.witboost.plugin.informatica.marketplace.common.Constants;
import com.witboost.plugin.informatica.marketplace.model.*;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@Slf4j
@SpringBootTest
@ActiveProfiles("test")
@Disabled("Integration tests - requires real Informatica API credentials and network access")
public class DeliveryTargetApiClientTest {

    @Autowired DeliveryTargetApiClient deliveryTargetApiClient;

    @Autowired DataCollectionApiClient dataCollectionApiClient;

    @Test
    public void getDeliveryTemplate() {
        var res =
                deliveryTargetApiClient.getDeliveryTarget(
                        "384c5765-4f9d-496d-81a3-610de80393d1", "all");
        System.out.println(res);
    }

    @Test
    public void getDeliveryTemplates() {
        log.info("========================================");
        log.info("Testing getDeliveryTemplates");
        log.info("========================================");

        var request = new GetDeliveryTemplatesRequest();
        var response = deliveryTargetApiClient.getAllDeliveryTemplatesPaginated(request);

        assertNotNull(response, "Response should not be null");
        assertFalse(response.isEmpty(), "Response should contain delivery templates");

        log.info("Total Delivery Templates found: {}", response.size());
        log.info("");

        for (int i = 0; i < response.size(); i++) {
            GetDeliveryTemplatesResponse.DeliveryTemplate template = response.get(i);
            log.info("┌─────────────────────────────────────────────────────────");
            log.info("│ Delivery Template #{}", i + 1);
            log.info("├─────────────────────────────────────────────────────────");
            log.info("│ ID:                  {}", template.getId());
            log.info("│ Name:                {}", template.getName());
            log.info("│ Description:         {}", template.getDescription());
            log.info("│ Status:              {}", template.getStatus());
            log.info("│ Delivery Type:       {}", template.getDeliveryType());
            log.info("│ Managed Access:      {}", template.getManagedAccess());
            log.info("│ Is Default:          {}", template.getIsDefault());
            log.info("│ Target System Ref:   {}", template.getTargetSystemReference());
            log.info("│ Physical Location:   {}", template.getDefaultPhysicalLocation());
            log.info("│ Created By:          {}", template.getCreatedBy());
            log.info("│ Created On:          {}", template.getCreatedOn());
            log.info("│ Modified By:         {}", template.getModifiedBy());
            log.info("│ Modified On:         {}", template.getModifiedOn());
            log.info("└─────────────────────────────────────────────────────────");
            log.info("");
        }

        log.info("========================================");
        log.info("Test completed successfully");
        log.info("========================================");
    }

    @Test
    public void getDeliveryTargets() {
        log.info("========================================");
        log.info("Testing getDeliveryTargets");
        log.info("========================================");

        var response = deliveryTargetApiClient.getAllDeliveryTargets(null, "all");

        assertNotNull(response, "Response should not be null");
        assertNotNull(response.getItems(), "Response items should not be null");

        log.info("Total Delivery Targets found: {}", response.getItems().size());
        log.info("");

        for (int i = 0; i < response.getItems().size(); i++) {
            DeliveryTarget target = response.getItems().get(i);
            log.info("┌─────────────────────────────────────────────────────────");
            log.info("│ Delivery Target #{}", i + 1);
            log.info("├─────────────────────────────────────────────────────────");
            log.info("│ ID:                  {}", target.getId());
            log.info("│ Name:                {}", target.getName());
            log.info("│ Description:         {}", target.getDescription());
            log.info("│ Status:              {}", target.getStatus());
            log.info("│ Is Default:          {}", target.getIsDefault());
            log.info("");
        }

        log.info("========================================");
        log.info("Test completed successfully");
        log.info("========================================");
    }

    @Test
    public void createUpdateDeleteDeliveryTarget() {
        log.info("========================================");
        log.info("Testing Create/Update/Delete Delivery Target");
        log.info("========================================");

        // Get prerequisites
        log.info("Step 1: Fetching data collections and delivery templates...");
        List<DataCollection> dataCollections =
                dataCollectionApiClient.getAllCollectionsPaginated(null);
        List<GetDeliveryTemplatesResponse.DeliveryTemplate> deliveryTemplates =
                deliveryTargetApiClient.getAllDeliveryTemplatesPaginated(
                        GetDeliveryTemplatesRequest.builder().build());

        log.info(
                "Found {} data collections and {} delivery templates",
                dataCollections.size(),
                deliveryTemplates.size());
        log.info("");

        // Create delivery target
        log.info("Step 2: Creating new delivery target...");
        CreateDeliveryTargetRequest request = new CreateDeliveryTargetRequest();
        request.setName("test-delivery-target-agilelab-xyz");
        request.setDescription("Test delivery target");
        request.setStatus(Constants.DELIVERY_TARGET_ACTIVE_STATUS);
        request.setDataCollectionId(dataCollections.get(0).getId());
        request.setDeliveryTemplateId(deliveryTemplates.get(0).getId());

        CreateDeliveryTargetResponse createDeliveryTargetResponse =
                deliveryTargetApiClient.createDeliveryTarget(request);
        String deliveryTargetId = createDeliveryTargetResponse.getId();

        log.info("✓ Delivery Target created successfully!");
        log.info("  ID:          {}", deliveryTargetId);
        log.info("  Name:        {}", request.getName());
        log.info("  Status:      {}", request.getStatus());
        log.info("");

        // Update summary (name and description)
        log.info("Step 3: Updating delivery target summary...");
        deliveryTargetApiClient.updateDeliveryTarget(
                deliveryTargetId,
                UpdateDeliveryTargetRequest.builder()
                        .operation("replace")
                        .segment("summary")
                        .value(
                                UpdateDeliveryTargetRequest.SummaryValue.builder()
                                        .name("test-delivery-target-agilelab-xyz-updated")
                                        .description("Updated description")
                                        .build())
                        .build());
        log.info("✓ Summary updated successfully");
        log.info("  New Name:    test-delivery-target-agilelab-xyz-updated");
        log.info("  Description: Updated description");
        log.info("");

        // Update delivery template ID
        log.info("Step 4: Updating delivery template...");
        deliveryTargetApiClient.updateDeliveryTarget(
                deliveryTargetId,
                UpdateDeliveryTargetRequest.builder()
                        .operation("replace")
                        .segment("deliveryTemplate")
                        .value(
                                UpdateDeliveryTargetRequest.DeliveryTemplateValue.builder()
                                        .deliveryTemplateId(deliveryTemplates.get(1).getId())
                                        .build())
                        .build());
        log.info("✓ Delivery template updated successfully");
        log.info("  New Template ID: {}", deliveryTemplates.get(1).getId());
        log.info("");

        // Update self attributes
        log.info("Step 5: Updating self attributes...");
        deliveryTargetApiClient.updateDeliveryTarget(
                deliveryTargetId,
                UpdateDeliveryTargetRequest.builder()
                        .operation("replace")
                        .segment("selfAttributes")
                        .value(
                                UpdateDeliveryTargetRequest.SelfAttributesValue.builder()
                                        .isDefault(true)
                                        .status("INACTIVE")
                                        .build())
                        .build());
        log.info("✓ Self attributes updated successfully");
        log.info("  Is Default:  true");
        log.info("  Status:      INACTIVE");
        log.info("");

        // Delete delivery target
        log.info("Step 6: Deleting delivery target...");
        deliveryTargetApiClient.deleteDeliveryTarget(deliveryTargetId);
        log.info("✓ Delivery target deleted successfully");
        log.info("  Deleted ID:  {}", deliveryTargetId);
        log.info("");

        log.info("========================================");
        log.info("Test completed successfully");
        log.info("========================================");
    }
}
