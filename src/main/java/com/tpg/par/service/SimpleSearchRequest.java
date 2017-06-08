package com.tpg.par.service;

import com.tpg.par.domain.ApplicationType;
import com.tpg.par.domain.DecisionStatus;
import org.springframework.data.domain.PageRequest;

public class SimpleSearchRequest {
    private final ApplicationType applicationType;
    private final DecisionStatus decisionStatus;
    private final PageRequest pageRequest;
    private final String query;

    public SimpleSearchRequest(ApplicationType applicationType, DecisionStatus decisionStatus, String query, PageRequest pageRequest) {

        this.applicationType = applicationType;
        this.decisionStatus = decisionStatus;
        this.pageRequest = pageRequest;
        this.query = query;
    }

    public ApplicationType getApplicationType() {
        return applicationType;
    }

    public DecisionStatus getDecisionStatus() {
        return decisionStatus;
    }

    public PageRequest getPageRequest() {
        return pageRequest;
    }

    public String getQuery() {
        return query;
    }
}
