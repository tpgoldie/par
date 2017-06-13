package com.tpg.par.domain;

import com.tpg.par.domain.builders.PlanningApplicationBuilder;

import java.time.ZonedDateTime;

public interface PlanningApplicationFixture {
    default PlanningApplication buildSearchResult(String referenceNumber, String summary,
                                                  ZonedDateTime dateReceived, ZonedDateTime dateValidated,
                                                  Address address, DecisionStatus decisionStatus) {

        PlanningApplicationBuilder planningApplicationBuilder = new PlanningApplicationBuilder();

        return planningApplicationBuilder
                .referenceNumber(referenceNumber)
                .summary(summary)
                .address(address)
                .dateReceived(dateReceived)
                .dateValidated(dateValidated)
                .decisionStatus(decisionStatus)
                .build();
    }
}
