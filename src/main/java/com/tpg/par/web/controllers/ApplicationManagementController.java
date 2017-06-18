package com.tpg.par.web.controllers;

import com.tpg.par.domain.NewPlanningApplication;
import com.tpg.par.service.Outcome;
import com.tpg.par.service.PlanningApplicationsService;
import com.tpg.par.service.Success;
import com.tpg.par.service.requests.NewPlanningApplicationRequest;
import com.tpg.par.web.request.NewPlanningApplicationWebRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Locale;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED_VALUE;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@Controller
@RequestMapping("par")
public class ApplicationManagementController {
    private PlanningApplicationsService planningApplicationsService;

    @Autowired
    public ApplicationManagementController(PlanningApplicationsService planningApplicationsService) {
        this.planningApplicationsService = planningApplicationsService;
    }

    @PostMapping(value = "/planningApplications", consumes = APPLICATION_FORM_URLENCODED_VALUE, produces = APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody
    ResponseEntity<NewPlanningApplication> handleNewPlanningApplication(Locale locale, NewPlanningApplicationWebRequest webRequest) {
        NewPlanningApplicationRequestConverter requestConverter = new NewPlanningApplicationRequestConverter();
        NewPlanningApplicationRequest request = requestConverter.convert(webRequest);

        Outcome<NewPlanningApplication> newPlanningApplication = planningApplicationsService.savePlanningApplication(request);

        return newPlanningApplication.getValue().map(nap -> new ResponseEntity<>(nap, OK)).orElse(null);
    }
}
