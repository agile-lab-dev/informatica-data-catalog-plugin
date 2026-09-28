package com.witboost.plugin.informatica.common.model.informatica;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.witboost.plugin.informatica.common.model.ValidationLevel;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.*;

/**
 * Data Contract model for Informatica Data Catalog marketplace integration.
 *
 * <p>This class represents a comprehensive data product specification aligned with the client's
 * data governance framework. It organizes metadata into three primary clusters based on the
 * official data product specification table:
 *
 * <ul>
 *   <li><b>Base Characteristics (Caratteristica Base)</b> - Fundamental identification, ownership,
 *       and data scope information including identifiers, product owners, managed companies, and
 *       publication status
 *   <li><b>Reference Context (Contesto di Riferimento)</b> - Domain classification metadata
 *       including data product type, company classification, domain, and subdomain
 *   <li><b>Additional Information (Informazioni Aggiuntive)</b> - Extended metadata covering
 *       lifecycle status, contacts, documentation links, compliance information, SLAs, and temporal
 *       attributes
 * </ul>
 *
 * <p>Each cluster maps to specific custom attributes in Informatica Data Catalog (IDMC), enabling
 * bidirectional synchronization between the marketplace and data catalog systems. Custom attribute
 * IDs are documented on each field for API integration purposes.
 *
 * <h2>Field Organization</h2>
 *
 * <p>Fields are categorized based on the client's specification table with the following structure:
 *
 * <ul>
 *   <li><b>Mandatory fields</b> - Annotated with {@code @NotNull} and validated
 *   <li><b>Optional fields</b> - May be null based on business requirements
 *   <li><b>Controlled vocabularies</b> - Validated with {@code @Pattern} for closed domains
 *   <li><b>Informatica mappings</b> - Each field includes Informatica field path reference
 *   <li><b>Custom Attribute IDs</b> - Documented for programmatic access via API
 * </ul>
 *
 * <h2>Data Product Types</h2>
 *
 * <p>Supports three data product type classifications:
 *
 * <ul>
 *   <li><b>Source-aligned</b> - Direct representations of source systems
 *   <li><b>Aggregated</b> - Combined and transformed data from multiple sources
 *   <li><b>Consumer-aligned</b> - Purpose-built datasets for specific use cases
 * </ul>
 *
 * <h2>Lifecycle States</h2>
 *
 * <p>Data products progress through defined lifecycle states:
 *
 * <ul>
 *   <li><b>Draft</b> - Initial definition phase
 *   <li><b>Proposed</b> - Under review for approval
 *   <li><b>In Development</b> - Being built and tested
 *   <li><b>Active</b> - Published and available for consumption (default for marketplace)
 *   <li><b>Retired</b> - No longer maintained but accessible
 *   <li><b>Deprecated</b> - Scheduled for removal
 * </ul>
 *
 * <h2>Validation</h2>
 *
 * <p>All mandatory fields are validated at runtime using Jakarta Bean Validation. Pattern
 * constraints ensure data quality for controlled vocabularies and enumerations. The entire object
 * graph is validated through the {@code @Valid} annotation.
 *
 * @see BaseCharacteristics
 * @see ReferenceContext
 * @see AdditionalInformation
 * @see Owner
 * @see ContactPoint
 * @since 1.0
 * @version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Valid
@Getter
@Setter
public class DataContract {

    /** Base characteristics cluster */
    @NotNull @Valid @Builder.Default
    private BaseCharacteristics baseCharacteristics = new BaseCharacteristics();

    /** Reference context cluster */
    @NotNull @Valid @Builder.Default
    private ReferenceContext referenceContext = new ReferenceContext();

    /** Additional information cluster */
    @NotNull @Valid @Builder.Default
    private AdditionalInformation additionalInformation = new AdditionalInformation();

    /**
     * Output ports associated with this data contract. Each OutputPort contains multiple
     * DataAssets.
     */
    @Valid @Builder.Default private List<DeliveryTarget> deliveryTargets = new ArrayList<>();

    @JsonIgnore private List<DeliveryTarget> marketplaceDeliveryTargets;

    public List<DeliveryTarget> getMarketplaceDeliveryTargets() {
        return marketplaceDeliveryTargets == null ? deliveryTargets : marketplaceDeliveryTargets;
    }

    /**
     * Resolved validation level for this provisioning. Drives how deeply the descriptor is checked
     * against Informatica and whether the technical element import (step 2/2) is fatal. Defaults to
     * {@link ValidationLevel#LOW}; set from {@link
     * com.witboost.plugin.informatica.datacatalog.config.ValidationLevelConfig}.
     */
    @Builder.Default private ValidationLevel validationLevel = ValidationLevel.LOW;

    /** Ordered Marketplace category names resolved from the configured descriptor paths. */
    @JsonIgnore @Builder.Default private List<String> marketplaceCategoryPath = new ArrayList<>();

    /** Additional descriptor attributes keyed by technical Informatica attribute ID. */
    @Builder.Default private Map<String, Object> customAttributes = new HashMap<>();

    /**
     * Base Characteristics - CARATTERISTICA BASE Contains fundamental information about the data
     * product.
     */
    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    public static class BaseCharacteristics {

        /**
         * Unique identifier for the data product across different tools.
         *
         * <p>Informatica: Summary.Reference ID
         *
         * <p>Custom Attribute ID: N/A (system field)
         */
        @NotNull(message = "id is mandatory")
        private String identifier;

        /**
         * Data Product name displayed in the tool.
         *
         * <p>Informatica: Summary.name
         */
        @NotNull(message = "name is mandatory")
        private String name;

        /**
         * Fully qualified name of the data product (Witboost top-level field). Used for the Data
         * Catalog "Long Name" column; falls back to {@link #name} when absent.
         */
        private String fullyQualifiedName;

        /**
         * Description and purpose of the Data Product. Includes objectives and business value
         * defined in Canvas.
         *
         * <p>Informatica: Summary.Purpose
         */
        @NotNull(message = "description (scopo) is mandatory")
        private String description;

        /**
         * Product Owner name.
         *
         * <p>Informatica: Summary.Product Owner
         *
         * <p>Custom Attribute ID: com.infa.odin.models.custom.ca_7007540664947074703
         *
         * <p>Type: PLAIN_TEXT
         */
        @NotNull(message = "dataProductOwnerDisplayName is mandatory")
        private String productOwnerName;

        /** Product Owner detailed information */
        private Owner owner;

        /**
         * Technical owners name(s).
         *
         * <p>Informatica: Summary.Technical Owners
         *
         * <p>Custom Attribute ID: com.infa.odin.models.custom.ca_7944917575130903557
         *
         * <p>Type: PLAIN_TEXT
         */
        @NotNull(message = "technicalOwners is mandatory")
        private String technicalOwnersNames;

        /** Technical owner detailed information */
        private String technicalOwner;

        /**
         * Macro purpose for the data product. Valid values: Generic, Analysis, AI Model, Regoletory
         *
         * <p>Default: Generic
         *
         * <p>Informatica: Summary.Certified Use
         */
        @NotNull(message = "certifiedUse is mandatory")
        @Pattern(
                regexp = "^(Generic|Analysis|AI Model|Regoletory)$",
                message = "certifiedUse must be one of: Generic, Analysis, AI Model, Regoletory")
        private String certifiedUse;

        /**
         * Publication status on Marketplace. Valid values: Unpublished, Published
         *
         * <p>Default: Published
         *
         * <p>Informatica: Summary.Status
         */
        @NotNull(message = "status is mandatory")
        @Pattern(
                regexp = "^(Unpublished|Published)$",
                message = "status must be one of: Unpublished, Published")
        private String status;
    }

    /**
     * Reference Context - CONTESTO DI RIFERIMENTO Contains contextual classification information
     * about the data product.
     */
    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ReferenceContext {

        /**
         * Company classification for marketplace placement. Closed domain defined by Platform
         * Governance.
         *
         * <p>Informatica: Summary.Classificazione
         */
        @NotNull(message = "company is mandatory")
        private String company;

        /**
         * Domain of reference for the data product. Closed domain defined by Platform Governance.
         */
        @NotNull(message = "businessDomain is mandatory")
        private String domain;

        /**
         * Sub-domain of reference for the data product. Closed domain defined by Platform
         * Governance.
         */
        @NotNull(message = "businessSubdomain is mandatory")
        private String subdomain;
    }

    /**
     * Additional Information - INFORMAZIONI AGGIUNTIVE Contains supplementary metadata and
     * lifecycle information about the data product.
     */
    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AdditionalInformation {

        /**
         * Data Product type classification. Valid values: Source-aligned, Aggregated,
         * Consumer-aligned
         *
         * <p>Informatica: Summary.Tipologia Data Product
         *
         * <p>Custom Attribute ID: com.infa.odin.models.custom.ca_827752163074873671
         *
         * <p>Type: DROPDOWN_SINGLE_SELECT
         */
        @NotNull(message = "dataProductType is mandatory")
        @Pattern(
                regexp = "^(Source-aligned|Aggregated|Consumer-aligned)$",
                message =
                        "dataProductType must be one of: Source-aligned, Aggregated, Consumer-aligned")
        private String dataProductType;

        /**
         * Contact information for key stakeholders (rich text format). Format: Product Owner
         * (mandatory), DEV Team (mandatory), Cabina di regia (optional)
         *
         * <p>Informatica: Summary.Contatti
         *
         * <p>Custom Attribute ID: com.infa.odin.models.custom.ca_5885184868630393511
         *
         * <p>Type: RICH_TEXT
         */
        private String contacts;

        /** Contact points (structured format) */
        private List<ContactPoint> contactPoints;

        /**
         * Lifecycle status of the data product. Valid values: Draft, Proposed, In Development,
         * Active, Retired, Deprecated
         *
         * <p>Default: Active
         *
         * <p>Informatica: Summary.Life cycle status
         *
         * <p>Custom Attribute ID: com.infa.odin.models.custom.ca_5189946131173565819
         *
         * <p>Type: DROPDOWN_SINGLE_SELECT
         */
        @NotNull(message = "lifeCycleStatus is mandatory")
        @Pattern(
                regexp = "^(Draft|Proposed|In Development|Active|Retired|Deprecated)$",
                message =
                        "lifeCycleStatus must be one of: Draft, Proposed, In Development, Active, Retired, Deprecated")
        private String lifeCycleStatus;

        /**
         * Version of the data product code. Format: {1, 2, ...}
         *
         * <p>Default: 1
         *
         * <p>Informatica: Summary.Versione
         *
         * <p>Custom Attribute ID: com.infa.odin.models.custom.ca_5755003175848260696
         *
         * <p>Type: PLAIN_TEXT
         */
        @NotNull(message = "version is mandatory")
        private String version;

        /**
         * Link to detailed documentation.
         *
         * <p>Informatica: Summary.Link a documentazione
         *
         * <p>Custom Attribute ID: com.infa.odin.models.custom.ca_3926074437122013534
         *
         * <p>Type: RICH_TEXT
         */
        private String linkDocumentation;

        /**
         * Link(s) to observability tools or dashboards.
         *
         * <p>Informatica: Summary.Link observability port
         *
         * <p>Custom Attribute ID: com.infa.odin.models.custom.ca_5922179831150172892
         *
         * <p>Type: RICH_TEXT
         */
        private String linkObservabilityPort;

        /**
         * Link to data quality dashboard.
         *
         * <p>Custom Attribute ID: com.infa.odin.models.custom.ca_7528839559788111812
         *
         * <p>Type: RICH_TEXT
         */
        private String linkDataQualityPort;

        /**
         * Link to metadata documentation. References SYSTEM object grouping all output port
         * systems.
         *
         * <p>Informatica: Summary.Metadata Details
         *
         * <p>Custom Attribute ID: com.infa.odin.models.custom.ca_940321610992278010
         *
         * <p>Type: RICH_TEXT
         */
        private String metadataDetails;

        /**
         * Link to data product graph.
         *
         * <p>Custom Attribute ID: com.infa.odin.models.custom.ca_3752540223274297980
         *
         * <p>Type: RICH_TEXT
         */
        private String linkDataProductGraph;

        /**
         * Indicates presence of sensitive information (PII). Canonical value: "Si" (no accent);
         * "Sì" with accent is also accepted in input for backward compatibility.
         *
         * <p>Default: No
         *
         * <p>Informatica: Summary.Dettaglio dati sensibili
         *
         * <p>Custom Attribute ID: com.infa.odin.models.custom.ca_3658568148084611617
         *
         * <p>Type: DROPDOWN_MULTI_SELECT
         */
        @NotNull(message = "sensitiveInfo is mandatory")
        @Pattern(regexp = "^(Si|Sì|No)$", message = "sensitiveInfo must be one of: Si, No")
        private String sensitiveInfo;

        /**
         * Additional information extending the purpose field.
         *
         * <p>Informatica: Summary.Informazioni Aggiuntive
         *
         * <p>Custom Attribute ID: com.infa.odin.models.custom.ca_8117437205157972590
         *
         * <p>Type: PLAIN_TEXT
         */
        private String additionalInfo;

        /**
         * Terms of use and limitations. Includes security and privacy information from Canvas.
         *
         * <p>Informatica: Terms of Use.General Terms of Use
         */
        private String termsOfUse;

        /**
         * Confidentiality level based on the strictest component level. Valid values: Public,
         * Private, Reserved
         *
         * <p>Default: Public
         *
         * <p>Informatica: Terms of Use.Terms of Use
         *
         * <p>Custom Attribute ID: com.infa.odin.models.custom.ca_7431962480998492324
         *
         * <p>Type: DROPDOWN_SINGLE_SELECT
         */
        @NotNull(message = "confidentiality is mandatory")
        @Pattern(
                regexp = "^(Public|Private|Reserved)$",
                message = "confidentiality must be one of: Public, Private, Reserved")
        private String confidentiality;

        /**
         * Creation or registration date in the marketplace.
         *
         * <p>Informatica: Summary.Created
         */
        @NotNull(message = "creationDate is mandatory")
        private String creationDate;

        /**
         * End date or decommissioning date.
         *
         * <p>Custom Attribute ID: com.infa.odin.models.custom.ca_3838148249273620906
         *
         * <p>Type: DATE
         */
        private String endDate;

        /**
         * Release date of the data product.
         *
         * <p>Informatica: Summary.Data rilascio
         *
         * <p>Custom Attribute ID: com.infa.odin.models.custom.ca_6352241241989561545
         *
         * <p>Type: DATE
         */
        private String releaseDate;

        /**
         * Suspension date (if applicable).
         *
         * <p>Custom Attribute ID: com.infa.odin.models.custom.ca_1105312267500485661
         *
         * <p>Type: DATE
         */
        private String suspensionDate;

        /**
         * Deprecation date (if applicable).
         *
         * <p>Informatica: Summary.Data deprecazione
         */
        private String deprecationDate;

        /**
         * Service Level Agreement indicators. Format: refresh rate, activation time, availability,
         * recovery rate, retention rate
         *
         * <p>Informatica: Summary.SLA
         *
         * <p>Custom Attribute ID: com.infa.odin.models.custom.ca_9099939197807515027
         *
         * <p>Type: RICH_TEXT
         */
        private String sla;

        /**
         * Historicization/storage type. Valid values: Si, No, In identification
         *
         * <p>Default: Si
         *
         * <p>Custom Attribute ID: com.infa.odin.models.custom.ca_3889712014915413375
         *
         * <p>Type: DROPDOWN_SINGLE_SELECT
         */
        @NotNull(message = "memorizationType is mandatory")
        @Pattern(
                regexp = "^(Si|No|In identification)$",
                message = "memorizationType must be one of: Si, No, In identification")
        private String memorizationType;

        /** Update frequency */
        private String updateFrequency;

        /** Data retention period */
        private String retention;

        /**
         * Internal or external regulations governing the Data Product.
         *
         * <p>Informatica: Summary.Norme
         *
         * <p>Custom Attribute ID: com.infa.odin.models.custom.ca_3095308045845914100
         *
         * <p>Type: RICH_TEXT
         */
        private String norms;

        /**
         * External company responsible for product creation on behalf of the data owner.
         *
         * <p>Informatica: Summary.Outsourcer
         *
         * <p>Custom Attribute ID: com.infa.odin.models.custom.ca_8065176047380147681
         *
         * <p>Type: PLAIN_TEXT
         */
        private String outsourcer;

        /**
         * Data quality dimensions monitored to ensure quality level. Format: List &lt;Data Quality
         * Dimensions&gt;
         *
         * <p>Informatica: Summary.Quality
         *
         * <p>Custom Attribute ID: com.infa.odin.models.custom.ca_5983049407571450947
         *
         * <p>Type: RICH_TEXT
         */
        private String quality;
    }

    /** Owner information for Product Owner and Technical Owner roles. */
    @Getter
    @Setter
    public static class Owner {
        /** Informatica user ID */
        private String informaticaId;

        /** Username */
        private String username;

        /** Full name */
        private String name;
    }

    /** Contact point information for stakeholders. */
    @Getter
    @Setter
    @AllArgsConstructor
    @Builder
    public static class ContactPoint {
        /** Contact name */
        private String name;

        /** Contact description */
        private String description;

        /** Communication channel */
        private String channel;

        /** Contact address */
        private String address;
    }

}
