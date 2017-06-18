package com.tpg.par.es.service;

import com.tpg.par.domain.Address;
import com.tpg.par.domain.PlanningApplication;
import com.tpg.par.es.documents.PlanningApplicationDocument;
import org.springframework.core.convert.converter.Converter;

import static java.util.Optional.empty;

public class PlanningApplicationDocumentConverter implements Converter<PlanningApplicationDocument, PlanningApplication> {
    @Override
    public PlanningApplication convert(PlanningApplicationDocument source) {
        Address address = new Address.Builder()
            .lineOne(source.getLineOneOfAddress())
            .postCode(source.getPostCode())
            .build();

        return new PlanningApplication(source.getReferenceNumber(), source.getSummary(), address,
                source.getDateReceived(), source.getDateValidated(), source.getDecisionStatus());
    }
}
