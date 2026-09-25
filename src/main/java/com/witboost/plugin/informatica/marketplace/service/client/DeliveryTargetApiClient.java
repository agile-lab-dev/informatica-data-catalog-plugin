package com.witboost.plugin.informatica.marketplace.service.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.witboost.plugin.informatica.common.client.BaseClient;
import com.witboost.plugin.informatica.common.client.InformaticaApiClient;
import com.witboost.plugin.informatica.common.exceptions.ApiCallException;
import com.witboost.plugin.informatica.common.exceptions.ExceptionFormatter;
import com.witboost.plugin.informatica.common.utils.HttpResponseValidator;
import com.witboost.plugin.informatica.marketplace.config.MarketplaceApiConfig;
import com.witboost.plugin.informatica.marketplace.model.CreateDeliveryTargetRequest;
import com.witboost.plugin.informatica.marketplace.model.CreateDeliveryTargetResponse;
import com.witboost.plugin.informatica.marketplace.model.DeliveryTarget;
import com.witboost.plugin.informatica.marketplace.model.DeliveryTargetsResponse;
import com.witboost.plugin.informatica.marketplace.model.GetDeliveryTemplatesRequest;
import com.witboost.plugin.informatica.marketplace.model.GetDeliveryTemplatesResponse;
import com.witboost.plugin.informatica.marketplace.model.UpdateDeliveryTargetRequest;
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
 * API client for Informatica Marketplace Delivery Targets.
 *
 * <p>Provides methods to interact with delivery targets in the Informatica Marketplace.
 */
@Service
@Slf4j
public class DeliveryTargetApiClient extends BaseClient {

    private static final int HTTP_OK = 200;
    private static final int HTTP_NO_CONTENT = 204;
    private static final ObjectMapper objectMapper =
            new ObjectMapper().registerModule(new JavaTimeModule());

    private final InformaticaApiClient informaticaApiClient;
    private final MarketplaceApiConfig config;

    public DeliveryTargetApiClient(
            InformaticaApiClient informaticaApiClient, MarketplaceApiConfig config) {
        this.informaticaApiClient = informaticaApiClient;
        this.config = config;
    }

    /**
     * Creates a new delivery target in Informatica Marketplace.
     *
     * <p>This method sends a POST request to create a delivery target with the specified
     * properties.
     *
     * @param request The create request containing name, description, status, deliveryTemplateId,
     *     dataCollectionId, and optional fields
     * @return CreateDeliveryTargetResponse containing the ID and basic info of the created delivery
     *     target
     * @throws ApiCallException if the API call fails or returns an error
     */
    public CreateDeliveryTargetResponse createDeliveryTarget(CreateDeliveryTargetRequest request) {
        if (request == null) {
            log.warn("Create request is null, cannot create delivery target");
            throw new ApiCallException("Create request cannot be null");
        }

        try {
            log.debug(
                    "Creating delivery target '{}' for data collection '{}'",
                    request.getName(),
                    request.getDataCollectionId());

            // Build URI
            String url = config.getDeliveryTargetsUrl();
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

            log.debug("Sending POST request to create delivery target");

            // Send request
            HttpResponse<String> response =
                    getHttpClient().send(httpRequest, HttpResponse.BodyHandlers.ofString());

            // Validate response (201 Created)
            int statusCode = response.statusCode();

            if (statusCode == HTTP_OK) {
                String responseBody = response.body();
                log.debug("Response status: 200 OK - delivery target created successfully");
                log.trace("Response body: {}", responseBody);

                // Parse response to get created delivery target info
                CreateDeliveryTargetResponse createResponse =
                        objectMapper.readValue(responseBody, CreateDeliveryTargetResponse.class);

                log.info(
                        "Delivery target '{}' created successfully (ID: {}, externalId: {})",
                        request.getName(),
                        createResponse.getId(),
                        createResponse.getExternalId());

                return createResponse;

            } else {
                String errorBody = response.body();
                log.error(
                        "Delivery target creation failed with status {}: {}",
                        statusCode,
                        errorBody);
                throw new ApiCallException(
                        String.format(
                                "Create request failed with status %d. Expected 201 Created. Response: %s",
                                statusCode, errorBody));
            }

        } catch (ApiCallException e) {
            log.error(
                    "API call failed while creating delivery target '{}': {}",
                    request.getName(),
                    e.getMessage(),
                    e);
            throw e;

        } catch (Exception e) {
            log.error(
                    "Unexpected error during delivery target creation for '{}': {}",
                    request.getName(),
                    e.getMessage(),
                    e);
            throw new ApiCallException(ExceptionFormatter.format(e));
        }
    }

