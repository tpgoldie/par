package com.tpg.par.web.controllers;

import com.tpg.par.domain.ApplicationType;
import com.tpg.par.domain.DecisionStatus;
import com.tpg.par.service.SimpleSearchRequest;
import com.tpg.par.web.request.SimpleSearchWebRequest;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.PageRequest;

public class SimpleSearchRequestConverter implements Converter<SimpleSearchWebRequest, SimpleSearchRequest> {
    private PageRequest pageRequest;

    public SimpleSearchRequestConverter(PageRequest pageRequest) {
        this.pageRequest = pageRequest;
    }

    @Override
    public SimpleSearchRequest convert(SimpleSearchWebRequest source) {
        SimpleSearchRequest target = new SimpleSearchRequest(
                ApplicationType.valueOf(source.getApplicationType()),
                DecisionStatus.valueOf(source.getDecisionStatus()),
                source.getSearchTerm(),
                pageRequest);

        return target;
    }
}
