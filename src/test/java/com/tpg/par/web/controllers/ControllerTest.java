package com.tpg.par.web.controllers;

import com.tpg.par.es.context.ElasticSearchConfig;
import com.tpg.par.es.repositories.PlanningApplicationsQueryRepository;
import com.tpg.par.web.app.ParWebApplication;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {ParWebApplication.class})
@AutoConfigureMockMvc
public abstract class ControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    PlanningApplicationsQueryRepository planningApplicationsQueryRepository;

    @MockBean
    ElasticSearchConfig elasticSearchConfig;

    @Mock
    ElasticsearchOperations elasticSearchOperations;
}
