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
import static org.hamcrest.Matchers.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class PlanningApplicationsQueryServiceImplTest implements UniqueIdGeneration {

    @Mock
    private PlanningApplicationsQueryRepository planningApplicationsQueryRepository;

    @InjectMocks
    private PlanningApplicationsQueryServiceImpl planningApplicationsQueryServiceImpl;

    private PageRequest pageRequest  = new PageRequest(0, 10);

    @Test
    public void findByReferenceNumber() {
        PlanningApplicationDocument document = new PlanningApplicationDocument(newId(), newId(), "577 Davidie Road", "CR0 1DD");

        Page<PlanningApplicationDocument> page = new PageImpl<>(singletonList(document), pageRequest, 1);

        when(planningApplicationsQueryRepository.findByReferenceNumber(eq(document.getReferenceNumber()), any(Pageable.class)))
            .thenReturn(page);

        Page<PlanningApplicationDocument> actual = planningApplicationsQueryServiceImpl.findByReferenceNumber(document.getReferenceNumber(), pageRequest);

        assertThat(actual.getNumberOfElements(), is(1));

        PlanningApplicationDocument actualDocument = actual.iterator().next();

        assertDocument(document, actualDocument);

        verify(planningApplicationsQueryRepository).findByReferenceNumber(eq(document.getReferenceNumber()), any(Pageable.class));
    }

    @Test
    public void findByPostCode() {
        PlanningApplicationDocument document = new PlanningApplicationDocument(newId(), newId(), "577 Davidie Road", "CR0 1DD");

        Page<PlanningApplicationDocument> page = new PageImpl<>(singletonList(document), pageRequest, 1);

        when(planningApplicationsQueryRepository.findByPostCode(eq(document.getPostCode()), any(Pageable.class)))
            .thenReturn(page);

        Page<PlanningApplicationDocument> actual = planningApplicationsQueryServiceImpl.findByPostCode(document.getPostCode(), pageRequest);

        assertThat(actual.getNumberOfElements(), is(1));

        PlanningApplicationDocument actualDocument = actual.iterator().next();

        assertDocument(document, actualDocument);

        verify(planningApplicationsQueryRepository).findByPostCode(eq(document.getPostCode()), any(Pageable.class));
    }

    @Test
    public void findByLineOneOfAddress() {
        PlanningApplicationDocument document = new PlanningApplicationDocument(newId(), newId(), "577 Davidie Road", "CR0 1DD");

        Page<PlanningApplicationDocument> page = new PageImpl<>(singletonList(document), pageRequest, 1);

        when(planningApplicationsQueryRepository.findByAddressLineOne(eq(document.getLineOneOfAddress()), any(Pageable.class)))
            .thenReturn(page);

        Page<PlanningApplicationDocument> actual = planningApplicationsQueryServiceImpl.findByLineOneOfAddress(document.getLineOneOfAddress(), pageRequest);

        assertThat(actual.getNumberOfElements(), is(1));

        PlanningApplicationDocument actualDocument = actual.iterator().next();

        assertDocument(document, actualDocument);

        verify(planningApplicationsQueryRepository).findByAddressLineOne(eq(document.getLineOneOfAddress()), any(Pageable.class));
    }

    private void assertDocument(PlanningApplicationDocument document, PlanningApplicationDocument actualDocument) {
        assertThat(actualDocument, hasProperty("id", is(notNullValue())));
        assertThat(actualDocument, hasProperty("referenceNumber", is(document.getReferenceNumber())));
        assertThat(actualDocument, hasProperty("lineOneOfAddress", is(document.getLineOneOfAddress())));
        assertThat(actualDocument, hasProperty("postCode", is(document.getPostCode())));
    }
}
