package de.thaso.demo.payara.example.greeting.control;

import de.thaso.demo.payara.example.greeting.utils.PropertiesFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Properties;

@ApplicationScoped
public class GreetingProperties {
    private final static Logger LOGGER = LoggerFactory.getLogger(GreetingProperties.class);

    @Inject
    @PropertiesFile("/greeting.properties")
    private Properties properties;

    public String readProperty(final String key) {
        LOGGER.info("==> readProperty: {}", key);
        return properties.getProperty(key);
    }
}
