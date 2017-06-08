package com.tpg.par.web.components;

import com.tpg.par.domain.ApplicationType;
import org.apache.commons.lang3.builder.EqualsBuilder;

public class ApplicationTypeCheckBox {
    private final ApplicationType applicationType;

    public ApplicationTypeCheckBox(ApplicationType applicationType) {
        this.applicationType = applicationType;
    }

    public String getLabel() { return applicationType.name(); }

    public String getName() { return applicationType.name().toLowerCase(); }

    public int getOrdinal() { return applicationType.ordinal(); }

    public ApplicationType getApplicationType() { return applicationType; }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) { return true; }
        if (!(obj instanceof ApplicationTypeCheckBox)) { return false; }

        ApplicationTypeCheckBox that = (ApplicationTypeCheckBox) obj;

        return new EqualsBuilder()
            .append(this.applicationType, that.applicationType)
            .isEquals();
    }

    @Override
    public String toString() { return applicationType.name(); }
}
