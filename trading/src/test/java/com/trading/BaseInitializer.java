package com.trading;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;
import org.testcontainers.containers.MySQLContainer;

import java.util.HashMap;
import java.util.Map;

public class BaseInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    private MySQLContainer mySQLContainer;

    public BaseInitializer(MySQLContainer mySQLContainer) {
        this.mySQLContainer = mySQLContainer;
    }

    public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
        ConfigurableEnvironment environment = configurableApplicationContext.getEnvironment();

        MutablePropertySources propertySources = environment.getPropertySources();
        Map<String, Object> map = new HashMap<>();
        map.put("RDS_HOSTNAME", mySQLContainer.getContainerIpAddress());
        map.put("RDS_PORT", mySQLContainer.getFirstMappedPort());
        map.put("RDS_USERNAME", mySQLContainer.getUsername());
        map.put("RDS_PASSWORD", mySQLContainer.getPassword());
        map.put("RDS_DB_NAME", mySQLContainer.getDatabaseName());
        propertySources.addFirst(new MapPropertySource("newmap", map));
    }
}
