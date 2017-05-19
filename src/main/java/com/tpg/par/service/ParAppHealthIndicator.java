package com.tpg.par.service;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class ParAppHealthIndicator implements HealthIndicator {
    public Health health() {
        return Health.up().build();
    }
}
