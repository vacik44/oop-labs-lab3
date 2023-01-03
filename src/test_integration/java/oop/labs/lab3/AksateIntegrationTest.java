package oop.labs.lab3;

import oop.labs.lab3.aksate.exceptions.AksateComponentAccessibleConstructorsNotFoundException;
import oop.labs.lab3.mimics.TestConfiguration;
import oop.labs.lab3.aksate.AksateEnvironment;
import oop.labs.lab3.aksate.AksateFeatures;
import org.fpm.di.Configuration;
import org.fpm.di.Container;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.catchThrowable;
import static org.assertj.core.api.Assertions.assertThat;

public class AksateIntegrationTest
{
    private Configuration configuration;
    private Container container;


    @Before
    public void setUp()
    {
        var envitonment = new AksateEnvironment().enable(AksateFeatures.IGNORE_CONSTRUCTORS_ACCESSIBILITY);
        configuration = new TestConfiguration();
        container = envitonment.configure(new TestConfiguration());
    }


    @Test
    public void shouldThrowAccessibleConstructorsNotFoundException()
    {
        var environment = new AksateEnvironment().disable(AksateFeatures.IGNORE_CONSTRUCTORS_ACCESSIBILITY);
        assertThat(catchThrowable(() -> environment.configure(configuration))).isInstanceOf(AksateComponentAccessibleConstructorsNotFoundException.class);
    }
}
