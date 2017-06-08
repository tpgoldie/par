package com.tpg.par.web.components;

import java.util.List;
import java.util.stream.Stream;

import static com.tpg.par.domain.ApplicationType.values;
import static java.util.Collections.unmodifiableList;
import static java.util.stream.Collectors.toList;

public class ApplicationTypeCheckBoxes {
    private final List<ApplicationTypeCheckBox> checkBoxes = unmodifiableList(Stream.of(values())
        .map(ApplicationTypeCheckBox::new).collect(toList()));

    public List<ApplicationTypeCheckBox> getValues() {
        return checkBoxes;
    }
}
