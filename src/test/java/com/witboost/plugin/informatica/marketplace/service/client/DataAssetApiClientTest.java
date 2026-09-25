package com.witboost.plugin.informatica.marketplace.service.client;

import static org.junit.jupiter.api.Assertions.*;

import com.witboost.plugin.informatica.marketplace.model.CreateDataAssetRequest;
import com.witboost.plugin.informatica.marketplace.model.GetDataAssetsRequest;
import com.witboost.plugin.informatica.marketplace.model.GetDataAssetsResponse;
import com.witboost.plugin.informatica.marketplace.model.UpdateDataAssetRequest;
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
public class DataAssetApiClientTest {

    @Autowired DataAssetApiClient dataAssetApiClient;

    @Test
    public void createDataAsset() {
        log.info("========================================");
        log.info("Testing createDataAsset");
        log.info("========================================");

        var request = new CreateDataAssetRequest();
        request.setName("data-asset-test-agilelab");
        request.setDescription("Test Data Asset");
        request.setSource("Test");
        request.setType("Test");

        log.debug("Creating data asset with name: {}", request.getName());
        var response = dataAssetApiClient.createDataAsset(request);

        assertNotNull(response, "Response should not be null");
        var res = response.getObjects().get(0);
        assertNotNull(res.getId(), "Created data asset ID should not be null");

        log.info("✓ Data Asset created successfully!");
        log.info("  ID:          {}", res.getId());
        //        log.info("  External ID: {}", res.getExternalId());
        log.info("  Name:        {}", request.getName());

        log.info("========================================");
        log.info("Test completed successfully");
        log.info("========================================");
    }

    @Test
    public void getDataAssets() {
        log.info("========================================");
        log.info("Testing getDataAssets");
        log.info("========================================");

        List<GetDataAssetsResponse.DataAssetObject> response =
                dataAssetApiClient.getAllDataAssetsPaginated(
                        GetDataAssetsRequest.builder().build());

        assertNotNull(response, "Response should not be null");

        log.info("Total Data Assets found: {}", response.size());
        log.info("");

        for (int i = 0; i < response.size(); i++) {
            GetDataAssetsResponse.DataAssetObject asset = response.get(i);
            log.info("┌─────────────────────────────────────────────────────────");
            log.info("│ Data Asset #{}", i + 1);
            log.info("├─────────────────────────────────────────────────────────");
            log.info("│ ID:                  {}", asset.getId());
            log.info("│ External ID:         {}", asset.getRefId());
            log.info("│ Name:                {}", asset.getName());
            log.info("│ Description:         {}", asset.getDescription());
            log.info("│ Status:              {}", asset.getStatus());
            log.info("│ Type:                {}", asset.getType());
            log.info("│ Source:              {}", asset.getSource());
            log.info("│ Descriptive Source:  {}", asset.getDescriptiveSource());
            log.info("│ Asset Location:      {}", asset.getAssetLocation());
            log.info("│ Technical Name:      {}", asset.getTechnicalAssetName());
            log.info("│ Ref Link:            {}", asset.getRefLink());
            log.info("│ Created By:          {}", asset.getCreatedBy());
            log.info("│ Created On:          {}", asset.getCreatedOn());
            log.info("│ Modified By:         {}", asset.getModifiedBy());
            log.info("│ Modified On:         {}", asset.getModifiedOn());
            log.info("└─────────────────────────────────────────────────────────");
            log.info("");
        }

        log.info("========================================");
        log.info("Test completed successfully");
        log.info("========================================");
    }

    @Test
    public void createUpdateDeleteDataAsset() {
        // Create
        var createResponse =
                dataAssetApiClient.createDataAsset(
                        CreateDataAssetRequest.builder()
                                .name("data-asset-test-agilelab")
                                .description("Test Data Asset")
                                .source("Test")
                                .type("test")
                                .build());
        String id = createResponse.getObjects().get(0).getId();
        System.out.println(createResponse);
        // Update
        var updateResponse =
                dataAssetApiClient.updateDataAsset(
                        UpdateDataAssetRequest.builder()
                                .id(id)
                                .name("data-asset-test-agilelab-updated")
                                .description("Test Data Asset Updated")
                                .source("Test")
                                .type("test")
                                .build());
        System.out.println(updateResponse);
        // Delete
        dataAssetApiClient.deleteDataAsset(id);
        System.out.println("Test completed");
    }
}
