package com.witboost.plugin.informatica.common.exceptions;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public record FailedOperation(List<Problem> problems) {
    public FailedOperation {
        Objects.requireNonNull(problems);
    }

    @Override
    public String toString() {
        var problemsString =
                problems.stream().map(Problem::description).collect(Collectors.joining(", "));
        return "FailedOperation{" + "problems=" + problemsString + '}';
    }
}
