package com.tpg.par.web.components;

import java.util.List;

import static com.tpg.par.domain.DecisionStatus.values;
import static java.util.Collections.unmodifiableList;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Stream.of;

public class StatusTypeSelectOptions {
    private final List<DecisionStatusSelectOption> decisionStatusSelectOptions = unmodifiableList(of(values())
            .map(DecisionStatusSelectOption::new).collect(toList()));

    public List<DecisionStatusSelectOption> getValues() { return decisionStatusSelectOptions; }
}
