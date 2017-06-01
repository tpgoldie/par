package com.tpg.par.es.service;

import com.tpg.par.es.documents.PlanningApplicationDocument;
import com.tpg.par.es.repositories.PlanningApplicationsQueryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PlanningApplicationsQueryServiceImpl implements PlanningApplicationsQueryService {
    private static final Logger LOG = LoggerFactory.getLogger(PlanningApplicationsQueryServiceImpl.class);

    private PlanningApplicationsQueryRepository planningApplicationsQueryRepository;

    public PlanningApplicationsQueryServiceImpl(PlanningApplicationsQueryRepository planningApplicationsQueryRepository) {
        this.planningApplicationsQueryRepository = planningApplicationsQueryRepository;
    }

    @Override
    public Page<PlanningApplicationDocument> findByReferenceNumber(String refNo, Pageable pageable) {
        Page<PlanningApplicationDocument> outcome = planningApplicationsQueryRepository.findByReferenceNumber(refNo, pageable);
        LOG.debug(outcome.toString());
        return outcome;
    }

    @Override
    public Page<PlanningApplicationDocument> findByPostCode(String postCode, Pageable pageable) {
        return null;
    }
}
