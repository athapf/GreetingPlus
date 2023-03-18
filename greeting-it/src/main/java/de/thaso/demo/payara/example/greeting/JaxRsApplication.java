package de.thaso.demo.payara.example.greeting;

import de.thaso.demo.payara.example.greeting.boundery.GreetingResource;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/")
public class JaxRsApplication extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> map = new HashSet<>();
        map.add(GreetingResource.class);
        return map;
    }
}
