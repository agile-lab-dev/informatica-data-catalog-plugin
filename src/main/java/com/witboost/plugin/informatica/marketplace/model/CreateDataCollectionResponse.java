package com.witboost.plugin.informatica.marketplace.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Response body for creating a data collection in Informatica Marketplace.
 *
 * <p>This response is returned when a data collection is successfully created (201 Created).
 *
 * <p>The response contains the system-generated unique identifier and details of the created data
 * collection.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateDataCollectionResponse {

    /** System-generated unique identifier of the created data collection. */
    @JsonProperty("id")
    private String id;

    /** External reference identifier of the created data collection. */
    @JsonProperty("externalId")
    private String externalId;

    /** Name of the created data collection. */
    @JsonProperty("name")
    private String name;

    /** Description of the created data collection. */
    @JsonProperty("description")
    private String description;

    /**
     * Status of the data collection. Indicates whether the data collection is discoverable by Data
     * Users. Values: "PUBLISHED" (discoverable), "UNPUBLISHED" (not discoverable).
     */
    @JsonProperty("status")
    private String status;

    /** Details of the asset groups assigned to the category that contains the data collection. */
    @JsonProperty("assetGroups")
    private List<AssetGroup> assetGroups;

    /** Asset group details. */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class AssetGroup {
        /** System-generated identifier of the asset group. */
        @JsonProperty("id")
        private String id;

        /** Name of the asset group. */
        @JsonProperty("name")
        private String name;

        /**
         * Indicates whether the asset group is directly assigned to the category or inherited from
         * the category hierarchy. - true: The asset group is inherited from the category hierarchy.
         * - false: The asset group is directly assigned to the category.
         */
        @JsonProperty("isInherited")
        private Boolean isInherited;
    }
}
