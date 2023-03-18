package de.thaso.demo.payara.example.greeting.web;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.importer.ZipImporter;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.File;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(Arquillian.class)
public class GreetingWebIT {
    private final static Logger LOGGER = LoggerFactory.getLogger(GreetingWebIT.class);

    @Deployment
    public static WebArchive createDeployment() {
        WebArchive war = ShrinkWrap
            .create(ZipImporter.class, "demo.war")
            .importFrom(new File("target/greeting-it.war"))
            .as(WebArchive.class)
            ;

        System.out.println("WebArchive: " + war.toString(true));
        return war;
    }

    @Test
    public void testRestCall() throws Exception {
        LOGGER.info("starting test ...");

        Client client = ClientBuilder.newClient();
        final WebTarget target = client.target("http://localhost:7085/greeting-web/hello");
        final Response response = target.request(MediaType.TEXT_PLAIN).get();
        final String result = response.readEntity(String.class);
        assertThat(result, is("Hello, World!"));
    }
}
