package com.witboost.plugin.informatica.marketplace.service.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.witboost.plugin.informatica.common.client.BaseClient;
import com.witboost.plugin.informatica.common.client.InformaticaApiClient;
import com.witboost.plugin.informatica.common.exceptions.ApiCallException;
import com.witboost.plugin.informatica.common.exceptions.ExceptionFormatter;
import com.witboost.plugin.informatica.common.utils.HttpResponseValidator;
import com.witboost.plugin.informatica.marketplace.config.MarketplaceApiConfig;
import com.witboost.plugin.informatica.marketplace.model.CategoriesResponse;
import com.witboost.plugin.informatica.marketplace.model.Category;
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
 * API client for Informatica Marketplace Categories.
 *
 * <p>Provides methods to interact with categories in the Informatica Marketplace.
 */
@Service
@Slf4j
public class CategoryApiClient extends BaseClient {

    private static final int HTTP_OK = 200;
    private static final ObjectMapper objectMapper =
            new ObjectMapper().registerModule(new JavaTimeModule());

    private final InformaticaApiClient informaticaApiClient;
    private final MarketplaceApiConfig config;

    public CategoryApiClient(
            InformaticaApiClient informaticaApiClient, MarketplaceApiConfig config) {
        this.informaticaApiClient = informaticaApiClient;
        this.config = config;
    }

    /**
     * Retrieves all categories from Informatica Marketplace with automatic pagination.
     *
     * <p>This method automatically handles pagination by fetching all pages until all categories
     * are retrieved. It uses default values for pagination parameters: offset=0, limit=50. Sorting
     * is done by modifiedOn in descending order by default.
     *
     * @param search Search term to find a category by name or description. Use "*" to retrieve all
     *     categories.
     * @param segments Type of details to return. Can include: - "all": Returns all category details
     *     - "parentCategory": Returns parent category details - "systemAttributes": Returns
     *     creation and modification details - "stakeholdership": Returns stakeholder type and role
     *     information
     * @return CategoriesResponse containing all retrieved categories across all pages
     * @throws ApiCallException if the API call fails or returns an error
     */
    public CategoriesResponse getAllCategories(String search, String segments) {
        List<Category> allCategories = new ArrayList<>();
        int offset = 0;
        int limit = 50; // Fetch 50 items per page for efficiency
        int totalCount = -1; // Initialize to -1 to fetch at least one page

        log.info(
                "Starting paginated fetch for categories (search: {}, segments: {})",
                search,
                segments);

        while (offset == 0 || offset < totalCount) {
            log.debug("Fetching page at offset {} (limit: {})", offset, limit);

            // Internal call with all parameters
            CategoriesResponse response =
                    fetchCategoriesPage(search, offset, limit, segments, "modifiedOn", "desc");

            if (response.getPageInfo() != null && response.getPageInfo().getTotalCount() != null) {
                totalCount = response.getPageInfo().getTotalCount();
                log.debug("Total count: {}", totalCount);
            }

            if (response.getItems() != null && !response.getItems().isEmpty()) {
                allCategories.addAll(response.getItems());
                log.debug(
                        "Added {} items, total collected: {} / {}",
                        response.getItems().size(),
                        allCategories.size(),
                        totalCount);

                offset += response.getItems().size();
            } else {
                log.debug("No more items returned, stopping pagination");
                break;
            }

            // Safety check to prevent infinite loops
            if (totalCount > 0 && allCategories.size() >= totalCount) {
                log.debug("All items collected, stopping pagination");
                break;
            }
        }

        log.info("Completed paginated fetch: retrieved {} categories", allCategories.size());

        // Create response with all collected categories
        CategoriesResponse finalResponse = new CategoriesResponse();
        finalResponse.setItems(allCategories);

        // Set page info with totals
        if (!allCategories.isEmpty()) {
            CategoriesResponse.PageInfo pageInfo = new CategoriesResponse.PageInfo();
            pageInfo.setOffset(0);
            pageInfo.setLimit(allCategories.size());
            pageInfo.setTotalCount(allCategories.size());
            finalResponse.setPageInfo(pageInfo);
        }

        return finalResponse;
    }

    /**
     * Internal method to fetch a single page of categories.
     *
     * @param search Search term to find a category by name or description
     * @param offset Starting index for paginated results
     * @param limit Maximum number of results per page
     * @param segments Type of details to return
     * @param sortby Parameter to sort results by
     * @param sortOrder Sorting order (asc or desc)
     * @return CategoriesResponse containing the page of categories
     * @throws ApiCallException if the API call fails or returns an error
     */
    private CategoriesResponse fetchCategoriesPage(
            String search,
            Integer offset,
            Integer limit,
            String segments,
            String sortby,
            String sortOrder) {
        try {
            log.debug(
                    "Fetching categories page (search: {}, offset: {}, limit: {}, segments: {}, sortby: {}, sortOrder: {})",
                    search,
                    offset,
                    limit,
                    segments,
                    sortby,
                    sortOrder);

            // Build URI with query parameters
            String baseUrl = config.getCategoriesUrl();
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
            log.debug("Categories URL: {}", uri);

            // Build HTTP request
            HttpRequest request =
                    HttpRequest.newBuilder()
                            .uri(uri)
                            .header("Content-Type", CONTENT_TYPE_JSON)
                            .header("Authorization", "Bearer " + informaticaApiClient.getJwtToken())
                            .GET()
                            .build();

            log.debug("Sending GET request to fetch categories page");

            // Send request
            HttpResponse<String> response =
                    getHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

            // Validate response
            HttpResponseValidator.validateResponse(
                    response, HTTP_OK, "Categories retrieval", ApiCallException::new);

            log.debug("Categories response received (status: 200)");
            log.trace("Response body: {}", response.body());

            // Parse response
            CategoriesResponse categoriesResponse =
                    objectMapper.readValue(response.body(), CategoriesResponse.class);

            int pageCount =
                    categoriesResponse.getItems() != null
                            ? categoriesResponse.getItems().size()
                            : 0;
            log.info("Retrieved {} categories from page (offset: {})", pageCount, offset);

            return categoriesResponse;

        } catch (ApiCallException e) {
            log.error("API call failed while fetching categories: {}", e.getMessage(), e);
            throw e;

        } catch (Exception e) {
            log.error("Unexpected error during categories retrieval: {}", e.getMessage(), e);
            throw new ApiCallException(ExceptionFormatter.format(e));
        }
    }
}
