package com.witboost.plugin.informatica.marketplace.service.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.witboost.plugin.informatica.common.client.BaseClient;
import com.witboost.plugin.informatica.common.client.InformaticaApiClient;
import com.witboost.plugin.informatica.common.exceptions.ApiCallException;
import com.witboost.plugin.informatica.common.exceptions.ExceptionFormatter;
import com.witboost.plugin.informatica.common.utils.HttpResponseValidator;
import com.witboost.plugin.informatica.marketplace.config.MarketplaceApiConfig;
import com.witboost.plugin.informatica.marketplace.model.*;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * API client for Informatica Marketplace Data Collections.
 *
 * <p>Provides methods to interact with data collections in the Informatica Marketplace.
 */
@Service
@Slf4j
public class DataCollectionApiClient extends BaseClient {

    private static final int HTTP_OK = 200;
    private static final int HTTP_CREATED = 201;
    private static final int HTTP_ACCEPTED = 202;
    private static final int HTTP_NO_CONTENT = 204;
    private static final ObjectMapper objectMapper =
            new ObjectMapper().registerModule(new JavaTimeModule());

    private final InformaticaApiClient informaticaApiClient;
    private final MarketplaceApiConfig config;

    public DataCollectionApiClient(
            InformaticaApiClient informaticaApiClient, MarketplaceApiConfig config) {
        this.informaticaApiClient = informaticaApiClient;
        this.config = config;
    }

    /**
     * Retrieves all data collections from Informatica Marketplace with default parameters.
     *
     * <p>Uses default values: search="*", offset=0, limit=30, segments="all"
     *
     * @return DataCollectionsResponse containing the list of data collections
     * @throws ApiCallException if the API call fails or returns an error
     */
    public DataCollectionsResponse getAllCollections() {
        return getAllCollections("*", 0, 30, "all");
    }

    /**
     * Retrieves data collections from Informatica Marketplace with custom query parameters.
     *
     * @param search Search query string (use "*" for all collections)
     * @param offset Pagination offset (starting position)
     * @param limit Maximum number of results to return per page
     * @param segments Segments filter (e.g., "all")
     * @return DataCollectionsResponse containing the list of data collections
     * @throws ApiCallException if the API call fails or returns an error
     */
    public DataCollectionsResponse getAllCollections(
            String search, Integer offset, Integer limit, String segments) {
        try {
            log.debug(
                    "Fetching data collections from Marketplace (search: {}, offset: {}, limit: {}, segments: {})",
                    search,
                    offset,
                    limit,
                    segments);

            // Build URI with query parameters
            String baseUrl = config.getDataCollectionsUrl();
            StringBuilder urlBuilder = new StringBuilder(baseUrl);

            // Add query parameters
            urlBuilder.append("?");
            if (search != null && !search.isBlank()) {
                String encodedSearchQuery = URLEncoder.encode(search, StandardCharsets.UTF_8);

                urlBuilder.append("search=").append(encodedSearchQuery).append("&");
            }
            if (offset != null) {
                urlBuilder.append("offset=").append(offset).append("&");
            }
            if (limit != null) {
                urlBuilder.append("limit=").append(limit).append("&");
            }
            if (segments != null && !segments.isBlank()) {
                urlBuilder.append("segments=").append(segments);
            }

            // Remove trailing '&' if present
            String urlString = urlBuilder.toString();
            if (urlString.endsWith("&")) {
                urlString = urlString.substring(0, urlString.length() - 1);
            }

            URI uri = new URI(urlString);
            log.debug("Data collections URL: {}", uri);

            // Build HTTP request
            HttpRequest request =
                    HttpRequest.newBuilder()
                            .uri(uri)
                            .header("Content-Type", CONTENT_TYPE_JSON)
                            .header("Authorization", "Bearer " + informaticaApiClient.getJwtToken())
                            .GET()
                            .build();

            log.debug("Sending GET request to fetch data collections");

            // Send request
            HttpResponse<String> response =
                    getHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

            // Validate response
            HttpResponseValidator.validateResponse(
                    response, HTTP_OK, "Data collections retrieval", ApiCallException::new);

            log.debug("Data collections response received (status: 200)");
            log.trace("Response body: {}", response.body());

            // Parse response
            DataCollectionsResponse collectionsResponse =
                    objectMapper.readValue(response.body(), DataCollectionsResponse.class);

            int totalCount =
                    collectionsResponse.getPageInfo() != null
                            ? collectionsResponse.getPageInfo().getTotalCount()
                            : (collectionsResponse.getItems() != null
                                    ? collectionsResponse.getItems().size()
                                    : 0);

            log.info(
                    "Successfully retrieved {} data collections (total: {})",
                    collectionsResponse.getItems() != null
                            ? collectionsResponse.getItems().size()
                            : 0,
                    totalCount);

            return collectionsResponse;

        } catch (ApiCallException e) {
            log.error("API call failed while fetching data collections: {}", e.getMessage(), e);
            throw e;

        } catch (Exception e) {
            log.error("Unexpected error during data collections retrieval: {}", e.getMessage(), e);
            throw new ApiCallException(ExceptionFormatter.format(e));
        }
    }

