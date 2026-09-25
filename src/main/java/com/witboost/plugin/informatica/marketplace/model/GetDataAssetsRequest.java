package com.witboost.plugin.informatica.marketplace.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Request parameters for retrieving data assets from Informatica Marketplace.
 *
 * <p>This class represents the GET request query parameters to retrieve data assets with optional
 * filtering, pagination, and sorting.
 *
 * <p>All fields are optional query parameters.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetDataAssetsRequest {

    /**
     * Search term to find data assets. Note: The search term should not contain an asterisk (*).
     * Optional.
     */
    @JsonProperty("search")
    private String search;

    /**
     * Fields on which the search term applies. Values: "NAME", "DESCRIPTION", "SOURCE", "TYPE",
     * "DESCRIPTIVE_SOURCE" Optional.
     */
    @JsonProperty("fields")
    private String fields;

    /**
     * Starting date for data assets created between a date range. Format: YYYY-MM-DD Optional. Must
     * be paired with createdDateTo.
     */
    @JsonProperty("createdDateFrom")
    private String createdDateFrom;

    /**
     * Ending date for data assets created between a date range. Format: YYYY-MM-DD Optional. Must
     * be paired with createdDateFrom.
     */
    @JsonProperty("createdDateTo")
    private String createdDateTo;

    /**
     * Starting date for data assets modified between a date range. Format: YYYY-MM-DD Optional.
     * Must be paired with modifiedDateTo.
     */
    @JsonProperty("modifiedDateFrom")
    private String modifiedDateFrom;

    /**
     * Ending date for data assets modified between a date range. Format: YYYY-MM-DD Optional. Must
     * be paired with modifiedDateFrom.
     */
    @JsonProperty("modifiedDateTo")
    private String modifiedDateTo;

    /**
     * Status of the data asset. Values: "ENABLED" (available), "DISABLED" (not available) Optional.
     */
    @JsonProperty("status")
    private String status;

    /**
     * Parameters to sort the search results. Values: "ID", "NAME", "SOURCE", "DESCRIPTIVE_SOURCE",
     * "TYPE", "ASSET_LOCATION", "ASSET_LOCATION_DESCRIPTION", "TECHNICAL_ASSET_NAME", "STATUS",
     * "CREATED_BY", "CREATED_ON", "MODIFIED_BY", "MODIFIED_ON" Default value: "MODIFIED_ON"
     * Optional.
     */
    @JsonProperty("sortByField")
    private String sortByField;

    /**
     * Sorting order of the search results. Values: "ASC" (ascending), "DESC" (descending) Default
     * value: "DESC" Optional.
     */
    @JsonProperty("sort")
    private String sort;

    /** Starting index for paginated results. Default value: 0 Optional. */
    @JsonProperty("offset")
    private Integer offset;

    /** Maximum number of results to return. Default value: 50 Maximum value: 100 Optional. */
    @JsonProperty("limit")
    private Integer limit;

    // Utility methods for common operations

    /** Creates a request with default pagination (offset=0, limit=50). */
    public static GetDataAssetsRequest withDefaults() {
        return GetDataAssetsRequest.builder()
                .offset(0)
                .limit(50)
                .sortByField("MODIFIED_ON")
                .sort("DESC")
                .build();
    }

    /** Creates a request with pagination parameters. */
    public static GetDataAssetsRequest withPagination(Integer offset, Integer limit) {
        return GetDataAssetsRequest.builder()
                .offset(offset)
                .limit(limit)
                .sortByField("MODIFIED_ON")
                .sort("DESC")
                .build();
    }

    /** Creates a request to search by term with default pagination. */
    public static GetDataAssetsRequest searchByTerm(String search) {
        return GetDataAssetsRequest.builder()
                .search(search)
                .offset(0)
                .limit(50)
                .sortByField("MODIFIED_ON")
                .sort("DESC")
                .build();
    }

    /** Creates a request filtered by status. */
    public static GetDataAssetsRequest filterByStatus(String status) {
        return GetDataAssetsRequest.builder()
                .status(status)
                .offset(0)
                .limit(50)
                .sortByField("MODIFIED_ON")
                .sort("DESC")
                .build();
    }
}
