package com.tpg.par.util;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public interface DateTimeFormatting {

    default DateTimeFormatter dateTimeFormatterEEEDDMMMYYYY() {
        return DateTimeFormatter.ofPattern("EEE dd MMM yyyy");
    }

    default DateTimeFormatter dateTimeFormatterDDMMYYYY() {
        return DateTimeFormatter.ofPattern("dd/MM/yyyy");
    }

    default String EEEDDMMYYYYFormat(ZonedDateTime value) {
        return dateTimeFormatterEEEDDMMMYYYY().format(value);
    }

    default String DDMMYYYYFormat(ZonedDateTime value) {
        return dateTimeFormatterDDMMYYYY().format(value);
    }

    default String IsoZonedDateTimeFormat(ZonedDateTime value) {
        return DateTimeFormatter.ISO_ZONED_DATE_TIME.format(value);
    }

    default ZonedDateTime parse(String value) { return ZonedDateTime.parse(value); }
}
