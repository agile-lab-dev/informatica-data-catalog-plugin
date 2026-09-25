package com.witboost.plugin.informatica.common.service;

import com.witboost.plugin.informatica.common.mapper.dataproduct.DataAssetMapper;
import com.witboost.plugin.informatica.common.mapper.dataproduct.DataContractMapper;
import com.witboost.plugin.informatica.common.mapper.dataproduct.DeliveryTargetMapper;
import com.witboost.plugin.informatica.common.model.informatica.DataContract;
import com.witboost.plugin.informatica.common.model.informatica.DeliveryTarget;
import com.witboost.plugin.informatica.common.model.witboost.DataProduct;
import com.witboost.plugin.informatica.common.model.witboost.OutputPort;
import com.witboost.plugin.informatica.common.model.witboost.Specific;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Service for mapping Witboost DataProduct to Informatica models.
 *
 * <p>This service orchestrates the mapping process from a Witboost DataProduct to:
 *
 * <ul>
 *   <li>{@link DataContract} - The data contract metadata
 *   <li>{@link DeliveryTarget} - Output ports grouped by technology, each containing DataAssets
 * </ul>
 *
 * <p>The mapping flow is:
 *
 * <pre>
 * DataProduct (Witboost)
 * ├── → DataContract (Informatica)
 * └── components (List&lt;OutputPort&gt;)
 *         │
 *         ▼ grouped by technology
 *     List&lt;OutputPort&gt; (Informatica)
 *     ├── OutputPort (tech=Databricks)
 *     │   └── List&lt;DataAsset&gt;
 *     └── OutputPort (tech=Snowflake)
 *         └── List&lt;DataAsset&gt;
 * </pre>
 */
@Service
@RequiredArgsConstructor
public class DataProductMappingService {

    private final DataContractMapper dataContractMapper;
    private final DeliveryTargetMapper deliveryTargetMapper;
    private final DataAssetMapper dataAssetMapper;

    /**
     * Maps a Witboost DataProduct to an Informatica DataContract.
     *
     * @param dataProduct the Witboost DataProduct
     * @return the mapped Informatica DataContract
     */
    public DataContract toDataContract(DataProduct dataProduct) {
        return dataContractMapper.toDataContract(dataProduct);
    }

    /**
     * Maps a Witboost DataProduct's components to Informatica OutputPorts, grouped by technology.
     * Each OutputPort contains the DataAssets (tables/views) for that technology.
     *
     * @param dataProduct the Witboost DataProduct
     * @return list of Informatica OutputPorts, one per technology
     */
    @SuppressWarnings("unchecked")
    public List<DeliveryTarget> toOutputPorts(DataProduct dataProduct) {
        List<OutputPort<Specific>> witboostOutputPorts = dataProduct.extractOutputPorts();

        return deliveryTargetMapper.toOutputPortsGroupedByTechnology(
                witboostOutputPorts, dataAssetMapper);
    }

    /** Result container for the complete mapping of a DataProduct. */
    public record MappingResult(DataContract dataContract, List<DeliveryTarget> deliveryTargets) {}

    /**
     * Maps a Witboost DataProduct to all Informatica models.
     *
     * @param dataProduct the Witboost DataProduct
     * @return a MappingResult containing the DataContract and OutputPorts
     */
    public MappingResult mapDataProduct(DataProduct dataProduct) {
        return new MappingResult(toDataContract(dataProduct), toOutputPorts(dataProduct));
    }
}
