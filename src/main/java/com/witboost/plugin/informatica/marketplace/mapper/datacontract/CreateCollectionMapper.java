package com.witboost.plugin.informatica.marketplace.mapper.datacontract;

import com.witboost.plugin.informatica.common.model.informatica.DataContract;
import com.witboost.plugin.informatica.marketplace.common.Constants;
import com.witboost.plugin.informatica.marketplace.mapper.MarketplaceAttributeMapper;
import com.witboost.plugin.informatica.marketplace.model.CreateDataCollectionRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * MapStruct mapper for converting DataContract objects to CreateDataCollectionRequest objects. Uses
 * declarative approach with @Mapping annotations for automatic field mapping.
 */
@Mapper(
        componentModel = "spring",
        imports = {Arrays.class, List.class, ArrayList.class})
public abstract class CreateCollectionMapper {

    @Autowired(required = false)
    protected MarketplaceAttributeMapper marketplaceAttributeMapper;

    /**
     * cr Maps a DataContract to a CreateDataCollectionRequest using MapStruct declarative approach.
     *
     * @param dataContract The source DataContract object (can be null, but if non-null, info must
     *     not be null)
     * @param categoryId The category ID to associate with the data collection
     * @return CreateDataCollectionRequest with mapped fields
     * @throws IllegalArgumentException if dataContract is non-null but dataContract.info is null
     */
    @Mapping(source = "dataContract.baseCharacteristics.name", target = "name")
    @Mapping(source = "dataContract.baseCharacteristics.description", target = "description")
    @Mapping(source = "categoryId", target = "categoryId")
    @Mapping(target = "status", constant = Constants.DATA_COLLECTION_PUBLISHED_STATUS)
    @Mapping(
            target = "customAttributes",
            source = "dataContract",
            qualifiedByName = "mapCustomAttributesToList")
    public abstract CreateDataCollectionRequest mapToCreateDataCollectionRequest(
            DataContract dataContract, String categoryId);

    /**
     * Custom method to convert DataContract to CustomAttribute list. Uses context-passed
        * ID-keyed custom attributes already extracted from the descriptor.
     *
     * @param dataContract The source DataContract object (must not have null info)
     * @return A list of mapped CustomAttribute objects
     * @throws IllegalArgumentException if dataContract.info is null or if required attribute
     *     mapping is missing
     */
    @Named("mapCustomAttributesToList")
    @SuppressWarnings("unused") // Used by MapStruct via @Named qualifier
    protected List<CreateDataCollectionRequest.CustomAttribute> mapCustomAttributesToList(
            DataContract dataContract) {

        Map<String, Object> customAttributeValues =
                marketplaceAttributeMapper == null
                        ? dataContract.getCustomAttributes()
                        : marketplaceAttributeMapper.map(dataContract);
        return customAttributeValues.entrySet().stream()
                .map(
                        entry -> {
                            String attributeId = entry.getKey();
                            Object attributeValue = entry.getValue();
                            return CreateDataCollectionRequest.CustomAttribute.builder()
                                    .id(attributeId)
                                    .value(attributeValue)
                                    .build();
                        })
                .collect(Collectors.toList());
    }
}
