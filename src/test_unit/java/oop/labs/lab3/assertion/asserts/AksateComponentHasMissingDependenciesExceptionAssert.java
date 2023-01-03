package oop.labs.lab3.assertion.asserts;

import oop.labs.lab3.aksate.exceptions.AksateComponentHasMissingDependenciesException;
import org.assertj.core.api.AbstractCollectionAssert;
import org.assertj.core.api.ObjectAssert;

import java.util.Collection;
import java.util.concurrent.Callable;

import static org.assertj.core.api.Assertions.assertThat;

public class AksateComponentHasMissingDependenciesExceptionAssert<T extends AksateComponentHasMissingDependenciesException> extends AksateComponentExceptionAssert<T>
{
    public AksateComponentHasMissingDependenciesExceptionAssert(T actual) { super(actual); }
    public <V> AksateComponentHasMissingDependenciesExceptionAssert(Callable<V> runnable) { super(runnable); }


    public AbstractCollectionAssert<?, Collection<? extends Class<?>>, Class<?>, ObjectAssert<Class<?>>> hasMissingDependenciesListThat()
    {
        return assertThat(actual.getMissingDependency());
    }

    public AksateComponentHasMissingDependenciesExceptionAssert<T> hasNoMissingDependenciesList()
    {
        assertThat(actual.getMissingDependency()).isNull();
        return this;
    }
}
