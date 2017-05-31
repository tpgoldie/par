package com.tpg.par.service;

import com.tpg.par.domain.SearchType;
import com.tpg.par.domain.StatusType;
import org.springframework.data.domain.PageRequest;

public class SimpleSearchRequest {
    private final SearchType searchType;
    private final StatusType statusType;
    private final PageRequest pageRequest;
    private final String query;

    public SimpleSearchRequest(SearchType searchType, StatusType statusType, String query, PageRequest pageRequest) {

        this.searchType = searchType;
        this.statusType = statusType;
        this.pageRequest = pageRequest;
        this.query = query;
    }

    public SearchType getSearchType() {
        return searchType;
    }

    public StatusType getStatusType() {
        return statusType;
    }

    public PageRequest getPageRequest() {
        return pageRequest;
    }

    public String getQuery() {
        return query;
    }
}
