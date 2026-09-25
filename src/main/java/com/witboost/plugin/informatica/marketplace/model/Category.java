package com.witboost.plugin.informatica.marketplace.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.Instant;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a Category from Informatica Marketplace.
 *
 * <p>A category is a logical grouping that organizes data assets in the Informatica Marketplace.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Category {

    @JsonProperty("externalId")
    private String externalId;

    @JsonProperty("id")
    private String id;

    @JsonProperty("parentId")
    private String parentId;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("status")
    private String status;

    @JsonProperty("effectiveStatus")
    private String effectiveStatus;

    @JsonProperty("parentCategory")
    private Category parentCategory;

    @JsonProperty("stakeholdership")
    private List<Stakeholder> stakeholdership;

    @JsonProperty("assetGroups")
    private List<AssetGroup> assetGroups;

    @JsonProperty("systemAttributes")
    private SystemAttributes systemAttributes;

    /** Stakeholder information for a category. */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Stakeholder {
        @JsonProperty("stakeholderId")
        private String stakeholderId;

        @JsonProperty("userType")
        private String userType;

        @JsonProperty("roleId")
        private String roleId;

        @JsonProperty("isInherited")
        private Boolean isInherited;
    }

    /** Asset group information assigned to a category. */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class AssetGroup {
        @JsonProperty("id")
        private String id;

        @JsonProperty("name")
        private String name;

        @JsonProperty("isInherited")
        private Boolean isInherited;
    }

    /** System attributes (audit information) for a category. */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class SystemAttributes {
        @JsonProperty("createdBy")
        private String createdBy;

        @JsonProperty("createdOn")
        private Instant createdOn;

        @JsonProperty("modifiedBy")
        private String modifiedBy;

        @JsonProperty("modifiedOn")
        private Instant modifiedOn;
    }
}
