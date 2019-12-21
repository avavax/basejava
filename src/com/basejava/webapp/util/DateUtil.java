package com.basejava.webapp.util;

import java.time.YearMonth;
import java.time.format.DateTimeParseException;

import static com.basejava.webapp.model.Position.NOW;

public class DateUtil {

    public static YearMonth parse(String str) {
        if (str.equals("сейчас")) {
            return NOW;
        }
        YearMonth result;
        try {
            result = YearMonth.parse(str);
        } catch(DateTimeParseException e) {
            result = NOW;
        }
        return result;
    }
}
