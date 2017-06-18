package com.tpg.par.service;

import com.tpg.par.domain.NewPlanningApplication;
import com.tpg.par.domain.PlanningApplication;
import com.tpg.par.es.documents.PlanningApplicationDocument;
import com.tpg.par.es.service.PlanningApplicationDocumentConverter;
import com.tpg.par.es.service.PlanningApplicationsQueryService;
import com.tpg.par.service.requests.NewPlanningApplicationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.tpg.par.service.requests.SimpleSearchRequestBuilder.simpleSearchBy;
import static java.util.stream.Collectors.toList;

@Service
public class PlanningApplicationsServiceImpl implements PlanningApplicationsService {
    private PlanningApplicationsQueryService planningApplicationsQueryService;

    private final PlanningApplicationDocumentConverter planningApplicationDocumentConverter;

    @Autowired
    public PlanningApplicationsServiceImpl(PlanningApplicationsQueryService planningApplicationsQueryService) {
        this.planningApplicationsQueryService = planningApplicationsQueryService;
        planningApplicationDocumentConverter = new PlanningApplicationDocumentConverter();
    }

    public List<PlanningApplication> simpleSearch(SimpleSearchRequest request) {
        Page<PlanningApplicationDocument> results = simpleSearchBy(planningApplicationsQueryService).apply(request);

        return results.getContent().stream()
            .map(planningApplicationDocumentConverter::convert)
            .collect(toList());
    }

    @Override
    public Success<NewPlanningApplication> savePlanningApplication(NewPlanningApplicationRequest newPlanningApplicationRequest) {
        return null;
    }
}
