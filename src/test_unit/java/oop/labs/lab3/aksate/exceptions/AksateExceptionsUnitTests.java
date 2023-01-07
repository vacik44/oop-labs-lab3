package oop.labs.lab3.aksate.exceptions;

import org.junit.Test;

import static oop.labs.lab3.assertion.CustomAssertions.assertThat;

public class AksateExceptionsUnitTests
{
    private static class A {}
    private static class B {}


    @Test
    public void testAksateComponentExceptionConstructors()
    {
        assertThat(new AksateComponentException(A.class)).generatedForComponent(A.class).hasNoCause();
        assertThat(new AksateComponentException(A.class, "test")).generatedForComponent(A.class).hasNoCause().hasMessage("test");

        var cause = new AssertionError();

        assertThat(new AksateComponentException(A.class, cause)).generatedForComponent(A.class).hasCause(cause);
        assertThat(new AksateComponentException(A.class, "test", cause)).generatedForComponent(A.class).hasCause(cause).hasMessage("test");
    }

    @Test
    public void testAksateComponentBindingExceptionConstructors()
    {
        assertThat(new AksateComponentBindingException(A.class)).generatedForComponent(A.class).hasNoCause();
        assertThat(new AksateComponentBindingException(A.class, "test")).generatedForComponent(A.class).hasNoCause().hasMessage("test");

        var cause = new AssertionError();

        assertThat(new AksateComponentBindingException(A.class, cause)).generatedForComponent(A.class).hasCause(cause);
        assertThat(new AksateComponentBindingException(A.class, "test", cause)).generatedForComponent(A.class).hasCause(cause).hasMessage("test");
    }

    @Test
    public void testAksateComponentInstantiationExceptionInstantiation()
    {
        assertThat(new AksateComponentInstantiationException(A.class)).generatedForComponent(A.class).hasNoCause();
        assertThat(new AksateComponentInstantiationException(A.class, "test")).generatedForComponent(A.class).hasNoCause().hasMessage("test");

        var cause = new AssertionError();

        assertThat(new AksateComponentInstantiationException(A.class, cause)).generatedForComponent(A.class).hasCause(cause);
        assertThat(new AksateComponentInstantiationException(A.class, "test", cause)).generatedForComponent(A.class).hasCause(cause).hasMessage("test");

        assertThat(AksateComponentInstantiationException.forComponent(A.class, cause)).generatedForComponent(A.class).hasCause(cause)
                .hasMessageStartingWith(String.format("Aksate container internal exception was occurred during instantiation of component '%s'", A.class.getName()));
    }

    @Test
    public void testAksateComponentNotFoundExceptionInstantiation()
    {
        assertThat(new AksateComponentNotFoundException(A.class)).generatedForComponent(A.class).hasNoCause();
        assertThat(new AksateComponentNotFoundException(A.class, "test")).generatedForComponent(A.class).hasNoCause().hasMessage("test");

        assertThat(AksateComponentNotFoundException.forComponent(A.class)).generatedForComponent(A.class).hasNoCause()
                .hasMessageStartingWith(String.format("Component binding '%s' seems to be missing in handled container.", A.class.getName()));
    }

    @Test
    public void testAksateComponentAccessibleConstructorsNotFoundExceptionInstantiation()
    {
        assertThat(new AksateComponentAccessibleConstructorsNotFoundException(A.class)).generatedForComponent(A.class).hasNoCause();
        assertThat(new AksateComponentAccessibleConstructorsNotFoundException(A.class, "test")).generatedForComponent(A.class).hasNoCause().hasMessage("test");

        assertThat(AksateComponentAccessibleConstructorsNotFoundException.forComponent(A.class)).generatedForComponent(A.class).hasNoCause()
                .hasMessageStartingWith(String.format("Unable to found accessible constructors for component '%s'.", A.class.getName()));
    }

    @Test
    public void testAksateComponentHasMissingDependenciesExceptionInstantiation()
    {
        assertThat(new AksateComponentHasMissingDependenciesException(A.class)).generatedForComponent(A.class).hasNoCause();
        assertThat(new AksateComponentHasMissingDependenciesException(A.class, "test")).generatedForComponent(A.class).hasNoCause().hasMessage("test");

        var e1 = new AksateComponentHasMissingDependenciesException(A.class);

        assertThat(e1).hasNoMissingDependenciesList();
        assertThat(e1).generatedForComponent(A.class).hasNoCause();

        var e2 = new AksateComponentHasMissingDependenciesException(A.class, B.class);

        assertThat(e2).generatedForComponent(A.class).hasNoCause();
        assertThat(e2).hasMissingDependenciesListThat().containsOnly(B.class);

        var e3 = new AksateComponentHasMissingDependenciesException(A.class, "test");

        assertThat(e3).hasNoMissingDependenciesList();
        assertThat(e3).generatedForComponent(A.class).hasNoCause().hasMessage("test");

        var e4 = new AksateComponentHasMissingDependenciesException(A.class, "test", B.class);

        assertThat(e4).generatedForComponent(A.class).hasNoCause().hasMessage("test");
        assertThat(e4).hasMissingDependenciesListThat().containsOnly(B.class);

        var e6 = AksateComponentHasMissingDependenciesException.forComponent(A.class);

        assertThat(e6).hasNoMissingDependenciesList();
        assertThat(e6).generatedForComponent(A.class).hasNoCause().hasMessageStartingWith(String.format("Component '%s' has some missing dependencies.", A.class.getName()));

        var e7 = AksateComponentHasMissingDependenciesException.forComponent(A.class, B.class);

        assertThat(e7).generatedForComponent(A.class).hasNoCause().hasMessageStartingWith(
                String.format("Component '%s' has some missing dependencies.", A.class.getName())
                        + String.format(" We found that necessary component '%s' is missing in current container.", B.class.getName()));
        assertThat(e7).hasMissingDependenciesListThat().containsOnly(B.class);
    }
}
