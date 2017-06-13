package com.tpg.par.es.context;

import org.springframework.data.elasticsearch.core.ElasticsearchOperations;

public interface ElasticSearchConfiguration {
    ElasticsearchOperations elasticsearchTemplate();
}
