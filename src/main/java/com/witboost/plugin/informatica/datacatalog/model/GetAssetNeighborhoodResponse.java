package com.witboost.plugin.informatica.datacatalog.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetAssetNeighborhoodResponse {

    @JsonProperty("core.identity")
    private String coreIdentity;

    @JsonProperty("core.externalId")
    private String coreExternalId;

    private List<Neighborhood> neighborhood;

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Neighborhood {
        private String type;
        private List<Neighbor> neighbors;
    }

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Neighbor {
        private String neighbor;
        private String details;
        private List<Path> paths;
    }

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Path {
        private String serialized;
        private List<Association> collection;
    }

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Association {
        private String association;
        private String curationStatus;
        private String from;
        private String to;
        private String fromType;
        private String toType;
        private String fromLocation;
        private String toLocation;
        private Map<String, Object> attributes;
        private AssociationDetails details;
        private Map<String, Object> fromProperties;
        private Map<String, Object> toProperties;
    }

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class AssociationDetails {
        private String fromUri;
        private String toUri;
    }
}
