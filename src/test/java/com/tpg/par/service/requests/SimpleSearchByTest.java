package com.tpg.par.service.requests;

import com.tpg.par.UniqueIdGeneration;
import com.tpg.par.es.documents.PlanningApplicationDocument;
import com.tpg.par.es.service.PlanningApplicationsQueryService;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;

@RunWith(MockitoJUnitRunner.class)
abstract class SimpleSearchByTest implements UniqueIdGeneration {
    @Mock
    PlanningApplicationsQueryService planningApplicationsQueryService;

    PageRequest pageRequest = new PageRequest(0, 5);

    PlanningApplicationDocument buildDocument(String lineOne, String postCode) {
        return new PlanningApplicationDocument(newId(), newId(), lineOne, postCode);
    }

    PageImpl<PlanningApplicationDocument> buildPage(PlanningApplicationDocument document) {
        List<PlanningApplicationDocument> values = document != null ? singletonList(document) : emptyList();

        return new PageImpl<>(values, pageRequest, 5);
    }
}
