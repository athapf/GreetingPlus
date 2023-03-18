package de.thaso.demo.payara.example.greeting.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigPropertiesProducer {
    private final static Logger LOGGER = LoggerFactory.getLogger(ConfigPropertiesProducer.class);

    @Produces
    @PropertiesFile
    public Properties create(InjectionPoint injectionPoint) throws IOException {

        final PropertiesFile propertyFileAnnotation = injectionPoint.getAnnotated().getAnnotation(PropertiesFile.class);
        final String propertiesFilename = propertyFileAnnotation.value();

        final Properties properties = new Properties();
        final InputStream inputStream = this.getClass().getResourceAsStream(propertiesFilename);

        if(inputStream != null) {
            properties.load(inputStream);
        } else {
            LOGGER.error("properties file '" + propertiesFilename + "' not found");
        }
        return properties;
    }
}
