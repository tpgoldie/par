package com.tpg.par.persistence.repositories;

import com.tpg.par.UniqueIdGeneration;
import com.tpg.par.persistence.PersistenceConfig;
import com.tpg.par.persistence.entities.PlanningApplicationEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)
@DataJpaTest
@ContextConfiguration(classes = {PersistenceConfig.class})
public class PlanningApplicationsRepositoriesTest implements UniqueIdGeneration {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private PlanningApplicationsManagementRepository planningApplicationsManagementRepository;

    @Autowired
    private PlanningApplicationsQueryRepository planningApplicationsQueryRepository;

    @Test
    public void searchForApplications() {
        PlanningApplicationEntity entity = new PlanningApplicationEntity();
        entity.setReferenceNumber(newId());

        planningApplicationsManagementRepository.save(entity);

        PlanningApplicationEntity actual = planningApplicationsQueryRepository
            .findByReferenceNumber(entity.getReferenceNumber()).get();

        assertThat(actual, hasProperty("referenceNumber", is(entity.getReferenceNumber())));
    }
}
