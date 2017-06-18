package com.tpg.par.web.controllers;

import com.tpg.par.service.requests.NewPlanningApplicationRequest;
import com.tpg.par.web.request.NewPlanningApplicationWebRequest;
import org.springframework.core.convert.converter.Converter;

public class NewPlanningApplicationRequestConverter implements Converter<NewPlanningApplicationWebRequest, NewPlanningApplicationRequest> {
    @Override
    public NewPlanningApplicationRequest convert(NewPlanningApplicationWebRequest source) {
        NewPlanningApplicationRequest target = new NewPlanningApplicationRequest.Builder()
                .build();

        return target;
    }
}
