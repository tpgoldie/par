package com.tpg.par.web.request;

public class SimpleSearchWebRequest {
    private int pageNumber = 0;
    private int offset = 0;
    private int limit = 10;

    private String applicationType;
    private String decisionStatus;
    private String searchTerm;

    public String getApplicationType() {
        return applicationType;
    }

    public String getDecisionStatus() {
        return decisionStatus;
    }

    public String getSearchTerm() {
        return searchTerm;
    }

    public void setApplicationType(String applicationType) {
        this.applicationType = applicationType;
    }

    public void setDecisionStatus(String decisionStatus) {
        this.decisionStatus = decisionStatus;
    }

    public void setSearchTerm(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }
}
