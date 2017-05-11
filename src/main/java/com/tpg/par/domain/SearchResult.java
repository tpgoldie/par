package com.tpg.par.domain;

import com.tpg.par.util.DateTimeFormatting;

import java.time.ZonedDateTime;

public class SearchResult implements DateTimeFormatting {
    private String summary;
    private String referenceNumber;
    private Address address;
    private final ZonedDateTime dateReceived;
    private final ZonedDateTime dateValidated;
    private final DecisionStatus decisionStatus;

    public SearchResult(String referenceNumber, String summary, Address address, ZonedDateTime dateReceived, ZonedDateTime dateValidated, DecisionStatus decisionStatus) {

        this.referenceNumber = referenceNumber;
        this.summary = summary;
        this.address = address;
        this.dateReceived = dateReceived;
        this.dateValidated = dateValidated;
        this.decisionStatus = decisionStatus;
    }

    public String getSummary() {
        return summary;
    }

    public String getReferenceNumber() {
        return referenceNumber;
    }

    public Address getAddress() {
        return address;
    }

    public ZonedDateTime getDateReceived() {
        return dateReceived;
    }

    public String getFormattedDateReceived() {
        return dateReceived.format(dateTimeFormatter());
    }

    public ZonedDateTime getDateValidated() {
        return dateValidated;
    }

    public String getFormattedDateValidated() {
        return dateValidated.format(dateTimeFormatter());
    }

    public DecisionStatus getDecisionStatus() {
        return decisionStatus;
    }
}
