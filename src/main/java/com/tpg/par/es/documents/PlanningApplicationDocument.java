package com.tpg.par.es.documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "planning-apps", type = "planningApplications")
public class PlanningApplicationDocument {
    @Id
    private String id;

    private String referenceNumber;

    public PlanningApplicationDocument() {}

    public PlanningApplicationDocument(String id, String referenceNumber) {
        this.id = id;
        this.referenceNumber = referenceNumber;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReferenceNumber() {
        return referenceNumber;
    }

    public void setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
    }

    @Override
    public String toString() {
        return referenceNumber;
    }
}
