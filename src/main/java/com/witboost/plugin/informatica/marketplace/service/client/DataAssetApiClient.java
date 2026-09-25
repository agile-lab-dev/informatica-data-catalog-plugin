package com.witboost.plugin.informatica.marketplace.service.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.witboost.plugin.informatica.common.client.BaseClient;
import com.witboost.plugin.informatica.common.client.InformaticaApiClient;
import com.witboost.plugin.informatica.common.exceptions.ApiCallException;
import com.witboost.plugin.informatica.common.exceptions.ExceptionFormatter;
import com.witboost.plugin.informatica.marketplace.config.MarketplaceApiConfig;
import com.witboost.plugin.informatica.marketplace.model.*;
import com.witboost.plugin.informatica.marketplace.model.GetDataAssetsResponse.DataAssetObject;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * API client for Informatica Marketplace Data Assets.
 *
 * <p>Provides methods to interact with data assets in the Informatica Marketplace.
 */
@Service
@Slf4j
public class DataAssetApiClient extends BaseClient {

    private static final int HTTP_OK = 200;
    private static final int HTTP_CREATED = 201;
    private static final int HTTP_NO_CONTENT = 204;
    private static final ObjectMapper objectMapper =
            new ObjectMapper().registerModule(new JavaTimeModule());

    private final InformaticaApiClient informaticaApiClient;
    private final MarketplaceApiConfig config;

    public DataAssetApiClient(
            InformaticaApiClient informaticaApiClient, MarketplaceApiConfig config) {
        this.informaticaApiClient = informaticaApiClient;
        this.config = config;
    }

    /**
     * Creates new data assets in Informatica Marketplace.
     *
     * <p>This method sends a POST request to create data assets with the specified properties.
     *
     * @param request The create request containing name, description, source, type, and optional
     *     fields
     * @return CreateDataAssetResponse containing the processing time, list of created data assets,
     *     and any errors
     * @throws ApiCallException if the API call fails or returns an error
     */
    public CreateDataAssetResponse createDataAsset(CreateDataAssetRequest request) {
        if (request == null) {
            log.warn("Create request is null, cannot create data asset");
            throw new ApiCallException("Create request cannot be null");
        }

        try {
            log.debug(
                    "Creating data asset '{}' with source '{}' and type '{}'",
                    request.getName(),
                    request.getSource(),
                    request.getType());

            // Build URI
            String url = config.getCreateDataAssetsUrl();
            URI uri = new URI(url);
            log.debug("Create URL: {}", uri);

            // The wrapped request expects a x-www-form-urlencoded payload
            String payloadJson = "{\"items\":[" + objectMapper.writeValueAsString(request) + "]}";
            String requestBody =
                    "payload=" + URLEncoder.encode(payloadJson, StandardCharsets.UTF_8);
            log.trace("Request body: {}", requestBody);

            // Build HTTP request
            HttpRequest httpRequest =
                    HttpRequest.newBuilder()
                            .uri(uri)
                            .header(
                                    "Content-Type",
                                    "application/x-www-form-urlencoded; charset=UTF-8")
                            .header("INFA-SESSION-ID", informaticaApiClient.getSessionId())
                            .header("X-INFA-ORG-ID", informaticaApiClient.getOrgId())
                            .header("Authorization", "Bearer " + informaticaApiClient.getJwtToken())
                            .POST(
                                    HttpRequest.BodyPublishers.ofString(
                                            requestBody, StandardCharsets.UTF_8))
                            .build();

            log.debug("Sending POST request to create data asset");

            // Send request
            HttpResponse<String> response =
                    getHttpClient().send(httpRequest, HttpResponse.BodyHandlers.ofString());

            // Validate response (200 OK)
            int statusCode = response.statusCode();

            if (statusCode == HTTP_OK) {
                String responseBody = response.body();
                log.debug("Response status: 200 OK - data asset created successfully");
                log.trace("Response body: {}", responseBody);

                // Parse response to get created data assets
                CreateDataAssetResponse createResponse =
                        parseDataAssetResponse(responseBody, CreateDataAssetResponse.class);

                // Log the created assets details
                if (createResponse.getObjects() != null && !createResponse.getObjects().isEmpty()) {
                    for (CreateDataAssetResponse.DataAssetObject asset :
                            createResponse.getObjects()) {
                        log.info(
                                "Data asset '{}' created successfully (ID: {}, refId: {})",
                                asset.getName(),
                                asset.getId(),
                                asset.getRefId());
                    }
                } else {
                    log.warn("No assets returned in create response");
                }

                return createResponse;

            } else {
                String errorBody = response.body();
                log.error("Data asset creation failed with status {}: {}", statusCode, errorBody);
                throw new ApiCallException(
                        String.format(
                                "Create request failed with status %d. Expected 200 OK. Response: %s",
                                statusCode, errorBody));
            }

        } catch (ApiCallException e) {
            log.error(
                    "API call failed while creating data asset '{}': {}",
                    request.getName(),
                    e.getMessage(),
                    e);
            throw e;

        } catch (Exception e) {
            log.error(
                    "Unexpected error during data asset creation for '{}': {}",
                    request.getName(),
                    e.getMessage(),
                    e);
            throw new ApiCallException(ExceptionFormatter.format(e));
        }
    }

