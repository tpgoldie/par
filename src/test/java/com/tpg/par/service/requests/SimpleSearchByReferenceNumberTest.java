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

public class SimpleSearchByReferenceNumberTest extends SimpleSearchByTest {

    @InjectMocks
    private SimpleSearchByReferenceNumber simpleSearchByReferenceNumber;

    @Test
    public void simpleSearch() {

        PlanningApplicationDocument document = buildDocument("577 Davidie Road", "CR0 8YY");

        String refNo = document.getReferenceNumber();

        Page<PlanningApplicationDocument> page = buildPage(document);

        when(planningApplicationsQueryService.findByReferenceNumber(refNo, pageRequest))
            .thenReturn(page);

        SimpleSearchRequest request = new SimpleSearchRequest(Applications, Current, document.getReferenceNumber(), pageRequest);

        simpleSearchByReferenceNumber.apply(request);

        verify(planningApplicationsQueryService).findByReferenceNumber(refNo, pageRequest);
    }

    @Test
    public void compositeSearch() {

        PlanningApplicationDocument document = buildDocument("577 Davidie Road", "CR0 8YY");

        Page<PlanningApplicationDocument> page = buildPage(null);

        when(planningApplicationsQueryService.findByReferenceNumber(document.getPostCode(), pageRequest))
            .thenReturn(page);

        page = buildPage(document);

        when(planningApplicationsQueryService.findByPostCode(document.getPostCode(), pageRequest))
                .thenReturn(page);

        SimpleSearchByPostCode simpleSearchByPostCode = new SimpleSearchByPostCode(planningApplicationsQueryService);

        SimpleSearchByReferenceNumber simpleSearchByReferenceNumber = new SimpleSearchByReferenceNumber(planningApplicationsQueryService, Optional.of(simpleSearchByPostCode));

        SimpleSearchRequest request = new SimpleSearchRequest(Applications, Current, document.getPostCode(), pageRequest);

        simpleSearchByReferenceNumber.apply(request);

        verify(planningApplicationsQueryService).findByReferenceNumber(document.getPostCode(), pageRequest);

        verify(planningApplicationsQueryService).findByPostCode(document.getPostCode(), pageRequest);
    }
}
