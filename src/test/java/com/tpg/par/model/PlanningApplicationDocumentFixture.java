package com.tpg.par.model;

import com.tpg.par.UniqueIdGeneration;
import com.tpg.par.es.documents.PlanningApplicationDocument;

public interface PlanningApplicationDocumentFixture extends UniqueIdGeneration {
    default PlanningApplicationDocument buildPlanningApplicationDocument(String referenceNumber, String addressLineOne, String postCode) {
        PlanningApplicationDocument document = new PlanningApplicationDocument();

        document.setId(newId());
        document.setReferenceNumber(referenceNumber);
        document.setAddressLineOne(addressLineOne);
        document.setPostCode(postCode);

        return document;
    }
}