    /**
     * Retrieves ALL data collections from Informatica Marketplace by automatically paginating
     * through all results.
     *
     * <p><strong>⚠️ WARNING - Memory Usage:</strong> This method loads ALL collections into memory
     * at once. For large datasets (hundreds or thousands of collections), this may cause memory
     * issues. Consider using {@link #getAllCollections(String, Integer, Integer, String)} with
     * manual pagination or {@link #getCollectionByName(String)} if searching for a specific
     * collection.
     *
     * <p>This method automatically handles pagination by fetching all pages until all collections
     * are retrieved. It uses the totalCount from pageInfo to determine when all items have been
     * fetched.
     *
     * @param search Search query string (use "*" for all collections)
     * @return List of all DataCollection items (across all pages)
     * @throws ApiCallException if the API call fails or returns an error
     * @see #getAllCollections(String, Integer, Integer, String)
     * @see #getCollectionByName(String)
     */
    public List<DataCollection> getAllCollectionsPaginated(String search) {
        List<DataCollection> allCollections = new ArrayList<>();

        int offset = 0;
        int limit = 50; // Fetch 50 items per page for efficiency
        int totalCount = -1; // Initialize to -1 to fetch at least one page

        log.info("Starting paginated fetch for data collections (search: {})", search);

        while (offset == 0 || offset < totalCount) {
            log.debug("Fetching page at offset {} (limit: {})", offset, limit);

            DataCollectionsResponse response = getAllCollections(search, offset, limit, "all");

            if (response.getPageInfo() != null && response.getPageInfo().getTotalCount() != null) {
                totalCount = response.getPageInfo().getTotalCount();
                log.debug("Total count: {}", totalCount);
            }

            if (response.getItems() != null && !response.getItems().isEmpty()) {
                allCollections.addAll(response.getItems());
                log.debug(
                        "Added {} items, total collected: {} / {}",
                        response.getItems().size(),
                        allCollections.size(),
                        totalCount);

                offset += response.getItems().size();
            } else {
                log.debug("No more items returned, stopping pagination");
                break;
            }

            // Safety check to prevent infinite loops
            if (totalCount > 0 && allCollections.size() >= totalCount) {
                log.debug("All items collected, stopping pagination");
                break;
            }
        }

        log.info("Completed paginated fetch: retrieved {} data collections", allCollections.size());
        return allCollections;
    }

    /**
     * Retrieves a specific data collection from Informatica Marketplace by its unique identifier.
     *
     * <p>This method fetches detailed information about a data collection using its
     * system-generated ID. You can optionally request specific segments of data to be included in
     * the response.
     *
     * <p><b>Available segments:</b>
     *
     * <ul>
     *   <li><b>all</b>
     *   <li><b>category</b>
     *   <li><b>customAttributes</b>
     *   <li><b>deliveryTargets</b>
     *   <li><b>stakeholdership</b>
     *   <li><b>systemAttributes</b>
     *   <li><b>termOfUse</b>
     *   <li><b>usageContext</b>
     * </ul>
     *
     * @param dataCollectionId The system-generated unique identifier of the data collection to
     *     retrieve. Cannot be null or blank.
     * @param segments Optional comma-separated list of segments to include in the response If null
     *     or blank, only basic information is returned.
     * @return The DataCollection object with the requested segments, or null if dataCollectionId is
     *     null/blank
     * @throws ApiCallException if the API call fails, returns an error status, or the response
     *     cannot be parsed
     * @see #getAllCollections()
     * @see #getCollectionByName(String)
     * @see #getCollectionByNameAndCategoryId(String, String)
     */
    public DataCollection getDataCollectionById(String dataCollectionId, String segments) {
        try {
            if (dataCollectionId == null || dataCollectionId.isBlank()) {
                log.warn("Collection ID is null or blank, returning null");
                return null;
            }
            log.debug(
                    "Fetching data collection from Marketplace (id: {}, segments: {})",
                    dataCollectionId,
                    segments);

            // Build URI with query parameters
            String baseUrl = config.getDataCollectionsUrl();
            StringBuilder urlBuilder = new StringBuilder(baseUrl);
            urlBuilder.append("/").append(dataCollectionId);

            // Add query parameter
            if (segments != null && !segments.isBlank()) {
                urlBuilder.append("?segments=").append(segments);
            }

            // Remove trailing '&' if present
            String urlString = urlBuilder.toString();

            URI uri = new URI(urlString);
            log.debug("Data collections URL: {}", uri);

            // Build HTTP request
            HttpRequest request =
                    HttpRequest.newBuilder()
                            .uri(uri)
                            .header("Content-Type", CONTENT_TYPE_JSON)
                            .header("Authorization", "Bearer " + informaticaApiClient.getJwtToken())
                            .GET()
                            .build();

            log.debug("Sending GET request to fetch data collection");

            // Send request
            HttpResponse<String> response =
                    getHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

            // Validate response
            HttpResponseValidator.validateResponse(
                    response, HTTP_OK, "Data collection retrieval", ApiCallException::new);

            log.debug("Data collection response received (status: 200)");
            log.trace("Response body: {}", response.body());

            // Parse and return response
            return objectMapper.readValue(response.body(), DataCollection.class);

        } catch (ApiCallException e) {
            log.error("API call failed while fetching data collections: {}", e.getMessage(), e);
            throw e;

        } catch (Exception e) {
            log.error("Unexpected error during data collections retrieval: {}", e.getMessage(), e);
            throw new ApiCallException(ExceptionFormatter.format(e));
        }
    }

