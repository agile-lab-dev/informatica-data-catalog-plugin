package com.witboost.plugin.informatica.common.model.informatica;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import lombok.*;

/**
 * OutputPort model for Informatica Data Catalog marketplace integration.
 *
 * <p>This class represents an output port specification aligned with the client's data governance
 * framework. It organizes metadata into the Base Characteristics cluster based on the official
 * output port specification table.
 *
 * <h2>Field Organization</h2>
 *
 * <p>Fields are categorized based on the client's specification table with the following structure:
 *
 * <ul>
 *   <li><b>Mandatory fields</b> - Annotated with {@code @NotNull} and validated
 *   <li><b>Optional fields</b> - May be null based on business requirements
 *   <li><b>Informatica mappings</b> - Each field includes Informatica field path reference
 * </ul>
 *
 * <h2>Validation</h2>
 *
 * <p>All mandatory fields are validated at runtime using Jakarta Bean Validation. The entire object
 * graph is validated through the {@code @Valid} annotation.
 *
 * @see BaseCharacteristics
 * @since 1.0
 * @version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Valid
@Getter
@Setter
public class DeliveryTarget {

    public enum CatalogAssetType {
        SYSTEM,
        DATASET
    }

    /** Base characteristics cluster */
    @NotNull @Valid private BaseCharacteristics baseCharacteristics = new BaseCharacteristics();

    /** Catalog asset type represented by this descriptor node. */
    private CatalogAssetType catalogAssetType = CatalogAssetType.SYSTEM;

    private boolean shoppable = true;
    private boolean consumable;

    /** Data assets associated with this output port */
    @Valid private List<DataAsset> dataAssets = new ArrayList<>();

    /**
     * Base Characteristics - CARATTERISTICA BASE Contains fundamental information about the output
     * port.
     */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class BaseCharacteristics {

        /**
         * Output port name.
         *
         * <p>Informatica: Summary.Nome Porta
         */
        @NotNull(message = "output port name is mandatory")
        private String portName;

        /**
         * Output port technology.
         *
         * <p>Informatica: Summary.Tecnologia Porta
         */
        @NotNull(message = "output port technology is mandatory")
        private String portTechnology;

        /**
         * Description and purpose of the output port.
         *
         * <p>Informatica: Summary.Descrizione o Scopo
         */
        @NotNull(message = "description is mandatory")
        private String description;

        /**
         * Output port version.
         *
         * <p>Informatica: Summary.Versione
         */
        @NotNull(message = "version is mandatory")
        private String version;

        /**
         * Indicates presence of sensitive information (PII).
         *
         * <p>Informatica: Summary.Is PII
         */
        @NotNull(message = "isPii is mandatory")
        private Boolean isPii;

        /**
         * Creation date.
         *
         * <p>Informatica: Summary.Data Creazione
         */
        @NotNull(message = "creationDate is mandatory")
        private String creationDate;

        /**
         * Modification date.
         *
         * <p>Informatica: Summary.Data Modifica
         */
        private String modificationDate;

        /**
         * Suspension date.
         *
         * <p>Informatica: Summary.Data Sospensione
         */
        private String suspensionDate;

        /**
         * Deprecation date.
         *
         * <p>Informatica: Summary.Data Deprecazione
         */
        private String deprecationDate;

        /**
         * Quality expectations - validation rules in terms of business rules or validation checks.
         *
         * <p>Informatica: Summary.Quality Aspettative
         */
        private String qualityExpectations;

        /**
         * Security considerations including:
         *
         * <ul>
         *   <li>Encryption in Transit: Is data transmission encrypted? If so, how?
         *   <li>Access Controls: Who has permission to access data from this port?
         * </ul>
         *
         * <p>Informatica: Summary.Security Considerations
         */
        private String securityConsiderations;

        /**
         * Technical specifications including:
         *
         * <ul>
         *   <li>Type of Output Port: (e.g., REST API Endpoint, Message Queue Topic, File Export
         *       Location, Database View, Streaming Platform Topic)
         *   <li>Protocol: (e.g., HTTP/HTTPS, Kafka, AMQP, FTP/SFTP, JDBC)
         *   <li>Address/Location: (e.g., URL, Broker Address and Topic, Server Address and Path,
         *       Connection String/Details)
         *   <li>Port Number(s): (The network port(s) involved, if applicable)
         *   <li>Authentication/Authorization for Consumers: (Details on how data consumers are
         *       authenticated and authorized)
         *   <li>Data Format: (e.g., JSON, CSV, XML, Avro, Protocol Buffers. Specify encoding like
         *       UTF-8)
         *   <li>Schema Definition: (A concise summary or link to the schema of the data being
         *       output)
         *   <li>Data Volume/Frequency of Output: (Information about how often data is published and
         *       the typical volume)
         * </ul>
         *
         * <p>Informatica: Summary.Technical Specifications
         */
        private String technicalSpecifications;
    }
}
