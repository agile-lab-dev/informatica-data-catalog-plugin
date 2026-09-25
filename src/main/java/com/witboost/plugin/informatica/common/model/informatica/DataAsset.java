package com.witboost.plugin.informatica.common.model.informatica;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;
import lombok.*;

/**
 * DataAsset model for Informatica Data Catalog marketplace integration.
 *
 * <p>This class represents a data asset (table/view/entity) specification aligned with the client's
 * data governance framework. It organizes metadata into three primary clusters:
 *
 * <ul>
 *   <li><b>System Info (Info Sistema Informativo)</b> - System location information including
 *       system name, server, database, and schema
 *   <li><b>Entity Info (Info Entità)</b> - Entity metadata including name, description, type,
 *       feeding frequency, historicization, and data sensitivity
 *   <li><b>Attributes (Info Attributi)</b> - List of attributes/columns with their properties
 * </ul>
 *
 * @since 1.0
 * @version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Valid
public class DataAsset {

    /**
     * Witboost component (output port) identifier this asset was mapped from (the descriptor {@code
     * id}/urn). Carried only so validation messages can point at the failing component; it is not
     * part of the Informatica payload (hence {@link JsonIgnore}) and is not validated.
     */
    @JsonIgnore private String componentId;

    /** System information cluster - Info Sistema Informativo */
    @NotNull @Valid @Builder.Default private SystemInfo systemInfo = new SystemInfo();

    /** Entity information cluster - Info Entità */
    @NotNull @Valid @Builder.Default private EntityInfo entityInfo = new EntityInfo();

    /** Attributes list - Info Attributi */
    @Valid @Builder.Default private List<AttributeInfo> attributes = new ArrayList<>();

    /**
     * System Info - INFO SISTEMA INFORMATIVO Contains information about the system where the entity
     * resides.
     */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SystemInfo {

        /**
         * Information system name.
         *
         * <p>Informatica: Summary.Nome Sistema Informativo
         */
        @NotNull(message = "systemName is mandatory")
        private String systemName;

        /**
         * Server name.
         *
         * <p>Informatica: Summary.Nome del Server
         */
        private String serverName;

        /**
         * Database name.
         *
         * <p>Informatica: Summary.Nome del Database
         */
        @NotNull(message = "databaseName is mandatory")
        private String databaseName;

        /**
         * Schema name.
         *
         * <p>Informatica: Summary.Nome dello Schema
         */
        @NotNull(message = "schemaName is mandatory")
        private String schemaName;
    }

    /** Entity Info - INFO ENTITÀ Contains metadata about the entity (table/view). */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class EntityInfo {

        /**
         * Entity name.
         *
         * <p>Informatica: Summary.Nome Entità
         */
        @NotNull(message = "entityName is mandatory")
        private String entityName;

        /**
         * Entity description.
         *
         * <p>Informatica: Summary.Descrizione Entità
         */
        @NotNull(message = "output port description is mandatory")
        private String entityDescription;

        /**
         * Entity type (e.g., table, view).
         *
         * <p>Informatica: Summary.Tipo Entità
         */
        @NotNull(message = "entityType is mandatory")
        private String entityType;

        /**
         * Feeding frequency - how often the entity is updated.
         *
         * <p>Informatica: Summary.Frequenza di Alimentazione
         *
         * <p>Valid values: Giornaliero, Settimanale, Decadale, Mensile, Trimestrale, Bimestrale,
         * Semestrale, Annuale, Quadrimestrale, Live, On-demand
         */
        @NotNull(message = "feedingFrequency is mandatory")
        @Pattern(
                regexp =
                        "^(Giornaliero|Settimanale|Decadale|Mensile|Trimestrale|Bimestrale|Semestrale|Annuale|Quadrimestrale|Live|On-demand)$",
                message =
                        "feedingFrequency must be one of: Giornaliero, Settimanale, Decadale, Mensile, Trimestrale, Bimestrale, Semestrale, Annuale, Quadrimestrale, Live, On-demand")
        private String feedingFrequency;

        /**
         * Feeding type - indicates how the entity is fed.
         *
         * <p>Informatica: Summary.Tipologia di Alimentazione
         *
         * <p>Valid values: PUSH, PULL
         *
         * <ul>
         *   <li>PUSH: data is sent/loaded to the system by other systems/flows
         *   <li>PULL: the system retrieves data directly from other systems/flows
         * </ul>
         */
        @Pattern(regexp = "^(PUSH|PULL)$", message = "feedingType must be one of: PUSH, PULL")
        private String feedingType;

        /**
         * Loading mode - indicates how the entity is loaded.
         *
         * <p>Informatica: Summary.Modalità di Caricamento
         *
         * <p>Valid values: Merge, Upsert, Incremental, Full
         */
        @Pattern(
                regexp = "^(Merge|Upsert|Incremental|Full)$",
                message = "loadingMode must be one of: Merge, Upsert, Incremental, Full")
        private String loadingMode;

        /**
         * Indicates if the loading process is manual.
         *
         * <p>Informatica: Summary.Presenza Manualità
         */
        private Boolean manualProcess;

        /**
         * Indicates if the entity is historicized.
         *
         * <p>Informatica: Summary.Storicizzazione
         *
         * <p>Default: false (No)
         */
        @NotNull(message = "historicized is mandatory")
        @Builder.Default
        private Boolean historicized = false;

        /**
         * Information retention policy description.
         *
         * <p>Informatica: Summary.Retention Informativa
         */
        private String retentionInfo;

        /**
         * Indicates presence of sensitive data.
         *
         * <p>Informatica: Summary.Dati Sensibili
         *
         * <p>Default: false (No)
         */
        @NotNull(message = "sensitiveData is mandatory")
        @Builder.Default
        private Boolean sensitiveData = false;

        /**
         * Service Level Agreement indicators.
         *
         * <p>Informatica: Summary.SLA - Service Level Agreement
         */
        @Valid private SLA sla;

        /**
         * Semantic links - business terms associated with the entity.
         *
         * <p>Informatica: Summary.Semantic Link(s)
         *
         * <p>Links to Business Glossary
         */
        private String semanticLinks;
    }

    /** SLA - Service Level Agreement Contains refresh rate and retention rate information. */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SLA {

        /**
         * Refresh rate - how often the data is refreshed.
         *
         * <p>Valid values: Oneshot, Giornaliero, Settimanale, Mensile, Trimestrale, Semestrale,
         * Annuale
         */
        @Pattern(
                regexp =
                        "^(Oneshot|Giornaliero|Settimanale|Mensile|Trimestrale|Semestrale|Annuale)$",
                message =
                        "refreshRate must be one of: Oneshot, Giornaliero, Settimanale, Mensile, Trimestrale, Semestrale, Annuale")
        private String refreshRate;

        /** Retention rate - how long the data is retained. */
        private String retentionRate;
    }

    /** Attribute Info - INFO ATTRIBUTI Contains metadata about a single attribute/column. */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AttributeInfo {

        /**
         * Attribute name.
         *
         * <p>Informatica: Summary.Nome Attributo
         */
        @NotNull(message = "column name is mandatory")
        private String attributeName;

        /**
         * Attribute description.
         *
         * <p>Informatica: Summary.Descrizione Attributo
         */
        @NotNull(message = "column description is mandatory")
        private String attributeDescription;

        /**
         * Indicates if the attribute is mandatory (must be populated).
         *
         * <p>Informatica: Summary.Attributo Obbligatorio
         */
        @NotNull(message = "mandatory is mandatory")
        private Boolean mandatory;

        /**
         * Indicates if the attribute is a primary key.
         *
         * <p>Informatica: Summary.Chiave Primaria
         */
        @NotNull(message = "primaryKey is mandatory")
        private Boolean primaryKey;

        /**
         * Position of the attribute within the entity.
         *
         * <p>Informatica: Summary.Posizione
         */
        @NotNull(message = "position is mandatory")
        private Integer position;

        /**
         * Attribute domain - list of possible values the attribute can assume.
         *
         * <p>Informatica: Summary.Dominio Attributo
         *
         * <p>Example values: Codice, Descrizione, Numero, Timestamp, Data, Flag, Ora
         */
        @NotNull(message = "column dataType is mandatory")
        private String attributeDomain;

        /**
         * Attribute length in digits.
         *
         * <p>Informatica: Summary.Lunghezza
         */
        @NotNull(message = "length is mandatory")
        private Integer length;

        /**
         * Semantic links - business term associated with the attribute.
         *
         * <p>Informatica: Summary.Semantic Link(s)
         *
         * <p>Links to Business Glossary
         */
        private String semanticLinks;

        /**
         * Quality control links - list of quality controls the attribute must comply with.
         *
         * <p>Informatica: Summary.Controllo Qualità Link(s)
         *
         * <p>Links to Control Registry
         */
        private String qualityControlLinks;

        /**
         * Indicates presence of sensitive data.
         *
         * <p>Informatica: Summary.Dati Sensibili
         *
         * <p>Default: false (No)
         */
        @NotNull(message = "sensitiveData is mandatory")
        @Builder.Default
        private Boolean sensitiveData = false;
    }
}
