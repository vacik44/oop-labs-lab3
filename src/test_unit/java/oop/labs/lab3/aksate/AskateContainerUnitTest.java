package oop.labs.lab3.aksate;

import org.fpm.di.Binder;
import org.fpm.di.Configuration;
import org.junit.Test;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class AskateContainerUnitTest
{
    private static class A {}
    private static class B {}

    private static class Config implements Configuration
    {
        @Override
        public void configure(Binder binder)
        {
            binder.bind(A.class);
            binder.bind(B.class);
        }
    }


    @Test
    public void testContainsMethods()
    {
        var container = (AksateContainer) new AksateEnvironment().configure(new Config());

        assertThat(container.containsComponent(A.class)).isTrue();
        assertThat(container.containsComponents(List.of(A.class, B.class))).isTrue();
        assertThat(container.containsComponents(new Class<?>[] {A.class, B.class})).isTrue();
    }
}
