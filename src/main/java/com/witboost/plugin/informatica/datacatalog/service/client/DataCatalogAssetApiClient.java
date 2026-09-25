package com.witboost.plugin.informatica.datacatalog.service.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.witboost.plugin.informatica.common.client.BaseClient;
import com.witboost.plugin.informatica.common.client.InformaticaApiClient;
import com.witboost.plugin.informatica.common.exceptions.ApiCallException;
import com.witboost.plugin.informatica.common.exceptions.ExceptionFormatter;
import com.witboost.plugin.informatica.common.utils.HttpResponseValidator;
import com.witboost.plugin.informatica.datacatalog.config.DataCatalogApiConfig;
import com.witboost.plugin.informatica.datacatalog.model.AssetGetResponse;
import com.witboost.plugin.informatica.datacatalog.model.GetAssetNeighborhoodResponse;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@Getter
public class DataCatalogAssetApiClient extends BaseClient {

    private static final int DEFAULT_PAGE_SIZE = 100;
    private static final int INITIAL_OFFSET = 0;
    private static final String SEGMENTS_ALL = "all";

    private final ObjectMapper objectMapper;

    private final DataCatalogApiConfig config;

    private final InformaticaApiClient informaticaApiClient;

    public DataCatalogAssetApiClient(
            DataCatalogApiConfig config, InformaticaApiClient informaticaApiClient) {
        this.config = config;
        this.informaticaApiClient = informaticaApiClient;
        this.objectMapper = new ObjectMapper();
    }

    /**
     * Queries assets from Informatica API with pagination support.
     *
     * @param body JSON request body containing pagination parameters (from, size)
     * @param query Knowledge query string for filtering assets
     * @return AssetGetResponse containing the query results
     * @throws ApiCallException if the API call fails or returns a non-200 status
     */
    public AssetGetResponse queryAssets(String body, String query) {
        try {
            String encodedQuery = URLEncoder.encode(query, StandardCharsets.UTF_8);
            URI uri =
                    new URI(
                            config.getAssetListUrl()
                                    + "?knowledgeQuery="
                                    + encodedQuery
                                    + "&segments="
                                    + SEGMENTS_ALL);

            log.debug("Querying assets with query: {} and body: {}", query, body);

            HttpRequest request =
                    getHttpRequestBuilder()
                            .uri(uri)
                            .header("Content-Type", CONTENT_TYPE_JSON)
                            .header("INFA-SESSION-ID", informaticaApiClient.getSessionId())
                            .header("X-INFA-ORG-ID", informaticaApiClient.getOrgId())
                            .header("Authorization", "Bearer " + informaticaApiClient.getJwtToken())
                            .POST(HttpRequest.BodyPublishers.ofString(body))
                            .build();

            HttpResponse<String> httpResponse =
                    httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            HttpResponseValidator.validateResponse(
                    httpResponse, HTTP_OK, "Asset query", ApiCallException::new);

            String responseBody = httpResponse.body();
            log.debug("Received response with {} characters", responseBody.length());

            return objectMapper.readValue(responseBody, AssetGetResponse.class);

        } catch (URISyntaxException e) {
            log.error("Invalid URI constructed for asset query: {}", e.getMessage());
            throw new ApiCallException("Invalid URI: " + ExceptionFormatter.format(e));
        } catch (IOException e) {
            log.error("IO error during asset query: {}", e.getMessage());
            throw new ApiCallException("IO error: " + ExceptionFormatter.format(e));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            log.error("Asset query interrupted: {}", e.getMessage());
            throw new ApiCallException("Request interrupted: " + ExceptionFormatter.format(e));
        } catch (Exception e) {
            log.error("Unexpected error during asset query: {}", e.getMessage(), e);
            throw new ApiCallException(ExceptionFormatter.format(e));
        }
    }

