package com.witboost.plugin.informatica.marketplace.service;

import com.witboost.plugin.informatica.common.exceptions.DataContractViolations;
import com.witboost.plugin.informatica.common.exceptions.FailedOperation;
import com.witboost.plugin.informatica.common.exceptions.Problem;
import com.witboost.plugin.informatica.common.model.informatica.DataContract;
import jakarta.validation.Validator;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service("MarketplaceDataContractValidatorService")
@Slf4j
public class DataContractValidatorService implements ValidatorService<DataContract> {

    private final Validator validator;

    public DataContractValidatorService(Validator validator) {
        this.validator = validator;
    }

    @Override
    public Optional<FailedOperation> validate(DataContract dataContract) {
        log.info("Validating Informatica data contract");
        // Same bean-validation as the Data Catalog plugin, via the shared formatter, so both
        // plugins
        // report structural errors uniformly ([component <urn>] / [data product <urn>]). The Data
        // Catalog-only catalog-source/technology check is intentionally not run here.
        List<Problem> problems =
                DataContractViolations.toProblems(validator.validate(dataContract));
        return problems.isEmpty() ? Optional.empty() : Optional.of(new FailedOperation(problems));
    }
}
