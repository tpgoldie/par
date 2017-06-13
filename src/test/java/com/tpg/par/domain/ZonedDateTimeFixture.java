package com.tpg.par.domain;

import java.time.ZoneId;
import java.time.ZonedDateTime;

public interface ZonedDateTimeFixture {
    default ZonedDateTime buildDate(int year, int month, int date, int hour) {
        return ZonedDateTime.of(year, month, date, hour, 0, 0, 0, ZoneId.of("Europe/London"));
    }
}