    /**
     * Polls for all results by paginating through the API responses. Automatically handles
     * pagination until all results are retrieved.
     *
     * @param query Knowledge query string for filtering assets
     * @return AssetGetResponse containing all paginated results
     * @throws ApiCallException if any API call fails
     */
    public AssetGetResponse pollForResults(String query) {
        return pollForResults(query, DEFAULT_PAGE_SIZE);
    }

    /**
     * Polls for all results by paginating through the API responses with custom page size.
     *
     * @param query Knowledge query string for filtering assets
     * @param pageSize Number of results per page
     * @return AssetGetResponse containing all paginated results
     * @throws ApiCallException if any API call fails
     */
    public AssetGetResponse pollForResults(String query, int pageSize) {
        log.info(
                "Starting to poll for all results with query: {} and page size: {}",
                query,
                pageSize);

        String body = buildPaginationBody(INITIAL_OFFSET, pageSize);
        AssetGetResponse response = queryAssets(body, query);

        if (response == null) {
            log.warn("Received null response for query: {}", query);
            return new AssetGetResponse(); // Return empty response instead of null
        }

        if (response.getHits() == null) {
            log.debug("No hits found for query: {}", query);
            return response;
        }

        if (response.getSummary() == null || response.getSummary().getTotalHits() == null) {
            log.warn("Response summary or totalHits is null, returning current results");
            return response;
        }

        int totalHits = parseTotalHits(response.getSummary().getTotalHits());
        int currentHits = response.getHits().size();
        log.info("Initial fetch: {} hits out of {} total", currentHits, totalHits);

        int offset = pageSize;
        int pageNumber = 2;

        while (offset < totalHits) {
            body = buildPaginationBody(offset, pageSize);
            log.debug("Fetching page {} with offset {}", pageNumber, offset);

            AssetGetResponse pageResponse = queryAssets(body, query);
            if (pageResponse != null && pageResponse.getHits() != null) {
                response.getHits().addAll(pageResponse.getHits());
                log.debug("Added {} hits from page {}", pageResponse.getHits().size(), pageNumber);
            }

            offset += pageSize;
            pageNumber++;
        }

        log.info("Completed polling: retrieved {} total hits", response.getHits().size());
        return response;
    }

    /**
     * Builds a JSON pagination body with from and size parameters.
     *
     * @param from Starting offset
     * @param size Page size
     * @return JSON string representing the pagination parameters
     */
    private String buildPaginationBody(int from, int size) {
        try {
            Map<String, Integer> pagination = new HashMap<>();
            pagination.put("from", from);
            pagination.put("size", size);
            return objectMapper.writeValueAsString(pagination);
        } catch (Exception e) {
            // Fallback to manual construction if JSON serialization fails
            log.warn("Failed to serialize pagination body, using fallback: {}", e.getMessage());
            return String.format("{\"from\":%d, \"size\":%d}", from, size);
        }
    }

    /**
     * Safely parses total hits string to integer.
     *
     * @param totalHitsStr String representation of total hits
     * @return Parsed integer value, or 0 if parsing fails
     */
    private int parseTotalHits(String totalHitsStr) {
        try {
            return Integer.parseInt(totalHitsStr);
        } catch (NumberFormatException e) {
            log.error("Failed to parse totalHits: {}", totalHitsStr, e);
            return 0;
        }
    }