    /**
     * Updates data assets in Informatica Marketplace.
     *
     * <p>This method sends a POST request to update data assets with the specified properties.
     *
     * @param request The update request containing id, name, description, source, type, and
     *     optional fields
     * @return UpdateDataAssetResponse containing the processing time, list of updated data assets,
     *     and any errors
     * @throws ApiCallException if the API call fails or returns an error
     */
    public UpdateDataAssetResponse updateDataAsset(UpdateDataAssetRequest request) {
        if (request == null) {
            log.warn("Update request is null, cannot update data asset");
            throw new ApiCallException("Update request cannot be null");
        }

        try {
            log.debug(
                    "Updating data asset '{}' with name '{}', source '{}' and type '{}'",
                    request.getId(),
                    request.getName(),
                    request.getSource(),
                    request.getType());

            // Build URI
            String url = config.getUpdateDataAssetsUrl();
            URI uri = new URI(url);
            log.debug("Create URL: {}", uri);

            // The wrapped request expects a x-www-form-urlencoded payload
            String payloadJson = "{\"items\":[" + objectMapper.writeValueAsString(request) + "]}";
            String requestBody =
                    "payload=" + URLEncoder.encode(payloadJson, StandardCharsets.UTF_8);
            log.trace("Request body: {}", requestBody);

            // Build HTTP request
            HttpRequest httpRequest =
                    HttpRequest.newBuilder()
                            .uri(uri)
                            .header(
                                    "Content-Type",
                                    "application/x-www-form-urlencoded; charset=UTF-8")
                            .header("INFA-SESSION-ID", informaticaApiClient.getSessionId())
                            .header("X-INFA-ORG-ID", informaticaApiClient.getOrgId())
                            .header("Authorization", "Bearer " + informaticaApiClient.getJwtToken())
                            .POST(
                                    HttpRequest.BodyPublishers.ofString(
                                            requestBody, StandardCharsets.UTF_8))
                            .build();

            log.debug("Sending POST request to create data asset");

            // Send request
            HttpResponse<String> response =
                    getHttpClient().send(httpRequest, HttpResponse.BodyHandlers.ofString());

            // Validate response (200 OK)
            int statusCode = response.statusCode();

            if (statusCode == HTTP_OK) {
                String responseBody = response.body();
                log.debug("Response status: 200 OK - data asset updated successfully");
                log.trace("Response body: {}", responseBody);

                // Parse response to get updated data assets
                UpdateDataAssetResponse updateResponse =
                        parseDataAssetResponse(responseBody, UpdateDataAssetResponse.class);

                // Log the updated assets details
                if (updateResponse.getObjects() != null && !updateResponse.getObjects().isEmpty()) {
                    for (UpdateDataAssetResponse.DataAssetObject asset :
                            updateResponse.getObjects()) {
                        log.info(
                                "Data asset '{}' updated successfully (ID: {}, refId: {})",
                                asset.getName(),
                                asset.getId(),
                                asset.getRefId());
                    }
                } else {
                    log.warn("No assets returned in update response");
                }

                return updateResponse;

            } else {
                String errorBody = response.body();
                log.error("Data asset update failed with status {}: {}", statusCode, errorBody);
                throw new ApiCallException(
                        String.format(
                                "Update request failed with status %d. Expected 200 OK. Response: %s",
                                statusCode, errorBody));
            }

        } catch (ApiCallException e) {
            log.error(
                    "API call failed while update data asset '{}': {}",
                    request.getName(),
                    e.getMessage(),
                    e);
            throw e;

        } catch (Exception e) {
            log.error(
                    "Unexpected error during data asset update for '{}': {}",
                    request.getName(),
                    e.getMessage(),
                    e);
            throw new ApiCallException(ExceptionFormatter.format(e));
        }
    }

