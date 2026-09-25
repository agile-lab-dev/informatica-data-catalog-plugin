package com.witboost.plugin.informatica.common.mapper.datacontract;

import com.witboost.plugin.informatica.common.exceptions.WorkbookException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Base abstract class for workbook mappers that work with Excel templates.
 *
 * <p>This class provides common functionality for loading Excel templates from the classpath and
 * initializing Apache POI Workbook instances.
 *
 * <p>Subclasses should extend this class and provide their specific template file name through the
 * constructor.
 *
 * <p>Example usage:
 *
 * <pre>{@code
 * public class MyWorkbookMapper extends BaseWorkbookMapper {
 *     public MyWorkbookMapper() {
 *         super("my-custom-import-datacatalog-template.xlsx");
 *     }
 * }
 * }</pre>
 */
@Slf4j
@Getter
public abstract class BaseWorkbookMapper {

    /** Base path where template files are located in the classpath. */
    private static final String TEMPLATE_BASE_PATH = "/iics-templates/";

    /** Default template file name if none is provided. */
    private static final String DEFAULT_TEMPLATE_FILE_NAME = "import-datacatalog-template-v3.xlsx";

    /** The name of the template file to be loaded. */
    private final String templateFileName;

    private Workbook workbook;

    /**
     * Default constructor using the default template file name.
     *
     * <p>The workbook is NOT initialized in the constructor (lazy initialization pattern). The
     * Excel template will be loaded from the classpath only when {@link #getWorkbook()} is called
     * for the first time.
     *
     * <p>This approach avoids throwing checked exceptions from constructors, which is considered a
     * best practice in Java.
     */
    protected BaseWorkbookMapper() {
        this(DEFAULT_TEMPLATE_FILE_NAME);
    }

    /**
     * Constructor with custom template file name.
     *
     * @param templateFileName The name of the template file (must not be null or blank)
     * @throws IllegalArgumentException if templateFileName is null or blank
     */
    protected BaseWorkbookMapper(String templateFileName) {
        if (templateFileName == null || templateFileName.isBlank()) {
            throw new IllegalArgumentException("Template file name cannot be null or blank");
        }
        this.templateFileName = templateFileName;
        this.initWorkbook();
        log.debug("BaseWorkbookMapper initialized with template: {}", templateFileName);
    }

    /**
     * Initializes and returns a Workbook instance from the configured template file.
     *
     * <p>This method loads the Excel template from the classpath using the configured template file
     * name and base path. The file must be located in the resources folder under the path:
     * /iics-templates/{templateFileName}
     *
     * <p>The method performs the following steps:
     *
     * <ol>
     *   <li>Constructs the full template path
     *   <li>Loads the template as an InputStream from classpath
     *   <li>Validates that the template exists
     *   <li>Creates and returns an XSSFWorkbook instance
     *   <li>Assigns the workbook to the instance field for later access
     * </ol>
     *
     * @return A new Workbook instance loaded from the template file
     * @throws WorkbookException if the template file is not found or an error occurs during loading
     * @see XSSFWorkbook
     */
    protected Workbook initWorkbook() {
        String fullTemplatePath = getFullTemplatePath();

        log.debug("Loading workbook template from: {}", fullTemplatePath);

        try {
            InputStream templateStream =
                    BaseWorkbookMapper.class.getResourceAsStream(fullTemplatePath);

            if (templateStream == null) {
                String errorMessage =
                        String.format(
                                "Excel template file not found: %s. "
                                        + "Please ensure the file exists in the resources folder at path: %s",
                                templateFileName, fullTemplatePath);
                log.error(errorMessage);
                throw new WorkbookException(errorMessage);
            }

            log.debug("Template file found, creating XSSFWorkbook instance");
            this.workbook = new XSSFWorkbook(templateStream);
            log.debug(
                    "Workbook initialized successfully with {} sheet(s)",
                    this.workbook.getNumberOfSheets());

            return this.workbook;
        } catch (Exception e) {
            String errorMessage =
                    String.format(
                            "Unexpected error while initializing workbook from template: %s",
                            fullTemplatePath);
            log.error(errorMessage, e);
            throw new WorkbookException(errorMessage, e);
        }
    }

    /**
     * Gets the full template path (base path + file name).
     *
     * @return The complete template path in the classpath
     */
    protected String getFullTemplatePath() {
        return TEMPLATE_BASE_PATH + templateFileName;
    }

