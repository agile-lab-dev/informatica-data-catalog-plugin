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
class DataCollectionApiClientTest {

    @Autowired DataCollectionApiClient dataCollectionApiClient;

    @Test
    void getAllCollections() {

        DataCollectionsResponse collections = dataCollectionApiClient.getAllCollections();
        assertNotNull(collections);
    }

    @Test
    void shouldCheckIfCollectionExists() {

        DataCollectionsResponse first = dataCollectionApiClient.getAllCollections("*", 0, 1, "all");

        if (first.getPageInfo().getTotalCount() > 0) {
            String existingCollectionName = first.getItems().get(0).getName();
            assertTrue(dataCollectionApiClient.collectionExists(existingCollectionName));
        }

        String nonExistingCollectionName = "Non Existing Collection Name";

        assertFalse(dataCollectionApiClient.collectionExists(nonExistingCollectionName));
    }

    @Test
    void getCollectionByName() {
        dataCollectionApiClient.getCollectionByName("Test Collection");
    }

    @Test
    void getCollectionById() {
        var dataCollection =
                dataCollectionApiClient.getDataCollectionById(
                        "fd2ccdb8-0ff7-45a7-9a0f-dc02b2237d01", "all");
        System.out.println(dataCollection);
    }

    @Test
    void collectionExists() {}

    @Test
    void updateCollection() {
        // First, verify the collection exists
        var collection = dataCollectionApiClient.getCollectionByName("Updated Test Collection");
        assertNotNull(collection, "Collection 'Updated Test Collection' not found");

        var id = collection.getId();
        assertNotNull(id, "Collection ID is null");

        log.info("Found collection: '{}' with ID: {}", collection.getName(), id);
        log.info("Current status: {}", collection.getStatus());
        log.info("Current description: {}", collection.getDescription());

        // Test: Update summary only
        log.info("Updating summary...");
        var updateSummaryRequest =
                UpdateDataCollectionRequest.updateSummary(
                        collection.getName(), // Keep same name
                        "Updated Description - " + System.currentTimeMillis(), // Change description
                        collection.getStatus() // Keep same status
                        );

        // Debug: Print the JSON that will be sent
        try {
            com.fasterxml.jackson.databind.ObjectMapper mapper =
                    new com.fasterxml.jackson.databind.ObjectMapper();
            String json =
                    mapper.writerWithDefaultPrettyPrinter()
                            .writeValueAsString(updateSummaryRequest);
            log.info("JSON to be sent:\n{}", json);
        } catch (Exception e) {
            log.error("Failed to serialize request for debugging", e);
        }

        dataCollectionApiClient.updateCollection(id, updateSummaryRequest);
        log.info("Summary updated successfully");
    }

