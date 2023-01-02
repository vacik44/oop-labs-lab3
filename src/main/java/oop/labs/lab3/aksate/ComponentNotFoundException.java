package oop.labs.lab3.aksate;

@SuppressWarnings("unused")
public class ComponentNotFoundException extends AksateException
{
    public ComponentNotFoundException() {}
    public ComponentNotFoundException(String message) { super(message); }
    public ComponentNotFoundException(Throwable cause) { super(cause); }


    public static ComponentNotFoundException forComponent(Class<?> clazz)
    {
        return new ComponentNotFoundException(
                String.format("Component binding '%s' seems to be missing in handled container.", clazz.getName()));
    }
}