    /**
     * Retrieves data assets from Informatica Marketplace with optional filtering and pagination.
     *
     * <p>This method sends a GET request to retrieve data assets with optional filtering,
     * pagination, and sorting.
     *
     * @param request The get request containing optional filter, pagination, and sort parameters
     * @return GetDataAssetsResponse containing the list of data assets and pagination info
     * @throws ApiCallException if the API call fails or returns an error
     */
    public GetDataAssetsResponse getDataAssets(GetDataAssetsRequest request) {
        if (request == null) {
            log.warn("Get request is null, creating empty request");
            request = new GetDataAssetsRequest();
        }

        try {
            log.debug(
                    "Retrieving data assets with filters: search={}, status={}, fields={}",
                    request.getSearch(),
                    request.getStatus(),
                    request.getFields());

            // Build URL with query parameters
            String baseUrl = config.getDataAssetsUrl();
            String urlWithParams = buildUrlWithQueryParams(baseUrl, request);
            URI uri = new URI(urlWithParams);
            log.debug("Get URL: {}", uri);

            // Build HTTP request
            HttpRequest httpRequest =
                    HttpRequest.newBuilder()
                            .uri(uri)
                            .header("Authorization", "Bearer " + informaticaApiClient.getJwtToken())
                            .GET()
                            .build();

            log.debug("Sending GET request to retrieve data assets");

            // Send request
            HttpResponse<String> response =
                    getHttpClient().send(httpRequest, HttpResponse.BodyHandlers.ofString());

            // Validate response (200 OK)
            int statusCode = response.statusCode();

            if (statusCode == HTTP_OK) {
                String responseBody = response.body();
                log.debug("Response status: 200 OK - data assets retrieved successfully");
                log.trace("Response body: {}", responseBody);

                // Parse response
                GetDataAssetsResponse getResponse =
                        objectMapper.readValue(responseBody, GetDataAssetsResponse.class);

                int count = getResponse.getObjects() != null ? getResponse.getObjects().size() : 0;
                log.info("Retrieved {} data asset(s) successfully", count);

                return getResponse;

            } else {
                String errorBody = response.body();
                log.error("Data assets retrieval failed with status {}: {}", statusCode, errorBody);
                throw new ApiCallException(
                        String.format(
                                "Get request failed with status %d. Expected 200 OK. Response: %s",
                                statusCode, errorBody));
            }

        } catch (ApiCallException e) {
            log.error("API call failed while retrieving data assets: {}", e.getMessage(), e);
            throw e;

        } catch (Exception e) {
            log.error("Unexpected error during data assets retrieval: {}", e.getMessage(), e);
            throw new ApiCallException(ExceptionFormatter.format(e));
        }
    }

    /**
     * Retrieves ALL data assets from Informatica Marketplace by automatically paginating through
     * all results.
     *
     * <p>This method automatically handles pagination by fetching all pages until all assets are
     * retrieved. It uses the totalCount from the response to determine when all objects have been
     * fetched.
     *
     * @param request The get request containing optional filter and sort parameters (pagination
     *     params will be overridden)
     * @return List of all DataAssetObject items (across all pages)
     * @throws ApiCallException if the API call fails or returns an error
     * @see #getDataAssets(GetDataAssetsRequest)
     */
    public List<DataAssetObject> getAllDataAssetsPaginated(GetDataAssetsRequest request) {
        if (request == null) {
            request = new GetDataAssetsRequest();
        }

        List<DataAssetObject> allAssets = new ArrayList<>();

        int offset = 0;
        int limit = 50; // Fetch 50 items per page for efficiency
        int totalCount = -1; // Initialize to -1 to fetch at least one page

        log.info(
                "Starting paginated fetch for data assets (search: {}, status: {})",
                request.getSearch(),
                request.getStatus());

        while (offset == 0 || offset < totalCount) {
            log.debug("Fetching page at offset {} (limit: {})", offset, limit);

            // Create a new request with pagination parameters
            GetDataAssetsRequest paginatedRequest =
                    GetDataAssetsRequest.builder()
                            .search(request.getSearch())
                            .fields(request.getFields())
                            .createdDateFrom(request.getCreatedDateFrom())
                            .createdDateTo(request.getCreatedDateTo())
                            .modifiedDateFrom(request.getModifiedDateFrom())
                            .modifiedDateTo(request.getModifiedDateTo())
                            .status(request.getStatus())
                            .limit(limit)
                            .offset(offset)
                            .sortByField(request.getSortByField())
                            .sort(request.getSort())
                            .build();

            GetDataAssetsResponse response = getDataAssets(paginatedRequest);

            if (response.getTotalCount() != null) {
                totalCount = response.getTotalCount();
                log.debug("Total count: {}", totalCount);
            }

            if (response.getObjects() != null && !response.getObjects().isEmpty()) {
                allAssets.addAll(response.getObjects());
                log.debug(
                        "Added {} objects, total collected: {} / {}",
                        response.getObjects().size(),
                        allAssets.size(),
                        totalCount);

                offset += response.getObjects().size();
            } else {
                log.debug("No more objects returned, stopping pagination");
                break;
            }

            // Safety check to prevent infinite loops
            if (totalCount > 0 && allAssets.size() >= totalCount) {
                log.debug("All objects collected, stopping pagination");
                break;
            }
        }

        log.info("Completed paginated fetch: retrieved {} data assets", allAssets.size());
        return allAssets;
    }

