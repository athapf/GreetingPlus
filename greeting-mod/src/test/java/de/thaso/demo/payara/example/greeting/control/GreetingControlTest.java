package de.thaso.demo.payara.example.greeting.control;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.MockitoAnnotations.openMocks;

class GreetingControlTest {

    @BeforeEach
    void setUp() {
        openMocks(this);
    }

    @InjectMocks
    private GreetingControl underTest;

    @Test
    void test() {
        assertThat(underTest.helloWorld(), is("Hello, World!"));
    }
}
