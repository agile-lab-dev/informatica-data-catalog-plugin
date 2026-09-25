package com.witboost.plugin.informatica.common.mapper.naming;

import static org.junit.jupiter.api.Assertions.*;

import com.witboost.plugin.informatica.common.model.informatica.DataAsset;
import com.witboost.plugin.informatica.common.model.informatica.DataContract;
import com.witboost.plugin.informatica.common.model.informatica.DeliveryTarget;
import org.junit.jupiter.api.Test;

class NameBuilderTest {

    // ========================================
    // Tests for getOutputPortMode
    // ========================================

    @Test
    void getOutputPortModeReturnsPrivateWhenNameContainsPrivateMode() {
        // Arrange
        String outputPortName = "my_port_private_mode";

        // Act
        OutputPortMode result = NameBuilder.getOutputPortMode(outputPortName);

        // Assert
        assertEquals(OutputPortMode.PRIVATE, result);
    }

    @Test
    void getOutputPortModeReturnsStandardWhenNameContainsStandardMode() {
        // Arrange
        String outputPortName = "my_port_standard_mode";

        // Act
        OutputPortMode result = NameBuilder.getOutputPortMode(outputPortName);

        // Assert
        assertEquals(OutputPortMode.STANDARD, result);
    }

    @Test
    void getOutputPortModeReturnsStandardAsFallback() {
        // Arrange
        String outputPortName = "some_random_port_name";

        // Act
        OutputPortMode result = NameBuilder.getOutputPortMode(outputPortName);

        // Assert
        assertEquals(OutputPortMode.STANDARD, result);
    }

    @Test
    void getOutputPortModeHandlesPrivateModeWithUppercase() {
        // Arrange
        String outputPortName = "PRIVATE_MODE";

        // Act
        OutputPortMode result = NameBuilder.getOutputPortMode(outputPortName);

        // Assert
        assertEquals(OutputPortMode.PRIVATE, result);
    }

    @Test
    void getOutputPortModeHandlesStandardModeWithUppercase() {
        // Arrange
        String outputPortName = "STANDARD_MODE";

        // Act
        OutputPortMode result = NameBuilder.getOutputPortMode(outputPortName);

        // Assert
        assertEquals(OutputPortMode.STANDARD, result);
    }

    // ========================================
    // Tests for getDataCatalogDataProductName
    // ========================================

    @Test
    void getDataCatalogDataProductNameFormatsWithDPPrefix() {
        // Arrange
        DataContract dataContract = createDataContractWithName("My Data Product");

        // Act
        String result = NameBuilder.getDataCatalogDataProductName(dataContract);

        // Assert
        assertEquals("DP_MY_DATA_PRODUCT", result);
    }

    @Test
    void getDataCatalogDataProductNameHandlesCamelCase() {
        // Arrange
        DataContract dataContract = createDataContractWithName("myDataProduct");

        // Act
        String result = NameBuilder.getDataCatalogDataProductName(dataContract);

        // Assert
        assertEquals("DP_MY_DATA_PRODUCT", result);
    }

    @Test
    void getDataCatalogDataProductNameHandlesHyphens() {
        // Arrange
        DataContract dataContract = createDataContractWithName("my-data-product");

        // Act
        String result = NameBuilder.getDataCatalogDataProductName(dataContract);

        // Assert
        assertEquals("DP_MY_DATA_PRODUCT", result);
    }

    @Test
    void getDataCatalogDataProductNameHandlesDots() {
        // Arrange
        DataContract dataContract = createDataContractWithName("my.data.product");

        // Act
        String result = NameBuilder.getDataCatalogDataProductName(dataContract);

        // Assert
        assertEquals("DP_MY_DATA_PRODUCT", result);
    }

    @Test
    void getDataCatalogDataProductNameHandlesSingleWord() {
        // Arrange
        DataContract dataContract = createDataContractWithName("product");

        // Act
        String result = NameBuilder.getDataCatalogDataProductName(dataContract);

        // Assert
        assertEquals("DP_PRODUCT", result);
    }