    /**
     * Retrieves details of neighbors for a given asset.
     *
     * @param assetExternalId External identifier of the asset
     * @param neighborClassType Class type to filter neighbors by
     * @return GetAssetNeighborhoodResponse containing neighborhood details
     * @throws ApiCallException if the API call fails or returns a non-200 status
     */
    public GetAssetNeighborhoodResponse getAssetNeighbors(
            String assetExternalId, String neighborClassType) {
        if (assetExternalId == null || assetExternalId.isBlank()) {
            throw new ApiCallException("assetExternalId cannot be null or empty");
        }
        if (neighborClassType == null || neighborClassType.isBlank()) {
            throw new ApiCallException("neighborClassType cannot be null or empty");
        }

        try {
            String segments = "neighborhood:" + neighborClassType;
            URI uri =
                    new URI(
                            config.getAssetListUrl()
                                    + "/"
                                    + assetExternalId
                                    + "?segments="
                                    + URLEncoder.encode(segments, StandardCharsets.UTF_8));

            log.debug(
                    "Fetching neighbors for asset: {} with class type: {}",
                    assetExternalId,
                    neighborClassType);

            HttpRequest request =
                    getHttpRequestBuilder()
                            .uri(uri)
                            .header("Content-Type", CONTENT_TYPE_JSON)
                            .header("INFA-SESSION-ID", informaticaApiClient.getSessionId())
                            .header("X-INFA-ORG-ID", informaticaApiClient.getOrgId())
                            .header("Authorization", "Bearer " + informaticaApiClient.getJwtToken())
                            .GET()
                            .build();

            HttpResponse<String> httpResponse =
                    httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            HttpResponseValidator.validateResponse(
                    httpResponse, HTTP_OK, "Asset neighbors query", ApiCallException::new);

            String responseBody = httpResponse.body();
            log.debug("Received neighbors response with {} characters", responseBody.length());

            return objectMapper.readValue(responseBody, GetAssetNeighborhoodResponse.class);

        } catch (URISyntaxException e) {
            log.error("Invalid URI constructed for asset neighbors query: {}", e.getMessage());
            throw new ApiCallException("Invalid URI: " + ExceptionFormatter.format(e));
        } catch (IOException e) {
            log.error("IO error during asset neighbors query: {}", e.getMessage());
            throw new ApiCallException("IO error: " + ExceptionFormatter.format(e));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            log.error("Asset neighbors query interrupted: {}", e.getMessage());
            throw new ApiCallException("Request interrupted: " + ExceptionFormatter.format(e));
        } catch (Exception e) {
            log.error("Unexpected error during asset neighbors query: {}", e.getMessage(), e);
            throw new ApiCallException(ExceptionFormatter.format(e));
        }
    }

    /**
     * Deletes an asset from Informatica Data Catalog.
     *
     * @param assetID The ID of the asset to delete
     * @throws ApiCallException if the API call fails or returns a non-201 status
     */
    public void deleteAsset(String assetID) {
        deleteAsset(assetID, null);
    }

    /**
     * Deletes an asset from Informatica Data Catalog with optional scheme.
     *
     * @param assetID The ID of the asset to delete
     * @param scheme Optional scheme filter (INTERNAL or EXTERNAL)
     * @throws ApiCallException if the API call fails or returns a non-201 status
     */
    public void deleteAsset(String assetID, AssetScheme scheme) {
        if (assetID == null || assetID.isBlank()) {
            throw new ApiCallException("assetID cannot be null or empty");
        }

        try {
            StringBuilder urlBuilder =
                    new StringBuilder(config.getAssetManageUrl()).append("/").append(assetID);
            if (scheme != null) {
                urlBuilder.append("?scheme=").append(scheme.getValue());
            }

            URI uri = new URI(urlBuilder.toString());
            log.debug("Deleting asset with ID: {} and scheme: {}", assetID, scheme);

            HttpRequest request =
                    getHttpRequestBuilder()
                            .uri(uri)
                            .header("Content-Type", CONTENT_TYPE_JSON)
                            .header("INFA-SESSION-ID", informaticaApiClient.getSessionId())
                            .header("X-INFA-ORG-ID", informaticaApiClient.getOrgId())
                            .header("Authorization", "Bearer " + informaticaApiClient.getJwtToken())
                            .DELETE()
                            .build();

            HttpResponse<String> httpResponse =
                    httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            HttpResponseValidator.validateResponse(
                    httpResponse, 201, "Asset deletion", ApiCallException::new);

            log.info("Successfully deleted asset with ID: {}", assetID);

        } catch (URISyntaxException e) {
            log.error("Invalid URI constructed for asset deletion: {}", e.getMessage());
            throw new ApiCallException("Invalid URI: " + ExceptionFormatter.format(e));
        } catch (IOException e) {
            log.error("IO error during asset deletion: {}", e.getMessage());
            throw new ApiCallException("IO error: " + ExceptionFormatter.format(e));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            log.error("Asset deletion interrupted: {}", e.getMessage());
            throw new ApiCallException("Request interrupted: " + ExceptionFormatter.format(e));
        } catch (Exception e) {
            log.error("Unexpected error during asset deletion: {}", e.getMessage(), e);
            throw new ApiCallException(ExceptionFormatter.format(e));
        }
    }

