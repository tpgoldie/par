package com.tpg.par.web.components;

import com.tpg.par.domain.SearchType;

import java.util.List;
import java.util.stream.Stream;

import static java.util.Collections.unmodifiableList;
import static java.util.stream.Collectors.toList;

public class SearchTypeCheckBoxes {
    private final List<SearchTypeCheckBox> checkBoxes = unmodifiableList(Stream.of(SearchType.values())
        .map(SearchTypeCheckBox::new).collect(toList()));

    public List<SearchTypeCheckBox> getValues() {
        return checkBoxes;
    }
}
