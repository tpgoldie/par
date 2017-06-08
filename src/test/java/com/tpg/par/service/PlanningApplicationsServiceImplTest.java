package com.tpg.par.service;

import com.tpg.par.domain.PlanningApplication;
import com.tpg.par.es.documents.PlanningApplicationDocument;
import com.tpg.par.es.service.PlanningApplicationsQueryService;
import com.tpg.par.model.PlanningApplicationDocumentFixture;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static com.tpg.par.domain.ApplicationType.Applications;
import static com.tpg.par.domain.DecisionStatus.Current;
import static java.util.Collections.singletonList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class PlanningApplicationsServiceImplTest implements PlanningApplicationDocumentFixture {
    @Mock
    private PlanningApplicationsQueryService planningApplicationsQueryService;

    @InjectMocks
    private PlanningApplicationsServiceImpl planningApplicationsServiceImpl;

    private PageRequest pageRequest = new PageRequest(0, 5);

    @Test
    public void simpleSearchCascadesToSearchReferenceNumber() {
        PlanningApplicationDocument planningApplicationDocument = buildPlanningApplicationDocument(newId(), "", "");

        SimpleSearchRequest request = buildSimpleSearchRequest(planningApplicationDocument.getReferenceNumber(), pageRequest);

        Page<PlanningApplicationDocument> page = buildPage(planningApplicationDocument);

        when(planningApplicationsQueryService.findByReferenceNumber(planningApplicationDocument.getReferenceNumber(), pageRequest)).thenReturn(page);

        List<PlanningApplication> actual = planningApplicationsServiceImpl.simpleSearch(request);

        assertThat(actual, hasSize(1));

        verify(planningApplicationsQueryService).findByReferenceNumber(planningApplicationDocument.getReferenceNumber(), pageRequest);
    }

    @Test
    public void simpleSearchCascadesToSearchPostCode() {
        PlanningApplicationDocument document = buildPlanningApplicationDocument(newId(), "577 Davidie Road", "CR8 0DQ");

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
        PlanningApplicationDocument document = buildPlanningApplicationDocument(newId(), "577 Davidie Street", "CR0 7DD");

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
