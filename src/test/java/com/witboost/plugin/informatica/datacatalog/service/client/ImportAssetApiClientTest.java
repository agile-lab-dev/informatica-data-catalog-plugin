package com.witboost.plugin.informatica.datacatalog.service.client;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.io.InputStream;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

/**
 * Unit tests for ImportAssetApiClient.
 *
 * <p>Tests the asset import functionality by loading test workbooks from resources and mocking the
 * HTTP communication with Informatica API.
 */
@SpringBootTest
@ActiveProfiles("test")
@Disabled("Integration tests - requires real Informatica API credentials and network access")
class ImportAssetApiClientTest {

    private static final String TEST_WORKBOOK_PATH = "/test-workbooks/import-test.xlsx";

    @Autowired private ImportAssetApiClient importAssetApiClient;

    @Test
    @DisplayName("Should successfully import assets from test workbook loaded from resources")
    void shouldImportAssetsFromTestWorkbookInResources() throws Exception {
        // Given - Load workbook from test resources
        Workbook workbook = loadWorkbookFromResources(TEST_WORKBOOK_PATH);
        assertNotNull(workbook, "Test workbook should be loaded from resources");

        String jobId = importAssetApiClient.invokeImportAssetJob(workbook);

        assertNotNull(jobId, "Job ID should not be null");
        System.out.println("JobID: " + jobId);
    }

    /**
     * Helper method to load a workbook from test resources.
     *
     * @param resourcePath Path to the workbook in test resources
     * @return Loaded Workbook instance
     * @throws IOException if the resource cannot be loaded
     */
    private Workbook loadWorkbookFromResources(String resourcePath) throws IOException {
        InputStream inputStream = getClass().getResourceAsStream(resourcePath);
        if (inputStream == null) {
            throw new IllegalArgumentException("Test resource not found: " + resourcePath);
        }
        return new XSSFWorkbook(inputStream);
    }

    @Test
    @DisplayName("Should successfully import assets from test workbook loaded from local path")
    void shouldImportAssetsFromTestWorkbookInLocalPath() throws Exception {
        // Given - Load workbook from test resources
        String localFilePath = System.getenv("TEST_PATH");
        System.out.println("Local file path: " + localFilePath);
        Workbook workbook = new XSSFWorkbook(localFilePath);
        assertNotNull(workbook, "Test workbook should be loaded from local path");

        String jobId = importAssetApiClient.invokeImportAssetJob(workbook);

        assertNotNull(jobId, "Job ID should not be null");
        System.out.println("JobID: " + jobId);
    }
}
