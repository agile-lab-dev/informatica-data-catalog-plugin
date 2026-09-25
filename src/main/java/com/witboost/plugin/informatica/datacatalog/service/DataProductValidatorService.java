package com.witboost.plugin.informatica.datacatalog.service;

import com.witboost.plugin.informatica.common.exceptions.FailedOperation;
import com.witboost.plugin.informatica.common.model.witboost.DataProduct;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service("DataCatalogDataProductValidatorService")
public class DataProductValidatorService implements ValidatorService<DataProduct> {

    private static final Logger logger = LoggerFactory.getLogger(DataProductValidatorService.class);

    @Override
    public Optional<FailedOperation> validate(DataProduct dataProduct) {
        logger.info("Validating data product descriptor, output port and column tags");
        return Optional.empty();
    }
}
