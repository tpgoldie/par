package com.tpg.par.domain;

import com.tpg.par.util.DateTimeFormatting;

import java.time.ZonedDateTime;

public class PlanningApplication implements DateTimeFormatting {
    private final String referenceNumber;
    private final String summary;
    private final Address address;
    private final ZonedDateTime dateReceived;
    private final ZonedDateTime dateValidated;
    private final DecisionStatus decisionStatus;

    public PlanningApplication(String referenceNumber, String summary, Address address, ZonedDateTime dateReceived,
                               ZonedDateTime dateValidated, DecisionStatus decisionStatus) {
        this.referenceNumber = referenceNumber;
        this.summary = summary;
        this.address = address;
        this.dateReceived = dateReceived;
        this.dateValidated = dateValidated;
        this.decisionStatus = decisionStatus;
    }

    public String getReferenceNumber() {
        return referenceNumber;
    }

    public String getSummary() {
        return summary;
    }

    public Address getAddress() {
        return address;
    }

    public ZonedDateTime getDateReceived() {
        return dateReceived;
    }

    public String getFormattedDateReceived() {
        return EEEDDMMYYYYFormat(dateReceived);
    }

    public ZonedDateTime getDateValidated() {
        return dateValidated;
    }

    public String getFormattedDateValidated() {
        return EEEDDMMYYYYFormat(dateValidated);
    }

    public DecisionStatus getDecisionStatus() {
        return decisionStatus;
    }
}
