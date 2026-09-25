package com.witboost.plugin.informatica.marketplace.service;

import com.witboost.plugin.informatica.common.exceptions.FailedOperation;
import com.witboost.plugin.informatica.common.model.witboost.DataProduct;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service("MarketplaceDataProductValidatorService")
@Slf4j
public class DataProductValidatorService implements ValidatorService<DataProduct> {

    @Override
    public Optional<FailedOperation> validate(DataProduct dataProduct) {
        log.info("Validating data product descriptor, output port and column tags");
        return Optional.empty();
    }
}
