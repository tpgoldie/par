package com.tpg.par.service.requests;

import com.tpg.par.es.documents.PlanningApplicationDocument;
import com.tpg.par.es.service.PlanningApplicationsQueryService;
import org.springframework.data.domain.Page;

import java.util.Optional;

import static java.util.Optional.empty;

public abstract class SimpleSearchBy implements SimpleSearch {

    protected PlanningApplicationsQueryService planningApplicationsQueryService;
    Optional<SimpleSearchBy> composite = empty();

    SimpleSearchBy(PlanningApplicationsQueryService planningApplicationsQueryService) {
        this(planningApplicationsQueryService, empty());
    }

    SimpleSearchBy(PlanningApplicationsQueryService planningApplicationsQueryService,
                             Optional<SimpleSearchBy> composite) {
        this.planningApplicationsQueryService = planningApplicationsQueryService;
        this.composite = composite;
    }

    boolean hasValues(Page<PlanningApplicationDocument> found) {
        return (found != null) && (found.getContent() != null) && !found.getContent().isEmpty();
    }
}
