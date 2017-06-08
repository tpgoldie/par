package com.tpg.par.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static java.util.Collections.unmodifiableList;

@JsonRootName("searchResults")
public class SearchResults {
    @JsonProperty
    private final int pageNumber;

    @JsonProperty
    private final int offset;

    @JsonProperty
    private final int limit;

    @JsonProperty
    private List<PlanningApplication> values;

    public SearchResults(int pageNumber, int offset, int limit, List<PlanningApplication> values) {
        this.pageNumber = pageNumber;
        this.offset = offset;
        this.limit = limit;
        setValues(values);
    }

    public void setValues(List<PlanningApplication> values) {
        this.values = unmodifiableList(new ArrayList<>(values));
    }

    public int size() {
        return values.size();
    }

    public Stream<PlanningApplication> stream() {
        return values.stream();
    }

    public int getOffset() {
        return offset;
    }

    public int getLimit() {
        return limit;
    }

    public int getPageNumber() {
        return pageNumber;
    }
}
