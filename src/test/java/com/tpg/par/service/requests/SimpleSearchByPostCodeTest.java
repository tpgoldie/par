package com.tpg.par.service.requests;

import com.tpg.par.es.documents.PlanningApplicationDocument;
import com.tpg.par.service.SimpleSearchRequest;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.springframework.data.domain.Page;

import java.util.Optional;

import static com.tpg.par.domain.SearchType.Applications;
import static com.tpg.par.domain.StatusType.Current;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class SimpleSearchByPostCodeTest extends SimpleSearchByTest {
    @InjectMocks
    private SimpleSearchByPostCode simpleSearchByPostCode;

    private PlanningApplicationDocument document = new PlanningApplicationDocument(newId(), newId(), "577 Davidie Road", "CR0 8DD");

    @Test
    public void simpleSearch() {
        String postCode = document.getPostCode();

        Page<PlanningApplicationDocument> page = buildPage(document);

        when(planningApplicationsQueryService.findByPostCode(postCode, pageRequest))
                .thenReturn(page);

        SimpleSearchRequest request = new SimpleSearchRequest(Applications, Current, document.getPostCode(), pageRequest);

        simpleSearchByPostCode.apply(request);

        verify(planningApplicationsQueryService).findByPostCode(postCode, pageRequest);
    }

    @Test
    public void compositeSearch() {
        String referenceNumber = document.getReferenceNumber();

        Page<PlanningApplicationDocument> page = buildPage(null);

        when(planningApplicationsQueryService.findByPostCode(referenceNumber, pageRequest))
            .thenReturn(page);

        page = buildPage(document);

        when(planningApplicationsQueryService.findByReferenceNumber(referenceNumber, pageRequest))
                .thenReturn(page);

        SimpleSearchRequest request = new SimpleSearchRequest(Applications, Current, document.getReferenceNumber(), pageRequest);

        SimpleSearchByReferenceNumber simpleSearchByReferenceNumber = new SimpleSearchByReferenceNumber(planningApplicationsQueryService);
        SimpleSearchByPostCode simpleSearchByPostCode = new SimpleSearchByPostCode(planningApplicationsQueryService, Optional.of(simpleSearchByReferenceNumber));

        simpleSearchByPostCode.apply(request);

        verify(planningApplicationsQueryService).findByPostCode(referenceNumber, pageRequest);

        verify(planningApplicationsQueryService).findByReferenceNumber(referenceNumber, pageRequest);
    }
}
