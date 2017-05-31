package com.tpg.par.service;

import com.tpg.par.domain.PlanningApplication;
import com.tpg.par.es.documents.PlanningApplicationDocument;
import com.tpg.par.es.service.PlanningApplicationDocumentConverter;
import com.tpg.par.es.service.PlanningApplicationsQueryService;
import org.springframework.data.domain.Page;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class PlanningApplicationsServiceImpl implements PlanningApplicationsService {
    private PlanningApplicationsQueryService planningApplicationsQueryService;

    private final PlanningApplicationDocumentConverter planningApplicationDocumentConverter;

    public PlanningApplicationsServiceImpl(PlanningApplicationsQueryService planningApplicationsQueryService) {
        this.planningApplicationsQueryService = planningApplicationsQueryService;
        planningApplicationDocumentConverter = new PlanningApplicationDocumentConverter();
    }

    public List<PlanningApplication> simpleSearch(SimpleSearchRequest request) {
        Page<PlanningApplicationDocument> results = planningApplicationsQueryService.findByReferenceNumber(request.getQuery().trim(), request.getPageRequest());

        return results.getContent()
                .stream()
                    .map(pad -> planningApplicationDocumentConverter.convert(pad))
                    .collect(toList());
    }
}
