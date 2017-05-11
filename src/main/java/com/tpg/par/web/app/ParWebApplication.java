package com.tpg.par.web.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.tpg.par.web.app", "com.tpg.par.web.controllers"})
public class ParWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(ParWebApplication.class, args);
    }
}