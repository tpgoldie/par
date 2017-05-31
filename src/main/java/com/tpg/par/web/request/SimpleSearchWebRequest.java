package com.tpg.par.web.request;

public class SimpleSearchWebRequest {
    private String searchFor;
    private String decisionStatus;
    private String searchTerm;

    public SimpleSearchWebRequest() {}

    public SimpleSearchWebRequest(String searchFor, String searchTerm, String decisionStatus) {
        this.setSearchFor(searchFor);
        this.setSearchTerm(searchTerm);
        this.setDecisionStatus(decisionStatus);
    }

    public String getSearchFor() {
        return searchFor;
    }

    public String getDecisionStatus() {
        return decisionStatus;
    }

    public String getSearchTerm() {
        return searchTerm;
    }

    public void setSearchFor(String searchFor) {
        this.searchFor = searchFor;
    }

    public void setDecisionStatus(String decisionStatus) {
        this.decisionStatus = decisionStatus;
    }

    public void setSearchTerm(String searchTerm) {
        this.searchTerm = searchTerm;
    }
}
