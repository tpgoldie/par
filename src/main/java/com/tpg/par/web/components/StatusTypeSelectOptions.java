package com.tpg.par.web.components;

import com.tpg.par.domain.StatusType;

import java.util.List;

import static java.util.Collections.unmodifiableList;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Stream.of;

public class StatusTypeSelectOptions {
    private final List<StatusTypeSelectOption> statusTypeSelectOptions = unmodifiableList(of(StatusType.values())
            .map(StatusTypeSelectOption::new).collect(toList()));

    public List<StatusTypeSelectOption> getValues() { return statusTypeSelectOptions; }
}