    /**
     * Deletes a delivery target from Informatica Marketplace.
     *
     * <p>This method sends a DELETE request to remove the specified delivery target.
     *
     * @param deliveryTargetId The ID of the delivery target to delete
     * @throws ApiCallException if the API call fails or returns an error
     */
    public void deleteDeliveryTarget(String deliveryTargetId) {
        if (deliveryTargetId == null || deliveryTargetId.trim().isEmpty()) {
            log.warn("Delivery target ID is null or empty, cannot delete delivery target");
            throw new ApiCallException("Delivery target ID cannot be null or empty");
        }

        try {
            log.debug("Deleting delivery target with ID '{}'", deliveryTargetId);

            // Build URI
            String url = config.getDeliveryTargetsUrl() + "/" + deliveryTargetId;
            URI uri = new URI(url);
            log.debug("Delete URL: {}", uri);

            // Build HTTP request
            HttpRequest httpRequest =
                    HttpRequest.newBuilder()
                            .uri(uri)
                            .header("Authorization", "Bearer " + informaticaApiClient.getJwtToken())
                            .DELETE()
                            .build();

            log.debug("Sending DELETE request to remove delivery target");

            // Send request
            HttpResponse<String> response =
                    getHttpClient().send(httpRequest, HttpResponse.BodyHandlers.ofString());

            // Validate response (204 No Content)
            int statusCode = response.statusCode();

            if (statusCode == HTTP_NO_CONTENT) {
                log.info("Delivery target '{}' deleted successfully", deliveryTargetId);
            } else {
                String errorBody = response.body();
                log.error(
                        "Delivery target deletion failed with status {}: {}",
                        statusCode,
                        errorBody);
                throw new ApiCallException(
                        String.format(
                                "Delete request failed with status %d. Expected 204 No Content. Response: %s",
                                statusCode, errorBody));
            }

        } catch (ApiCallException e) {
            log.error(
                    "API call failed while deleting delivery target '{}': {}",
                    deliveryTargetId,
                    e.getMessage(),
                    e);
            throw e;

        } catch (Exception e) {
            log.error(
                    "Unexpected error during delivery target deletion for '{}': {}",
                    deliveryTargetId,
                    e.getMessage(),
                    e);
            throw new ApiCallException(ExceptionFormatter.format(e));
        }
    }

