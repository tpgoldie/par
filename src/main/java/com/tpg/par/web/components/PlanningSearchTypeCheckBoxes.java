package com.tpg.par.web.components;

import com.tpg.par.domain.PlanningSearchType;

import java.util.List;
import java.util.stream.Stream;

import static java.util.Collections.unmodifiableList;
import static java.util.stream.Collectors.toList;

public class PlanningSearchTypeCheckBoxes {
    private final List<PlanningSearchTypeCheckBox> checkBoxes = unmodifiableList(Stream.of(PlanningSearchType.values())
        .map(PlanningSearchTypeCheckBox::new).collect(toList()));

    public List<PlanningSearchTypeCheckBox> getValues() {
        return checkBoxes;
    }
}
