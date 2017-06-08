package com.tpg.par.service.requests;

import com.tpg.par.es.documents.PlanningApplicationDocument;
import com.tpg.par.es.service.PlanningApplicationsQueryService;
import com.tpg.par.model.PlanningApplicationDocumentFixture;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;

@RunWith(MockitoJUnitRunner.class)
abstract class SimpleSearchByTest implements PlanningApplicationDocumentFixture {
    @Mock
    PlanningApplicationsQueryService planningApplicationsQueryService;

    PageRequest pageRequest = new PageRequest(0, 5);

    PageImpl<PlanningApplicationDocument> buildPage(PlanningApplicationDocument document) {
        List<PlanningApplicationDocument> values = document != null ? singletonList(document) : emptyList();

        return new PageImpl<>(values, pageRequest, 5);
    }
}