    /**
     * Retrieves delivery templates from Informatica Marketplace.
     *
     * <p>This method sends a GET request to retrieve delivery templates with optional filtering and
     * pagination.
     *
     * @param request The get request containing optional filter, pagination, and sort parameters
     * @return GetDeliveryTemplatesResponse containing the list of delivery templates and pagination
     *     info
     * @throws ApiCallException if the API call fails or returns an error
     */
    public GetDeliveryTemplatesResponse getDeliveryTemplates(GetDeliveryTemplatesRequest request) {
        if (request == null) {
            log.warn("Get request is null, creating empty request");
            request = new GetDeliveryTemplatesRequest();
        }

        try {
            log.debug(
                    "Retrieving delivery templates with filters: search={}, status={}, deliveryType={}",
                    request.getSearch(),
                    request.getStatus(),
                    request.getDeliveryType());

            // Build URL with query parameters
            String baseUrl = config.getDeliveryTemplatesUrl();
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

            log.debug("Sending GET request to retrieve delivery templates");

            // Send request
            HttpResponse<String> response =
                    getHttpClient().send(httpRequest, HttpResponse.BodyHandlers.ofString());

            // Validate response (200 OK)
            int statusCode = response.statusCode();

            if (statusCode == HTTP_OK) {
                String responseBody = response.body();
                log.debug("Response status: 200 OK - delivery templates retrieved successfully");
                log.trace("Response body: {}", responseBody);

                // Parse response
                GetDeliveryTemplatesResponse getResponse =
                        objectMapper.readValue(responseBody, GetDeliveryTemplatesResponse.class);

                int count = getResponse.getObjects() != null ? getResponse.getObjects().size() : 0;
                log.info("Retrieved {} delivery template(s) successfully", count);

                return getResponse;

            } else {
                String errorBody = response.body();
                log.error(
                        "Delivery templates retrieval failed with status {}: {}",
                        statusCode,
                        errorBody);
                throw new ApiCallException(
                        String.format(
                                "Get request failed with status %d. Expected 200 OK. Response: %s",
                                statusCode, errorBody));
            }

        } catch (ApiCallException e) {
            log.error("API call failed while retrieving delivery templates: {}", e.getMessage(), e);
            throw e;

        } catch (Exception e) {
            log.error(
                    "Unexpected error during delivery templates retrieval: {}", e.getMessage(), e);
            throw new ApiCallException(ExceptionFormatter.format(e));
        }
    }

    /**
     * Retrieves ALL delivery templates from Informatica Marketplace by automatically paginating
     * through all results.
     *
     * <p>This method automatically handles pagination by fetching all pages until all templates are
     * retrieved. It uses the totalCount from the response to determine when all items have been
     * fetched.
     *
     * @param request The get request containing optional filter and sort parameters (pagination
     *     params will be overridden)
     * @return List of all DeliveryTemplate items (across all pages)
     * @throws ApiCallException if the API call fails or returns an error
     * @see #getDeliveryTemplates(GetDeliveryTemplatesRequest)
     */
    public List<GetDeliveryTemplatesResponse.DeliveryTemplate> getAllDeliveryTemplatesPaginated(
            GetDeliveryTemplatesRequest request) {
        if (request == null) {
            request = new GetDeliveryTemplatesRequest();
        }

        List<GetDeliveryTemplatesResponse.DeliveryTemplate> allTemplates = new ArrayList<>();

        int offset = 0;
        int limit = 50; // Fetch 50 items per page for efficiency
        int totalCount = -1; // Initialize to -1 to fetch at least one page

        log.info(
                "Starting paginated fetch for delivery templates (search: {}, status: {}, deliveryType: {})",
                request.getSearch(),
                request.getStatus(),
                request.getDeliveryType());

        while (offset == 0 || offset < totalCount) {
            log.debug("Fetching page at offset {} (limit: {})", offset, limit);

            // Create a new request with pagination parameters
            GetDeliveryTemplatesRequest paginatedRequest =
                    GetDeliveryTemplatesRequest.builder()
                            .search(request.getSearch())
                            .fields(request.getFields())
                            .ids(request.getIds())
                            .status(request.getStatus())
                            .deliveryType(request.getDeliveryType())
                            .isDefault(request.getIsDefault())
                            .deliveryMethodIds(request.getDeliveryMethodIds())
                            .deliveryFormatIds(request.getDeliveryFormatIds())
                            .templateOwnerUserIds(request.getTemplateOwnerUserIds())
                            .createdDateFrom(request.getCreatedDateFrom())
                            .createdDateTo(request.getCreatedDateTo())
                            .modifiedDateFrom(request.getModifiedDateFrom())
                            .modifiedDateTo(request.getModifiedDateTo())
                            .limit(limit)
                            .offset(offset)
                            .sortByField(request.getSortByField())
                            .sort(request.getSort())
                            .build();

            GetDeliveryTemplatesResponse response = getDeliveryTemplates(paginatedRequest);

            if (response.getTotalCount() != null) {
                totalCount = response.getTotalCount();
                log.debug("Total count: {}", totalCount);
            }

            if (response.getObjects() != null && !response.getObjects().isEmpty()) {
                allTemplates.addAll(response.getObjects());
                log.debug(
                        "Added {} items, total collected: {} / {}",
                        response.getObjects().size(),
                        allTemplates.size(),
                        totalCount);

                offset += response.getObjects().size();
            } else {
                log.debug("No more items returned, stopping pagination");
                break;
            }

            // Safety check to prevent infinite loops
            if (totalCount > 0 && allTemplates.size() >= totalCount) {
                log.debug("All items collected, stopping pagination");
                break;
            }
        }

        log.info("Completed paginated fetch: retrieved {} delivery templates", allTemplates.size());
        return allTemplates;
    }