    /**
     * Builds a URL with query parameters from the request object.
     *
     * @param baseUrl The base URL
     * @param request The request object containing query parameters
     * @return The URL with query parameters appended
     */
    private String buildUrlWithQueryParams(String baseUrl, GetDataAssetsRequest request) {
        List<String> queryParams = new ArrayList<>();

        // Add search filter if present
        if (request.getSearch() != null && !request.getSearch().isBlank()) {
            queryParams.add(
                    "search=" + URLEncoder.encode(request.getSearch(), StandardCharsets.UTF_8));
        }

        // Add fields filter if present
        if (request.getFields() != null && !request.getFields().isBlank()) {
            queryParams.add(
                    "fields=" + URLEncoder.encode(request.getFields(), StandardCharsets.UTF_8));
        }

        // Add created date range filters if present
        if (request.getCreatedDateFrom() != null && !request.getCreatedDateFrom().isBlank()) {
            queryParams.add(
                    "createdDateFrom="
                            + URLEncoder.encode(
                                    request.getCreatedDateFrom(), StandardCharsets.UTF_8));
        }

        if (request.getCreatedDateTo() != null && !request.getCreatedDateTo().isBlank()) {
            queryParams.add(
                    "createdDateTo="
                            + URLEncoder.encode(
                                    request.getCreatedDateTo(), StandardCharsets.UTF_8));
        }

        // Add modified date range filters if present
        if (request.getModifiedDateFrom() != null && !request.getModifiedDateFrom().isBlank()) {
            queryParams.add(
                    "modifiedDateFrom="
                            + URLEncoder.encode(
                                    request.getModifiedDateFrom(), StandardCharsets.UTF_8));
        }

        if (request.getModifiedDateTo() != null && !request.getModifiedDateTo().isBlank()) {
            queryParams.add(
                    "modifiedDateTo="
                            + URLEncoder.encode(
                                    request.getModifiedDateTo(), StandardCharsets.UTF_8));
        }

        // Add status filter if present
        if (request.getStatus() != null && !request.getStatus().isBlank()) {
            queryParams.add(
                    "status=" + URLEncoder.encode(request.getStatus(), StandardCharsets.UTF_8));
        }

        // Add pagination parameters if present
        if (request.getOffset() != null) {
            queryParams.add("offset=" + request.getOffset());
        }

        if (request.getLimit() != null) {
            queryParams.add("limit=" + request.getLimit());
        }

        // Add sort parameters if present
        if (request.getSortByField() != null && !request.getSortByField().isBlank()) {
            queryParams.add(
                    "sortByField="
                            + URLEncoder.encode(request.getSortByField(), StandardCharsets.UTF_8));
        }

        if (request.getSort() != null && !request.getSort().isBlank()) {
            queryParams.add("sort=" + URLEncoder.encode(request.getSort(), StandardCharsets.UTF_8));
        }

        // Build final URL
        if (queryParams.isEmpty()) {
            return baseUrl;
        } else {
            return baseUrl + "?" + String.join("&", queryParams);
        }
    }