    @Test
    void getDataCatalogDataProductNameHandlesAccentedCharacters() {
        // Arrange
        DataContract dataContract = createDataContractWithName("Città Perù");

        // Act
        String result = NameBuilder.getDataCatalogDataProductName(dataContract);

        // Assert
        assertEquals("DP_CITTA_PERU", result);
    }

    // ========================================
    // Tests for getDataCatalogOutputPortName
    // ========================================

    @Test
    void getDataCatalogOutputPortNameFormatsCorrectlyForSnowflakeStandard() {
        // Arrange
        DataContract dataContract = createDataContractWithName("Sales Data");
        DeliveryTarget deliveryTarget =
                createDeliveryTargetWithTechnologyAndMode("snowflake", "standard_mode");

        // Act
        String result = NameBuilder.getDataCatalogOutputPortName(dataContract, deliveryTarget);

        // Assert
        assertEquals("DP_SALES_DATA_SNOWFLAKE_STANDARD_MODE", result);
    }

    @Test
    void getDataCatalogOutputPortNameFormatsCorrectlyForSnowflakePrivate() {
        // Arrange
        DataContract dataContract = createDataContractWithName("Customer Data");
        DeliveryTarget deliveryTarget =
                createDeliveryTargetWithTechnologyAndMode("snowflake", "private_mode");

        // Act
        String result = NameBuilder.getDataCatalogOutputPortName(dataContract, deliveryTarget);

        // Assert
        assertEquals("DP_CUSTOMER_DATA_SNOWFLAKE_PRIVATE_MODE", result);
    }

    @Test
    void getDataCatalogOutputPortNameFormatsCorrectlyForDatabricksStandard() {
        // Arrange
        DataContract dataContract = createDataContractWithName("Product Catalog");
        DeliveryTarget deliveryTarget =
                createDeliveryTargetWithTechnologyAndMode("databricks", "standard_mode");

        // Act
        String result = NameBuilder.getDataCatalogOutputPortName(dataContract, deliveryTarget);

        // Assert
        assertEquals("DP_PRODUCT_CATALOG_DATABRICKS_STANDARD_MODE", result);
    }

    @Test
    void getDataCatalogOutputPortNameFormatsCorrectlyForDatabricksPrivate() {
        // Arrange
        DataContract dataContract = createDataContractWithName("Internal Metrics");
        DeliveryTarget deliveryTarget =
                createDeliveryTargetWithTechnologyAndMode("databricks", "private_mode");

        // Act
        String result = NameBuilder.getDataCatalogOutputPortName(dataContract, deliveryTarget);

        // Assert
        assertEquals("DP_INTERNAL_METRICS_DATABRICKS_PRIVATE_MODE", result);
    }

    @Test
    void getDataCatalogOutputPortNameHandlesCamelCaseInDataContractName() {
        // Arrange
        DataContract dataContract = createDataContractWithName("salesDataProduct");
        DeliveryTarget deliveryTarget =
                createDeliveryTargetWithTechnologyAndMode("snowflake", "standard_mode");

        // Act
        String result = NameBuilder.getDataCatalogOutputPortName(dataContract, deliveryTarget);

        // Assert
        assertEquals("DP_SALES_DATA_PRODUCT_SNOWFLAKE_STANDARD_MODE", result);
    }

    @Test
    void getDataCatalogOutputPortNameHandlesAccentedCharactersInDataContractName() {
        // Arrange
        DataContract dataContract = createDataContractWithName("Città Perù");
        DeliveryTarget deliveryTarget =
                createDeliveryTargetWithTechnologyAndMode("snowflake", "standard_mode");

        // Act
        String result = NameBuilder.getDataCatalogOutputPortName(dataContract, deliveryTarget);

        // Assert
        assertEquals("DP_CITTA_PERU_SNOWFLAKE_STANDARD_MODE", result);
    }

    // ========================================
    // Tests for getDataCatalogDataSetName
    // ========================================