    /**
     * Retrieves a data collection by its exact name from Informatica Marketplace.
     *
     * <p>This method paginates through results until it finds a collection with matching name or
     * exhausts all pages. It's more efficient than loading all collections at once.
     *
     * @param name The exact name of the collection to search for
     * @return The DataCollection object if found, null otherwise
     * @throws ApiCallException if the API call fails or returns an error
     */
    public DataCollection getCollectionByName(String name) {
        if (name == null || name.isBlank()) {
            log.warn("Collection name is null or blank, returning null");
            return null;
        }

        log.debug("Searching for collection '{}'", name);

        int offset = 0;
        int limit = 50; // Fetch 50 items per page
        int totalCount = -1;

        while (offset == 0 || offset < totalCount) {
            log.debug(
                    "Fetching page at offset {} (limit: {}) to search for '{}'",
                    offset,
                    limit,
                    name);

            DataCollectionsResponse response = getAllCollections(name, offset, limit, "all");

            // Get total count from first response
            if (response.getPageInfo() != null && response.getPageInfo().getTotalCount() != null) {
                totalCount = response.getPageInfo().getTotalCount();
                log.debug("Total collections matching search '{}': {}", name, totalCount);
            }

            // Search for collection in current page
            if (response.getItems() != null && !response.getItems().isEmpty()) {
                DataCollection found =
                        response.getItems().stream()
                                .filter(collection -> name.equals(collection.getName()))
                                .findFirst()
                                .orElse(null);

                if (found != null) {
                    log.info("Collection '{}' found (ID: {})", name, found.getId());
                    return found;
                }

                log.debug("Collection '{}' not found in current page, checking next page", name);
                offset += response.getItems().size();
            } else {
                log.debug("No more items returned, collection '{}' not found", name);
                break;
            }

            // Safety check to prevent infinite loops
            if (totalCount > 0 && offset >= totalCount) {
                log.debug("Reached end of results, collection '{}' not found", name);
                break;
            }
        }

        log.info("Collection '{}' does not exist", name);
        return null;
    }

    /**
     * Retrieves a data collection by its exact name and category ID from Informatica Marketplace.
     *
     * <p>This method is more precise than {@link #getCollectionByName(String)} when multiple
     * collections with the same name exist in different categories. It searches for collections
     * matching the name and then filters by categoryId to ensure uniqueness.
     *
     * <p><b>Use Case:</b> When you need to uniquely identify a collection based on its hierarchical
     * category structure (e.g., Company > Domain > Subdomain).
     *
     * @param name The exact name of the collection to search for
     * @param categoryId The category ID that the collection belongs to
     * @return The DataCollection object if found, null otherwise
     * @throws ApiCallException if the API call fails or returns an error
     */
    public DataCollection getCollectionByNameAndCategoryId(String name, String categoryId) {
        if (name == null || name.isBlank()) {
            log.warn("Collection name is null or blank, returning null");
            return null;
        }

        if (categoryId == null || categoryId.isBlank()) {
            log.warn("Category ID is null or blank, returning null");
            return null;
        }

        log.debug("Searching for collection '{}' in category '{}'", name, categoryId);

        int offset = 0;
        int limit = 50; // Fetch 50 items per page
        int totalCount = -1;

        while (offset == 0 || offset < totalCount) {
            log.debug(
                    "Fetching page at offset {} (limit: {}) to search for '{}' in category '{}'",
                    offset,
                    limit,
                    name,
                    categoryId);

            DataCollectionsResponse response = getAllCollections(name, offset, limit, "all");

            // Get total count from first response
            if (response.getPageInfo() != null && response.getPageInfo().getTotalCount() != null) {
                totalCount = response.getPageInfo().getTotalCount();
                log.debug("Total collections matching search '{}': {}", name, totalCount);
            }

            // Search for collection with matching name AND categoryId in current page
            if (response.getItems() != null && !response.getItems().isEmpty()) {
                DataCollection found =
                        response.getItems().stream()
                                .filter(
                                        collection ->
                                                name.equals(collection.getName())
                                                        && categoryId.equals(
                                                                collection.getCategory().getId()))
                                .findFirst()
                                .orElse(null);

                if (found != null) {
                    log.info(
                            "Collection '{}' found in category '{}' (ID: {})",
                            name,
                            categoryId,
                            found.getId());
                    return found;
                }

                log.debug(
                        "Collection '{}' with categoryId '{}' not found in current page, checking next page",
                        name,
                        categoryId);
                offset += response.getItems().size();
            } else {
                log.debug(
                        "No more items returned, collection '{}' not found in category '{}'",
                        name,
                        categoryId);
                break;
            }

            // Safety check to prevent infinite loops
            if (totalCount > 0 && offset >= totalCount) {
                log.debug(
                        "Reached end of results, collection '{}' not found in category '{}'",
                        name,
                        categoryId);
                break;
            }
        }

        log.info("Collection '{}' does not exist in category '{}'", name, categoryId);
        return null;
    }

