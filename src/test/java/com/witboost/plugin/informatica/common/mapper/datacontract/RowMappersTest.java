package com.witboost.plugin.informatica.common.mapper.datacontract;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.witboost.plugin.informatica.common.model.informatica.DataContract;
import com.witboost.plugin.informatica.common.model.informatica.DeliveryTarget;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link RowMappers}.
 *
 * <p>These tests assert the <b>entire</b> output row produced for the "System" sheet, column by
 * column, using the real header of {@code import-datacatalog-template-v2.xlsx}. Asserting the full
 * array (rather than a couple of cells) guards every column against regressions such as a field
 * being silently dropped or shifted (regression: "Compagnie Gestite" / "Dati gestiti" were
 * hardcoded to {@code null} on the data product row).
 *
 * <p>If the template column layout changes, {@link #SYSTEM_HEADER} must be updated together with
 * the expected rows below — that is the intended early-warning signal.
 */
class RowMappersTest {

    /** Header of the "System" sheet, in template column order. */
    private static final List<String> SYSTEM_HEADER =
            List.of(
                    "Reference ID",
                    "Name",
                    "Description",
                    "Data deprecazione",
                    "Data sospensione",
                    "Lifecycle",
                    "Long Name",
                    "PII",
                    "Quality aspettative",
                    "Security consideration",
                    "Technical specifications",
                    "Tecnologia porta",
                    "Versione",
                    "Operation",
                    "Parent: System",
                    "Stakeholder: Governance Administrator",
                    "Stakeholder: Governance Owner",
                    "Stakeholder: CDGC - Super Admin [Custom]",
                    "Stakeholder: Governance Data Owner",
                    "Stakeholder: Governance User [Custom]");

    @Test
    void dataContractFullRow_create() {
        DataContract dataContract =
                DataContract.builder()
                        .baseCharacteristics(
                                DataContract.BaseCharacteristics.builder()
                                        .name("Long DP Name")
                                        .fullyQualifiedName("urn:dmb:dp:fqn")
                                        .description("DP description")
                                        .build())
                        .additionalInformation(
                                DataContract.AdditionalInformation.builder()
                                        .version("1.0.0")
                                        .sensitiveInfo("Si")
                                        .deprecationDate("2026-12-31")
                                        .suspensionDate("2026-06-30")
                                        .build())
                        .build();

        Map<String, Object> extras = Map.of(MappingContext.DATA_PRODUCT_NAME, "dp-name");

        String[] row = RowMappers.map(SYSTEM_HEADER, dataContract, Operation.CREATE, extras);

        String[] expected = {
            "", // Reference ID (empty on CREATE)
            "dp-name", // Name (from extras)
            "DP description", // Description
            "2026-12-31", // Data deprecazione
            "2026-06-30", // Data sospensione
            null, // Lifecycle
            "urn:dmb:dp:fqn", // Long Name (= fullyQualifiedName)
            "Si", // PII (= sensitiveInfo, canonical "Si")
            null, // Quality aspettative (output-port level)
            null, // Security consideration (output-port level)
            null, // Technical specifications (output-port level)
            null, // Tecnologia porta (output-port level)
            "1.0.0", // Versione
            "Create", // Operation
            null, // Parent: System (root)
            null,
            null,
            null,
            null,
            null // Stakeholders (populated by Informatica)
        };

        assertArrayEquals(expected, row);
    }

    @Test
    void dataContractReferenceId_update() {
        DataContract dataContract =
                DataContract.builder()
                        .baseCharacteristics(DataContract.BaseCharacteristics.builder().build())
                        .additionalInformation(DataContract.AdditionalInformation.builder().build())
                        .build();

        Map<String, Object> extras = Map.of(MappingContext.DATA_PRODUCT_REFERENCE_ID, "REF-123");

        String[] row = RowMappers.map(SYSTEM_HEADER, dataContract, Operation.UPDATE, extras);

        assertEquals("REF-123", row[SYSTEM_HEADER.indexOf("Reference ID")]);
        assertEquals("Update", row[SYSTEM_HEADER.indexOf("Operation")]);
    }

    @Test
    void dataContractLongNameFallsBackToNameWhenFqnAbsent() {
        DataContract dataContract =
                DataContract.builder()
                        .baseCharacteristics(
                                DataContract.BaseCharacteristics.builder()
                                        .name("Plain DP Name")
                                        .build())
                        .build();

        List<String> header = List.of("Long Name");

        String[] row = RowMappers.map(header, dataContract, Operation.CREATE, Map.of());

        assertEquals("Plain DP Name", row[0]);
    }

    @Test
    void deliveryTargetFullRow_create() {
        DeliveryTarget deliveryTarget = new DeliveryTarget();
        deliveryTarget.setBaseCharacteristics(
                DeliveryTarget.BaseCharacteristics.builder()
                        .portName("ignored - name comes from extras")
                        .description("DT description")
                        .deprecationDate("2026-12-31")
                        .suspensionDate("2026-06-30")
                        .isPii(true)
                        .qualityExpectations("high quality")
                        .securityConsiderations("encrypted at rest")
                        .technicalSpecifications("snowflake table")
                        .portTechnology("Snowflake")
                        .version("1.0.0")
                        .build());

        Map<String, Object> extras =
                Map.of(
                        MappingContext.DELIVERY_TARGET_NAME, "dt-name",
                        MappingContext.DATA_PRODUCT_NAME, "dp-name");

        String[] row = RowMappers.map(SYSTEM_HEADER, deliveryTarget, Operation.CREATE, extras);

        String[] expected = {
            "", // Reference ID (empty on CREATE)
            "dt-name", // Name (from extras)
            "DT description", // Description
            "2026-12-31", // Data deprecazione
            "2026-06-30", // Data sospensione
            null, // Lifecycle
            null, // Long Name
            "Si", // PII (isPii=true)
            "high quality", // Quality aspettative
            "encrypted at rest", // Security consideration
            "snowflake table", // Technical specifications
            "Snowflake", // Tecnologia porta
            "1.0.0", // Versione
            "Create", // Operation
            "dp-name", // Parent: System (= data product name)
            null,
            null,
            null,
            null,
            null // Stakeholders
        };

        assertArrayEquals(expected, row);
    }
}