    /**
     * Handles relationship operations (add, remove) for assets in Informatica Data Catalog.
     *
     * @param operation The operation type (add, remove)
     * @param associationType The association type for the relationship
     * @param fromExternalId External ID of the source asset
     * @param toExternalId External ID of the target asset
     * @throws ApiCallException if the API call fails or any parameter is invalid
     */
    public void handleRelationship(
            String operation, String associationType, String fromExternalId, String toExternalId) {
        if (operation == null || operation.isBlank()) {
            throw new ApiCallException("operation cannot be null or empty");
        }
        if (associationType == null || associationType.isBlank()) {
            throw new ApiCallException("associationType cannot be null or empty");
        }
        if (fromExternalId == null || fromExternalId.isBlank()) {
            throw new ApiCallException("fromExternalId cannot be null or empty");
        }
        if (toExternalId == null || toExternalId.isBlank()) {
            throw new ApiCallException("toExternalId cannot be null or empty");
        }

        try {
            URI uri =
                    new URI(config.getAssetManageUrl() + "/" + fromExternalId + "?scheme=EXTERNAL");

            Map<String, Object> relationshipItem = new HashMap<>();
            relationshipItem.put("fromExternalIdentity", fromExternalId);
            relationshipItem.put("toExternalIdentity", toExternalId);
            relationshipItem.put("association", associationType);

            Map<String, Object> relationshipOperation = new HashMap<>();
            relationshipOperation.put("operation", operation);
            relationshipOperation.put("segment", "relationship");
            relationshipOperation.put("items", java.util.List.of(relationshipItem));

            String body = objectMapper.writeValueAsString(java.util.List.of(relationshipOperation));

            log.debug(
                    "Handling relationship: operation={}, associationType={}, fromExternalId={}, toExternalId={}",
                    operation,
                    associationType,
                    fromExternalId,
                    toExternalId);

            HttpRequest request =
                    getHttpRequestBuilder()
                            .uri(uri)
                            .header("Content-Type", CONTENT_TYPE_JSON)
                            .header("INFA-SESSION-ID", informaticaApiClient.getSessionId())
                            .header("X-INFA-ORG-ID", informaticaApiClient.getOrgId())
                            .header("Authorization", "Bearer " + informaticaApiClient.getJwtToken())
                            .method("PATCH", HttpRequest.BodyPublishers.ofString(body))
                            .build();

            HttpResponse<String> httpResponse =
                    httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            HttpResponseValidator.validateResponse(
                    httpResponse, HTTP_OK, "Relationship operation", ApiCallException::new);

            log.info("Successfully handled relationship with operation: {}", operation);

        } catch (URISyntaxException e) {
            log.error("Invalid URI constructed for relationship operation: {}", e.getMessage());
            throw new ApiCallException("Invalid URI: " + ExceptionFormatter.format(e));
        } catch (IOException e) {
            log.error("IO error during relationship operation: {}", e.getMessage());
            throw new ApiCallException("IO error: " + ExceptionFormatter.format(e));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            log.error("Relationship operation interrupted: {}", e.getMessage());
            throw new ApiCallException("Request interrupted: " + ExceptionFormatter.format(e));
        } catch (Exception e) {
            log.error("Unexpected error during relationship operation: {}", e.getMessage(), e);
            throw new ApiCallException(ExceptionFormatter.format(e));
        }
    }
}
