package oop.labs.lab3.mimics;

import org.fpm.di.Binder;
import org.fpm.di.Configuration;

public class TestConfiguration implements Configuration
{
    public static final ExtendsDependsOnAB staticSingleton = new ExtendsDependsOnAB(new SimpleA(), new SimpleB(), new SimpleC());


    @Override
    public void configure(Binder binder)
    {
        binder.bind(SimpleA.class);
        binder.bind(SimpleB.class);
        binder.bind(SimpleC.class);

        binder.bind(AnnotatedSingleton.class);

        binder.bind(HasManyDependencies.class);

        binder.bind(HasExternalDependencies.class);
        binder.bind(UseHasExternalDependencies.class);

        binder.bind(HasMultipleInjectConstructors.class);
        binder.bind(HasPrivateConstructor.class);

        binder.bind(InternalA.class, ExternalC.class);
        binder.bind(DependsOnAB.class, ExtendsDependsOnAB.class);
        binder.bind(ExtendsDependsOnAB.class, staticSingleton);
    }
}
