package com.tpg.par.service.requests;

import com.tpg.par.es.documents.PlanningApplicationDocument;
import com.tpg.par.es.service.PlanningApplicationsQueryService;
import com.tpg.par.service.SimpleSearchRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.Optional;

import static java.util.Collections.emptyList;
import static java.util.Optional.empty;

public class SimpleSearchByPostCode extends SimpleSearchBy {
    public SimpleSearchByPostCode(PlanningApplicationsQueryService planningApplicationsQueryService) {
        this(planningApplicationsQueryService, empty());
    }

    public SimpleSearchByPostCode(PlanningApplicationsQueryService planningApplicationsQueryService,
                                  Optional<SimpleSearchBy> composite) {
        super(planningApplicationsQueryService, composite);
    }

    @Override
    public Page<PlanningApplicationDocument> apply(SimpleSearchRequest simpleSearchRequest) {
        Page<PlanningApplicationDocument> found = planningApplicationsQueryService.findByPostCode(simpleSearchRequest.getQuery(),
                simpleSearchRequest.getPageRequest());

        if (hasValues(found)) { return found; }

        if (!composite.isPresent()) { return found; }

        Optional<Page<PlanningApplicationDocument>> result = composite.map(f -> f.apply(simpleSearchRequest));
        return result.orElse(new PageImpl<>(emptyList(), simpleSearchRequest.getPageRequest(), 0));
    }
}
