package com.witboost.plugin.informatica.common.mapper.datacontract;

import static org.junit.jupiter.api.Assertions.*;

import com.witboost.plugin.informatica.common.model.informatica.DataContract;
import com.witboost.plugin.informatica.common.model.informatica.TechnicalDataElement;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.jupiter.api.Test;

@Slf4j
class DataContractWorkbookMapperTest {

    private static final String TEST_OUTPUT_DIR = "target/test-workbooks";

    private DataContractWorkbookMapper mapper = new DataContractWorkbookMapper();

    @Test
    void addSystem() throws IOException {
        var dataContract = createSampleDataContract();

        mapper.addSystem(dataContract, Map.of());

        var workbook = mapper.getWorkbook();

        // Write workbook to test folder for manual verification
        String filename = "test-data-contract-system.xlsx";
        writeWorkbookToTestFolder(workbook, filename);

        log.info("Workbook written to: {}/{}", TEST_OUTPUT_DIR, filename);

        // Basic validation
        assertNotNull(workbook);
        assertNotNull(workbook.getSheet(DataContractWorkbookMapper.DATASET_SHEET_NAME));
    }

    @Test
    void addColumns() throws IOException {
        var cols =
                List.of(
                        TechnicalDataElement.builder()
                                .externalIdentity(
                                        "00000000-0000-0000-0000-000000000001://example_database/example_schema/example_dataset/example_date_column~com.infa.odin.models.relational.Column")
                                .coreName("example_date_column")
                                .classType("com.infa.odin.models.relational.Column")
                                .coreIdentity("012b1f1e-51b7-4b7f-89bf-1ce8850313ad")
                                .build(),
                        TechnicalDataElement.builder()
                                .externalIdentity(
                                        "00000000-0000-0000-0000-000000000001://example_database/example_schema/example_dataset/example_type_column~com.infa.odin.models.relational.Column")
                                .coreName("example_type_column")
                                .classType("com.infa.odin.models.relational.Column")
                                .coreIdentity("40abe916-22ce-488a-bb26-f98722f2086b")
                                .build());
        mapper.addDatasetColumns(
                cols, Map.of(MappingContext.DATA_ASSET_NAME, "Data-Set-Test-Import-Api-2"));
        var workbook = mapper.getWorkbook();

        String filename = "test-technical-elements-system.xlsx";
        writeWorkbookToTestFolder(workbook, filename);

        log.info("Workbook written to: {}/{}", TEST_OUTPUT_DIR, filename);

        // Basic validation
        assertNotNull(workbook);
        assertNotNull(workbook.getSheet(DataContractWorkbookMapper.DATASET_SHEET_NAME));
    }

    @Test
    void testAddSystem() {}

    @Test
    void addDataSets() {}

    @Test
    void addDatasetColumns() {}

    private DataContract createSampleDataContract() {
        // Implement a method to create and return a sample DataContract object for testing
        var baseCharacteristics =
                DataContract.BaseCharacteristics.builder()
                        // .identifier("DC-001")
                        .name("Sample Data Contract")
                        .description("This is a sample data contract for testing purposes")
                        .status("ACTIVE")
                        .productOwnerName("John Doe")
                        .technicalOwnersNames("Jane Smith, Bob Johnson")
                        .certifiedUse("Generic")
                        .build();

        var referenceContext =
                DataContract.ReferenceContext.builder()
                        .company("Example Organization")
                        .domain("Sales")
                        .subdomain("Policies")
                        .build();

        var additionalInformation =
                DataContract.AdditionalInformation.builder()
                        .version("1.0.0")
                        .creationDate("2026-01-15")
                        .releaseDate("2026-01-20")
                        .lifeCycleStatus("In Development")
                        .dataProductType("Source-aligned")
                        .sensitiveInfo("PII")
                        .norms("GDPR, Privacy Policy")
                        .memorizationType("Full")
                        .quality("High")
                        .confidentiality("Internal")
                        .sla("99.9% uptime")
                        .updateFrequency("Daily")
                        .retention("7 years")
                        .build();

        var dataContract =
                DataContract.builder()
                        .baseCharacteristics(baseCharacteristics)
                        .referenceContext(referenceContext)
                        .additionalInformation(additionalInformation)
                        .build();

        return dataContract;
    }

    /**
     * Helper method to write a workbook to the test output folder.
     *
     * <p>This method creates the test output directory if it doesn't exist, and writes the workbook
     * to an Excel file for manual verification.
     *
     * @param workbook The workbook to write
     * @param filename The name of the output file (should end with .xlsx)
     * @throws IOException if an I/O error occurs during writing
     */
    private void writeWorkbookToTestFolder(Workbook workbook, String filename) throws IOException {
        // Create test output directory if it doesn't exist
        Path outputDir = Paths.get(TEST_OUTPUT_DIR);
        if (!Files.exists(outputDir)) {
            Files.createDirectories(outputDir);
            log.info("Created test output directory: {}", outputDir.toAbsolutePath());
        }

        // Build full file path
        Path filePath = outputDir.resolve(filename);

        // Write workbook to file
        try (FileOutputStream fileOut = new FileOutputStream(filePath.toFile())) {
            workbook.write(fileOut);
            log.info("Successfully wrote workbook to: {}", filePath.toAbsolutePath());
        }
    }
}
