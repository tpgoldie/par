package com.tpg.par.es.repositories;

import com.tpg.par.es.documents.PlanningApplicationDocument;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface PlanningApplicationsQueryRepository extends ElasticsearchRepository<PlanningApplicationDocument, String> {

    Page<PlanningApplicationDocument> findByReferenceNumber(String refNo, Pageable pageable);

    Page<PlanningApplicationDocument> findByPostCode(String postCode, Pageable pageable);

    Page<PlanningApplicationDocument> findByAddressLineOne(String lineOne, Pageable pageable);
}
