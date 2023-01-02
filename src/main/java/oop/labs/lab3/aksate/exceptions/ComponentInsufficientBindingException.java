package oop.labs.lab3.aksate.exceptions;

@SuppressWarnings("unused")
public class ComponentInsufficientBindingException extends InstantiationException
{
    public ComponentInsufficientBindingException() {}
    public ComponentInsufficientBindingException(String message) { super(message); }
    public ComponentInsufficientBindingException(Throwable cause) { super(cause); }


    public static ComponentInsufficientBindingException forComponent(Class<?> clazz)
    {
        return new ComponentInsufficientBindingException(
                String.format("Component '%s' has insufficient binding in handled container. " +
                              "There seems to be missing some dependencies for this component.", clazz.getName()));
    }
}
