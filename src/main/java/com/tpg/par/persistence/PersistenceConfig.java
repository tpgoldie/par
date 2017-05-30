package com.tpg.par.persistence;

import com.tpg.par.persistence.entities.BaseEntity;
import com.tpg.par.persistence.repositories.ManagementRepository;
import com.tpg.par.persistence.repositories.QueryRepository;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan(basePackageClasses = BaseEntity.class)
@EnableJpaRepositories(basePackageClasses = { ManagementRepository.class, QueryRepository.class})
public class PersistenceConfig {
}