    @Test
    void getDataCatalogDataSetNameFormatsCorrectlyForSnowflakeStandard() {
        // Arrange
        DeliveryTarget deliveryTarget =
                createDeliveryTargetWithTechnologyAndMode("snowflake", "standard_mode");
        DataAsset dataAsset = createDataAssetWithEntityName("Customer");

        // Act
        String result = NameBuilder.getDataCatalogDataSetName(deliveryTarget, dataAsset);

        // Assert
        assertEquals("snowflake_standard_mode-customer", result);
    }

    @Test
    void getDataCatalogDataSetNameFormatsCorrectlyForSnowflakePrivate() {
        // Arrange
        DeliveryTarget deliveryTarget =
                createDeliveryTargetWithTechnologyAndMode("snowflake", "private_mode");
        DataAsset dataAsset = createDataAssetWithEntityName("Order");

        // Act
        String result = NameBuilder.getDataCatalogDataSetName(deliveryTarget, dataAsset);

        // Assert
        assertEquals("snowflake_private_mode-order", result);
    }

    @Test
    void getDataCatalogDataSetNameFormatsCorrectlyForDatabricksStandard() {
        // Arrange
        DeliveryTarget deliveryTarget =
                createDeliveryTargetWithTechnologyAndMode("Databricks", "standard_mode");
        DataAsset dataAsset = createDataAssetWithEntityName("Product");

        // Act
        String result = NameBuilder.getDataCatalogDataSetName(deliveryTarget, dataAsset);

        // Assert
        assertEquals("databricks_standard_mode-product", result);
    }

    @Test
    void getDataCatalogDataSetNameFormatsCorrectlyForDatabricksPrivate() {
        // Arrange
        DeliveryTarget deliveryTarget =
                createDeliveryTargetWithTechnologyAndMode("Databricks", "private_mode");
        DataAsset dataAsset = createDataAssetWithEntityName("Invoice");

        // Act
        String result = NameBuilder.getDataCatalogDataSetName(deliveryTarget, dataAsset);

        // Assert
        assertEquals("databricks_private_mode-invoice", result);
    }

    @Test
    void getDataCatalogDataSetNameHandlesCamelCaseEntityName() {
        // Arrange
        DeliveryTarget deliveryTarget =
                createDeliveryTargetWithTechnologyAndMode("Snowflake", "standard_mode");
        DataAsset dataAsset = createDataAssetWithEntityName("customerOrder");

        // Act
        String result = NameBuilder.getDataCatalogDataSetName(deliveryTarget, dataAsset);

        // Assert
        assertEquals("snowflake_standard_mode-customer_order", result);
    }

    @Test
    void getDataCatalogDataSetNameHandlesHyphensInEntityName() {
        // Arrange
        DeliveryTarget deliveryTarget =
                createDeliveryTargetWithTechnologyAndMode("Snowflake", "standard_mode");
        DataAsset dataAsset = createDataAssetWithEntityName("customer-order");

        // Act
        String result = NameBuilder.getDataCatalogDataSetName(deliveryTarget, dataAsset);

        // Assert
        assertEquals("snowflake_standard_mode-customer_order", result);
    }

    @Test
    void getDataCatalogDataSetNameHandlesLowercasePortTechnology() {
        // Arrange
        DeliveryTarget deliveryTarget =
                createDeliveryTargetWithTechnologyAndMode("snowflake", "standard_mode");
        DataAsset dataAsset = createDataAssetWithEntityName("Product");

        // Act
        String result = NameBuilder.getDataCatalogDataSetName(deliveryTarget, dataAsset);

        // Assert
        assertEquals("snowflake_standard_mode-product", result);
    }

    @Test
    void getDataCatalogDataSetNameHandlesAccentedCharactersInEntityName() {
        // Arrange
        DeliveryTarget deliveryTarget =
                createDeliveryTargetWithTechnologyAndMode("snowflake", "standard_mode");
        DataAsset dataAsset = createDataAssetWithEntityName("Città Perù");

        // Act
        String result = NameBuilder.getDataCatalogDataSetName(deliveryTarget, dataAsset);

        // Assert
        assertEquals("snowflake_standard_mode-citta_peru", result);
    }

