package com.witboost.plugin.informatica.common.exceptions;

import java.io.PrintWriter;
import java.io.StringWriter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class ExceptionFormatter {

    public static String format(Throwable t) {
        if (t == null) return "";
        StringWriter sw = new StringWriter();
        t.printStackTrace(new PrintWriter(sw));
        String message = sw.toString().trim();
        log.error(message);
        return message;
    }
}
