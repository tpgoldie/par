package com.tpg.par.service;

import com.tpg.par.domain.PlanningApplication;

import java.util.List;

public interface PlanningApplicationsService {
    List<PlanningApplication> simpleSearch(SimpleSearchRequest request);
}
