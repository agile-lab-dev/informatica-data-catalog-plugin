package com.witboost.plugin.informatica.marketplace.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.Instant;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a Data Collection from Informatica Marketplace.
 *
 * <p>A data collection is a logical grouping of related data assets in the Informatica Marketplace.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class DataCollection {

    @JsonProperty("id")
    private String id;

    @JsonProperty("externalId")
    private String externalId;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("status")
    private String status;

    @JsonProperty("stakeholdership")
    private List<Stakeholder> stakeholdership;

    @JsonProperty("deliveryTargets")
    private List<DeliveryTarget> deliveryTargets;

    @JsonProperty("category")
    private Category category;

    @JsonProperty("systemAttributes")
    private SystemAttributes systemAttributes;

    /** Stakeholder information. */
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

    /** Delivery target information. */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class DeliveryTarget {
        @JsonProperty("id")
        private String id;

        @JsonProperty("externalId")
        private String externalId;

        @JsonProperty("name")
        private String name;

        @JsonProperty("description")
        private String description;

        @JsonProperty("isDefault")
        private Boolean isDefault;

        @JsonProperty("status")
        private String status;

        @JsonProperty("physicalLocation")
        private String physicalLocation;

        @JsonProperty("targetSystemReference")
        private String targetSystemReference;

        @JsonProperty("_links")
        private LinkInfo links;
    }

    /** Category information. */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Category {
        @JsonProperty("externalId")
        private String externalId;

        @JsonProperty("id")
        private String id;

        @JsonProperty("name")
        private String name;

        @JsonProperty("description")
        private String description;

        @JsonProperty("status")
        private String status;

        @JsonProperty("effectiveStatus")
        private String effectiveStatus;

        @JsonProperty("_links")
        private LinkInfo links;
    }

    /** System attributes (audit information). */
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

    /** Link information for HATEOAS. */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class LinkInfo {
        @JsonProperty("href")
        private String href;
    }
}