    /**
     * Extracts the header row from a sheet and returns column names as a list.
     *
     * <p>This method reads the first row (index 0) of the given sheet and extracts all cell values
     * as strings to build the list of column names. It's useful for mapping data to columns or
     * validating the template structure.
     *
     * <p>The method handles the following scenarios:
     *
     * <ul>
     *   <li>Validates that the sheet is not null
     *   <li>Checks that the sheet has at least one row
     *   <li>Handles empty cells (returns empty string)
     *   <li>Converts all cell values to strings
     * </ul>
     *
     * <p>Example usage:
     *
     * <pre>{@code
     * Sheet sheet = workbook.getSheetAt(0);
     * List<String> headers = getHeader(sheet);
     * // headers: ["Column1", "Column2", "Column3"]
     *
     * // Use headers for validation
     * if (headers.contains("SystemName")) {
     *     // Process sheet
     * }
     * }</pre>
     *
     * @param sheet The Excel sheet to extract headers from (must not be null)
     * @return A list of column names from the first row (never null, but may be empty)
     * @throws IllegalArgumentException if sheet is null
     * @throws WorkbookException if the sheet has no rows or the first row is null
     * @see Sheet
     * @see Row
     * @see Cell
     */
    protected List<String> getHeader(Sheet sheet) {
        // Input validation
        if (sheet == null) {
            log.error("Sheet parameter cannot be null");
            throw new IllegalArgumentException("Sheet cannot be null");
        }

        log.debug("Extracting header from sheet: {}", sheet.getSheetName());

        // Check if sheet has any rows
        if (sheet.getPhysicalNumberOfRows() == 0) {
            String errorMessage =
                    String.format(
                            "Sheet '%s' has no rows. Cannot extract header.", sheet.getSheetName());
            log.error(errorMessage);
            throw new WorkbookException(errorMessage);
        }

        // Get first row (header row)
        Row firstRow = sheet.getRow(0);
        if (firstRow == null) {
            String errorMessage =
                    String.format(
                            "First row in sheet '%s' is null. Cannot extract header.",
                            sheet.getSheetName());
            log.error(errorMessage);
            throw new WorkbookException(errorMessage);
        }

        List<String> columnNames = new ArrayList<>();

        try {
            // Iterate through cells in the first row
            Iterator<Cell> cellIterator = firstRow.cellIterator();
            int columnIndex = 0;

            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();

                if (cell == null) {
                    log.warn(
                            "Null cell found at index {} in header row of sheet '{}'",
                            columnIndex,
                            sheet.getSheetName());
                    columnNames.add("");
                } else {
                    try {
                        // Try to get string value from cell
                        String cellValue = cell.getStringCellValue();
                        columnNames.add(cellValue != null ? cellValue : "");
                        log.trace("Header column {}: '{}'", columnIndex, cellValue);
                    } catch (IllegalStateException e) {
                        // Cell is not a string type, convert to string
                        log.warn(
                                "Cell at index {} is not a string type, converting: {}",
                                columnIndex,
                                e.getMessage());
                        columnNames.add(cell.toString());
                    }
                }
                columnIndex++;
            }

            log.debug(
                    "Extracted {} column names from sheet '{}': {}",
                    columnNames.size(),
                    sheet.getSheetName(),
                    columnNames);

            return columnNames;

        } catch (Exception e) {
            String errorMessage =
                    String.format(
                            "Failed to extract header from sheet '%s': %s",
                            sheet.getSheetName(), e.getMessage());
            log.error(errorMessage, e);
            throw new WorkbookException(errorMessage, e);
        }
    }

    /**
     * Sets a value to a cell, handling null values appropriately.
     *
     * @param cell The cell to set the value to
     * @param value The value to set (can be null)
     */
    protected void setValue(Cell cell, Object value) {
        if (cell == null) {
            log.warn("Attempted to set value on null cell");
            return;
        }

        if (value == null) {
            cell.setBlank();
            return;
        }

        // Convert value to string and set
        cell.setCellValue(value.toString());
    }

    /**
     * Gets a new row from the sheet, creating it at the next available row index.
     *
     * @param sheet The sheet to create the row in
     * @return A new Row instance
     * @throws WorkbookException if sheet is null
     */
    protected Row getNewRow(Sheet sheet) {
        if (sheet == null) {
            throw new WorkbookException("Sheet cannot be null");
        }

        int newRowIndex = sheet.getLastRowNum() + 1;
        Row row = sheet.createRow(newRowIndex);

        log.trace("Created new row at index: {}", newRowIndex);
        return row;
    }
}
