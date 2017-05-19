package com.tpg.par.web.context;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class ParWebConfigurer extends WebMvcConfigurerAdapter implements LocaleResolution, ThymeLeafConfiguration,
    MessageSourcing {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(localeChangeInterceptor());
    }
}
