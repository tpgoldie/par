package com.tpg.par.web.controllers;

import org.junit.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.boot.actuate.health.Status.UP;
import static org.springframework.http.MediaType.TEXT_HTML;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(HealthCheckController.class)
public class HealthCheckControllerTest extends ControllerTest {

    @Test
    public void handleHealthCheckRequest_healthCheckRequest_healthCheckStatusGiven() throws Exception {
        mockMvc.perform(get("/par/health")
                .contentType(TEXT_HTML))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.status", is(UP.getCode())));
    }
}
