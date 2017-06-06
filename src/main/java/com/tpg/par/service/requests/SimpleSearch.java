package com.tpg.par.service.requests;

import com.tpg.par.es.documents.PlanningApplicationDocument;
import com.tpg.par.service.SimpleSearchRequest;
import org.springframework.data.domain.Page;

import java.util.function.Function;

public interface SimpleSearch extends Function<SimpleSearchRequest, Page<PlanningApplicationDocument>> {
}
