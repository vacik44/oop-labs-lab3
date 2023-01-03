package oop.labs.lab3.aksate.exceptions;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class AksateComponentHasMissingDependenciesException extends AksateComponentInstantiationException
{
    private final List<Class<?>> missingDependencies;


    public AksateComponentHasMissingDependenciesException(Class<?> clazz)
    {
        super(clazz);
        this.missingDependencies = null;
    }

    public AksateComponentHasMissingDependenciesException(Class<?> clazz, Class<?>... dependencies)
    {
        super(clazz);
        this.missingDependencies = Arrays.asList(dependencies);
    }

    public AksateComponentHasMissingDependenciesException(Class<?> clazz, String message)
    {
        super(clazz, message);
        this.missingDependencies = null;
    }

    public AksateComponentHasMissingDependenciesException(Class<?> clazz, String message, Class<?>... dependencies)
    {
        super(clazz, message);
        this.missingDependencies = Arrays.asList(dependencies);
    }


    public Collection<Class<?>> getMissingDependency()
    {
        return missingDependencies == null ? null : Collections.unmodifiableCollection(missingDependencies);
    }


    public static AksateComponentHasMissingDependenciesException forComponent(Class<?> clazz, Class<?> dependency)
    {
        var msg = String.format("Component '%s' has some missing dependencies.", clazz.getName());
        msg += String.format(" We found that necessary component '%s' is missing in current container.", dependency.getName());

        return new AksateComponentHasMissingDependenciesException(clazz, msg, dependency);
    }

    public static AksateComponentHasMissingDependenciesException forComponent(Class<?> clazz)
    {
        var msg = String.format("Component '%s' has some missing dependencies.", clazz.getName());

        return new AksateComponentHasMissingDependenciesException(clazz, msg);
    }
}
