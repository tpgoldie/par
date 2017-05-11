package com.tpg.par.service;

import com.tpg.par.domain.SearchRequest;
import com.tpg.par.domain.SearchResult;

import java.util.List;

public interface ApplicationQueryService {
    List<SearchResult> findApplications(SearchRequest searchRequest);
}
