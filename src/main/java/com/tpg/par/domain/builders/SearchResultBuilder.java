package com.tpg.par.domain.builders;

import com.tpg.par.domain.Address;
import com.tpg.par.domain.DecisionStatus;
import com.tpg.par.domain.SearchResult;

import java.time.ZonedDateTime;

public class SearchResultBuilder {
    private String referenceNumber;
    private String summary;
    private Address address;
    private ZonedDateTime dateReceived;
    private ZonedDateTime dateValidated;
    private DecisionStatus decisionStatus;

    public SearchResultBuilder summary(String value) {
        summary = value;
        return this;
    }

    public SearchResultBuilder referenceNumber(String value) {
        referenceNumber = value;
        return this;
    }

    public SearchResultBuilder address(Address value) {
        address = value;
        return this;
    }

    public SearchResultBuilder dateReceived(ZonedDateTime value) {
        dateReceived = value;
        return this;
    }

    public SearchResultBuilder dateValidated(ZonedDateTime value) {
        dateValidated = value;
        return this;
    }

    public SearchResultBuilder decisionStatus(DecisionStatus value) {
        decisionStatus = value;
        return this;
    }

    public SearchResult build() {
        return new SearchResult(referenceNumber, summary, address,
                dateReceived, dateValidated, decisionStatus);
    }
}
