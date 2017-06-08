package com.tpg.par.context;

import com.tpg.par.service.ParAppHealthIndicator;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.tpg.par.service", "com.tpg.par.es.service"})
public class ParAppConfigurer {
    @Bean
    public HealthIndicator healthIndicator() {
        return new ParAppHealthIndicator();
    }
}
