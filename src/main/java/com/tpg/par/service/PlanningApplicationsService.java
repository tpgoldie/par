package com.tpg.par.service;

import com.tpg.par.domain.NewPlanningApplication;
import com.tpg.par.domain.PlanningApplication;
import com.tpg.par.service.requests.NewPlanningApplicationRequest;

import java.util.List;
import java.util.Optional;

public interface PlanningApplicationsService {
    List<PlanningApplication> simpleSearch(SimpleSearchRequest request);

    Success<NewPlanningApplication> savePlanningApplication(NewPlanningApplicationRequest newPlanningApplicationRequest);
}