    // ========================================
    // Tests for getDataMarketplaceTemplateRefId
    // ========================================

    @Test
    void getDataMarketplaceTemplateRefIdFormatsCorrectlyForSnowflakeStandard() {
        // Arrange
        DeliveryTarget deliveryTarget =
                createDeliveryTargetWithTechnologyAndMode("snowflake", "standard_mode");

        // Act
        String result = NameBuilder.getDataMarketplaceTemplateRefId(deliveryTarget);

        // Assert
        assertEquals("snowflake_standard_output_port", result);
    }

    @Test
    void getDataMarketplaceTemplateRefIdFormatsCorrectlyForSnowflakePrivate() {
        // Arrange
        DeliveryTarget deliveryTarget =
                createDeliveryTargetWithTechnologyAndMode("snowflake", "private_mode");

        // Act
        String result = NameBuilder.getDataMarketplaceTemplateRefId(deliveryTarget);

        // Assert
        assertEquals("snowflake_private_output_port", result);
    }

    @Test
    void getDataMarketplaceTemplateRefIdFormatsCorrectlyForDatabricksStandard() {
        // Arrange
        DeliveryTarget deliveryTarget =
                createDeliveryTargetWithTechnologyAndMode("databricks", "standard_mode");

        // Act
        String result = NameBuilder.getDataMarketplaceTemplateRefId(deliveryTarget);

        // Assert
        assertEquals("databricks_standard_output_port", result);
    }

    @Test
    void getDataMarketplaceTemplateRefIdFormatsCorrectlyForDatabricksPrivate() {
        // Arrange
        DeliveryTarget deliveryTarget =
                createDeliveryTargetWithTechnologyAndMode("databricks", "private_mode");

        // Act
        String result = NameBuilder.getDataMarketplaceTemplateRefId(deliveryTarget);

        // Assert
        assertEquals("databricks_private_output_port", result);
    }

    // ========================================
    // Tests for getDataMarketplaceOutputPortName
    // ========================================

    @Test
    void getDataMarketplaceOutputPortNameFormatsCorrectlyForSnowflakeStandard() {
        // Arrange
        DataContract dataContract = createDataContractWithName("Sales Data");
        DeliveryTarget deliveryTarget =
                createDeliveryTargetWithTechnologyAndMode("snowflake", "standard_mode");

        // Act
        String result = NameBuilder.getDataMarketplaceOutputPortName(dataContract, deliveryTarget);

        // Assert
        assertEquals("sales_data_snowflake_standard_mode", result);
    }

    @Test
    void getDataMarketplaceOutputPortNameFormatsCorrectlyForSnowflakePrivate() {
        // Arrange
        DataContract dataContract = createDataContractWithName("Customer Data");
        DeliveryTarget deliveryTarget =
                createDeliveryTargetWithTechnologyAndMode("snowflake", "private_mode");

        // Act
        String result = NameBuilder.getDataMarketplaceOutputPortName(dataContract, deliveryTarget);

        // Assert
        assertEquals("customer_data_snowflake_private_mode", result);
    }

    @Test
    void getDataMarketplaceOutputPortNameFormatsCorrectlyForDatabricksStandard() {
        // Arrange
        DataContract dataContract = createDataContractWithName("Product Catalog");
        DeliveryTarget deliveryTarget =
                createDeliveryTargetWithTechnologyAndMode("databricks", "standard_mode");

        // Act
        String result = NameBuilder.getDataMarketplaceOutputPortName(dataContract, deliveryTarget);

        // Assert
        assertEquals("product_catalog_databricks_standard_mode", result);
    }

    @Test
    void getDataMarketplaceOutputPortNameFormatsCorrectlyForDatabricksPrivate() {
        // Arrange
        DataContract dataContract = createDataContractWithName("Internal Metrics");
        DeliveryTarget deliveryTarget =
                createDeliveryTargetWithTechnologyAndMode("databricks", "private_mode");

        // Act
        String result = NameBuilder.getDataMarketplaceOutputPortName(dataContract, deliveryTarget);

        // Assert
        assertEquals("internal_metrics_databricks_private_mode", result);
    }

