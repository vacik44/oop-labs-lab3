package oop.labs.lab3.aksate;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AksateEnvironmentUnitTest
{
    @Test
    public void testConfiguration()
    {
        var environment = new AksateEnvironment();
        assertThat(environment.state(AksateFeatures.IGNORE_CONSTRUCTORS_ACCESSIBILITY)).isTrue();

        environment.disable(AksateFeatures.IGNORE_CONSTRUCTORS_ACCESSIBILITY);
        assertThat(environment.state(AksateFeatures.IGNORE_CONSTRUCTORS_ACCESSIBILITY)).isFalse();

        environment.enable(AksateFeatures.IGNORE_CONSTRUCTORS_ACCESSIBILITY);
        assertThat(environment.state(AksateFeatures.IGNORE_CONSTRUCTORS_ACCESSIBILITY)).isTrue();

        environment.set(AksateFeatures.IGNORE_CONSTRUCTORS_ACCESSIBILITY, false);
        assertThat(environment.state(AksateFeatures.IGNORE_CONSTRUCTORS_ACCESSIBILITY)).isFalse();
    }
}