    /**
     * Checks if a data collection with the given name exists in Informatica Marketplace.
     *
     * @param name The exact name of the collection to search for
     * @return true if a collection with the given name exists, false otherwise
     * @throws ApiCallException if the API call fails or returns an error
     */
    public boolean collectionExists(String name) {
        return getCollectionByName(name) != null;
    }

    /**
     * Checks if a data collection with the given name and category ID exists in Informatica
     * Marketplace.
     *
     * @param name The exact name of the collection to search for
     * @param categoryId The category ID that the collection belongs to
     * @return true if a collection with the given name and categoryId exists, false otherwise
     * @throws ApiCallException if the API call fails or returns an error
     */
    public boolean collectionExistsByNameAndCategory(String name, String categoryId) {
        return getCollectionByNameAndCategoryId(name, categoryId) != null;
    }

    /**
     * Creates a new data collection in Informatica Marketplace.
     *
     * <p>This method sends a POST request to create a data collection with the specified
     * properties.
     *
     * @param request The create request containing name, description, categoryId, status, and
     *     optional fields
     * @return CreateDataCollectionResponse containing the ID and basic info of the created
     *     collection
     * @throws ApiCallException if the API call fails or returns an error
     */
    public CreateDataCollectionResponse createCollection(CreateDataCollectionRequest request) {
        if (request == null) {
            log.warn("Create request is null, cannot create collection");
            throw new ApiCallException("Create request cannot be null");
        }

        try {
            log.debug(
                    "Creating data collection '{}' in category '{}'",
                    request.getName(),
                    request.getCategoryId());

            // Build URI
            String url = config.getDataCollectionsUrl();
            URI uri = new URI(url);
            log.debug("Create URL: {}", uri);

            // Serialize request body
            String requestBody = objectMapper.writeValueAsString(request);
            log.trace("Request body: {}", requestBody);

            // Build HTTP request
            HttpRequest httpRequest =
                    HttpRequest.newBuilder()
                            .uri(uri)
                            .header("Content-Type", CONTENT_TYPE_JSON)
                            .header("Authorization", "Bearer " + informaticaApiClient.getJwtToken())
                            .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                            .build();

            log.debug("Sending POST request to create data collection");

            // Send request
            HttpResponse<String> response =
                    getHttpClient().send(httpRequest, HttpResponse.BodyHandlers.ofString());

            // Validate response (201 Created)
            int statusCode = response.statusCode();

            if (statusCode == HTTP_CREATED) {
                String responseBody = response.body();
                log.debug("Response status: 201 Created - data collection created successfully");
                log.trace("Response body: {}", responseBody);

                // Parse response to get created collection info
                CreateDataCollectionResponse createResponse =
                        objectMapper.readValue(responseBody, CreateDataCollectionResponse.class);

                log.info(
                        "Data collection '{}' created successfully (ID: {}, externalId: {})",
                        request.getName(),
                        createResponse.getId(),
                        createResponse.getExternalId());

                return createResponse;

            } else {
                String errorBody = response.body();
                log.error(
                        "Data collection creation failed with status {}: {}",
                        statusCode,
                        errorBody);
                throw new ApiCallException(
                        String.format(
                                "Create request failed with status %d. Expected 201 Created. Response: %s",
                                statusCode, errorBody));
            }

        } catch (ApiCallException e) {
            log.error(
                    "API call failed while creating data collection '{}': {}",
                    request.getName(),
                    e.getMessage(),
                    e);
            throw e;

        } catch (Exception e) {
            log.error(
                    "Unexpected error during data collection creation for '{}': {}",
                    request.getName(),
                    e.getMessage(),
                    e);
            throw new ApiCallException(ExceptionFormatter.format(e));
        }
    }

