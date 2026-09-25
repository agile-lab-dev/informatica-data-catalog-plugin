package com.witboost.plugin.informatica.datacatalog.service;

import com.witboost.plugin.informatica.common.exceptions.DataContractViolations;
import com.witboost.plugin.informatica.common.exceptions.FailedOperation;
import com.witboost.plugin.informatica.common.exceptions.Problem;
import com.witboost.plugin.informatica.common.model.informatica.DataContract;
import com.witboost.plugin.informatica.datacatalog.config.DataCatalogSourceConfig;
import jakarta.validation.Validator;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service("DataCatalogDataContractValidatorService")
public class DataContractValidatorService implements ValidatorService<DataContract> {

    private static final Logger logger =
            LoggerFactory.getLogger(DataContractValidatorService.class);

    private final Validator validator;
    private final DataCatalogSourceConfig dataCatalogSourceConfig;

    public DataContractValidatorService(
            Validator validator, DataCatalogSourceConfig dataCatalogSourceConfig) {
        this.validator = validator;
        this.dataCatalogSourceConfig = dataCatalogSourceConfig;
    }

    @Override
    public Optional<FailedOperation> validate(DataContract dataContract) {
        logger.info("Validating Informatica data contract");

        List<Problem> problems =
                new ArrayList<>(
                        DataContractViolations.toProblems(validator.validate(dataContract)));

        for (var dt : dataContract.getDeliveryTargets()) {
            String portTech =
                    dt.getBaseCharacteristics() != null
                            ? dt.getBaseCharacteristics().getPortTechnology()
                            : null;
            if (portTech == null) continue;
            var sourceName =
                    dataCatalogSourceConfig.findCatalogSourceByTechnology(portTech.toLowerCase());
            if (sourceName.isEmpty()) {
                problems.add(
                        new Problem(
                                "No catalog source configured for technology '" + portTech + "'"));
            }
        }

        if (problems.isEmpty()) {
            return Optional.empty();
        }

        // Failures are logged once at the operation boundary (validate/provision/unprovision), so
        // we
        // don't log here to avoid duplicate lines.
        return Optional.of(new FailedOperation(problems));
    }
}
