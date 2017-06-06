package com.tpg.par.service.requests;

import com.tpg.par.es.service.PlanningApplicationsQueryService;

import java.util.Optional;

public class SimpleSearchRequestBuilder {

    public static SimpleSearchBy simpleSearchBy(PlanningApplicationsQueryService planningApplicationsQueryService) {
        return new SimpleSearchRequestBuilder(planningApplicationsQueryService).build();
    }

    private PlanningApplicationsQueryService planningApplicationsQueryService;

    public SimpleSearchRequestBuilder(PlanningApplicationsQueryService planningApplicationsQueryService) {
        this.planningApplicationsQueryService = planningApplicationsQueryService;
    }

    SimpleSearchBy build() {
        SimpleSearchByReferenceNumber simpleSearchByReferenceNumber = new SimpleSearchByReferenceNumber(planningApplicationsQueryService);
        SimpleSearchByPostCode simpleSearchByPostCode = new SimpleSearchByPostCode(planningApplicationsQueryService, Optional.of(simpleSearchByReferenceNumber));
        SimpleSearchByLineOneOfAddress simpleSearchByLineOneOfAddress = new SimpleSearchByLineOneOfAddress(planningApplicationsQueryService, Optional.of(simpleSearchByPostCode));

        return simpleSearchByLineOneOfAddress;
    }
}