    /**
     * Builds a URL with query parameters from the request object.
     *
     * @param baseUrl The base URL
     * @param request The request object containing query parameters
     * @return The URL with query parameters appended
     */
    private String buildUrlWithQueryParams(String baseUrl, GetDeliveryTemplatesRequest request) {
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

        // Add ids filter if present
        if (request.getIds() != null && !request.getIds().isEmpty()) {
            for (String id : request.getIds()) {
                queryParams.add("ids=" + URLEncoder.encode(id, StandardCharsets.UTF_8));
            }
        }

        // Add status filter if present
        if (request.getStatus() != null && !request.getStatus().isBlank()) {
            queryParams.add(
                    "status=" + URLEncoder.encode(request.getStatus(), StandardCharsets.UTF_8));
        }

        // Add deliveryType filter if present
        if (request.getDeliveryType() != null && !request.getDeliveryType().isBlank()) {
            queryParams.add(
                    "deliveryType="
                            + URLEncoder.encode(request.getDeliveryType(), StandardCharsets.UTF_8));
        }

        // Add isDefault filter if present
        if (request.getIsDefault() != null) {
            queryParams.add("isDefault=" + request.getIsDefault());
        }

        // Add deliveryMethodIds if present
        if (request.getDeliveryMethodIds() != null && !request.getDeliveryMethodIds().isEmpty()) {
            for (String methodId : request.getDeliveryMethodIds()) {
                queryParams.add(
                        "deliveryMethodIds=" + URLEncoder.encode(methodId, StandardCharsets.UTF_8));
            }
        }

        // Add deliveryFormatIds if present
        if (request.getDeliveryFormatIds() != null && !request.getDeliveryFormatIds().isEmpty()) {
            for (String formatId : request.getDeliveryFormatIds()) {
                queryParams.add(
                        "deliveryFormatIds=" + URLEncoder.encode(formatId, StandardCharsets.UTF_8));
            }
        }

        // Add templateOwnerUserIds if present
        if (request.getTemplateOwnerUserIds() != null
                && !request.getTemplateOwnerUserIds().isEmpty()) {
            for (String userId : request.getTemplateOwnerUserIds()) {
                queryParams.add(
                        "templateOwnerUserIds="
                                + URLEncoder.encode(userId, StandardCharsets.UTF_8));
            }
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
     * Retrieves all delivery targets from Informatica Marketplace with automatic pagination.
     *
     * <p>This method automatically handles pagination by fetching all pages until all delivery
     * targets are retrieved. It uses default values for pagination parameters: offset=0, limit=50.
     * Sorting is done by modifiedOn in descending order by default.
     *
     * @param search Search term to find a delivery target by name. Use null to retrieve all
     *     delivery targets.
     * @param segments Type of details to return. Can include: - "all": Returns all delivery target
     *     details - "deliveryTemplate": Returns delivery template details - "deliveryFormat":
     *     Returns delivery format details - "deliveryMethod": Returns delivery method details -
     *     "systemAttributes": Returns creation and modification details
     * @return DeliveryTargetsResponse containing all retrieved delivery targets across all pages
     * @throws ApiCallException if the API call fails or returns an error
     */
    public DeliveryTargetsResponse getAllDeliveryTargets(String search, String segments) {
        List<DeliveryTarget> allDeliveryTargets = new ArrayList<>();
        int offset = 0;
        int limit = 50; // Fetch 50 items per page for efficiency
        int totalCount = -1; // Initialize to -1 to fetch at least one page

        log.info(
                "Starting paginated fetch for delivery targets (search: {}, segments: {})",
                search,
                segments);

        while (offset == 0 || offset < totalCount) {
            log.debug("Fetching page at offset {} (limit: {})", offset, limit);

            // Internal call with all parameters
            DeliveryTargetsResponse response =
                    fetchDeliveryTargetsPage(search, offset, limit, segments, "modifiedOn", "desc");

            if (response.getPageInfo() != null && response.getPageInfo().getTotalCount() != null) {
                totalCount = response.getPageInfo().getTotalCount();
                log.debug("Total count: {}", totalCount);
            }

            if (response.getItems() != null && !response.getItems().isEmpty()) {
                allDeliveryTargets.addAll(response.getItems());
                log.debug(
                        "Added {} items, total collected: {} / {}",
                        response.getItems().size(),
                        allDeliveryTargets.size(),
                        totalCount);

                offset += response.getItems().size();
            } else {
                log.debug("No more items returned, stopping pagination");
                break;
            }

            // Safety check to prevent infinite loops
            if (totalCount > 0 && allDeliveryTargets.size() >= totalCount) {
                log.debug("All items collected, stopping pagination");
                break;
            }
        }

        log.info(
                "Completed paginated fetch: retrieved {} delivery targets",
                allDeliveryTargets.size());

        // Create response with all collected delivery targets
        DeliveryTargetsResponse finalResponse = new DeliveryTargetsResponse();
        finalResponse.setItems(allDeliveryTargets);

        // Set page info with totals
        if (!allDeliveryTargets.isEmpty()) {
            DeliveryTargetsResponse.PageInfo pageInfo = new DeliveryTargetsResponse.PageInfo();
            pageInfo.setOffset(0);
            pageInfo.setLimit(allDeliveryTargets.size());
            pageInfo.setTotalCount(allDeliveryTargets.size());
            finalResponse.setPageInfo(pageInfo);
        }

        return finalResponse;
    }

    /**
     * Internal method to fetch a single page of delivery targets.
     *
     * @param search Search term to find a delivery target by name
     * @param offset Starting index for paginated results
     * @param limit Maximum number of results per page
     * @param segments Type of details to return
     * @param sortby Parameter to sort results by
     * @param sortOrder Sorting order (asc or desc)
     * @return DeliveryTargetsResponse containing the page of delivery targets
     * @throws ApiCallException if the API call fails or returns an error
     */
    private DeliveryTargetsResponse fetchDeliveryTargetsPage(
            String search,
            Integer offset,
            Integer limit,
            String segments,
            String sortby,
            String sortOrder) {
        try {
            log.debug(
                    "Fetching delivery targets page (search: {}, offset: {}, limit: {}, segments: {}, sortby: {}, sortOrder: {})",
                    search,
                    offset,
                    limit,
                    segments,
                    sortby,
                    sortOrder);

            // Build URI with query parameters
            String baseUrl = config.getDeliveryTargetsUrl();
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
                urlBuilder.append("segments=").append(segments).append("&");
            }
            if (sortby != null && !sortby.isBlank()) {
                urlBuilder.append("sortby=").append(sortby).append("&");
            }
            if (sortOrder != null && !sortOrder.isBlank()) {
                urlBuilder.append("sortOrder=").append(sortOrder);
            }

            // Remove trailing '&' if present
            String urlString = urlBuilder.toString();
            if (urlString.endsWith("&")) {
                urlString = urlString.substring(0, urlString.length() - 1);
            }

            URI uri = new URI(urlString);
            log.debug("Delivery targets URL: {}", uri);

            // Build HTTP request
            HttpRequest request =
                    HttpRequest.newBuilder()
                            .uri(uri)
                            .header("Content-Type", CONTENT_TYPE_JSON)
                            .header("Authorization", "Bearer " + informaticaApiClient.getJwtToken())
                            .GET()
                            .build();

            log.debug("Sending GET request to fetch delivery targets page");

            // Send request
            HttpResponse<String> response =
                    getHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

            // Validate response
            HttpResponseValidator.validateResponse(
                    response, HTTP_OK, "Delivery targets retrieval", ApiCallException::new);

            log.debug("Delivery targets response received (status: 200)");
            log.trace("Response body: {}", response.body());

            // Parse response
            DeliveryTargetsResponse deliveryTargetsResponse =
                    objectMapper.readValue(response.body(), DeliveryTargetsResponse.class);

            int pageCount =
                    deliveryTargetsResponse.getItems() != null
                            ? deliveryTargetsResponse.getItems().size()
                            : 0;
            log.info("Retrieved {} delivery targets from page (offset: {})", pageCount, offset);

            return deliveryTargetsResponse;

        } catch (ApiCallException e) {
            log.error("API call failed while fetching delivery targets: {}", e.getMessage(), e);
            throw e;

        } catch (Exception e) {
            log.error("Unexpected error during delivery targets retrieval: {}", e.getMessage(), e);
            throw new ApiCallException(ExceptionFormatter.format(e));
        }
    }

    public DeliveryTarget getDeliveryTarget(String deliveryTargetId, String segments) {
        try {
            log.debug(
                    "Fetching delivery target (ID: {}, segments: {})", deliveryTargetId, segments);

            // Build URI with query parameters
            String baseUrl = config.getDeliveryTargetsUrl();
            StringBuilder urlBuilder = new StringBuilder(baseUrl);
            urlBuilder.append("/").append(deliveryTargetId);

            // Add query parameters
            if (segments != null && !segments.isBlank()) {
                urlBuilder.append("?segments=").append(segments);
            }

            URI uri = new URI(urlBuilder.toString());
            log.debug("Delivery target URL: {}", uri);

            // Build HTTP request
            HttpRequest request =
                    HttpRequest.newBuilder()
                            .uri(uri)
                            .header("Content-Type", CONTENT_TYPE_JSON)
                            .header("Authorization", "Bearer " + informaticaApiClient.getJwtToken())
                            .GET()
                            .build();

            log.debug("Sending GET request to fetch delivery targets page");

            // Send request
            HttpResponse<String> response =
                    getHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

            // Validate response
            HttpResponseValidator.validateResponse(
                    response, HTTP_OK, "Delivery targets retrieval", ApiCallException::new);

            log.debug("Delivery target response received (status: 200)");
            log.trace("Response body: {}", response.body());

            // Parse and return response
            return objectMapper.readValue(response.body(), DeliveryTarget.class);

        } catch (ApiCallException e) {
            log.error("API call failed while fetching delivery targets: {}", e.getMessage(), e);
            throw e;

        } catch (Exception e) {
            log.error("Unexpected error during delivery targets retrieval: {}", e.getMessage(), e);
            throw new ApiCallException(ExceptionFormatter.format(e));
        }
    }

    /**
     * Updates a delivery target in Informatica Marketplace.
     *
     * <p>This method sends a PATCH request to modify various aspects of a delivery target based on
     * the operation and segment specified in the request.
     *
     * @param deliveryTargetId The system-generated unique identifier of the delivery target to
     *     update
     * @param request The update request containing operation, segment, and data to modify
     * @return true if delivery target was updated, false otherwise
     * @throws ApiCallException if the API call fails or returns an error
     */
    public boolean updateDeliveryTarget(
            String deliveryTargetId, UpdateDeliveryTargetRequest request) {
        if (deliveryTargetId == null || deliveryTargetId.isBlank()) {
            log.warn("Delivery target ID is null or blank, cannot update");
            return false;
        }

        if (request == null) {
            log.warn("Update request is null, cannot update delivery target");
            return false;
        }

        try {
            log.debug(
                    "Updating delivery target '{}' (operation: {}, segment: {})",
                    deliveryTargetId,
                    request.getOperation(),
                    request.getSegment());

            // Build URI
            String url = config.getDeliveryTargetsUrl() + "/" + deliveryTargetId;
            URI uri = new URI(url);
            log.debug("Update URL: {}", uri);

            // Serialize request body
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

            log.debug("Sending PATCH request to update delivery target");

            // Send request
            HttpResponse<String> response =
                    getHttpClient().send(httpRequest, HttpResponse.BodyHandlers.ofString());

            // Validate response
            // 204 No Content: Delivery target successfully modified (immediate)
            int statusCode = response.statusCode();

            if (statusCode == HTTP_NO_CONTENT) {
                log.info(
                        "Delivery target '{}' updated successfully (operation: {}, segment: {})",
                        deliveryTargetId,
                        request.getOperation(),
                        request.getSegment());
                log.debug("Response status: 204 No Content - modification applied immediately");
                return true; // No response body for immediate updates

            } else {
                String errorBody = response.body();
                log.error(
                        "Delivery target update failed with status {}: {}", statusCode, errorBody);
                throw new ApiCallException(
                        String.format(
                                "Update request failed with status %d. Expected 204 or 202. Response: %s",
                                statusCode, errorBody));
            }

        } catch (ApiCallException e) {
            log.error(
                    "API call failed while updating delivery target '{}': {}",
                    deliveryTargetId,
                    e.getMessage(),
                    e);
            throw e;

        } catch (Exception e) {
            log.error(
                    "Unexpected error during delivery target update for '{}': {}",
                    deliveryTargetId,
                    e.getMessage(),
                    e);
            throw new ApiCallException(ExceptionFormatter.format(e));
        }
    }

    /**
     * Retrieves a delivery template by its reference ID (refId).
     *
     * <p>This method searches through all delivery templates to find one matching the specified
     * refId. The refId is a user-defined reference identifier (e.g.,
     * "snowflake_standard_output_port").
     *
     * <p><b>Use Case:</b> When you know the refId of a template but need its system-generated ID to
     * create a delivery target.
     *
     * @param refId The reference identifier of the delivery template (e.g.,
     *     "snowflake_standard_output_port")
     * @return The DeliveryTemplate object if found, null otherwise
     * @throws ApiCallException if the API call fails or returns an error
     */
    public GetDeliveryTemplatesResponse.DeliveryTemplate getDeliveryTemplateByRefId(String refId) {
        if (refId == null || refId.isBlank()) {
            log.warn("Delivery template refId is null or blank, returning null");
            return null;
        }

        log.debug("Searching for delivery template with refId: '{}'", refId);

        // Get all delivery templates
        GetDeliveryTemplatesRequest request = GetDeliveryTemplatesRequest.builder().build();
        List<GetDeliveryTemplatesResponse.DeliveryTemplate> allTemplates =
                getAllDeliveryTemplatesPaginated(request);

        // Search for template with matching refId
        GetDeliveryTemplatesResponse.DeliveryTemplate found =
                allTemplates.stream()
                        .filter(template -> refId.equals(template.getRefId()))
                        .findFirst()
                        .orElse(null);

        if (found != null) {
            log.info(
                    "Delivery template found: refId='{}', ID='{}', name='{}'",
                    refId,
                    found.getId(),
                    found.getName());
            return found;
        }

        log.warn("Delivery template with refId '{}' not found", refId);
        return null;
    }

    /**
     * Checks if a delivery template with the given refId exists.
     *
     * @param refId The reference identifier of the delivery template
     * @return true if a template with the given refId exists, false otherwise
     * @throws ApiCallException if the API call fails or returns an error
     */
    public boolean deliveryTemplateExistsByRefId(String refId) {
        return getDeliveryTemplateByRefId(refId) != null;
    }
}
