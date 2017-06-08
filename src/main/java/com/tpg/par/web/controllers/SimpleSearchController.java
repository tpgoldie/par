package com.tpg.par.web.controllers;

import com.tpg.par.domain.PlanningApplication;
import com.tpg.par.domain.SearchResults;
import com.tpg.par.service.PlanningApplicationsService;
import com.tpg.par.service.SimpleSearchRequest;
import com.tpg.par.web.components.ApplicationTypeCheckBox;
import com.tpg.par.web.components.ApplicationTypeCheckBoxes;
import com.tpg.par.web.components.DecisionStatusSelectOption;
import com.tpg.par.web.components.StatusTypeSelectOptions;
import com.tpg.par.web.mav.IndexModel;
import com.tpg.par.web.request.SimpleSearchWebRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.PageRequest;
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
public class SimpleSearchController {
    private static final List<ApplicationTypeCheckBox> SEARCH_TYPE_CHECK_BOXES = new ApplicationTypeCheckBoxes().getValues();

    private static final List<DecisionStatusSelectOption> STATUS_TYPE_SELECT_OPTIONS = new StatusTypeSelectOptions().getValues();

    private MessageSource messageSource;
    private PlanningApplicationsService planningApplicationsService;

    @Autowired
    public SimpleSearchController(MessageSource messageSource, PlanningApplicationsService planningApplicationsService) {
        this.messageSource = messageSource;
        this.planningApplicationsService = planningApplicationsService;
    }

    @ModelAttribute("applicationTypes")
    public List<ApplicationTypeCheckBox> searchTypes() {
        return SEARCH_TYPE_CHECK_BOXES;
    }

    @ModelAttribute("decisionStatusTypes")
    public List<DecisionStatusSelectOption> statusTypes() { return STATUS_TYPE_SELECT_OPTIONS; }


    @GetMapping(value = "/", produces = TEXT_HTML_VALUE)
    public String handleIndexViewRequest(Locale locale, Model model) {
        new IndexModel(messageSource).addAttributes(locale, model);

        return "index";
    }

    @PostMapping(value = "/search", consumes = APPLICATION_FORM_URLENCODED_VALUE, produces = APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody
    ResponseEntity<SearchResults> handleSearchRequest(SimpleSearchWebRequest simpleSearchWebRequest) {
        PageRequest pageRequest = new PageRequest(simpleSearchWebRequest.getPageNumber(), simpleSearchWebRequest.getLimit());

        SimpleSearchRequest simpleSearchRequest = new SimpleSearchRequestConverter(pageRequest).convert(simpleSearchWebRequest);

        List<PlanningApplication> results = planningApplicationsService.simpleSearch(simpleSearchRequest);

        return new ResponseEntity<>(new SearchResults(pageRequest.getPageNumber(), pageRequest.getOffset(), pageRequest.getPageSize(), results), OK);
    }
}
