package com.tpg.par.es.service;

import com.tpg.par.domain.PlanningApplication;
import com.tpg.par.es.documents.PlanningApplicationDocument;
import org.springframework.core.convert.converter.Converter;

public class PlanningApplicationDocumentConverter implements Converter<PlanningApplicationDocument, PlanningApplication> {
    @Override
    public PlanningApplication convert(PlanningApplicationDocument source) {
        return new PlanningApplication(source.getReferenceNumber());
    }
}