    /**
     * Deletes a data asset from Informatica Marketplace.
     *
     * <p>This method sends a POST request to delete a data asset by its ID.
     *
     * @param id The ID of the data asset to delete
     * @throws ApiCallException if the API call fails or returns an error
     */
    public void deleteDataAsset(String id) {
        if (id == null || id.isBlank()) {
            log.warn("Data asset ID is null or empty, cannot delete data asset");
            throw new ApiCallException("Data asset ID cannot be null or empty");
        }

        try {
            log.debug("Deleting data asset with ID: {}", id);

            // Build URI
            String url = config.getDeleteDataAssetsUrl();
            URI uri = new URI(url);
            log.debug("Delete URL: {}", uri);

            // Request body: data_asset_id=<id>
            String requestBody = "data_asset_id=" + URLEncoder.encode(id, StandardCharsets.UTF_8);
            log.trace("Request body: {}", requestBody);

            // Build HTTP request
            HttpRequest httpRequest =
                    HttpRequest.newBuilder()
                            .uri(uri)
                            .header("Content-Type", "application/x-www-form-urlencoded")
                            .header("INFA-SESSION-ID", informaticaApiClient.getSessionId())
                            .header("X-INFA-ORG-ID", informaticaApiClient.getOrgId())
                            .header("Authorization", "Bearer " + informaticaApiClient.getJwtToken())
                            .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                            .build();

            log.debug("Sending POST request to delete data asset");

            // Send request
            HttpResponse<String> response =
                    getHttpClient().send(httpRequest, HttpResponse.BodyHandlers.ofString());

            // Validate response (200 OK or 204 No Content)
            int statusCode = response.statusCode();

            if (statusCode == HTTP_OK || statusCode == HTTP_NO_CONTENT) {
                log.info(
                        "Data asset with ID '{}' deleted successfully (status: {})",
                        id,
                        statusCode);
                if (statusCode == HTTP_OK) {
                    log.trace("Response body: {}", response.body());
                }
            } else {
                String errorBody = response.body();
                log.error("Data asset deletion failed with status {}: {}", statusCode, errorBody);
                throw new ApiCallException(
                        String.format(
                                "Delete request failed with status %d. Expected 200 OK or 204 No Content. Response: %s",
                                statusCode, errorBody));
            }

        } catch (ApiCallException e) {
            log.error("API call failed while deleting data asset '{}': {}", id, e.getMessage(), e);
            throw e;

        } catch (Exception e) {
            log.error(
                    "Unexpected error during data asset deletion for '{}': {}",
                    id,
                    e.getMessage(),
                    e);
            throw new ApiCallException(ExceptionFormatter.format(e));
        }
    }

    /**
     * Parses a data asset API response that may be wrapped in an "output" field.
     *
     * <p>The API can return responses in two formats:
     *
     * <ol>
     *   <li>Direct JSON response with "objects" array (already parsed JSON object)
     *   <li>Wrapped response with "output" field containing a JSON string:
     *       <pre>{"output": "{\"processingTime\":...,\"objects\":[...],\"errors\":null}"}</pre>
     * </ol>
     *
     * <p>This method attempts to parse both formats:
     *
     * <ol>
     *   <li>First tries to parse as a wrapped response and extract the "output" field
     *   <li>If the "output" field is present and valid JSON, parses it as the response type
     *       containing "objects" array
     *   <li>If the "output" field is not found, tries to parse the entire response directly
     * </ol>
     *
     * @param responseBody The response body as a JSON string
     * @param responseType The expected response type (CreateDataAssetResponse,
     *     UpdateDataAssetResponse, etc.)
     * @param <T> The type parameter for the response type
     * @return The parsed response object containing the "objects" array
     * @throws Exception If the response cannot be parsed in any format
     */
    private <T> T parseDataAssetResponse(String responseBody, Class<T> responseType)
            throws Exception {
        try {
            // First, try to parse as a wrapped response with an "output" field
            WrappedDataAssetResponse wrappedResponse =
                    objectMapper.readValue(responseBody, WrappedDataAssetResponse.class);

            // If the wrapped response has an "output" field with content, parse it
            if (wrappedResponse != null
                    && wrappedResponse.getOutput() != null
                    && !wrappedResponse.getOutput().isEmpty()) {
                log.debug("Wrapped response detected, extracting and parsing 'output' field");
                log.trace("Output field content: {}", wrappedResponse.getOutput());

                try {
                    // Parse the JSON string from the "output" field
                    T parsedResponse =
                            objectMapper.readValue(wrappedResponse.getOutput(), responseType);
                    log.debug(
                            "Successfully parsed wrapped response as {}",
                            responseType.getSimpleName());
                    return parsedResponse;

                } catch (Exception e) {
                    log.error(
                            "Failed to parse 'output' field as {}: {}",
                            responseType.getSimpleName(),
                            e.getMessage());
                    throw new ApiCallException(
                            String.format(
                                    "Failed to parse API response output field as %s: %s",
                                    responseType.getSimpleName(), e.getMessage()));
                }
            }

            // If no "output" field found, try to parse the entire response directly
            log.debug(
                    "No 'output' field found, attempting to parse response directly as {}",
                    responseType.getSimpleName());
            return objectMapper.readValue(responseBody, responseType);

        } catch (ApiCallException e) {
            throw e;
        } catch (Exception e) {
            log.error(
                    "Failed to parse API response as {}: {}",
                    responseType.getSimpleName(),
                    e.getMessage());
            throw new ApiCallException(
                    String.format(
                            "Failed to parse API response as %s: %s",
                            responseType.getSimpleName(), e.getMessage()));
        }
    }
}
