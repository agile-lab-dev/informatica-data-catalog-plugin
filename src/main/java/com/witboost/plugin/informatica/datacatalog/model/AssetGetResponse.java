package com.witboost.plugin.informatica.datacatalog.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class AssetGetResponse {

    private Summary summary;
    List<Hit> hits = new ArrayList<>();

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Summary {
        @JsonProperty("total_hits")
        private String totalHits;
    }

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Hit {
        @JsonProperty("core.identity")
        private String coreIdentity;

        @JsonProperty("core.externalId")
        private String externalIdentity;

        private Summary summary;

        @Getter
        @Setter
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class Summary {
            @JsonProperty("core.name")
            private String coreName;

            @JsonProperty("core.location")
            private String coreLocation;
        }

        private SystemAttributes systemAttributes;
        private SelfAttributes selfAttributes;

        @Getter
        @Setter
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class SystemAttributes {
            @JsonProperty("core.classType")
            private String classType;
        }

        @Getter
        @Setter
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class SelfAttributes {
            @JsonProperty("core.resourceName")
            private String resourceName;

            @JsonProperty("core.resourceType")
            private String resourceType;
        }
    }

    public boolean hasValidHits() {
        return this.getHits() != null && !this.getHits().isEmpty();
    }
}