    @Test
    void updateCollectionCustomAttributes() {
        // First, verify the collection exists
        var collection = dataCollectionApiClient.getCollectionByName("Updated Test Collection");
        assertNotNull(collection, "Collection 'Updated Test Collection' not found");

        var id = collection.getId();
        assertNotNull(id, "Collection ID is null");

        log.info("Found collection: '{}' with ID: {}", collection.getName(), id);
        log.info("Current status: {}", collection.getStatus());
        log.info("Current description: {}", collection.getDescription());

        // Create all custom attributes
        var customAttributes =
                List.of(
                        // Confidentiality (DROPDOWN_SINGLE_SELECT)
                        UpdateDataCollectionRequest.CustomAttribute.builder()
                                .id("com.infa.odin.models.custom.ca_2101612002328922269")
                                .value("Default ")
                                .build(),

                        // Compagnie Gestite (PLAIN_TEXT)
                        UpdateDataCollectionRequest.CustomAttribute.builder()
                                .id("com.infa.odin.models.custom.ca_4512717490444826982")
                                .value(
                                        "Updated - Indicazione della Compagnia/e a cui fanno riferimento i dati della tabella")
                                .build(),

                        // Tipologia Data Product (DROPDOWN_SINGLE_SELECT)
                        UpdateDataCollectionRequest.CustomAttribute.builder()
                                .id("com.infa.odin.models.custom.ca_2026554882729119419")
                                .value("Source-aligned")
                                .build(),

                        // Dati gestiti (RICH_TEXT)
                        UpdateDataCollectionRequest.CustomAttribute.builder()
                                .id("com.infa.odin.models.custom.ca_4627923793675393130")
                                .value(
                                        "<p>Company: &lt;Company&gt; e.g. Example Organization</p><p><br></p><ul><li>&lt;Domain&gt;: &lt;Subdomain&gt; e.g. Example Domain: Example Subdomain</li></ul><p><br></p>")
                                .build(),

                        // Storicizzazione (DROPDOWN_SINGLE_SELECT)
                        UpdateDataCollectionRequest.CustomAttribute.builder()
                                .id("com.infa.odin.models.custom.ca_6210973776664587841")
                                .value("Yes")
                                .build(),

                        // Life cycle status (DROPDOWN_SINGLE_SELECT)
                        UpdateDataCollectionRequest.CustomAttribute.builder()
                                .id("com.infa.odin.models.custom.ca_3641419596917035685")
                                .value("In Development")
                                .build(),

                        // Dettaglio dati sensibili (DROPDOWN_MULTI_SELECT) - Array of values
                        UpdateDataCollectionRequest.CustomAttribute.builder()
                                .id("com.infa.odin.models.custom.ca_1400253484102225908")
                                .value(List.of("nessuno"))
                                .build());

        var updateRequest = UpdateDataCollectionRequest.updateCustomAttributes(customAttributes);

        // Debug: Print the JSON that will be sent
        try {
            com.fasterxml.jackson.databind.ObjectMapper mapper =
                    new com.fasterxml.jackson.databind.ObjectMapper();
            String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(updateRequest);
            log.info("JSON to be sent:\n{}", json);
        } catch (Exception e) {
            log.error("Failed to serialize request for debugging", e);
        }

        log.info(
                "Custom attribute update request: operation={}, segment={}, attributes count={}",
                updateRequest.getOperation(),
                updateRequest.getSegment(),
                customAttributes.size());

        dataCollectionApiClient.updateCollection(id, updateRequest);
        log.info(
                "Custom attributes updated successfully - {} attributes updated",
                customAttributes.size());
    }

    @Test
    void minimalUpdateTest() {
        // Get ANY existing collection to test with
        DataCollectionsResponse collections =
                dataCollectionApiClient.getAllCollections("*", 0, 1, "all");

        if (collections.getItems() == null || collections.getItems().isEmpty()) {
            log.warn("No collections found!");
            return;
        }

        var collection = collections.getItems().get(0);
        log.info("Using collection: '{}' (ID: {})", collection.getName(), collection.getId());
        log.info("Current description: {}", collection.getDescription());
        log.info("Current status: {}", collection.getStatus());
        log.info("Current externalId: {}", collection.getExternalId());

        // Update ONLY description (minimal change)
        var updateRequest =
                UpdateDataCollectionRequest.builder()
                        .operation("replace")
                        .segment("summary")
                        .value(
                                UpdateDataCollectionRequest.SummaryValue.builder()
                                        .name(collection.getName()) // Keep same
                                        .description(
                                                (collection.getDescription() != null
                                                                ? collection.getDescription()
                                                                : "")
                                                        + " [TEST-"
                                                        + System.currentTimeMillis()
                                                        + "]") // Only change this
                                        .status(collection.getStatus()) // Keep same
                                        .externalId(
                                                collection
                                                        .getExternalId()) // Keep same (if not null)
                                        .build())
                        .build();

        // Print JSON for debugging
        try {
            com.fasterxml.jackson.databind.ObjectMapper mapper =
                    new com.fasterxml.jackson.databind.ObjectMapper();
            String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(updateRequest);
            log.info("JSON to be sent:\n{}", json);
        } catch (Exception e) {
            log.error("Failed to serialize request", e);
        }

        // Try update
        dataCollectionApiClient.updateCollection(collection.getId(), updateRequest);
        log.info("✅ Update successful!");
    }

