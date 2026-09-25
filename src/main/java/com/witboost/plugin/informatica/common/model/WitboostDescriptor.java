package com.witboost.plugin.informatica.common.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Valid
public class WitboostDescriptor {

    // TODO Port fields into package com.witboost.plugin.informatica.common.model.witboost
    // and remove this class

    @NotNull private String name;

    private String fullyQualifiedName;
    private String description;
    private String kind;
    private String domain;
    private String version;
    private String environment;
    private String dataProductOwner;
    private String dataProductOwnerDisplayName;
    private String email;
    private String ownerGroup;
    private String devGroup;
    private String informationSLA;

    @Pattern(regexp = "Draft|Published|Retired")
    private String status;

    private String maturity;
    private Map<String, String> billing;
    private List<String> tags;

    @Valid private SpecificHeader specific;

    @Valid private List<Component> components;

    @Getter
    @Setter
    public static class SpecificHeader {
        private String dataOwnerInformaticaId;
        private String technicalOwnerInformaticaId;
        private String creationDate;
        private String releaseDate;
        private String endDate;
        private String suspensionDate;

        @NotNull
        @Pattern(
                regexp =
                        "Live|Daily|Weekly|EveryTenDays|Monthly|Bimonthly|Quarterly|Biannual|Quadrimester|Annual|On-demand")
        private String updateFrequency;

        private String retention;

        @NotNull private String marketplaceClassification;

        @NotNull
        @Pattern(regexp = "Source-aligned|Aggregate|Customer-aligned")
        private String dataProductType;

        private String generalQualityInfo;
        private boolean sensitiveInfo;

        @NotNull
        @Pattern(regexp = "SCD Tipo 0|SCD Tipo 1|SCD Tipo 2")
        private String memorizationType;

        private String norms;
    }

    @Getter
    @Setter
    public static class DataContract {
        private List<Map<String, Object>> schema;

        @NotNull private SLA SLA;

        private String termsAndConditions;
        private String endpoint;
        private String biTempBusinessTs;
        private String biTempWriteTs;
    }

    @Getter
    @Setter
    public static class SLA {
        private String intervalOfChange;
        private String timeliness;
        private String upTime;
    }

    @Getter
    @Setter
    public static class DataSharingAgreements {
        private String purpose;
        private String billing;
        private String security;
        private String intendedUsage;
        private String limitations;
        private String lifeCycle;
        private String confidentiality;
    }

    @Getter
    @Setter
    public static class Component {
        @NotNull private String id;

        @NotNull
        @Pattern(regexp = "^[A-Za-z0-9_-]+$", message = "Invalid Component name format")
        private String name;

        private String fullyQualifiedName;
        private String description;
        private String outputPortType;
        private String kind;
        private String version;
        private String platform;
        private String technology;
        private String creationDate;
        private String startDate;
        private String retentionTime;

        @Valid private DataContract dataContract;

        @NotNull @Valid private DataSharingAgreement dataSharingAgreement;

        @Getter
        @Setter
        public static class DataSharingAgreement {
            @NotNull private String purpose;

            @NotNull private String intendedUsage;

            @NotNull private String audience;
        }

        // @Valid
        @NotNull private SpecificComponent specific;

        @Getter
        @Setter
        public static class DataContract {
            @Valid private List<Schema> schema;

            @NotNull private SLA SLA;

            @Getter
            @Setter
            public static class SLA {
                private String intervalOfChange;
                private String lastUpdate;
            }

            @Getter
            @Setter
            public static class Schema {
                @NotNull
                @Pattern(regexp = "^[A-Za-z0-9_-]+$", message = "Invalid Column name format")
                private String name;
            }
        }

        @Getter
        @Setter
        public static class SpecificComponent {
            private String databaseName;

            @NotNull
            @Pattern(regexp = "^[A-Za-z0-9_-]+$", message = "Invalid assetName name format")
            private String assetName;

            private String assetVersion;
            private String assetFullyQualifiedName;
            private String assetDisplayName;
            private String assetDescription;
            private String assetType;
            private String assetDataCategory;
            private String assetTipologiaContribuzione;

            @NotNull
            @Pattern(
                    regexp = "^[^/]+/[^/]+/[^/]+/[^/]+$",
                    message = "catalogHierarchicalPath non valido")
            private String catalogHierarchicalPath;

            private String schemaName;
            private String displayName;
            private List<QualityControl> quality;

            @Getter
            @Setter
            public static class QualityControl {
                private String controlId;
                private String description;
            }
        }
    }
}
