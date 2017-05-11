package com.tpg.par.domain;

public class SearchRequest {
    private SearchFor searchFor;
    private DecisionStatus decisionStatus;
    private String searchTerm;

    public SearchRequest(SearchFor searchFor, String searchTerm, DecisionStatus decisionStatus) {
        this.searchFor = searchFor;
        this.searchTerm = searchTerm;
        this.decisionStatus = decisionStatus;
    }

    public SearchFor getSearchFor() {
        return searchFor;
    }

    public DecisionStatus getDecisionStatus() {
        return decisionStatus;
    }

    public String getSearchTerm() {
        return searchTerm;
    }
}
