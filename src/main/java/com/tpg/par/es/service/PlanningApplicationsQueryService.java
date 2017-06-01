package com.tpg.par.es.service;

import com.tpg.par.es.documents.PlanningApplicationDocument;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PlanningApplicationsQueryService {

    Page<PlanningApplicationDocument> findByReferenceNumber(String refNo, Pageable pageable);

    Page<PlanningApplicationDocument> findByPostCode(String postCode, Pageable pageable);
}
