package com.tpg.par.web.components;

import com.tpg.par.domain.DecisionStatus;
import org.apache.commons.lang3.builder.EqualsBuilder;

public class DecisionStatusSelectOption {
    private final DecisionStatus decisionStatus;

    public DecisionStatusSelectOption(DecisionStatus decisionStatus) {
        this.decisionStatus = decisionStatus;
    }

    public DecisionStatus getDecisionStatus() { return decisionStatus; }

    public String getLabel() { return decisionStatus.name().toLowerCase(); }

    public String getName() { return decisionStatus.name(); }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) { return true; }
        if (!(obj instanceof DecisionStatusSelectOption)) { return false; }

        DecisionStatusSelectOption that = (DecisionStatusSelectOption) obj;

        return new EqualsBuilder()
            .append(this.decisionStatus, that.decisionStatus)
            .isEquals();

    }

    @Override
    public String toString() {
        return decisionStatus.name();
    }
}
