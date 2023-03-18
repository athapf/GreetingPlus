package de.thaso.demo.payara.example.greeting.control;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class GreetingControl {
    private final static Logger LOGGER = LoggerFactory.getLogger(GreetingControl.class);

    public String helloWorld() {
        LOGGER.info("==> helloWorld ...");
        return "Hello, World!";
    }
}
