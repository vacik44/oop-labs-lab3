package oop.labs.lab3.assertion;

import oop.labs.lab3.aksate.exceptions.AksateComponentException;
import oop.labs.lab3.aksate.exceptions.AksateComponentHasMissingDependenciesException;
import oop.labs.lab3.assertion.asserts.AksateComponentExceptionAssert;
import oop.labs.lab3.assertion.asserts.AksateComponentHasMissingDependenciesExceptionAssert;

public class CustomAssertions
{
    public static <T extends AksateComponentException> AksateComponentExceptionAssert<T> assertThat(T actual)
    {
        return new AksateComponentExceptionAssert<T>(actual);
    }

    public static <T extends AksateComponentHasMissingDependenciesException> AksateComponentHasMissingDependenciesExceptionAssert<T> assertThat(T actual)
    {
        return new AksateComponentHasMissingDependenciesExceptionAssert<T>(actual);
    }
}
