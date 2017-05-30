package com.tpg.par.web.controllers;

import com.tpg.par.domain.*;
import com.tpg.par.service.ApplicationQueryService;
import com.tpg.par.web.components.SearchTypeCheckBox;
import com.tpg.par.web.components.SearchTypeCheckBoxes;
import com.tpg.par.web.components.StatusTypeSelectOption;
import com.tpg.par.web.components.StatusTypeSelectOptions;
import com.tpg.par.web.request.SimpleSearchRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.*;

@Controller
@RequestMapping("par")
public class SearchController {
    private static final List<SearchTypeCheckBox> SEARCH_TYPE_CHECK_BOXES = new SearchTypeCheckBoxes().getValues();

    private static final List<StatusTypeSelectOption> STATUS_TYPE_SELECT_OPTIONS = new StatusTypeSelectOptions().getValues();

    private MessageSource messageSource;
    private ApplicationQueryService applicationQueryService;

    @Autowired
    public SearchController(MessageSource messageSource, ApplicationQueryService applicationQueryService) {
        this.messageSource = messageSource;
        this.applicationQueryService = applicationQueryService;
    }

    @ModelAttribute("searchTypes")
    public List<SearchTypeCheckBox> searchTypes() {
        return SEARCH_TYPE_CHECK_BOXES;
    }

    @ModelAttribute("statusTypes")
    public List<StatusTypeSelectOption> statusTypes() { return STATUS_TYPE_SELECT_OPTIONS; }


    @GetMapping(value = "/", produces = TEXT_HTML_VALUE)
    public String handleIndexViewRequest(Locale locale, Model model) {
        String title = messageSource.getMessage("index.title", new String[0], locale);
        String h1 = messageSource.getMessage("index.h1", new String[0], locale);
        String h2 = messageSource.getMessage("index.h2", new String[0], locale);
        String searchSummary = messageSource.getMessage("index.searchSummary", new String[0], locale);
        String searchButtonText = messageSource.getMessage("search.button.text", new String[0], locale);
        String footerInfo = messageSource.getMessage("footer.info", new String[0], locale);

        model.addAttribute("title", title);
        model.addAttribute("welcome", h1);
        model.addAttribute("simpleSearchSubTitle", h2);
        model.addAttribute("searchSummary", searchSummary);
        model.addAttribute("searchButtonText", searchButtonText);
        model.addAttribute("footerInfo", footerInfo);

        return "index";
    }

    @PostMapping(value = "/search", consumes = APPLICATION_FORM_URLENCODED_VALUE, produces = APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody
    ResponseEntity<SearchResults> handleSearchRequest(SimpleSearchRequest simpleSearchRequest) {
        SearchRequest searchRequest = new SearchRequest(SearchFor.valueOf(simpleSearchRequest.getSearchFor()),
                simpleSearchRequest.getSearchTerm(),
                DecisionStatus.valueOf(simpleSearchRequest.getDecisionStatus()));

        List<SearchResult> searchResults = applicationQueryService.findApplications(searchRequest);

        return new ResponseEntity<>(new SearchResults(searchResults), OK);
    }
}
