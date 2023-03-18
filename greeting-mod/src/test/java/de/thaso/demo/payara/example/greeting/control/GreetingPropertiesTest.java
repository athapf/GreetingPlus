package de.thaso.demo.payara.example.greeting.control;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Properties;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class GreetingPropertiesTest {

    @InjectMocks
    private GreetingProperties underTest;

    @Mock
    private Properties properties;

    @BeforeEach
    void setUp() {
        openMocks(this);

        when(properties.getProperty("sample1")).thenReturn("ok");
    }

    @Test
    void readProperty() {
        assertThat(underTest.readProperty("sample1"), is("ok"));
    }

    @Test
    void readProperty_WhenKeyNotFound_ThenNull() {
        assertThat(underTest.readProperty("unknown"), nullValue());
    }

    @Test
    void readProperty_WhenKeyNull_ThenNull() {
        assertThat(underTest.readProperty(null), nullValue());
    }
}
