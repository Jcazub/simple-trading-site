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
        addContextListener(sc);
        addDispatchServlet(sc);
        addSecurityFilter(sc);
    }

    private void addContextListener(ServletContext sc) {
        AnnotationConfigWebApplicationContext root = new AnnotationConfigWebApplicationContext();

        root.scan("com.trading");
        sc.addListener(new ContextLoaderListener(root));
    }

    private void addDispatchServlet(ServletContext sc) {
        DispatcherServlet dispatcherServlet = new DispatcherServlet(new GenericWebApplicationContext());

        ServletRegistration.Dynamic appServlet = sc.addServlet("mvc", dispatcherServlet);
        appServlet.setLoadOnStartup(1);
        appServlet.addMapping("/");
        appServlet.setInitParameter("throwExceptionIfNoHandlerFound", "true");
    }

    private void addSecurityFilter(ServletContext sc) {
        sc.addFilter("securityFilter", new DelegatingFilterProxy("springSecurityFilterChain"))
                .addMappingForUrlPatterns(null, false, "/*");
    }

}
