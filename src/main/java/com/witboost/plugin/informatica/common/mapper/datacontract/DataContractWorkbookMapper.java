package com.witboost.plugin.informatica.common.mapper.datacontract;

import com.witboost.plugin.informatica.common.exceptions.WorkbookException;
import com.witboost.plugin.informatica.common.mapper.naming.NameBuilder;
import com.witboost.plugin.informatica.common.model.informatica.DataContract;
import com.witboost.plugin.informatica.common.model.informatica.DeliveryTarget;
import com.witboost.plugin.informatica.common.model.informatica.TechnicalDataElement;
import com.witboost.plugin.informatica.datacatalog.service.client.DataCatalogAssetApiClient;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * Workbook mapper for Data Contract information.
 *
 * <p>This class extends BaseWorkbookMapper to populate Excel workbooks with data contract metadata
 * following the Informatica import template format.
 */
@Slf4j
public class DataContractWorkbookMapper extends BaseWorkbookMapper {

    static final String SYSTEM_SHEET_NAME = "System";
    static final String DATASET_SHEET_NAME = "Data Set";
    static final String TECHNICAL_DATA_ELEMENT_SHEET_NAME = "Technical Data element";

    /**
     * Constructs a DataContractWorkbookMapper with the given data contract.
     *
     * @throws IllegalArgumentException if dataContract is null
     */
    public DataContractWorkbookMapper() {
        super();
    }

    public Workbook addSystem(DataContract dataContract, Map<String, Object> extras) {
        return addSystem(dataContract, Operation.CREATE, extras);
    }

    public Workbook addSystem(
            DataContract dataContract, Operation operation, Map<String, Object> extras) {
        Sheet sheet = validateAndGetSheet(SYSTEM_SHEET_NAME);
        List<String> header = getHeader(sheet);
        Row row = getNewRow(sheet);
        populateRow(row, RowMappers.map(header, dataContract, operation, extras));
        return getWorkbook();
    }

    public Workbook addSystem(DeliveryTarget deliveryTarget, Map<String, Object> extras) {
        return addSystem(deliveryTarget, Operation.CREATE, extras);
    }

    public Workbook addSystem(
            DeliveryTarget deliveryTarget, Operation operation, Map<String, Object> extras) {
        Sheet sheet = validateAndGetSheet(SYSTEM_SHEET_NAME);
        List<String> header = getHeader(sheet);
        Row row = getNewRow(sheet);
        populateRow(row, RowMappers.map(header, deliveryTarget, operation, extras));
        return getWorkbook();
    }

    public Workbook addDataSets(
            DeliveryTarget deliveryTarget,
            Map<String, Operation> operationByDataSet,
            Map<String, Object> extras,
            DataCatalogAssetApiClient assetApiClient) {
        Sheet sheet = validateAndGetSheet(DATASET_SHEET_NAME);
        List<String> header = getHeader(sheet);

        // TODO non è necessario se tutti i dataset sono CREATE
        var dataSetsInSystem =
                assetApiClient.pollForResults(
                        String.format(
                                "dataset in system \"%s\"",
                                extras.get(MappingContext.DELIVERY_TARGET_NAME)));

        deliveryTarget
                .getDataAssets()
                .forEach(
                        dataSet -> {
                            Row row = getNewRow(sheet);
                            HashMap<String, Object> datasetExtras = new HashMap<>(extras);
                            var dataSetName =
                                    NameBuilder.getDataCatalogDataSetName(deliveryTarget, dataSet);
                            datasetExtras.put(MappingContext.DATA_ASSET_NAME, dataSetName);
                            var dataSetOperation = Operation.CREATE;
                            if (operationByDataSet != null) {
                                dataSetOperation = operationByDataSet.get(dataSetName);
                                if (dataSetOperation == null) {
                                    throw new IllegalArgumentException(
                                            "Operation not found for dataset " + dataSetName);
                                }
                            }
                            if (dataSetOperation.equals(Operation.UPDATE)) {
                                for (var hit : dataSetsInSystem.getHits()) {
                                    // TODO usare mapping
                                    if (hit.getSummary().getCoreName().equals(dataSetName)) {
                                        var externalIdentity = hit.getExternalIdentity();
                                        datasetExtras.remove(
                                                MappingContext
                                                        .DATA_ASSET_NAME); // TODO probabilmente non
                                        // necessario
                                        datasetExtras.put(
                                                MappingContext.DATA_ASSET_REFERENCE_ID,
                                                externalIdentity);
                                    }
                                }
                            }
                            populateRow(
                                    row,
                                    RowMappers.map(
                                            header, dataSet, dataSetOperation, datasetExtras));
                        });
        return getWorkbook();
    }

    public Workbook addDatasetColumns(
            List<TechnicalDataElement> datasetColumns, Map<String, Object> extras) {
        Sheet sheet = validateAndGetSheet(TECHNICAL_DATA_ELEMENT_SHEET_NAME);
        List<String> header = getHeader(sheet);
        for (var col : datasetColumns) {
            Row row = getNewRow(sheet);
            populateRow(row, RowMappers.map(header, col, extras));
        }
        return getWorkbook();
    }

    private Row populateRow(Row row, String[] values) {
        for (int i = 0; i < values.length; i++) {
            Cell cell = row.createCell(i);
            cell.setCellValue(values[i]);
        }
        return row;
    }

    /**
     * Validates that a sheet exists in the workbook and returns it.
     *
     * <p>This utility method checks if a sheet with the specified name exists in the workbook. If
     * the sheet is found, it is returned. If not, a WorkbookException is thrown with a descriptive
     * error message.
     *
     * @param sheetName The name of the sheet to validate and retrieve (must not be null)
     * @return The Sheet object if found
     * @throws WorkbookException if the sheet is not found in the workbook
     * @throws IllegalArgumentException if sheetName is null or blank
     */
    private Sheet validateAndGetSheet(String sheetName) {
        if (sheetName == null || sheetName.isBlank()) {
            throw new IllegalArgumentException("Sheet name cannot be null or blank");
        }

        log.debug("Validating sheet existence: {}", sheetName);
        Sheet sheet = getWorkbook().getSheet(sheetName);

        if (sheet == null) {
            String errorMessage = String.format("Sheet '%s' not found in workbook", sheetName);
            log.error(errorMessage);
            throw new WorkbookException(errorMessage);
        }

        log.trace("Sheet '{}' found successfully", sheetName);
        return sheet;
    }
}
