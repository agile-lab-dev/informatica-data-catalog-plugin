package com.witboost.plugin.informatica.datacatalog.service;

import static org.junit.jupiter.api.Assertions.*;

import com.witboost.plugin.informatica.common.mapper.dataproduct.DataAssetMapper;
import com.witboost.plugin.informatica.common.mapper.dataproduct.DataContractMapper;
import com.witboost.plugin.informatica.common.mapper.dataproduct.DeliveryTargetMapper;
import com.witboost.plugin.informatica.common.model.informatica.DataContract;
import com.witboost.plugin.informatica.common.parser.Parser;
import com.witboost.plugin.informatica.common.utils.ResourceUtils;
import java.io.IOException;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
@Disabled("Integration tests - requires real Informatica API credentials and network access")
class InformaticaDataCatalogServiceTest {

    @Autowired private InformaticaDataCatalogService service;

    @Autowired private DataContractMapper dataContractMapper;
    @Autowired private DeliveryTargetMapper deliveryTargetMapper;
    @Autowired private DataAssetMapper dataAssetMapper;

    @Test
    public void checkExistingDataProduct() {
        // Implement integration tests for InformaticaDataCatalogService methods
        var dataContract = createTestDataContract("System-Test", "This is a test data contract");
        assertTrue(service.isProductInDataCatalog(dataContract.getBaseCharacteristics().getName()));
    }

    @Test
    public void insertDataProduct() {
        var dataContract =
                createTestDataContract(
                        "System-Test-Insert", "This is a test data contract for insert");
        assertDoesNotThrow(() -> service.insertProductToDataCatalog(dataContract));
    }

    @Test
    public void testUpdateDataProduct() throws IOException {
        String descriptorContent =
                ResourceUtils.getContentFromResource(
                        "/descriptors/descriptor_integration_test.yml");
        var dataProduct = Parser.parseDataProduct(descriptorContent).get();
        var dataContract =
                dataContractMapper.toDataContractWithOutputPorts(
                        dataProduct, deliveryTargetMapper, dataAssetMapper);
        service.updateProductToDataCatalog(dataContract);
    }

    private DataContract createTestDataContract(String name, String description) {
        DataContract dataContract = new DataContract();
        dataContract.getBaseCharacteristics().setName(name);
        dataContract.getBaseCharacteristics().setDescription(description);
        return dataContract;
    }
}
