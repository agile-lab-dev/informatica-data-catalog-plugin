package com.witboost.plugin.informatica.marketplace.service.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.witboost.plugin.informatica.common.client.BaseClient;
import com.witboost.plugin.informatica.common.client.InformaticaApiClient;
import com.witboost.plugin.informatica.common.exceptions.ApiCallException;
import com.witboost.plugin.informatica.common.exceptions.ExceptionFormatter;
import com.witboost.plugin.informatica.marketplace.config.MarketplaceApiConfig;
import com.witboost.plugin.informatica.marketplace.model.CustomAttributesResponse;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CustomAttributeApiClient extends BaseClient {

    private static final ObjectMapper objectMapper =
            new ObjectMapper().registerModule(new JavaTimeModule());

    private final InformaticaApiClient informaticaApiClient;
    private final MarketplaceApiConfig config;

    public CustomAttributeApiClient(
            InformaticaApiClient informaticaApiClient, MarketplaceApiConfig config) {
        this.informaticaApiClient = informaticaApiClient;
        this.config = config;
    }

    /**
     * Retrieves custom attributes from Informatica Marketplace.
     *
     * <p>This method fetches custom attribute details for a specific item type (data collection,
     * consumer access, or order) configured in Metadata Command Center.
     *
     * @param classType The type of item for which to retrieve custom attributes. Use constants from
     *     {@link CustomAttributesResponse.ClassType}:
     *     <ul>
     *       <li>DATA_COLLECTION - for data collection custom attributes
     *       <li>CONSUMER_ACCESS - for consumer access custom attributes
     *       <li>ORDER - for order custom attributes
     *     </ul>
     *
     * @param offset Starting index for paginated results (default: 0)
     * @param limit Maximum number of results (default: 50, max: 100)
     * @return CustomAttributesResponse containing the list of custom attributes
     * @throws ApiCallException if the API call fails or returns an error
     */
    public CustomAttributesResponse getCustomAttributes(String classType, int offset, int limit) {
        if (classType == null || classType.isBlank()) {
            log.warn("Class type is null or blank, cannot retrieve custom attributes");
            throw new ApiCallException("Class type is required");
        }

        try {
            log.debug(
                    "Fetching custom attributes for classType: {} (offset: {}, limit: {})",
                    classType,
                    offset,
                    limit);

            // Build URI with query parameters
            String url =
                    String.format(
                            "%s?classType=%s&offset=%d&limit=%d",
                            config.getCustomAttributesUrl(), classType, offset, limit);
            URI uri = new URI(url);
            log.debug("Custom attributes URL: {}", uri);

            // Build HTTP request
            HttpRequest httpRequest =
                    HttpRequest.newBuilder()
                            .uri(uri)
                            .header("Content-Type", CONTENT_TYPE_JSON)
                            .header("Authorization", "Bearer " + informaticaApiClient.getJwtToken())
                            .GET()
                            .build();

            log.debug("Sending GET request to fetch custom attributes");

            // Send request
            HttpResponse<String> response =
                    getHttpClient().send(httpRequest, HttpResponse.BodyHandlers.ofString());

            // Validate response (200 OK)
            int statusCode = response.statusCode();

            if (statusCode == HTTP_OK) {
                String responseBody = response.body();
                log.debug("Custom attributes response received (status: 200)");
                log.trace("Response body: {}", responseBody);

                // Parse response
                CustomAttributesResponse customAttributesResponse =
                        objectMapper.readValue(responseBody, CustomAttributesResponse.class);

                int totalCount =
                        customAttributesResponse.getTotalCount() != null
                                ? customAttributesResponse.getTotalCount()
                                : 0;
                int itemsCount =
                        customAttributesResponse.getItems() != null
                                ? customAttributesResponse.getItems().size()
                                : 0;

                log.info(
                        "Successfully retrieved {} custom attributes for classType '{}' (total: {})",
                        itemsCount,
                        classType,
                        totalCount);

                return customAttributesResponse;

            } else {
                String errorBody = response.body();
                log.error(
                        "Custom attributes retrieval failed with status {}: {}",
                        statusCode,
                        errorBody);
                throw new ApiCallException(
                        String.format(
                                "Get custom attributes request failed with status %d. Expected 200 OK. Response: %s",
                                statusCode, errorBody));
            }

        } catch (ApiCallException e) {
            log.error(
                    "API call failed while fetching custom attributes for classType '{}': {}",
                    classType,
                    e.getMessage(),
                    e);
            throw e;

        } catch (Exception e) {
            log.error(
                    "Unexpected error during custom attributes retrieval for classType '{}': {}",
                    classType,
                    e.getMessage(),
                    e);
            throw new ApiCallException(ExceptionFormatter.format(e));
        }
    }

    /**
     * Retrieves custom attributes for data collections with default pagination.
     *
     * @return CustomAttributesResponse containing data collection custom attributes
     * @throws ApiCallException if the API call fails or returns an error
     */
    public CustomAttributesResponse getDataCollectionCustomAttributes() {
        return getCustomAttributes(CustomAttributesResponse.ClassType.DATA_COLLECTION, 0, 50);
    }

    /**
     * Retrieves all custom attributes for a specific class type by paginating through all results.
     *
     * <p><strong>Warning:</strong> This method loads all results into memory. Use with caution if
     * the total number of custom attributes is large.
     *
     * @param classType The type of item for which to retrieve custom attributes
     * @return CustomAttributesResponse containing all custom attributes (across all pages)
     * @throws ApiCallException if the API call fails or returns an error
     */
    public CustomAttributesResponse getAllCustomAttributes(String classType) {
        log.debug("Fetching all custom attributes for classType: {}", classType);

        List<CustomAttributesResponse.CustomAttributeItem> allItems = new ArrayList<>();
        int offset = 0;
        int limit = 100; // Use max limit for fewer API calls
        int totalCount = -1;

        while (offset == 0 || offset < totalCount) {
            log.debug("Fetching page at offset {} (limit: {})", offset, limit);

            CustomAttributesResponse response = getCustomAttributes(classType, offset, limit);

            // Get total count from first response
            if (response.getTotalCount() != null) {
                totalCount = response.getTotalCount();
                log.debug("Total custom attributes for classType '{}': {}", classType, totalCount);
            }

            // Collect items from current page
            if (response.getItems() != null && !response.getItems().isEmpty()) {
                allItems.addAll(response.getItems());
                log.debug(
                        "Collected {} items (total so far: {})",
                        response.getItems().size(),
                        allItems.size());
                offset += response.getItems().size();
            } else {
                log.debug("No more items returned, stopping pagination");
                break;
            }

            // Safety check to prevent infinite loops
            if (totalCount > 0 && offset >= totalCount) {
                log.debug("Reached end of results");
                break;
            }
        }

        log.info(
                "Retrieved all {} custom attributes for classType '{}'",
                allItems.size(),
                classType);

        // Build final response with all items
        CustomAttributesResponse finalResponse = new CustomAttributesResponse();
        finalResponse.setOffset(0);
        finalResponse.setLimit(allItems.size());
        finalResponse.setTotalCount(allItems.size());
        finalResponse.setClassType(classType);
        finalResponse.setItems(allItems);

        return finalResponse;
    }

    /**
     * Retrieves a specific custom attribute by name for data collections.
     *
     * <p>This method searches for a custom attribute with the specified name in the data collection
     * custom attributes. It uses pagination to search through all available attributes until the
     * matching one is found.
     *
     * @param name The name of the custom attribute to retrieve
     * @return CustomAttributeItem if found, null otherwise
     * @throws ApiCallException if the API call fails or returns an error
     */
    public CustomAttributesResponse.CustomAttributeItem getCustomAttributeByName(String name) {
        return getCustomAttributeByName(name, CustomAttributesResponse.ClassType.DATA_COLLECTION);
    }

    /**
     * Retrieves a specific custom attribute by name for a specific class type.
     *
     * <p>This method searches for a custom attribute with the specified name by paginating through
     * all available attributes until the matching one is found.
     *
     * @param name The name of the custom attribute to retrieve
     * @param classType The type of item for which to search the custom attribute
     * @return CustomAttributeItem if found, null otherwise
     * @throws ApiCallException if the API call fails or returns an error
     */
    public CustomAttributesResponse.CustomAttributeItem getCustomAttributeByName(
            String name, String classType) {
        if (name == null || name.isBlank()) {
            log.warn("Custom attribute name is null or blank, returning null");
            return null;
        }

        log.debug("Searching for custom attribute '{}' in classType '{}'", name, classType);

        int offset = 0;
        int limit = 100; // Use max limit for fewer API calls
        int totalCount = -1;

        while (offset == 0 || offset < totalCount) {
            log.debug(
                    "Fetching page at offset {} (limit: {}) to search for '{}'",
                    offset,
                    limit,
                    name);

            CustomAttributesResponse response = getCustomAttributes(classType, offset, limit);

            // Get total count from first response
            if (response.getTotalCount() != null) {
                totalCount = response.getTotalCount();
                log.debug("Total custom attributes for classType '{}': {}", classType, totalCount);
            }

            // Check if attribute exists in current page
            if (response.getItems() != null && !response.getItems().isEmpty()) {
                for (CustomAttributesResponse.CustomAttributeItem item : response.getItems()) {
                    if (name.equals(item.getName())) {
                        log.info(
                                "Custom attribute '{}' found (ID: {})",
                                name,
                                item.getCustomAttributeId());
                        return item;
                    }
                }

                log.debug(
                        "Custom attribute '{}' not found in current page, checking next page",
                        name);
                offset += response.getItems().size();
            } else {
                log.debug("No more items returned, custom attribute '{}' not found", name);
                break;
            }

            // Safety check to prevent infinite loops
            if (totalCount > 0 && offset >= totalCount) {
                log.debug("Reached end of results, custom attribute '{}' not found", name);
                break;
            }
        }

        log.info("Custom attribute '{}' does not exist in classType '{}'", name, classType);
        return null;
    }
}
