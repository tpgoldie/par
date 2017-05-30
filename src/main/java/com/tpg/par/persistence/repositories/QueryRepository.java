package com.tpg.par.persistence.repositories;

import com.tpg.par.persistence.entities.BaseEntity;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

@NoRepositoryBean
public interface QueryRepository<T extends BaseEntity> extends Repository<T, Long> {
}
