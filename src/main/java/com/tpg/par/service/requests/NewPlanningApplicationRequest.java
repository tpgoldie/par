package com.tpg.par.service.requests;

import com.tpg.par.domain.ContactDetails;
import com.tpg.par.domain.Name;
import com.tpg.par.domain.Title;

import java.time.ZonedDateTime;

public class NewPlanningApplicationRequest {

    private final Title title;
    private final Name name;
    private final ContactDetails contactDetails;
    private final ZonedDateTime dateOfBirth;
    private final ZonedDateTime dateReceived;
    private final String summary;

    public NewPlanningApplicationRequest(Title title, Name name, ContactDetails contactDetails, ZonedDateTime dateOfBirth, ZonedDateTime dateReceived, String summary) {

        this.title = title;
        this.name = name;
        this.contactDetails = contactDetails;
        this.dateOfBirth = dateOfBirth;
        this.dateReceived = dateReceived;
        this.summary = summary;
    }

    public Title getTitle() {
        return title;
    }

    public Name getName() {
        return name;
    }

    public ContactDetails getContactDetails() {
        return contactDetails;
    }

    public ZonedDateTime getDateOfBirth() {
        return dateOfBirth;
    }

    public ZonedDateTime getDateReceived() {
        return dateReceived;
    }

    public String getSummary() {
        return summary;
    }

    public static class Builder {
        private Title title;
        private ZonedDateTime dateOfBirth;
        private ZonedDateTime dateReceived;
        private String summary;
        private ContactDetails contactDetails;
        private Name name;

        public NewPlanningApplicationRequest build() {
            return new NewPlanningApplicationRequest(title, name, contactDetails, dateOfBirth, dateReceived, summary);
        }

        public Builder title(Title title) {
            this.title = title;
            return this;
        }

        public Builder name(Name name) {
            this.name = name;
            return this;
        }

        public Builder dateOfBirth(ZonedDateTime dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
            return this;
        }

        public Builder dateReceived(ZonedDateTime dateReceived) {
            this.dateReceived = dateReceived;
            return this;
        }

        public Builder summary(String summary) {
            this.summary = summary;
            return this;
        }

        public Builder contactDetails(ContactDetails contactDetails) {
            this.contactDetails = contactDetails;
            return this;
        }
    }
}
