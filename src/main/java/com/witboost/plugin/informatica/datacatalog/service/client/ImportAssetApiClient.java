package com.witboost.plugin.informatica.datacatalog.service.client;

import com.witboost.plugin.informatica.common.client.BaseClient;
import com.witboost.plugin.informatica.common.client.InformaticaApiClient;
import com.witboost.plugin.informatica.common.exceptions.ApiCallException;
import com.witboost.plugin.informatica.common.exceptions.ExceptionFormatter;
import com.witboost.plugin.informatica.common.exceptions.WorkbookException;
import com.witboost.plugin.informatica.common.utils.HttpResponseValidator;
import com.witboost.plugin.informatica.datacatalog.config.DataCatalogApiConfig;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

/**
 * API client for importing assets into Informatica Data Catalog.
 *
 * <p>This client handles the process of uploading Excel workbooks containing asset metadata to the
 * Informatica catalog import API endpoint.
 */
@Service
@Slf4j
public class ImportAssetApiClient extends BaseClient {

    private static final int HTTP_ACCEPTED = 202;
    private static final String VALIDATION_POLICY =
            "{\"validationPolicy\": \"CONTINUE_ON_ERROR_WARNING\"}";
    private static final String TEMP_FILE_PREFIX = "informatica-import-";
    private static final String TEMP_FILE_SUFFIX = ".xlsx";

    private final DataCatalogApiConfig dataCatalogApiConfig;
    private final InformaticaApiClient informaticaApiClient;

    public ImportAssetApiClient(
            DataCatalogApiConfig dataCatalogApiConfig, InformaticaApiClient informaticaApiClient) {
        this.dataCatalogApiConfig = dataCatalogApiConfig;
        this.informaticaApiClient = informaticaApiClient;
    }

    /**
     * Imports assets into Informatica Data Catalog from an Excel workbook.
     *
     * <p>This method performs the following steps:
     *
     * <ol>
     *   <li>Validates the workbook is not null
     *   <li>Removes empty sheets from the workbook
     *   <li>Creates a temporary file using the system's temp directory
     *   <li>Writes the workbook to the temporary file
     *   <li>Uploads the file to Informatica using multipart/form-data
     *   <li>Validates the HTTP response (expects 202 Accepted)
     *   <li>Extracts and returns the job ID from the response
     *   <li>Cleans up temporary files
     * </ol>
     *
     * <p>The temporary file is created using Files.createTempFile() which ensures unique naming and
     * uses the OS-appropriate temporary directory.
     *
     * <p>The upload includes a validation policy configuration that continues processing even if
     * there are errors or warnings.
     *
     * <p>Example usage:
     *
     * <pre>{@code
     * Workbook workbook = new XSSFWorkbook();
     * // ... populate workbook with data
     * String jobId = importAssetApiClient.importAssets(workbook);
     * // Use jobId to track the import job status
     * }</pre>
     *
     * @param workbook The Excel workbook containing asset data to import (must not be null)
     * @return The job ID returned by Informatica for tracking the import operation
     * @throws IllegalArgumentException if workbook is null
     * @throws WorkbookException if there are issues writing the workbook to file
     * @throws ApiCallException if the API call fails or returns an error status
     * @see Workbook
     */
    public String invokeImportAssetJob(Workbook workbook) {
        // Input validation
        if (workbook == null) {
            log.error("Workbook parameter cannot be null");
            throw new IllegalArgumentException("Workbook cannot be null");
        }

        Path tempFilePath = null;
        FileOutputStream fileOut = null;

        try {
            log.debug("Starting asset import process");

            // Step 1: Clean up workbook by removing empty sheets
            deleteEmptySheets(workbook);

            // Step 2: Create temporary file for workbook
            tempFilePath = Files.createTempFile(TEMP_FILE_PREFIX, TEMP_FILE_SUFFIX);
            log.debug("Writing workbook to temporary file: {}", tempFilePath);

            // Step 3: Write workbook to file
            fileOut = new FileOutputStream(tempFilePath.toFile());
            workbook.write(fileOut);
            fileOut.flush();
            log.debug("Workbook written successfully to temporary file");

            // Step 4: Prepare multipart request
            log.debug("Preparing multipart upload request");
            MultiPartBodyPublisher publisher =
                    new MultiPartBodyPublisher()
                            .addStringPart("config", VALIDATION_POLICY)
                            .addFilePart("file", tempFilePath);

            // Step 5: Get JWT token for authentication
            String jwtToken = informaticaApiClient.getJwtToken();
            log.debug("Retrieved JWT token for authentication");

            // Step 6: Build HTTP request
            String importEndpoint = dataCatalogApiConfig.getAssetImportUrl();
            log.debug("Building HTTP request to: {}", importEndpoint);

            HttpRequest request =
                    HttpRequest.newBuilder()
                            .uri(new URI(importEndpoint))
                            .header(
                                    "Content-Type",
                                    "multipart/form-data; boundary=" + publisher.getBoundary())
                            .header("X-INFA-ORG-ID", informaticaApiClient.getOrgId())
                            .header("Authorization", "Bearer " + jwtToken)
                            .POST(publisher.build())
                            .build();

            // Step 7: Send request
            log.debug("Sending import request to Informatica");
            HttpResponse<String> httpResponse =
                    getHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

            // Step 8: Validate response
            HttpResponseValidator.validateResponse(
                    httpResponse, HTTP_ACCEPTED, "Asset import", ApiCallException::new);

            log.debug("Import request accepted by Informatica (status: 202)");

            // Step 9: Parse response to get job ID
            String responseBody = httpResponse.body();
            JSONObject jsonBody = new JSONObject(responseBody);
            String jobId = jsonBody.getString("jobId");

            log.info("Asset import initiated successfully. Job ID: {}", jobId);

            return jobId;

        } catch (Exception e) {
            String errorMessage =
                    String.format("Unexpected error during asset import: %s", e.getMessage());
            log.error(errorMessage, e);
            throw new ApiCallException(ExceptionFormatter.format(e));

        } finally {
            // Step 10: Cleanup resources
            cleanupResources(fileOut, tempFilePath);
        }
    }

