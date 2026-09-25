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
 * Response body for retrieving data assets from Informatica Marketplace.
 *
 * <p>This response is returned when data assets are successfully retrieved (200 OK).
 *
 * <p>The response contains processing time, pagination information, and a list of data asset
 * objects.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetDataAssetsResponse {

    /** Processing time in milliseconds for the API call. */
    @JsonProperty("processingTime")
    private Long processingTime;

    /** Starting index for the current page. */
    @JsonProperty("offset")
    private Integer offset;

    /** Maximum number of results per page. */
    @JsonProperty("limit")
    private Integer limit;

    /** Total count of data assets available. */
    @JsonProperty("totalCount")
    private Integer totalCount;

    /** List of data asset objects. */
    @JsonProperty("objects")
    private List<DataAssetObject> objects;

    /** Data asset object details. */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class DataAssetObject {
        /** Name of the data asset. */
        @JsonProperty("name")
        private String name;

        /** System-generated unique identifier of the data asset. */
        @JsonProperty("id")
        private String id;

        /** External identifier (reference ID) of the data asset. */
        @JsonProperty("refId")
        private String refId;

        /** Description of the data asset. */
        @JsonProperty("description")
        private String description;

        /** Source system of the data asset. */
        @JsonProperty("source")
        private String source;

        /** Descriptive source of the data asset. */
        @JsonProperty("descriptiveSource")
        private String descriptiveSource;

        /** Type of the data asset. */
        @JsonProperty("type")
        private String type;

        /** Reference link for the data asset. */
        @JsonProperty("refLink")
        private String refLink;

        /** Asset location. */
        @JsonProperty("assetLocation")
        private String assetLocation;

        /** Description of the asset location. */
        @JsonProperty("assetLocationDescription")
        private String assetLocationDescription;

        /** Technical name of the asset. */
        @JsonProperty("technicalAssetName")
        private String technicalAssetName;

        /** Status of the data asset (ENABLED or DISABLED). */
        @JsonProperty("status")
        private String status;

        /** Resource reference information. */
        @JsonProperty("resourceReference")
        private ResourceReference resourceReference;

        /** User identifier or email of the user who created the resource. */
        @JsonProperty("createdBy")
        private String createdBy;

        /** Timestamp when the resource was created (ISO 8601 format in UTC). */
        @JsonProperty("createdOn")
        private String createdOn;

        /** User identifier or email of the user who last modified the resource. */
        @JsonProperty("modifiedBy")
        private String modifiedBy;

        /** Timestamp when the resource was last modified (ISO 8601 format in UTC). */
        @JsonProperty("modifiedOn")
        private String modifiedOn;
    }

    /** Resource reference information. */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ResourceReference {
        /** Type reference for the resource. */
        @JsonProperty("typeReference")
        private String typeReference;

        /** Source asset ID. */
        @JsonProperty("sourceAssetId")
        private String sourceAssetId;
    }
}
