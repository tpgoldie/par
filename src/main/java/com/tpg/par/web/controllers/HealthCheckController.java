package com.tpg.par.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Controller
@RequestMapping("/par")
public class HealthCheckController {
    private HealthIndicator healthIndicator;

    @Autowired
    public HealthCheckController(HealthIndicator healthIndicator) {
        this.healthIndicator = healthIndicator;
    }

    @GetMapping(value = "/health", produces = APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<Health> handleHealthCheck() {
        return new ResponseEntity(healthIndicator.health(), OK);
    }
}
