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

public class SimpleSearchByLineOneOfAddressTest extends SimpleSearchByTest {

    @InjectMocks
    private SimpleSearchByLineOneOfAddress simpleSearchByLineOneOfAddress;

    @Test
    public void simpleSearch() {

        String lineOne = "577 Davidie Road";

        PlanningApplicationDocument document = buildDocument(lineOne, "CR0 8DD");

        Page<PlanningApplicationDocument> page = buildPage(document);

        when(planningApplicationsQueryService.findByLineOneOfAddress(lineOne, pageRequest))
            .thenReturn(page);

        SimpleSearchRequest request = new SimpleSearchRequest(Applications, Current, document.getLineOneOfAddress(), pageRequest);

        simpleSearchByLineOneOfAddress.apply(request);

        verify(planningApplicationsQueryService).findByLineOneOfAddress(lineOne, pageRequest);
    }

    @Test
    public void simpleCompositeSearch() {

        String lineOne = "577 Davidie Road";
        String postCode = "CR0 8DD";

        PlanningApplicationDocument document = buildDocument(lineOne, postCode);

        Page<PlanningApplicationDocument> page = buildPage(null);

        when(planningApplicationsQueryService.findByLineOneOfAddress(postCode, pageRequest))
            .thenReturn(page);

        page = buildPage(document);

        when(planningApplicationsQueryService.findByPostCode(postCode, pageRequest))
                .thenReturn(page);

        SimpleSearchByPostCode simpleSearchByPostCode = new SimpleSearchByPostCode(planningApplicationsQueryService);

        SimpleSearchByLineOneOfAddress simpleSearchByLineOneOfAddress = new SimpleSearchByLineOneOfAddress(planningApplicationsQueryService,
            Optional.of(simpleSearchByPostCode));

        SimpleSearchRequest request = new SimpleSearchRequest(Applications, Current, document.getPostCode(), pageRequest);

        simpleSearchByLineOneOfAddress.apply(request);

        verify(planningApplicationsQueryService).findByPostCode(postCode, pageRequest);

        verify(planningApplicationsQueryService).findByLineOneOfAddress(postCode, pageRequest);
    }
}
