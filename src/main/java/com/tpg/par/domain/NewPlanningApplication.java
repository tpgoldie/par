package com.tpg.par.domain;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.tpg.par.util.DateTimeFormatting;

import java.io.Serializable;
import java.time.ZonedDateTime;

@JsonRootName("newApplication")
public class NewPlanningApplication implements DateTimeFormatting, Serializable {
    private Title title;
    private Name name;
    private ContactDetails contactDetails;
    private ZonedDateTime dateOfBirth;
    private ZonedDateTime dateReceived;
    private String referenceNumber;
    private String summary;

    NewPlanningApplication(Title title, Name name, ContactDetails contactDetails, ZonedDateTime dateOfBirth,
                           ZonedDateTime dateReceived, String referenceNumber, String summary) {
        this.title = title;
        this.name = name;
        this.contactDetails = contactDetails;
        this.dateOfBirth = dateOfBirth;
        this.dateReceived = dateReceived;
        this.referenceNumber = referenceNumber;
        this.summary = summary;
    }

    public Title getTitle() {
        return title;
    }

    public Name getName() {
        return name;
    }

    public ZonedDateTime getDateOfBirth() {
        return dateOfBirth;
    }

    public String getFormattedDateOfBirth() {
        return EEEDDMMYYYYFormat(dateOfBirth);
    }

    public ZonedDateTime getDateReceived() {
        return dateReceived;
    }

    public String getFormattedDateReceived() {
        return EEEDDMMYYYYFormat(dateReceived);
    }

    public String getSummary() {
        return summary;
    }

    public String getReferenceNumber() {
        return referenceNumber;
    }

    public ContactDetails getContactDetails() {
        return contactDetails;
    }

    public static class Builder implements DateTimeFormatting {
        private Title title;
        private Name name;
        private ContactDetails contactDetails;
        private ZonedDateTime dateOfBirth;
        private ZonedDateTime dateReceived;
        private String referenceNumber;
        private String summary;

        public Builder title(String value) {
            title = Title.valueOf(value);

            return this;
        }

        public Builder name(String firstName, String surname) {
            name = new Name.Builder().firstName(firstName).surname(surname).build();

            return this;
        }

        public Builder dateOfBirth(String value) {
            dateOfBirth = parse(value);
            return this;
        }

        public Builder dateReceived(String value) {
            dateReceived = parse(value);
            return this;
        }

        public Builder contactDetails(ContactDetails value) {
            contactDetails = value;
            return this;
        }

        public Builder referenceNumber(String value) {
            referenceNumber = value;
            return this;
        }

        public Builder summary(String value) {
            summary = value;
            return this;
        }

        public NewPlanningApplication build() {
            return new NewPlanningApplication(title, name, contactDetails, dateOfBirth, dateReceived, referenceNumber, summary);
        }
    }
}
