package com.tpg.par.es.service;

import com.tpg.par.es.documents.PlanningApplicationDocument;
import com.tpg.par.es.repositories.PlanningApplicationsQueryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PlanningApplicationsQueryServiceImpl implements PlanningApplicationsQueryService {
    private static final Logger LOG = LoggerFactory.getLogger(PlanningApplicationsQueryServiceImpl.class);

    private PlanningApplicationsQueryRepository planningApplicationsQueryRepository;

    @Autowired
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
        Page<PlanningApplicationDocument> outcome = planningApplicationsQueryRepository.findByPostCode(postCode, pageable);
        LOG.debug(outcome.toString());
        return outcome;
    }

    @Override
    public Page<PlanningApplicationDocument> findByLineOneOfAddress(String lineOneOfAddress, Pageable pageable) {
        Page<PlanningApplicationDocument> outcome = planningApplicationsQueryRepository.findByAddressLineOne(lineOneOfAddress, pageable);
        LOG.debug(outcome.toString());
        return outcome;
    }
}