    /**
     * Updates a data collection in Informatica Marketplace.
     *
     * <p>This method sends a PATCH request to modify various aspects of a data collection based on
     * the operation and segment specified in the request.
     *
     * @param dataCollectionId The system-generated unique identifier of the data collection to
     *     update
     * @param request The update request containing operation, segment, and data to modify
     * @return UpdateDataCollectionResponse containing job IDs if async (202), or null if immediate
     *     (204)
     * @throws ApiCallException if the API call fails or returns an error
     */
    public UpdateDataCollectionResponse updateCollection(
            String dataCollectionId, UpdateDataCollectionRequest request) {
        if (dataCollectionId == null || dataCollectionId.isBlank()) {
            log.warn("Data collection ID is null or blank, cannot update");
            return null;
        }

        if (request == null) {
            log.warn("Update request is null, cannot update collection");
            return null;
        }

        try {
            log.debug(
                    "Updating data collection '{}' (operation: {}, segment: {})",
                    dataCollectionId,
                    request.getOperation(),
                    request.getSegment());

            // Build URI
            String url = config.getDataCollectionsUrl() + "/" + dataCollectionId;
            URI uri = new URI(url);
            log.debug("Update URL: {}", uri);

            // Serialize request body
            // Serialize request to JSON
            String requestBody = objectMapper.writeValueAsString(request);
            log.debug("Request body: {}", requestBody);

            // Build HTTP request
            HttpRequest httpRequest =
                    HttpRequest.newBuilder()
                            .uri(uri)
                            .header("Content-Type", CONTENT_TYPE_JSON)
                            .header("Authorization", "Bearer " + informaticaApiClient.getJwtToken())
                            .method("PATCH", HttpRequest.BodyPublishers.ofString(requestBody))
                            .build();

            log.debug("Sending PATCH request to update data collection");

            // Send request
            HttpResponse<String> response =
                    getHttpClient().send(httpRequest, HttpResponse.BodyHandlers.ofString());

            // Validate response
            // 204 No Content: Data collection successfully modified (immediate)
            // 202 Accepted: Job created for category/stakeholder modification (async)
            int statusCode = response.statusCode();

            if (statusCode == HTTP_NO_CONTENT) {
                log.info(
                        "Data collection '{}' updated successfully (operation: {}, segment: {})",
                        dataCollectionId,
                        request.getOperation(),
                        request.getSegment());
                log.debug("Response status: 204 No Content - modification applied immediately");
                return null; // No response body for immediate updates

            } else if (statusCode == HTTP_ACCEPTED) {
                String responseBody = response.body();
                log.debug(
                        "Response status: 202 Accepted - background job created for category/stakeholder modification");
                log.trace("Response body: {}", responseBody);

                // Parse response to get job IDs
                UpdateDataCollectionResponse updateResponse =
                        objectMapper.readValue(responseBody, UpdateDataCollectionResponse.class);

                log.info(
                        "Data collection '{}' update accepted - job created (operation: {}, segment: {}, trackerJobId: {}, propagationJobId: {})",
                        dataCollectionId,
                        request.getOperation(),
                        request.getSegment(),
                        updateResponse.getTrackerJobId(),
                        updateResponse.getPropagationJobId());

                return updateResponse;

            } else {
                String errorBody = response.body();
                log.error(
                        "Data collection update failed with status {}: {}", statusCode, errorBody);
                throw new ApiCallException(
                        String.format(
                                "Update request failed with status %d. Expected 204 or 202. Response: %s",
                                statusCode, errorBody));
            }

        } catch (ApiCallException e) {
            log.error(
                    "API call failed while updating data collection '{}': {}",
                    dataCollectionId,
                    e.getMessage(),
                    e);
            throw e;

        } catch (Exception e) {
            log.error(
                    "Unexpected error during data collection update for '{}': {}",
                    dataCollectionId,
                    e.getMessage(),
                    e);
            throw new ApiCallException(ExceptionFormatter.format(e));
        }
    }

