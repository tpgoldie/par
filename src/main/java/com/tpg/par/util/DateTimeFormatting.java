package com.tpg.par.util;

import java.time.format.DateTimeFormatter;

public interface DateTimeFormatting {

    default DateTimeFormatter dateTimeFormatter() {
        return DateTimeFormatter.ofPattern("EEE dd MMM yyyy");
    }
}
