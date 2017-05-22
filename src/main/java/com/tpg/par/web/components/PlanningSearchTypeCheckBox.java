package com.tpg.par.web.components;

import com.tpg.par.domain.PlanningSearchType;
import org.apache.commons.lang3.builder.EqualsBuilder;

public class PlanningSearchTypeCheckBox {
    private final PlanningSearchType planningSearchType;

    public PlanningSearchTypeCheckBox(PlanningSearchType planningSearchType) {
        this.planningSearchType = planningSearchType;
    }

    public String getLabel() { return planningSearchType.name(); }

    public String getName() { return planningSearchType.name().toLowerCase(); }

    public int getOrdinal() { return planningSearchType.ordinal(); }

    public PlanningSearchType getValue() { return planningSearchType; }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) { return true; }
        if (!(obj instanceof PlanningSearchTypeCheckBox)) { return false; }

        PlanningSearchTypeCheckBox that = (PlanningSearchTypeCheckBox) obj;

        return new EqualsBuilder()
            .append(this.planningSearchType, that.planningSearchType)
            .isEquals();
    }

    @Override
    public String toString() { return planningSearchType.name(); }
}
