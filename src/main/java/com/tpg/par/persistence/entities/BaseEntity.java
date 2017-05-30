package com.tpg.par.persistence.entities;


import javax.persistence.*;
import java.io.Serializable;

@MappedSuperclass
public abstract class BaseEntity implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_generator")
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
