package oop.labs.lab3.assertion.asserts;

import oop.labs.lab3.aksate.exceptions.AksateComponentException;
import org.assertj.core.api.ThrowableAssert;
import java.util.concurrent.Callable;

import static org.assertj.core.api.Assertions.assertThat;

public class AksateComponentExceptionAssert<T extends AksateComponentException> extends ThrowableAssert<T>
{
    public AksateComponentExceptionAssert(T actual) { super(actual); }
    public <V> AksateComponentExceptionAssert(Callable<V> runnable) { super(runnable); }


    public AksateComponentExceptionAssert<T> generatedForComponent(Class<?> clazz)
    {
        assertThat(actual.getComponentClazz()).isSameAs(clazz);
        return this;
    }

    public AksateComponentExceptionAssert<T> hasNoComponentSpecified()
    {
        assertThat(actual.getComponentClazz()).isNull();
        return this;
    }
}
