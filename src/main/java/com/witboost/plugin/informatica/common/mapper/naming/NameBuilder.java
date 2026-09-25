package com.witboost.plugin.informatica.common.mapper.naming;

import static com.witboost.plugin.informatica.marketplace.utils.StringUtil.toSnakeLowerCase;
import static com.witboost.plugin.informatica.marketplace.utils.StringUtil.toSnakeUpperCase;

import com.witboost.plugin.informatica.common.model.informatica.DataAsset;
import com.witboost.plugin.informatica.common.model.informatica.DataContract;
import com.witboost.plugin.informatica.common.model.informatica.DeliveryTarget;

public class NameBuilder {

    public static OutputPortMode getOutputPortMode(String outputPortName) {
        if (outputPortName == null || outputPortName.isEmpty()) {
            throw new IllegalArgumentException("empty output port name");
        }
        var lowerCaseName = outputPortName.toLowerCase();
        if (lowerCaseName.contains(OutputPortMode.PRIVATE.getValue())) {
            return OutputPortMode.PRIVATE;
        } else if (lowerCaseName.contains(OutputPortMode.STANDARD.getValue())) {
            return OutputPortMode.STANDARD;
        } else {
            // Output Port name is malformed, return default
            return OutputPortMode.STANDARD;
        }
    }

    public static String getDataCatalogDataProductName(DataContract dataContract) {
        var dataProductName = dataContract.getBaseCharacteristics().getName();
        return "DP_" + toSnakeUpperCase(dataProductName);
    }

    public static String getDataCatalogOutputPortName(
            DataContract dataContract, DeliveryTarget deliveryTarget) {
        var dataProductName = dataContract.getBaseCharacteristics().getName();
        var portTechnology = deliveryTarget.getBaseCharacteristics().getPortTechnology();
        var portName = deliveryTarget.getBaseCharacteristics().getPortName();

        var outputPortMode = getOutputPortMode(portName);

        return String.format(
                        "DP_%s_%s_%s",
                        toSnakeUpperCase(dataProductName),
                        toSnakeUpperCase(portTechnology),
                        outputPortMode.getValue())
                .toUpperCase();
    }

    public static String getDataCatalogDataSetName(
            DeliveryTarget deliveryTarget, DataAsset dataAsset) {
        var portTechnology = deliveryTarget.getBaseCharacteristics().getPortTechnology();
        var portName = deliveryTarget.getBaseCharacteristics().getPortName();
        var entityName = dataAsset.getEntityInfo().getEntityName();

        var outputPortMode = getOutputPortMode(portName);

        return String.format(
                        "%s_%s-%s",
                        portTechnology, outputPortMode.getValue(), toSnakeUpperCase(entityName))
                .toLowerCase();
    }

    public static String getDataMarketplaceTemplateRefId(DeliveryTarget deliveryTarget) {
        var portTechnology = deliveryTarget.getBaseCharacteristics().getPortTechnology();
        var portName = deliveryTarget.getBaseCharacteristics().getPortName();

        var outputPortMode = getOutputPortMode(portName);

        return String.format(
                "%s_%s_output_port",
                portTechnology.toLowerCase(), outputPortMode.name().toLowerCase());
    }

    public static String getDataMarketplaceOutputPortName(
            DataContract dataContract, DeliveryTarget deliveryTarget) {
        var dataProductName = dataContract.getBaseCharacteristics().getName();
        var portTechnology = deliveryTarget.getBaseCharacteristics().getPortTechnology();
        var portName = deliveryTarget.getBaseCharacteristics().getPortName();

        var outputPortMode = getOutputPortMode(portName);

        return String.format(
                "%s_%s_%s",
                toSnakeLowerCase(dataProductName),
                portTechnology.toLowerCase(),
                outputPortMode.getValue());
    }

    public static String getDataMarketplaceDataAssetName(
            DataContract dataContract, DeliveryTarget deliveryTarget) {
        return getDataCatalogOutputPortName(dataContract, deliveryTarget);
    }
}