    /**
     * Deletes a data collection from Informatica Marketplace.
     *
     * <p>This method sends a DELETE request to remove a data collection.
     *
     * <p><strong>Note:</strong> If the data collection is linked to another collection, the link is
     * removed when you delete the data collection. The other linked collection does not get
     * deleted.
     *
     * @param dataCollectionId The system-generated unique identifier of the data collection to
     *     delete
     * @return true if the deletion was successful, false otherwise
     * @throws ApiCallException if the API call fails or returns an error
     */
    public boolean deleteCollection(String dataCollectionId) {
        if (dataCollectionId == null || dataCollectionId.isBlank()) {
            log.warn("Data collection ID is null or blank, cannot delete");
            return false;
        }

        try {
            log.debug("Deleting data collection '{}'", dataCollectionId);

            // Build URI
            String url = config.getDataCollectionsUrl() + "/" + dataCollectionId;
            URI uri = new URI(url);
            log.debug("Delete URL: {}", uri);

            // Build HTTP request (DELETE has no payload)
            HttpRequest httpRequest =
                    HttpRequest.newBuilder()
                            .uri(uri)
                            .header("Content-Type", CONTENT_TYPE_JSON)
                            .header("Authorization", "Bearer " + informaticaApiClient.getJwtToken())
                            .DELETE()
                            .build();

            log.debug("Sending DELETE request to delete data collection");

            // Send request
            HttpResponse<String> response =
                    getHttpClient().send(httpRequest, HttpResponse.BodyHandlers.ofString());

            // Validate response (204 No Content)
            int statusCode = response.statusCode();

            if (statusCode == HTTP_NO_CONTENT) {
                log.info("Data collection '{}' deleted successfully", dataCollectionId);
                log.debug("Response status: 204 No Content - data collection removed");
                return true;

            } else {
                String errorBody = response.body();
                log.error(
                        "Data collection deletion failed with status {}: {}",
                        statusCode,
                        errorBody);
                throw new ApiCallException(
                        String.format(
                                "Delete request failed with status %d. Expected 204 No Content. Response: %s",
                                statusCode, errorBody));
            }

        } catch (ApiCallException e) {
            log.error(
                    "API call failed while deleting data collection '{}': {}",
                    dataCollectionId,
                    e.getMessage(),
                    e);
            throw e;

        } catch (Exception e) {
            log.error(
                    "Unexpected error during data collection deletion for '{}': {}",
                    dataCollectionId,
                    e.getMessage(),
                    e);
            throw new ApiCallException(ExceptionFormatter.format(e));
        }
    }

    /**
     * Retrieves ALL data assets for a data collection from Informatica Marketplace by automatically
     * paginating through all results.
     *
     * <p><strong>⚠️ WARNING - Memory Usage:</strong> This method loads ALL data assets into memory
     * at once. For large datasets (hundreds or thousands of data assets), this may cause memory
     * issues. Consider using {@link #retrieveDataAssetsForDataCollection(String, Integer, Integer)}
     * with manual pagination if you need to handle large collections.
     *
     * <p>This method automatically handles pagination by fetching all pages until all data assets
     * are retrieved. It uses the totalCount from pageInfo to determine when all items have been
     * fetched.
     *
     * @param dataCollectionId The system-generated unique identifier of the data collection
     * @return List of all DataAssetItem items (across all pages)
     * @throws ApiCallException if the API call fails or returns an error
     * @see #retrieveDataAssetsForDataCollection(String, Integer, Integer)
     */
    public List<GetDataAssetsDataCollectionResponse.DataAssetItem>
            retrieveDataAssetsForDataCollectionPaginated(String dataCollectionId) {
        if (dataCollectionId == null || dataCollectionId.isBlank()) {
            log.warn("Data collection ID is null or blank, cannot retrieve data assets");
            throw new ApiCallException("Data collection ID cannot be null or blank");
        }

        List<GetDataAssetsDataCollectionResponse.DataAssetItem> allDataAssets = new ArrayList<>();

        int offset = 0;
        int limit = 50; // Fetch 50 items per page for efficiency
        int totalCount = -1; // Initialize to -1 to fetch at least one page

        log.info("Starting paginated fetch for data assets in collection '{}' ", dataCollectionId);

        while (offset == 0 || offset < totalCount) {
            log.debug(
                    "Fetching page at offset {} (limit: {}) for collection '{}'",
                    offset,
                    limit,
                    dataCollectionId);

            GetDataAssetsDataCollectionResponse response =
                    retrieveDataAssetsForDataCollection(dataCollectionId, offset, limit);

            if (response.getPageInfo() != null && response.getPageInfo().getTotalCount() != null) {
                totalCount = response.getPageInfo().getTotalCount();
                log.debug("Total count: {}", totalCount);
            }

            if (response.getItems() != null && !response.getItems().isEmpty()) {
                allDataAssets.addAll(response.getItems());
                log.debug(
                        "Added {} items, total collected: {} / {}",
                        response.getItems().size(),
                        allDataAssets.size(),
                        totalCount);

                offset += response.getItems().size();
            } else {
                log.debug("No more items returned, stopping pagination");
                break;
            }

            // Safety check to prevent infinite loops
            if (totalCount > 0 && allDataAssets.size() >= totalCount) {
                log.debug("All items collected, stopping pagination");
                break;
            }
        }

        log.info(
                "Completed paginated fetch: retrieved {} data assets for collection '{}'",
                allDataAssets.size(),
                dataCollectionId);
        return allDataAssets;
    }

