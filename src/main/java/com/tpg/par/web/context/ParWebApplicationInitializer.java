package com.tpg.par.web.context;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
public class ParWebApplicationInitializer extends SpringBootServletInitializer {
    public static final String SERVLET_NAME = "par";

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(ParWebApplicationInitializer.class, ParWebConfigurer.class);
    }

//    @Override
//    public void onStartup(ServletContext servletContext) {
//        AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
//
//        servletContext.addListener(new ContextLoaderListener(rootContext));
//
//        AnnotationConfigWebApplicationContext dispatcherContext = new AnnotationConfigWebApplicationContext();
//        dispatcherContext.register(ParWebConfigurer.class);
//
//        ServletRegistration.Dynamic dispatcher = servletContext.addServlet(SERVLET_NAME, new DispatcherServlet(dispatcherContext));
//
//        dispatcher.setLoadOnStartup(2);
//        dispatcher.addMapping("/par");
//    }
//        AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
////        rootContext.register(RootConfiguration.class);
//        ContextLoaderListener contextLoaderListener = new ContextLoaderListener(rootContext);
//        container.addListener(contextLoaderListener);
//
//        AnnotationConfigWebApplicationContext webContext = new AnnotationConfigWebApplicationContext();
//        webContext.register(ParWebConfigurer.class);
//
//        DispatcherServlet dispatcherServlet = new DispatcherServlet(webContext);
//        ServletRegistration.Dynamic dispatcher = container.addServlet("dispatcher", dispatcherServlet);
//        dispatcher.addMapping("/par/");
//    }
}
