package com.tpg.par.domain.builders;

import com.tpg.par.domain.Address;
import com.tpg.par.domain.DecisionStatus;
import com.tpg.par.domain.PlanningApplication;

import java.time.ZonedDateTime;

public class PlanningApplicationBuilder {
    private String referenceNumber;
    private String summary;
    private Address address;
    private ZonedDateTime dateReceived;
    private ZonedDateTime dateValidated;
    private DecisionStatus decisionStatus;

    public PlanningApplicationBuilder summary(String value) {
        summary = value;
        return this;
    }

    public PlanningApplicationBuilder referenceNumber(String value) {
        referenceNumber = value;
        return this;
    }

    public PlanningApplicationBuilder address(Address value) {
        address = value;
        return this;
    }

    public PlanningApplicationBuilder dateReceived(ZonedDateTime value) {
        dateReceived = value;
        return this;
    }

    public PlanningApplicationBuilder dateValidated(ZonedDateTime value) {
        dateValidated = value;
        return this;
    }

    public PlanningApplicationBuilder decisionStatus(DecisionStatus value) {
        decisionStatus = value;
        return this;
    }

    public PlanningApplication build() {
        return new PlanningApplication(referenceNumber, summary, address,
                dateReceived, dateValidated, decisionStatus);
    }
}
