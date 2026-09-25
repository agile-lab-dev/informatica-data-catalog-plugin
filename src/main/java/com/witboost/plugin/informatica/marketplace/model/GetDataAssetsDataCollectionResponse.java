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
 * Response body for retrieving data assets from a data collection in Informatica Marketplace.
 *
 * <p>This response is returned when data assets for a specific collection are successfully
 * retrieved (200 OK).
 *
 * <p>API endpoint: GET /api/v2/data-collections/{dataCollectionId}/data-assets
 *
 * <p>The response contains pagination information, navigation links, and a list of data asset
 * items.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetDataAssetsDataCollectionResponse {

    /** Pagination information for the response. */
    @JsonProperty("pageInfo")
    private PageInfo pageInfo;

    /** Navigation links for paginated results. */
    @JsonProperty("links")
    private Links links;

    /** List of data asset items. */
    @JsonProperty("items")
    private List<DataAssetItem> items;

    /** Pagination information. */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class PageInfo {
        /** Starting index for the current page. */
        @JsonProperty("offset")
        private Integer offset;

        /** Maximum number of results per page. */
        @JsonProperty("limit")
        private Integer limit;

        /** Total count of data assets available. */
        @JsonProperty("totalCount")
        private Integer totalCount;
    }

    /** Navigation links for paginated results. */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Links {
        /** Link to the current page. */
        @JsonProperty("self")
        private Link self;

        /** Link to the first page. */
        @JsonProperty("first")
        private Link first;

        /** Link to the next page (if available). */
        @JsonProperty("next")
        private Link next;

        /** Link to the previous page (if available). */
        @JsonProperty("previous")
        private Link previous;

        /** Link to the last page. */
        @JsonProperty("last")
        private Link last;
    }

    /** Link object containing href. */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Link {
        /** The API endpoint URL. */
        @JsonProperty("href")
        private String href;
    }

    /** Data asset item details. */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class DataAssetItem {
        /** System-generated unique identifier of the data asset. */
        @JsonProperty("id")
        private String id;

        /** External identifier (reference ID) of the data asset (e.g., "DAS-7"). */
        @JsonProperty("externalId")
        private String externalId;

        /** Name of the data asset. */
        @JsonProperty("name")
        private String name;

        /** Description of the data asset. */
        @JsonProperty("description")
        private String description;

        /** Status of the data asset. Values: ENABLED (available), DISABLED (not available). */
        @JsonProperty("status")
        private String status;

        /** Type of the data asset. */
        @JsonProperty("type")
        private String type;

        /** Source system from which the data is supplied to Data Marketplace. */
        @JsonProperty("source")
        private String source;

        /** Source application from which the description of the data asset is taken. */
        @JsonProperty("descriptiveSource")
        private String descriptiveSource;

        /**
         * Uniform resource identifier of the location in the data source where the data asset is
         * stored.
         */
        @JsonProperty("refLink")
        private String refLink;

        /** Location of the data asset in the data source. */
        @JsonProperty("assetLocation")
        private String assetLocation;

        /** Description of the location in the data source where the data asset is stored. */
        @JsonProperty("assetLocationDescription")
        private String assetLocationDescription;

        /** Name of the data asset as it appears in the data source. */
        @JsonProperty("technicalAssetName")
        private String technicalAssetName;

        /**
         * Average rating of the data asset (if imported from Data Governance and Catalog).
         * Represents user assessment between 1-5 stars. Value is the average of all user ratings.
         */
        @JsonProperty("averageRating")
        private String averageRating;
    }

    /**
     * Convenience method to get total count from pageInfo.
     *
     * @return Total count of data assets, or 0 if pageInfo is null
     */
    public Integer getTotalCount() {
        return pageInfo != null && pageInfo.getTotalCount() != null ? pageInfo.getTotalCount() : 0;
    }

    /**
     * Convenience method to get offset from pageInfo.
     *
     * @return Current offset, or 0 if pageInfo is null
     */
    public Integer getOffset() {
        return pageInfo != null && pageInfo.getOffset() != null ? pageInfo.getOffset() : 0;
    }

    /**
     * Convenience method to get limit from pageInfo.
     *
     * @return Current limit, or 0 if pageInfo is null
     */
    public Integer getLimit() {
        return pageInfo != null && pageInfo.getLimit() != null ? pageInfo.getLimit() : 0;
    }
}
