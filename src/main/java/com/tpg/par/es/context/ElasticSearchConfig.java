package com.tpg.par.es.context;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import javax.annotation.Resource;

import static org.elasticsearch.node.NodeBuilder.nodeBuilder;

@Configuration
@EnableElasticsearchRepositories(basePackages = {"com.tpg.par.es.repositories"})
@ComponentScan(basePackages = {"com.tpg.par.es.service"})
@PropertySource(value = "classpath:elasticsearch.properties")
public class ElasticSearchConfig implements ElasticSearchConfiguration {
    @Resource
    private Environment environment;

     @Bean
    public ElasticsearchOperations elasticsearchTemplate() {
        return new ElasticsearchTemplate(nodeBuilder().local(true).node().client());
    }

//    @Bean
//    public Client client() {
//        TransportClient client = new TransportClient();
//
//        TransportAddress address = new InetSocketTransportAddress(environment.getProperty("elasticsearch.host"),
//                Integer.parseInt(environment.getProperty("elasticsearch.port")));
//
//        client.addTransportAddress(address);
//        return client;
//    }
}
