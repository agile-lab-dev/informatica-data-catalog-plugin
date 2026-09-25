package com.witboost.plugin.informatica.marketplace.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Request parameters for retrieving delivery templates from Informatica Marketplace.
 *
 * <p>This class represents the GET request query parameters to retrieve delivery templates with
 * optional filtering, pagination, and sorting.
 *
 * <p>All fields are optional query parameters.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetDeliveryTemplatesRequest {

    /**
     * Search term to find a delivery template. Note: The search term should not contain an asterisk
     * (*). Optional.
     */
    @JsonProperty("search")
    private String search;

    /** Fields on which the search term applies. Values: "NAME", "DESCRIPTION" Optional. */
    @JsonProperty("fields")
    private String fields;

    /**
     * System-generated unique identifier of a delivery template. Can be specified multiple times as
     * separate query parameters. Optional.
     */
    @JsonProperty("ids")
    private List<String> ids;

    /** Status of the delivery template. Values: "ACTIVE", "INACTIVE" Optional. */
    @JsonProperty("status")
    private String status;

    /**
     * Type of delivery. Values: "AUTOMATIC" (automated approval and fulfillment), "MANUAL" (manual
     * approval and fulfillment) Optional.
     */
    @JsonProperty("deliveryType")
    private String deliveryType;

    /**
     * Whether to retrieve the default delivery template for Data Marketplace. Values: true or false
     * Optional.
     */
    @JsonProperty("isDefault")
    private Boolean isDefault;

    /**
     * System-generated unique identifier of the delivery method that is part of the delivery
     * template. Can be specified multiple times as separate query parameters. Optional.
     */
    @JsonProperty("deliveryMethodIds")
    private List<String> deliveryMethodIds;

    /**
     * System-generated unique identifier of the delivery format that is part of the delivery
     * template. Can be specified multiple times as separate query parameters. Optional.
     */
    @JsonProperty("deliveryFormatIds")
    private List<String> deliveryFormatIds;

    /**
     * System-generated unique identifier of the Delivery Owner that manages the delivery template.
     * Can be specified multiple times as separate query parameters. Optional.
     */
    @JsonProperty("templateOwnerUserIds")
    private List<String> templateOwnerUserIds;

    /**
     * Starting date for delivery templates created between a date range. Format: YYYY-MM-DD
     * Optional. Must be paired with createdDateTo.
     */
    @JsonProperty("createdDateFrom")
    private String createdDateFrom;

    /**
     * Ending date for delivery templates created between a date range. Format: YYYY-MM-DD Optional.
     * Must be paired with createdDateFrom.
     */
    @JsonProperty("createdDateTo")
    private String createdDateTo;

    /**
     * Starting date for delivery templates modified between a date range. Format: YYYY-MM-DD
     * Optional. Must be paired with modifiedDateTo.
     */
    @JsonProperty("modifiedDateFrom")
    private String modifiedDateFrom;

    /**
     * Ending date for delivery templates modified between a date range. Format: YYYY-MM-DD
     * Optional. Must be paired with modifiedDateFrom.
     */
    @JsonProperty("modifiedDateTo")
    private String modifiedDateTo;

    /**
     * Parameters to sort the search results. Values: "ID", "NAME", "TARGET_SYSTEM_REFERENCE",
     * "STATUS", "CREATED_BY", "CREATED_ON", "MODIFIED_BY", "MODIFIED_ON" Default value:
     * "MODIFIED_ON" Optional.
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
    public static GetDeliveryTemplatesRequest withDefaults() {
        return GetDeliveryTemplatesRequest.builder()
                .offset(0)
                .limit(50)
                .sortByField("MODIFIED_ON")
                .sort("DESC")
                .build();
    }

    /** Creates a request with pagination parameters. */
    public static GetDeliveryTemplatesRequest withPagination(Integer offset, Integer limit) {
        return GetDeliveryTemplatesRequest.builder()
                .offset(offset)
                .limit(limit)
                .sortByField("MODIFIED_ON")
                .sort("DESC")
                .build();
    }

    /** Creates a request to search by term with default pagination. */
    public static GetDeliveryTemplatesRequest searchByTerm(String search) {
        return GetDeliveryTemplatesRequest.builder()
                .search(search)
                .offset(0)
                .limit(50)
                .sortByField("MODIFIED_ON")
                .sort("DESC")
                .build();
    }

    /** Creates a request filtered by status. */
    public static GetDeliveryTemplatesRequest filterByStatus(String status) {
        return GetDeliveryTemplatesRequest.builder()
                .status(status)
                .offset(0)
                .limit(50)
                .sortByField("MODIFIED_ON")
                .sort("DESC")
                .build();
    }
}
