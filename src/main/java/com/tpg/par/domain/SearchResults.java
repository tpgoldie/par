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
    private  List<SearchResult> values;

    public SearchResults(List<SearchResult> values) {
        setValues(values);
    }

    public void setValues(List<SearchResult> values) {
        this.values = unmodifiableList(new ArrayList<>(values));
    }

    public int size() {
        return values.size();
    }

    public Stream<SearchResult> stream() {
        return values.stream();
    }
}
