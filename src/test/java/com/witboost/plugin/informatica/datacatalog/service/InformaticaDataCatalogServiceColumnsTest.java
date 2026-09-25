package com.witboost.plugin.informatica.datacatalog.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.witboost.plugin.informatica.common.exceptions.ApiCallException;
import com.witboost.plugin.informatica.common.mapper.datacontract.DataContractWorkbookMapper;
import com.witboost.plugin.informatica.common.model.informatica.DataAsset;
import com.witboost.plugin.informatica.common.model.informatica.DataContract;
import com.witboost.plugin.informatica.common.model.informatica.DeliveryTarget;
import com.witboost.plugin.informatica.common.model.informatica.TechnicalDataElement;
import com.witboost.plugin.informatica.datacatalog.config.DataCatalogSourceConfig;
import com.witboost.plugin.informatica.datacatalog.service.client.AssetApiUtil;
import com.witboost.plugin.informatica.datacatalog.service.client.CatalogSourceApiClient;
import com.witboost.plugin.informatica.datacatalog.service.client.DataCatalogAssetApiClient;
import com.witboost.plugin.informatica.datacatalog.service.client.ImportAssetApiClient;
import com.witboost.plugin.informatica.datacatalog.service.client.JobApiClient;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class InformaticaDataCatalogServiceColumnsTest {

    @Mock private AssetApiUtil assetApiUtil;
    @Mock private DataCatalogAssetApiClient assetApiClient;
    @Mock private ImportAssetApiClient importAssetApiClient;
    @Mock private JobApiClient jobApiClient;
    @Mock private CatalogSourceApiClient catalogSourceApiClient;
    @Mock private TechnicalElementService technicalElementService;
    @Mock private DataCatalogSourceConfig dataCatalogSourceConfig;

    @Mock private DataContractWorkbookMapper mapper;

    private InformaticaDataCatalogService service;

    @BeforeEach
    void setup() {
        service =
                new InformaticaDataCatalogService(
                        assetApiUtil,
                        assetApiClient,
                        importAssetApiClient,
                        jobApiClient,
                        catalogSourceApiClient,
                        technicalElementService,
                        dataCatalogSourceConfig);
    }

    /**
     * When a dataset's technical elements cannot be retrieved (searchDataElements throws), that
     * dataset must be skipped (created without columns) without aborting the whole workbook: the
     * other datasets' columns are still added and no exception propagates.
     */
    @Test
    void skipsDatasetWhoseColumnsCannotBeRetrievedAndKeepsTheOthers() {
        when(dataCatalogSourceConfig.findCatalogSourceByTechnology("snowflake"))
                .thenReturn(Optional.of("snowflake-src"));

        DeliveryTarget dt = new DeliveryTarget();
        dt.getBaseCharacteristics().setPortName("output_private");
        dt.getBaseCharacteristics().setPortTechnology("snowflake");
        dt.setDataAssets(List.of(dataAsset("ENTITY_OK"), dataAsset("ENTITY_KO")));

        DataContract dataContract = new DataContract();
        dataContract.setDeliveryTargets(List.of(dt));

        List<TechnicalDataElement> okColumns = List.of(TechnicalDataElement.builder().build());
        when(technicalElementService.searchDataElements(any(), any(), any(), eq("ENTITY_OK")))
                .thenReturn(okColumns);
        when(technicalElementService.searchDataElements(any(), any(), any(), eq("ENTITY_KO")))
                .thenThrow(
                        new ApiCallException(
                                "List Asset API returned empty result for entity: ENTITY_KO"));

        assertDoesNotThrow(
                () -> service.addDataSetColumnsToWorkbook(dataContract, mapper, new HashMap<>()));

        // columns added only for the dataset that resolved correctly, and nothing else
        verify(mapper, times(1)).addDatasetColumns(eq(okColumns), any());
        verify(mapper, times(1)).addDatasetColumns(any(), any());
    }

    private DataAsset dataAsset(String entityName) {
        return DataAsset.builder()
                .systemInfo(
                        DataAsset.SystemInfo.builder().databaseName("DB").schemaName("SCH").build())
                .entityInfo(DataAsset.EntityInfo.builder().entityName(entityName).build())
                .build();
    }
}