    /**
     * Retrieves data assets associated with a specific data collection with custom pagination.
     *
     * <p>This method fetches the list of data assets that are part of the specified data
     * collection.
     *
     * <p><b>Response includes:</b>
     *
     * <ul>
     *   <li>Data asset basic information (id, name, description, status, type)
     *   <li>Source system information (source, descriptiveSource)
     *   <li>Asset location details (refLink, assetLocation, technicalAssetName)
     *   <li>Average rating (if imported from Data Governance and Catalog)
     *   <li>Pagination information (offset, limit, totalCount)
     * </ul>
     *
     * @param dataCollectionId The system-generated unique identifier of the data collection
     * @param offset Starting index for paginated results (default: 0)
     * @param limit Maximum number of results per page (default: 20, max: 200)
     * @return GetDataAssetsResponse containing the list of data assets and pagination info
     * @throws ApiCallException if the API call fails or returns an error
     */
    public GetDataAssetsDataCollectionResponse retrieveDataAssetsForDataCollection(
            String dataCollectionId, Integer offset, Integer limit) {
        // fixme pagination
        if (dataCollectionId == null || dataCollectionId.isBlank()) {
            log.warn("Data collection ID is null or blank, cannot retrieve data assets");
            throw new ApiCallException("Data collection ID cannot be null or blank");
        }

        try {
            log.debug(
                    "Retrieving data assets for data collection '{}' (offset: {}, limit: {})",
                    dataCollectionId,
                    offset,
                    limit);

            // Build URI with query parameters
            String baseUrl =
                    config.getDataCollectionsUrl() + "/" + dataCollectionId + "/data-assets";
            StringBuilder urlBuilder = new StringBuilder(baseUrl);

            // Add query parameters
            urlBuilder.append("?");
            if (offset != null) {
                urlBuilder.append("offset=").append(offset).append("&");
            }
            if (limit != null) {
                // Validate limit (max 200)
                int validatedLimit = Math.min(limit, 200);
                if (limit > 200) {
                    log.warn("Limit {} exceeds maximum of 200, using 200 instead", limit);
                }
                urlBuilder.append("limit=").append(validatedLimit);
            }

            // Remove trailing '&' if present
            String urlString = urlBuilder.toString();
            if (urlString.endsWith("&")) {
                urlString = urlString.substring(0, urlString.length() - 1);
            }

            URI uri = new URI(urlString);
            log.debug("Data assets URL: {}", uri);

            // Build HTTP request
            HttpRequest request =
                    HttpRequest.newBuilder()
                            .uri(uri)
                            .header("Content-Type", CONTENT_TYPE_JSON)
                            .header("Authorization", "Bearer " + informaticaApiClient.getJwtToken())
                            .GET()
                            .build();

            log.debug("Sending GET request to retrieve data assets");

            // Send request
            HttpResponse<String> response =
                    getHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

            // Validate response (200 OK)
            HttpResponseValidator.validateResponse(
                    response,
                    HTTP_OK,
                    "Data assets retrieval for collection " + dataCollectionId,
                    ApiCallException::new);

            log.debug("Data assets response received (status: 200)");
            log.trace("Response body: {}", response.body());

            // Parse response
            GetDataAssetsDataCollectionResponse assetsResponse =
                    objectMapper.readValue(
                            response.body(), GetDataAssetsDataCollectionResponse.class);

            int totalCount = assetsResponse.getTotalCount();
            int itemsCount =
                    assetsResponse.getItems() != null ? assetsResponse.getItems().size() : 0;

            log.info(
                    "Successfully retrieved {} data assets for collection '{}' (total: {})",
                    itemsCount,
                    dataCollectionId,
                    totalCount);

            return assetsResponse;

        } catch (ApiCallException e) {
            log.error(
                    "API call failed while retrieving data assets for collection '{}': {}",
                    dataCollectionId,
                    e.getMessage(),
                    e);
            throw e;

        } catch (Exception e) {
            log.error(
                    "Unexpected error during data assets retrieval for collection '{}': {}",
                    dataCollectionId,
                    e.getMessage(),
                    e);
            throw new ApiCallException(ExceptionFormatter.format(e));
        }
    }

    /**
     * Adds a data asset to a data collection.
     *
     * <p>This method adds the specified data asset to the data collection.
     *
     * @param dataCollectionId The system-generated unique identifier of the data collection
     * @param dataAssetId The system-generated unique identifier of the data asset to add
     * @return true if the data asset was successfully added (204 No Content)
     * @throws ApiCallException if the API call fails or returns an error
     */
    public boolean addDataAssetToCollection(String dataCollectionId, String dataAssetId) {
        return manageDataAssetInCollection(dataCollectionId, dataAssetId, "add");
    }

