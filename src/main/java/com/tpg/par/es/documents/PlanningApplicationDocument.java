package com.tpg.par.es.documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "planning-apps", type = "planningApplications")
public class PlanningApplicationDocument {
    @Id
    private String id;

    private String referenceNumber;

    private String postCode;

    public PlanningApplicationDocument() {}

    public PlanningApplicationDocument(String id, String referenceNumber, String postCode) {
        this.id = id;
        this.referenceNumber = referenceNumber;
        this.postCode = postCode;
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

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    @Override
    public String toString() {
        return referenceNumber;
    }
}
