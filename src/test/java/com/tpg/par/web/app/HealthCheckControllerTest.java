package com.tpg.par.web.app;

import com.tpg.par.web.controllers.HealthCheckController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.boot.actuate.health.Status.UP;
import static org.springframework.http.MediaType.TEXT_HTML;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {ParWebApplication.class})
@WebMvcTest(HealthCheckController.class)
@AutoConfigureMockMvc
public class HealthCheckControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void handleHealthCheckRequest_healthCheckRequest_healthCheckStatusGiven() throws Exception {
        mockMvc.perform(get("/par/health")
                .contentType(TEXT_HTML))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.status", is(UP.getCode())));
    }
}
