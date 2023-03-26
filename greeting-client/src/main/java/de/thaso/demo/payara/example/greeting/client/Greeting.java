package de.thaso.demo.payara.example.greeting.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.security.NoSuchAlgorithmException;

public class Greeting {
    private final static Logger LOGGER = LoggerFactory.getLogger(Greeting.class);

    public static void main(final String[] args) throws NoSuchAlgorithmException {
        //System.setProperty("https.protocols", "TLSv1.2");

        System.setProperty("javax.net.ssl.keyStoreType", "jks");
        System.setProperty("javax.net.ssl.keyStore", "./CA/mTLS/keystore.jks");
        System.setProperty("javax.net.ssl.keyStorePassword", "changeit");

        System.setProperty("javax.net.ssl.trustStoreType", "jks");
        System.setProperty("javax.net.ssl.trustStore", "./CA/payara/truststore.jks");
        System.setProperty("javax.net.ssl.trustStorePassword", "changeit");

        //System.setProperty("javax.net.debug", "ssl");

        final Greeting greeting = new Greeting();
        System.out.println("Calling: " + greeting.callGreeting());
    }

    public String callGreeting() throws NoSuchAlgorithmException {
        LOGGER.info("==> calling Greeting ...");

        final Response response = ClientBuilder
            .newBuilder()
            //.sslContext(SSLContext.getDefault())
            .build()
            .target("https://localhost:5081")
            .path("greeting-web/hello")
            .request(MediaType.TEXT_PLAIN)
            .get();

        return response.readEntity(String.class);
    }
}
