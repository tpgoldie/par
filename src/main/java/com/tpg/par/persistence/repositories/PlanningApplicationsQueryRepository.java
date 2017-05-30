package com.tpg.par.persistence.repositories;

import com.tpg.par.persistence.entities.PlanningApplicationEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlanningApplicationsQueryRepository extends QueryRepository<PlanningApplicationEntity> {

    Optional<PlanningApplicationEntity> findByReferenceNumber(String referenceNumber);
}
