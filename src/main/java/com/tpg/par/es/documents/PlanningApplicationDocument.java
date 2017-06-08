package com.tpg.par.es.documents;

import com.tpg.par.domain.DecisionStatus;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.time.ZonedDateTime;

@Document(indexName = "planning-apps", type = "planningApplications")
public class PlanningApplicationDocument {
    @Id
    private String id;

    private String referenceNumber;

    private String summary;

    private String addressLineOne;

    private String postCode;

    private ZonedDateTime dateReceived;

    private ZonedDateTime dateValidated;

    private DecisionStatus decisionStatus;

    public PlanningApplicationDocument() {}

    public PlanningApplicationDocument(String id, String referenceNumber, String addressLineOne, String postCode,
                                       ZonedDateTime dateReceived, ZonedDateTime dateValidated, DecisionStatus decisionStatus) {
        this.id = id;

        this.referenceNumber = referenceNumber;

        this.addressLineOne = addressLineOne;

        this.postCode = postCode;

        this.dateReceived = dateReceived;

        this.dateValidated = dateValidated;

        this.decisionStatus = decisionStatus;
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

    public String getLineOneOfAddress() {
        return addressLineOne;
    }

    public void setAddressLineOne(String addressLineOne) {
        this.addressLineOne = addressLineOne;
    }

    public ZonedDateTime getDateReceived() {
        return dateReceived;
    }

    public void setDateReceived(ZonedDateTime dateReceived) {
        this.dateReceived = dateReceived;
    }

    public ZonedDateTime getDateValidated() {
        return dateValidated;
    }

    public void setDateValidated(ZonedDateTime dateValidated) {
        this.dateValidated = dateValidated;
    }

    public DecisionStatus getDecisionStatus() {
        return decisionStatus;
    }

    public void setDecisionStatus(DecisionStatus decisionStatus) {
        this.decisionStatus = decisionStatus;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
}
