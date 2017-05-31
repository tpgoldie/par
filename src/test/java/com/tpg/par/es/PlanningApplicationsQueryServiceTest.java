package com.tpg.par.es;

import com.tpg.par.UniqueIdGeneration;
import com.tpg.par.es.documents.PlanningApplicationDocument;
import com.tpg.par.es.repositories.PlanningApplicationsQueryRepository;
import com.tpg.par.es.service.PlanningApplicationsQueryServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static java.util.Collections.singletonList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class PlanningApplicationsQueryServiceTest implements UniqueIdGeneration {
    @Mock
    private PlanningApplicationsQueryRepository planningApplicationsQueryRepository;

    @InjectMocks
    private PlanningApplicationsQueryServiceImpl planningApplicationsQueryService;

    @Test
    public void findByReferenceNumber() {
        PlanningApplicationDocument planningApplication = new PlanningApplicationDocument(newId(), newId());

        PageRequest pageRequest  = new PageRequest(0, 10);

        Page<PlanningApplicationDocument> page = new PageImpl<>(singletonList(planningApplication), pageRequest, 1);

        when(planningApplicationsQueryRepository.findByReferenceNumber(eq(planningApplication.getReferenceNumber()), any(Pageable.class)))
            .thenReturn(page);

        Page<PlanningApplicationDocument> actual = planningApplicationsQueryService.findByReferenceNumber(planningApplication.getReferenceNumber(), pageRequest);

        assertThat(actual.getNumberOfElements(), is(1));

        PlanningApplicationDocument document = actual.iterator().next();

        assertThat(document.getId(), is(notNullValue()));
        assertThat(document.getReferenceNumber(), is(planningApplication.getReferenceNumber()));

        verify(planningApplicationsQueryRepository).findByReferenceNumber(eq(planningApplication.getReferenceNumber()), any(Pageable.class));
    }
}