    /**
     * Removes a data asset from a data collection.
     *
     * <p>This method removes the specified data asset from the data collection.
     *
     * @param dataCollectionId The system-generated unique identifier of the data collection
     * @param dataAssetId The system-generated unique identifier of the data asset to remove
     * @return true if the data asset was successfully removed (204 No Content)
     * @throws ApiCallException if the API call fails or returns an error
     */
    public boolean removeDataAssetFromCollection(String dataCollectionId, String dataAssetId) {
        return manageDataAssetInCollection(dataCollectionId, dataAssetId, "remove");
    }

    /**
     * Adds or removes a data asset from a data collection.
     *
     * <p>This method performs a PATCH operation to add or remove a data asset from the collection.
     *
     * <p><b>Operations supported:</b>
     *
     * <ul>
     *   <li><b>add</b>: Adds the data asset to the data collection
     *   <li><b>remove</b>: Removes the data asset from the data collection
     * </ul>
     *
     * @param dataCollectionId The system-generated unique identifier of the data collection
     * @param dataAssetId The system-generated unique identifier of the data asset
     * @param operation The operation to perform: "add" or "remove"
     * @return true if the operation was successful (204 No Content)
     * @throws ApiCallException if the API call fails or returns an error
     */
    private boolean manageDataAssetInCollection(
            String dataCollectionId, String dataAssetId, String operation) {
        if (dataCollectionId == null || dataCollectionId.isBlank()) {
            log.warn("Data collection ID is null or blank");
            throw new ApiCallException("Data collection ID cannot be null or blank");
        }

        if (dataAssetId == null || dataAssetId.isBlank()) {
            log.warn("Data asset ID is null or blank");
            throw new ApiCallException("Data asset ID cannot be null or blank");
        }

        if (operation == null || (!operation.equals("add") && !operation.equals("remove"))) {
            log.warn("Invalid operation: {}. Must be 'add' or 'remove'", operation);
            throw new ApiCallException("Operation must be 'add' or 'remove'");
        }

        try {
            log.debug(
                    "Starting to {} data asset '{}' {} collection '{}'",
                    operation,
                    dataAssetId,
                    operation.equals("add") ? "to" : "from",
                    dataCollectionId);

            // Build URI
            String url = config.getDataCollectionsUrl() + "/" + dataCollectionId + "/data-assets";
            URI uri = new URI(url);
            log.debug("Data assets management URL: {}", uri);

            // Build request body
            Map<String, Object> requestBody = new HashMap<>();
            requestBody.put("operation", operation);
            requestBody.put("dataAssetIds", List.of(dataAssetId));

            String requestBodyJson = objectMapper.writeValueAsString(requestBody);
            log.trace("Request body: {}", requestBodyJson);

            // Build HTTP request
            HttpRequest httpRequest =
                    HttpRequest.newBuilder()
                            .uri(uri)
                            .header("Content-Type", CONTENT_TYPE_JSON)
                            .header("Authorization", "Bearer " + informaticaApiClient.getJwtToken())
                            .method("PATCH", HttpRequest.BodyPublishers.ofString(requestBodyJson))
                            .build();

            log.debug("Sending PATCH request to {} data asset", operation);

            // Send request
            HttpResponse<String> response =
                    getHttpClient().send(httpRequest, HttpResponse.BodyHandlers.ofString());

            // Validate response (204 No Content)
            int statusCode = response.statusCode();

            if (statusCode == HTTP_NO_CONTENT) {
                log.info(
                        "Successfully {} data asset '{}' {} collection '{}'",
                        operation.equals("add") ? "added" : "removed",
                        dataAssetId,
                        operation.equals("add") ? "to" : "from",
                        dataCollectionId);
                return true;

            } else {
                String errorBody = response.body();
                log.error(
                        "Data asset {} operation failed with status {}: {}",
                        operation,
                        statusCode,
                        errorBody);
                throw new ApiCallException(
                        String.format(
                                "Data asset %s operation failed with status %d. Expected 204 No Content. Response: %s",
                                operation, statusCode, errorBody));
            }

        } catch (ApiCallException e) {
            log.error(
                    "API call failed while {} data asset '{}' {} collection '{}': {}",
                    operation,
                    dataAssetId,
                    operation.equals("add") ? "to" : "from",
                    dataCollectionId,
                    e.getMessage(),
                    e);
            throw e;

        } catch (Exception e) {
            log.error(
                    "Unexpected error during data asset {} operation for asset '{}' in collection '{}': {}",
                    operation,
                    dataAssetId,
                    dataCollectionId,
                    e.getMessage(),
                    e);
            throw new ApiCallException(ExceptionFormatter.format(e));
        }
    }
}
