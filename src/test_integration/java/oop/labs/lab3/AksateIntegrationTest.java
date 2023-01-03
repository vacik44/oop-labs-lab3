package oop.labs.lab3;

import oop.labs.lab3.aksate.exceptions.AksateComponentAccessibleConstructorsNotFoundException;
import oop.labs.lab3.aksate.exceptions.AksateComponentHasMissingDependenciesException;
import oop.labs.lab3.aksate.exceptions.AksateComponentNotFoundException;
import oop.labs.lab3.mimics.*;
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


    @Before
    public void shouldNotThrowAccessibleConstructorsNotFoundException()
    {
        var envitonment = new AksateEnvironment().enable(AksateFeatures.IGNORE_CONSTRUCTORS_ACCESSIBILITY);
        assertThat(catchThrowable(() -> envitonment.configure(configuration))).isNull();
    }

    @Test
    public void shouldThrowAccessibleConstructorsNotFoundException()
    {
        var environment = new AksateEnvironment().disable(AksateFeatures.IGNORE_CONSTRUCTORS_ACCESSIBILITY);
        assertThat(catchThrowable(() -> environment.configure(configuration))).isInstanceOf(AksateComponentAccessibleConstructorsNotFoundException.class);
    }

    @Test
    public void shouldThrowHasMissingDependenciesException()
    {
        assertThat(catchThrowable(() -> container.getComponent(HasExternalDependencies.class))).isInstanceOf(AksateComponentHasMissingDependenciesException.class);
        assertThat(catchThrowable(() -> container.getComponent(UseHasExternalDependencies.class))).isInstanceOf(AksateComponentHasMissingDependenciesException.class);
        assertThat(catchThrowable(() -> container.getComponent(InternalA.class))).isInstanceOf(AksateComponentHasMissingDependenciesException.class);
    }

    @Test
    public void shouldThrowComponentNotFoundException()
    {
        assertThat(catchThrowable(() -> container.getComponent(ExternalB.class))).isInstanceOf(AksateComponentNotFoundException.class);
    }

    @Test
    public void shouldReturnStaticSingletonAbstraction()
    {
        assertThat(container.getComponent(DependsOnAB.class)).isSameAs(container.getComponent(DependsOnAB.class))
                .isSameAs(TestConfiguration.staticSingleton).isInstanceOf(ExtendsDependsOnAB.class);
    }

    @Test
    public void shouldReturnSingleton()
    {
        assertThat(container.getComponent(AnnotatedSingleton.class)).isSameAs(container.getComponent(AnnotatedSingleton.class));
    }

    @Test
    public void shouldReturnPrototypeWithManyDependencies()
    {
        assertThat(container.getComponent(HasManyDependencies.class)).isNotSameAs(container.getComponent(HasManyDependencies.class));
    }

    @Test
    public void shouldChangeRightInjectionConstructor()
    {
        assertThat(container.getComponent(HasMultipleInjectConstructors.class).getSingleton()).isSameAs(container.getComponent(AnnotatedSingleton.class));
    }
}