    @Test
    void getDataMarketplaceOutputPortNameHandlesCamelCaseInDataContractName() {
        // Arrange
        DataContract dataContract = createDataContractWithName("salesDataProduct");
        DeliveryTarget deliveryTarget =
                createDeliveryTargetWithTechnologyAndMode("snowflake", "standard_mode");

        // Act
        String result = NameBuilder.getDataMarketplaceOutputPortName(dataContract, deliveryTarget);

        // Assert
        assertEquals("sales_data_product_snowflake_standard_mode", result);
    }

    @Test
    void getDataMarketplaceOutputPortNameHandlesSpacesInDataContractName() {
        // Arrange
        DataContract dataContract = createDataContractWithName("My Sales Data");
        DeliveryTarget deliveryTarget =
                createDeliveryTargetWithTechnologyAndMode("snowflake", "standard_mode");

        // Act
        String result = NameBuilder.getDataMarketplaceOutputPortName(dataContract, deliveryTarget);

        // Assert
        assertEquals("my_sales_data_snowflake_standard_mode", result);
    }

    @Test
    void getDataMarketplaceOutputPortNameHandlesHyphensInDataContractName() {
        // Arrange
        DataContract dataContract = createDataContractWithName("sales-data-product");
        DeliveryTarget deliveryTarget =
                createDeliveryTargetWithTechnologyAndMode("snowflake", "standard_mode");

        // Act
        String result = NameBuilder.getDataMarketplaceOutputPortName(dataContract, deliveryTarget);

        // Assert
        assertEquals("sales_data_product_snowflake_standard_mode", result);
    }

    @Test
    void getDataMarketplaceOutputPortNameLowercasesMixedCaseTechnology() {
        DataContract dataContract = createDataContractWithName("datapr-strutturaagenzie");
        DeliveryTarget deliveryTarget =
                createDeliveryTargetWithTechnologyAndMode("Snowflake", "standard_mode");

        String result = NameBuilder.getDataMarketplaceOutputPortName(dataContract, deliveryTarget);

        assertEquals("datapr_strutturaagenzie_snowflake_standard_mode", result);
    }

    @Test
    void getDataMarketplaceOutputPortNameHandlesAccentedCharactersInDataContractName() {
        // Arrange
        DataContract dataContract = createDataContractWithName("Città Perù");
        DeliveryTarget deliveryTarget =
                createDeliveryTargetWithTechnologyAndMode("snowflake", "standard_mode");

        // Act
        String result = NameBuilder.getDataMarketplaceOutputPortName(dataContract, deliveryTarget);

        // Assert
        assertEquals("citta_peru_snowflake_standard_mode", result);
    }

    // ========================================
    // Helper methods
    // ========================================

    private DataContract createDataContractWithName(String name) {
        DataContract dataContract = new DataContract();
        DataContract.BaseCharacteristics baseCharacteristics =
                new DataContract.BaseCharacteristics();
        baseCharacteristics.setName(name);
        dataContract.setBaseCharacteristics(baseCharacteristics);
        return dataContract;
    }

    private DeliveryTarget createDeliveryTargetWithTechnologyAndMode(
            String technology, String portName) {
        DeliveryTarget deliveryTarget = new DeliveryTarget();
        DeliveryTarget.BaseCharacteristics baseCharacteristics =
                new DeliveryTarget.BaseCharacteristics();
        baseCharacteristics.setPortTechnology(technology);
        baseCharacteristics.setPortName(portName);
        deliveryTarget.setBaseCharacteristics(baseCharacteristics);
        return deliveryTarget;
    }

    private DataAsset createDataAssetWithEntityName(String entityName) {
        DataAsset dataAsset = new DataAsset();
        DataAsset.EntityInfo entityInfo = new DataAsset.EntityInfo();
        entityInfo.setEntityName(entityName);
        dataAsset.setEntityInfo(entityInfo);
        return dataAsset;
    }
}
