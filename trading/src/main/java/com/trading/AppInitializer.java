package com.trading;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;

public class AppInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(final ServletContext sc) throws ServletException {

        AnnotationConfigWebApplicationContext root = new AnnotationConfigWebApplicationContext();

        root.scan("com.trading");
        sc.addListener(new ContextLoaderListener(root));

        DispatcherServlet dispatcherServlet = new DispatcherServlet(new GenericWebApplicationContext());
        dispatcherServlet.setThrowExceptionIfNoHandlerFound(true);

        ServletRegistration.Dynamic appServlet = sc.addServlet("mvc", dispatcherServlet);
        appServlet.setLoadOnStartup(1);
        appServlet.addMapping("/");
//        appServlet.addMapping("*.css");
//        appServlet.addMapping("*.js");
//        appServlet.addMapping("*.jpg");
//        appServlet.addMapping("*.png");

        sc.addFilter("securityFilter", new DelegatingFilterProxy("springSecurityFilterChain"))
                .addMappingForUrlPatterns(null, false, "/*");

    }

}
