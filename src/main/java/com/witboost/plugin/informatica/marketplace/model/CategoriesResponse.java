package com.witboost.plugin.informatica.marketplace.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Response wrapper for Informatica Marketplace Categories API.
 *
 * <p>Maps the JSON response from GET /api/v2/categories endpoint.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CategoriesResponse {

    /** Pagination information. */
    @JsonProperty("pageInfo")
    private PageInfo pageInfo;

    /** HATEOAS links for navigation. */
    @JsonProperty("links")
    private Links links;

    /** List of category items. */
    @JsonProperty("items")
    private List<Category> items;

    /** Pagination information. */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class PageInfo {
        @JsonProperty("offset")
        private Integer offset;

        @JsonProperty("limit")
        private Integer limit;

        @JsonProperty("totalCount")
        private Integer totalCount;
    }

    /** HATEOAS navigation links. */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Links {
        @JsonProperty("self")
        private Link self;

        @JsonProperty("first")
        private Link first;

        @JsonProperty("next")
        private Link next;

        @JsonProperty("previous")
        private Link previous;

        @JsonProperty("last")
        private Link last;
    }

    /** Individual link for HATEOAS navigation. */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Link {
        @JsonProperty("href")
        private String href;
    }
}
