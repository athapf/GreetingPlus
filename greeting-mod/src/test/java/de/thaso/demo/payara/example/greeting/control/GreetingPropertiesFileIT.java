package de.thaso.demo.payara.example.greeting.control;

import de.thaso.demo.payara.example.greeting.utils.PropertiesFile;
import org.jboss.weld.junit5.EnableWeld;
import org.jboss.weld.junit5.WeldInitiator;
import org.jboss.weld.junit5.WeldSetup;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.util.Properties;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

@EnableWeld
class GreetingPropertiesFileIT {

    @WeldSetup
    WeldInitiator weldInitiator = WeldInitiator.of(WeldInitiator.createWeld()
            .addPackages(true
                    , GreetingControl.class
                    , PropertiesFile.class
                    ));

    @Inject
    @PropertiesFile("/greeting.properties")
    private Properties properties;

    @Inject
    @PropertiesFile("/notfound.properties")
    private Properties notfound;

    @Test
    void readProperty() {
        assertThat(properties.getProperty("sample1"), is("hello"));
    }

    @Test
    void readPropertyNotFound() {
        assertThat(notfound.getProperty("sample1"), nullValue());
    }
}
