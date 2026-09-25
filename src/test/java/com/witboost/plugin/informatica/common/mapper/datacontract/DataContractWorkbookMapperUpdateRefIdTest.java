package com.witboost.plugin.informatica.common.mapper.datacontract;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import com.witboost.plugin.informatica.common.mapper.naming.NameBuilder;
import com.witboost.plugin.informatica.common.model.informatica.DataAsset;
import com.witboost.plugin.informatica.common.model.informatica.DeliveryTarget;
import com.witboost.plugin.informatica.datacatalog.model.AssetGetResponse;
import com.witboost.plugin.informatica.datacatalog.service.client.DataCatalogAssetApiClient;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * Guards the fix that resolves the "Multiple assets exist with the same asset name … Enter a unique
 * Reference ID" import failure on re-provision: for a Data set already in the catalog (UPDATE), the
 * workbook row must carry Reference ID = the existing asset's external identity, not an empty cell.
 */
@ExtendWith(MockitoExtension.class)
class DataContractWorkbookMapperUpdateRefIdTest {

    @Mock private DataCatalogAssetApiClient assetApiClient;

    private final DataContractWorkbookMapper mapper = new DataContractWorkbookMapper();

    private static DeliveryTarget snowflakeDeliveryTargetWith(DataAsset dataAsset) {
        DeliveryTarget dt = new DeliveryTarget();
        DeliveryTarget.BaseCharacteristics bc = new DeliveryTarget.BaseCharacteristics();
        bc.setPortTechnology("snowflake");
        bc.setPortName("standard_mode");
        dt.setBaseCharacteristics(bc);
        dt.setDataAssets(List.of(dataAsset));
        return dt;
    }

    private static DataAsset dataAssetWithEntity(String entityName) {
        DataAsset da = new DataAsset();
        DataAsset.EntityInfo ei = new DataAsset.EntityInfo();
        ei.setEntityName(entityName);
        da.setEntityInfo(ei);
        return da;
    }

    @Test
    void updateUsesExistingAssetExternalIdentityAsReferenceId() {
        DataAsset dataAsset = dataAssetWithEntity("CONTRATTO");
        DeliveryTarget dt = snowflakeDeliveryTargetWith(dataAsset);
        String dataSetName =
                NameBuilder.getDataCatalogDataSetName(
                        dt, dataAsset); // snowflake_standard_mode-contratto

        // Existing asset returned by the "dataset in system" query.
        AssetGetResponse.Hit.Summary summary = new AssetGetResponse.Hit.Summary();
        summary.setCoreName(dataSetName);
        AssetGetResponse.Hit hit = new AssetGetResponse.Hit();
        hit.setSummary(summary);
        hit.setExternalIdentity("external-identity-test");
        AssetGetResponse response = new AssetGetResponse();
        response.getHits().add(hit);
        when(assetApiClient.pollForResults(anyString())).thenReturn(response);

        Map<String, Object> extras = new HashMap<>();
        extras.put(MappingContext.DELIVERY_TARGET_NAME, "DP_UNICA_VIGORE_SWF_STANDARD_MODE");

        Workbook workbook =
                mapper.addDataSets(
                        dt, Map.of(dataSetName, Operation.UPDATE), extras, assetApiClient);

        assertEquals("external-identity-test", readLastRowCell(workbook, "Reference ID"));
    }

    @Test
    void createLeavesReferenceIdEmpty() {
        DataAsset dataAsset = dataAssetWithEntity("CONTRATTO");
        DeliveryTarget dt = snowflakeDeliveryTargetWith(dataAsset);
        String dataSetName = NameBuilder.getDataCatalogDataSetName(dt, dataAsset);

        when(assetApiClient.pollForResults(anyString())).thenReturn(new AssetGetResponse());

        Map<String, Object> extras = new HashMap<>();
        extras.put(MappingContext.DELIVERY_TARGET_NAME, "DP_UNICA_VIGORE_SWF_STANDARD_MODE");

        Workbook workbook =
                mapper.addDataSets(
                        dt, Map.of(dataSetName, Operation.CREATE), extras, assetApiClient);

        assertEquals("", readLastRowCell(workbook, "Reference ID"));
    }

    /** Reads the last data row's cell under the given header in the "Data Set" sheet. */
    private static String readLastRowCell(Workbook workbook, String header) {
        Sheet sheet = workbook.getSheet(DataContractWorkbookMapper.DATASET_SHEET_NAME);
        Row headerRow = sheet.getRow(0);
        int col = -1;
        for (Cell cell : headerRow) {
            if (header.equals(cell.getStringCellValue())) {
                col = cell.getColumnIndex();
                break;
            }
        }
        if (col < 0) {
            throw new IllegalArgumentException("Column '" + header + "' not found");
        }
        Cell cell = sheet.getRow(sheet.getLastRowNum()).getCell(col);
        return cell == null ? null : cell.getStringCellValue();
    }
}
