package com.tpg.par.persistence.repositories;

import com.tpg.par.persistence.entities.PlanningApplicationEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanningApplicationsManagementRepository extends ManagementRepository<PlanningApplicationEntity> {

    void save(PlanningApplicationEntity entity);
}
