package com.tpg.par.service;

import com.tpg.par.UniqueIdGeneration;
import com.tpg.par.domain.PlanningApplication;
import com.tpg.par.es.documents.PlanningApplicationDocument;
import com.tpg.par.es.service.PlanningApplicationsQueryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static com.tpg.par.domain.SearchType.Applications;
import static com.tpg.par.domain.StatusType.Current;
import static java.util.Collections.singletonList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class PlanningApplicationsServiceImplTest implements UniqueIdGeneration {
    @Mock
    private PlanningApplicationsQueryService planningApplicationsQueryService;

    @InjectMocks
    private PlanningApplicationsServiceImpl service;

    @Test
    public void simpleSearch() {
        PageRequest pageRequest = new PageRequest(0, 5);
        PlanningApplicationDocument planningApplicationDocument = new PlanningApplicationDocument(newId(), newId());

        SimpleSearchRequest request = new SimpleSearchRequest(Applications, Current,
        planningApplicationDocument.getReferenceNumber(), pageRequest);

        Page<PlanningApplicationDocument> page = new PageImpl<>(singletonList(planningApplicationDocument), pageRequest, 5);
        when(planningApplicationsQueryService.findByReferenceNumber(planningApplicationDocument.getReferenceNumber(), pageRequest)).thenReturn(page);

        List<PlanningApplication> actual = service.simpleSearch(request);

        assertThat(actual, hasSize(1));

        verify(planningApplicationsQueryService).findByReferenceNumber(planningApplicationDocument.getReferenceNumber(), pageRequest);
    }
}
