package de.thaso.demo.payara.example.greeting.control;

import de.thaso.demo.payara.example.greeting.utils.PropertiesFile;
import org.jboss.weld.junit5.EnableWeld;
import org.jboss.weld.junit5.WeldInitiator;
import org.jboss.weld.junit5.WeldSetup;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.util.Properties;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@EnableWeld
class GreetingPropertiesIT {

    @WeldSetup
    WeldInitiator weldInitiator = WeldInitiator.of(WeldInitiator.createWeld()
            .addPackages(true
                    , GreetingControl.class
                    , PropertiesFile.class
                    ));

    @Inject
    private GreetingProperties underTest;

    @Inject
    @PropertiesFile("/greeting.properties")
    private Properties properties;

    @Test
    void readProperty() {
        assertThat(underTest.readProperty("sample1"), is("hello"));
    }
}
