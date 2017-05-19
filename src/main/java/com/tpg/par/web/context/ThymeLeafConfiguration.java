package com.tpg.par.web.context;

import nz.net.ultraq.thymeleaf.LayoutDialect;
import org.springframework.context.annotation.Bean;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;

public interface ThymeLeafConfiguration {

    @Bean
    default SpringResourceTemplateResolver templateResolver() {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();

        templateResolver.setPrefix("/templates/");
        templateResolver.setSuffix(".html");

        templateResolver.setTemplateMode("HTML5");
        templateResolver.setCacheable(true);

        return templateResolver;
    }

    @Bean
    default SpringTemplateEngine templateEngine() {
        SpringTemplateEngine engine = new SpringTemplateEngine();
        engine.setTemplateResolver(templateResolver());
        engine.addDialect(new LayoutDialect());
//        engine.addDialect(new SpringSecurityDialect());

        return engine;
    }

    @Bean
    default ThymeleafViewResolver viewResolver() {
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();

        viewResolver.setTemplateEngine(templateEngine());

        viewResolver.setViewNames(new String[] {".html"});

        return viewResolver;
    }
}
