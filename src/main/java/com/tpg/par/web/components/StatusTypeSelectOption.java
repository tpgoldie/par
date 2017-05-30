package com.tpg.par.web.components;

import com.tpg.par.domain.StatusType;
import org.apache.commons.lang3.builder.EqualsBuilder;

public class StatusTypeSelectOption {
    private final StatusType statusType;

    public StatusTypeSelectOption(StatusType statusType) {
        this.statusType = statusType;
    }

    public StatusType getStatusType() { return statusType; }

    public String getLabel() { return statusType.name().toLowerCase(); }

    public String getName() { return statusType.name(); }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) { return true; }
        if (!(obj instanceof StatusTypeSelectOption)) { return false; }

        StatusTypeSelectOption that = (StatusTypeSelectOption) obj;

        return new EqualsBuilder()
            .append(this.statusType, that.statusType)
            .isEquals();

    }

    @Override
    public String toString() {
        return statusType.name();
    }
}
