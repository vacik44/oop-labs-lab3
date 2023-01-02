package oop.labs.lab3.aksate.exceptions;

@SuppressWarnings("unused")
public class AmbiguousInjectionConstructorsException extends InstantiationException
{
    public AmbiguousInjectionConstructorsException() {}
    public AmbiguousInjectionConstructorsException(String message) { super(message); }
    public AmbiguousInjectionConstructorsException(Throwable cause) { super(cause); }
}
