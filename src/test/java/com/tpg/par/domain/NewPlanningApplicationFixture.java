package com.tpg.par.domain;

import com.tpg.par.UniqueIdGeneration;
import com.tpg.par.util.DateTimeFormatting;

import java.time.ZonedDateTime;

public interface NewPlanningApplicationFixture extends DateTimeFormatting, UniqueIdGeneration {
    default NewPlanningApplication buildPlanningApplication(String title, String firstName, String surname,
                                                            ContactDetails contactDetails,
                                                            ZonedDateTime dateOfBirth, ZonedDateTime dateReceived,
                                                            String summary) {
        return new NewPlanningApplication.Builder()
                .title(title)
                .name(firstName, surname)
                .dateOfBirth(dateTimeFormatterDDMMYYYY().format(dateOfBirth))
                .dateReceived(dateTimeFormatterDDMMYYYY().format(dateReceived))
                .contactDetails(contactDetails)
                .referenceNumber(newId())
                .summary(summary)
                .build();
    }
}
