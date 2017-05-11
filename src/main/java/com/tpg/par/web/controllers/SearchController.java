package com.tpg.par.web.controllers;

import com.tpg.par.domain.DecisionStatus;
import com.tpg.par.domain.SearchFor;
import com.tpg.par.domain.SearchRequest;
import com.tpg.par.domain.SearchResult;
import com.tpg.par.service.ApplicationQueryService;
import com.tpg.par.web.request.SimpleSearchRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.*;

@Controller
@RequestMapping("/par")
public class SearchController {
    private ApplicationQueryService applicationQueryService;

    @Autowired
    public SearchController(ApplicationQueryService applicationQueryService) {
        this.applicationQueryService = applicationQueryService;
    }

    @GetMapping(value = "/", produces = TEXT_HTML_VALUE)
    public String handleIndexViewRequest() {
        return "index";
    }

    @PostMapping(value = "/search", consumes = APPLICATION_FORM_URLENCODED_VALUE, produces = APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody
    ResponseEntity<List<SearchResult>> handleSearchRequest(SimpleSearchRequest simpleSearchRequest) {
        SearchRequest searchRequest = new SearchRequest(SearchFor.valueOf(simpleSearchRequest.getSearchFor()),
                simpleSearchRequest.getSearchTerm(),
                DecisionStatus.valueOf(simpleSearchRequest.getDecisionStatus()));

        List<SearchResult> searchResults = applicationQueryService.findApplications(searchRequest);

        return new ResponseEntity<List<SearchResult>>(searchResults, OK);
    }
}
