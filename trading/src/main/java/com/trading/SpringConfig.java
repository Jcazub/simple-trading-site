package com.trading;

import com.trading.security.handlers.CustomExceptionResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.core.Ordered;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import javax.annotation.Resource;
import javax.sql.DataSource;
import javax.ws.rs.InternalServerErrorException;
import java.util.Properties;

@EnableWebMvc
@Configuration
@ComponentScan(basePackages = {"com.trading"})
public class SpringConfig implements WebMvcConfigurer {

    @Resource
    private Environment env;

    @Autowired
    public SpringConfig(Environment env) {
        super();
    }

    // API
    @Bean
    public ViewResolver viewResolver() {
        final InternalResourceViewResolver bean = new InternalResourceViewResolver();

        bean.setViewClass(JstlView.class);
        bean.setPrefix("/jsp/");
        bean.setSuffix(".jsp");
        bean.setExposeContextBeansAsAttributes(true);

        return bean;
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        String database_url = "jdbc:mysql://" + env.getRequiredProperty("RDS_HOSTNAME")
                + ":" + env.getRequiredProperty("RDS_PORT")
                + "/" + env.getRequiredProperty("RDS_DB_NAME")
                + "?useSSL=false";

        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl(database_url);
        dataSource.setUsername(env.getRequiredProperty("RDS_USERNAME"));
        dataSource.setPassword(env.getRequiredProperty("RDS_PASSWORD"));

        return dataSource;
    }

    @Bean
    HandlerExceptionResolver customExceptionResolver () {
        CustomExceptionResolver resolver = new CustomExceptionResolver();
        Properties mappings = new Properties();

        setMappingsProperties(mappings);
        resolver.setExceptionMappings(mappings);
        setResolverSettings(resolver);

        return resolver;
    }

    private void setMappingsProperties(Properties mappings) {
        // Mapping Spring internal error NoHandlerFoundException to a view name
        mappings.setProperty(NoHandlerFoundException.class.getName(), "/customError");
        mappings.setProperty(InternalServerErrorException.class.getName(), "/customError");
        mappings.setProperty(NullPointerException.class.getName(), "/customError");
        mappings.setProperty(ClassNotFoundException.class.getName(), "/customError");
        mappings.setProperty(Exception.class.getName(), "/customError");
    }

    private void setResolverSettings(CustomExceptionResolver resolver) {
        // Set specific HTTP codes
        resolver.addStatusCode("404", HttpStatus.NOT_FOUND.value());
        resolver.addStatusCode("500", HttpStatus.INTERNAL_SERVER_ERROR.value());
        resolver.setDefaultErrorView("/customError");
        resolver.setDefaultStatusCode(200);
        // This resolver will be processed before the default ones
        resolver.setOrder(Ordered.HIGHEST_PRECEDENCE);
        resolver.setExceptionAttribute("exception");
    }
}
