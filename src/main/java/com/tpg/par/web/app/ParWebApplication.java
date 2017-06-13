package com.tpg.par.web.app;

import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.tpg.par.web", "com.tpg.par.context", "com.tpg.par.es.context"})
public class ParWebApplication {
//    public static void main(String[] args) {
//        SpringApplication.run(ParWebApplication.class, args);
//    }

    public static void main(String[] args) {
        configureApplication(new SpringApplicationBuilder()).run(args);
    }

    private static SpringApplicationBuilder configureApplication(SpringApplicationBuilder builder) {
        return builder.sources(ParWebApplication.class).bannerMode(Banner.Mode.OFF);
    }
}