    @Test
    void createCollection() {
        CreateDataCollectionRequest request = new CreateDataCollectionRequest();
        request.setName("Test Collection 27");
        request.setCategoryId("14149962-f5dc-435f-811d-e1334bcbb56e");
        request.setDescription("Test Description");
        request.setStatus(Constants.DATA_COLLECTION_PUBLISHED_STATUS);

        log.info(
                "Creating collection with request: name='{}', categoryId='{}', status='{}'",
                request.getName(),
                request.getCategoryId(),
                request.getStatus());

        var res = dataCollectionApiClient.createCollection(request);

        log.info("========================================");
        log.info("Collection created successfully!");
        log.info("========================================");
        log.info("ID:          {}", res.getId());
        log.info("Name:        {}", res.getName());
        log.info("External ID: {}", res.getExternalId());
        log.info("Status:      {}", res.getStatus());
        log.info("Description: {}", res.getDescription());
        log.info(
                "Category ID: {}",
                res.getAssetGroups() != null && !res.getAssetGroups().isEmpty()
                        ? res.getAssetGroups().get(0).getName()
                        : "N/A");
        log.info("========================================");

        assertNotNull(res.getId(), "Created collection ID should not be null");
        assertNotNull(res.getExternalId(), "Created collection External ID should not be null");
    }

    @Test
    public void deleteCollection() {
        String collectionId =
                "76b34640-1157-4506-8e2b-b341b921dcea"; // Replace with an actual collection ID to
        // delete
        dataCollectionApiClient.deleteCollection(collectionId);
    }

    @Test
    public void getAllDataAssetsForDataCollection() {
        try {
            DataCollection testCollection =
                    dataCollectionApiClient.getCollectionByName("Data Product Test Metadata 3");

            List<GetDataAssetsDataCollectionResponse.DataAssetItem> getDataAssetsResponse =
                    dataCollectionApiClient.retrieveDataAssetsForDataCollectionPaginated(
                            testCollection.getId());
            assertNotNull(getDataAssetsResponse);
            assertFalse(getDataAssetsResponse.isEmpty());
            log.info("No error when deleting non-existing collection (as expected)");
        } catch (Exception e) {
            fail("Exception thrown when deleting non-existing collection: " + e.getMessage());
        }
    }

    @Test
    public void deleteDataAssetFromDataCollection() {
        try {
            DataCollection testCollection =
                    dataCollectionApiClient.getCollectionByName(
                            "Test Sales Data Product 1769083015152");
            var dataAssetsDataCollectionResponse =
                    dataCollectionApiClient.retrieveDataAssetsForDataCollectionPaginated(
                            testCollection.getId());

            String dataAssetIdToDelete =
                    dataAssetsDataCollectionResponse
                            .get(0)
                            .getId(); // Replace with actual data asset ID to delete

            dataCollectionApiClient.removeDataAssetFromCollection(
                    testCollection.getId(), dataAssetIdToDelete);
            log.info("Data asset deleted successfully from data collection");
        } catch (Exception e) {
            fail("Exception thrown when deleting data asset from collection: " + e.getMessage());
        }
    }

    @Test
    public void addDataAssetToDataCollection() {
        try {
            DataCollection testCollection =
                    dataCollectionApiClient.getCollectionByName(
                            "Test Sales Data Product 1769083015152");

            String dataAssetIdToAdd =
                    "4aca1424-4307-4cd6-a222-9bd00832ae76"; // Replace with actual data asset ID to
            // add

            dataCollectionApiClient.addDataAssetToCollection(
                    testCollection.getId(), dataAssetIdToAdd);
            log.info("Data asset deleted successfully from data collection");
        } catch (Exception e) {
            fail("Exception thrown when deleting data asset from collection: " + e.getMessage());
        }
    }
}