    /**
     * Deletes empty sheets from the workbook.
     *
     * <p>A sheet is considered empty if it has no rows (lastRowNum == 0). This helps reduce the
     * workbook size and avoids uploading unnecessary data.
     *
     * <p>The deletion is performed in reverse order to avoid index shifting issues.
     *
     * @param workbook The workbook to clean up (must not be null)
     * @throws WorkbookException if workbook is null or sheet deletion fails
     */
    private void deleteEmptySheets(Workbook workbook) {
        if (workbook == null) {
            throw new WorkbookException("Workbook cannot be null");
        }

        try {
            log.debug("Scanning workbook for empty sheets");

            Iterator<Sheet> sheetIterator = workbook.sheetIterator();
            List<Integer> toDeleteIndexes = new ArrayList<>();
            int index = 0;

            while (sheetIterator.hasNext()) {
                Sheet currentSheet = sheetIterator.next();
                if (currentSheet.getLastRowNum() == 0) {
                    log.debug(
                            "Empty sheet found at index {}: '{}'",
                            index,
                            currentSheet.getSheetName());
                    toDeleteIndexes.add(index);
                }
                index++;
            }

            if (toDeleteIndexes.isEmpty()) {
                log.debug("No empty sheets found");
                return;
            }

            // Delete in reverse order to avoid index shifting
            Collections.reverse(toDeleteIndexes);
            log.debug("Deleting {} empty sheet(s)", toDeleteIndexes.size());

            for (Integer sheetIndex : toDeleteIndexes) {
                workbook.removeSheetAt(sheetIndex);
                log.trace("Removed sheet at index: {}", sheetIndex);
            }

            log.debug(
                    "Empty sheets deletion completed. Removed {} sheet(s)", toDeleteIndexes.size());

        } catch (Exception e) {
            String errorMessage =
                    String.format("Failed to delete empty sheets: %s", e.getMessage());
            log.error(errorMessage, e);
            throw new WorkbookException(errorMessage, e);
        }
    }

    /**
     * Cleans up file output stream and temporary file.
     *
     * @param fileOut The file output stream to close (can be null)
     * @param tempFilePath The temporary file path to delete (can be null)
     */
    private void cleanupResources(FileOutputStream fileOut, Path tempFilePath) {
        // Close file output stream
        if (fileOut != null) {
            try {
                fileOut.close();
                log.trace("File output stream closed");
            } catch (IOException e) {
                log.warn("Failed to close file output stream: {}", e.getMessage());
            }
        }

        // Delete temporary file
        if (tempFilePath != null && Files.exists(tempFilePath)) {
            try {
                Files.delete(tempFilePath);
                log.debug("Temporary file deleted: {}", tempFilePath);
            } catch (IOException e) {
                log.warn("Failed to delete temporary file {}: {}", tempFilePath, e.getMessage());
            }
        }
    }
}
