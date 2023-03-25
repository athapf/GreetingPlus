package de.thaso.demo.payara.example.greeting.client;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import java.security.NoSuchAlgorithmException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.MockitoAnnotations.openMocks;

@Disabled
class GreetingTest {

    @BeforeEach
    void setUp() {
        openMocks(this);

        System.setProperty("https.protocols", "TLSv1.2");

//        System.setProperty("javax.net.ssl.keyStoreType", "jks");
//        System.setProperty("javax.net.ssl.keyStore", "/home/mp008342/demo/payara/GreetingPlus/CA/serverstore.jks");
//        System.setProperty("javax.net.ssl.keyStorePassword", "geheim123");
        System.setProperty("javax.net.ssl.trustStoreType", "jks");
        System.setProperty("javax.net.ssl.trustStore", "../CA/payara/truststore.jks");
        System.setProperty("javax.net.ssl.trustStorePassword", "changeit");

        System.setProperty("javax.net.debug", "ssl");

    }

    @InjectMocks
    private Greeting underTest;

    @Test
    void test() throws Exception {
        assertThat(underTest.callGreeting(), is("Hello, World!"));
    }
}
