package com.witboost.plugin.informatica.marketplace.service;

import com.witboost.plugin.informatica.common.exceptions.FailedOperation;
import java.util.Optional;

public interface ValidatorService<T> {

    Optional<FailedOperation> validate(T target);
}
