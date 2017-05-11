package com.tpg.par.web.app;

import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ParAppConfigurer {
    @Bean
    public HealthIndicator healthIndicator() {
        return new ParAppHealthIndicator();
    }
}
