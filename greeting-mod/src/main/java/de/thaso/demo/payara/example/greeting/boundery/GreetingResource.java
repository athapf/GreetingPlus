package de.thaso.demo.payara.example.greeting.boundery;

import de.thaso.demo.payara.example.greeting.control.GreetingControl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/hello")
public class GreetingResource {
    private final static Logger LOGGER = LoggerFactory.getLogger(GreetingResource.class);

    @Inject
    private GreetingControl greetingControl;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String helloWorld() {
        LOGGER.info("==> helloWorld ...");
        return greetingControl.helloWorld();
    }
}
