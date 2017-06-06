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
    private PlanningApplicationsServiceImpl planningApplicationsServiceImpl;

    private PageRequest pageRequest = new PageRequest(0, 5);

    @Test
    public void simpleSearchCascadesToSearchReferenceNumber() {
        PlanningApplicationDocument planningApplicationDocument = new PlanningApplicationDocument(newId(), newId(),"", "");

        SimpleSearchRequest request = buildSimpleSearchRequest(planningApplicationDocument.getReferenceNumber(), pageRequest);

        Page<PlanningApplicationDocument> page = buildPage(planningApplicationDocument);

        when(planningApplicationsQueryService.findByReferenceNumber(planningApplicationDocument.getReferenceNumber(), pageRequest)).thenReturn(page);

        List<PlanningApplication> actual = planningApplicationsServiceImpl.simpleSearch(request);

        assertThat(actual, hasSize(1));

        verify(planningApplicationsQueryService).findByReferenceNumber(planningApplicationDocument.getReferenceNumber(), pageRequest);
    }

    @Test
    public void simpleSearchCascadesToSearchPostCode() {
        PlanningApplicationDocument document = new PlanningApplicationDocument(newId(), newId(), "577 Davidie Road", "CR0 0DD");

        SimpleSearchRequest request = buildSimpleSearchRequest(document.getPostCode(), pageRequest);

        Page<PlanningApplicationDocument> page = buildPage(document);

        when(planningApplicationsQueryService.findByPostCode(document.getPostCode(), pageRequest)).thenReturn(page);

        List<PlanningApplication> actual = planningApplicationsServiceImpl.simpleSearch(request);

        assertThat(actual, hasSize(1));

        verify(planningApplicationsQueryService).findByPostCode(document.getPostCode(), pageRequest);
    }

    private PageImpl<PlanningApplicationDocument> buildPage(PlanningApplicationDocument document) {
        return new PageImpl<>(singletonList(document), pageRequest, 5);
    }

    @Test
    public void simpleSearchCascadesToFirstLineOfAddress() {
        PlanningApplicationDocument document = new PlanningApplicationDocument(newId(), newId(), "577 Davidie Street", "CR0 7DD");

        SimpleSearchRequest request = buildSimpleSearchRequest(document.getLineOneOfAddress(), pageRequest);

        Page<PlanningApplicationDocument> page = buildPage(document);

        when(planningApplicationsQueryService.findByLineOneOfAddress(document.getLineOneOfAddress(), pageRequest))
            .thenReturn(page);

        List<PlanningApplication> actual = planningApplicationsServiceImpl.simpleSearch(request);

        assertThat(actual, hasSize(1));

        verify(planningApplicationsQueryService).findByLineOneOfAddress(document.getLineOneOfAddress(), pageRequest);
    }

    private SimpleSearchRequest buildSimpleSearchRequest(String lineOneOfAddress, PageRequest pageRequest) {
        return new SimpleSearchRequest(Applications, Current, lineOneOfAddress, pageRequest);
    }
}
