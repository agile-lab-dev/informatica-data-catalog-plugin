package com.witboost.plugin.informatica.marketplace.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a Delivery Target from Informatica Marketplace.
 *
 * <p>A delivery target is a destination where data can be delivered in the Informatica Marketplace.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class DeliveryTarget {

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

    @JsonProperty("assetGroups")
    private List<AssetGroup> assetGroups;

    @JsonProperty("physicalLocation")
    private String physicalLocation;

    @JsonProperty("targetSystemReference")
    private String targetSystemReference;

    @JsonProperty("deliveryTemplate")
    private DeliveryTemplateInfo deliveryTemplate;

    @JsonProperty("deliveryFormat")
    private DeliveryFormatInfo deliveryFormat;

    @JsonProperty("deliveryMethod")
    private DeliveryMethodInfo deliveryMethod;

    @JsonProperty("systemAttributes")
    private SystemAttributes systemAttributes;

    /** Asset group information assigned to a delivery target. */
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

    /** Delivery template information. */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class DeliveryTemplateInfo {
        @JsonProperty("id")
        private String id;

        @JsonProperty("name")
        private String name;

        @JsonProperty("description")
        private String description;
    }

    /** Delivery format information. */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class DeliveryFormatInfo {
        @JsonProperty("id")
        private String id;

        @JsonProperty("name")
        private String name;

        @JsonProperty("description")
        private String description;
    }

    /** Delivery method information. */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class DeliveryMethodInfo {
        @JsonProperty("id")
        private String id;

        @JsonProperty("name")
        private String name;

        @JsonProperty("description")
        private String description;
    }

    /** System attributes (audit information) for a delivery target. */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class SystemAttributes {
        @JsonProperty("createdBy")
        private String createdBy;

        @JsonProperty("createdOn")
        private String createdOn;

        @JsonProperty("modifiedBy")
        private String modifiedBy;

        @JsonProperty("modifiedOn")
        private String modifiedOn;
    }
}
