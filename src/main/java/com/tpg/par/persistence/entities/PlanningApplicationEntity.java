package com.tpg.par.persistence.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Table(name = "planning_applications")
@Entity
@SequenceGenerator(name = "seq_generator", sequenceName = "plan_apps_seq")
public class PlanningApplicationEntity extends BaseEntity {
    @NotNull
    @Size(max = 20)
    @Column(name = "ref_number", unique = true)
    private String referenceNumber;

    public String getReferenceNumber() {
        return referenceNumber;
    }

    public void setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
    }
}
