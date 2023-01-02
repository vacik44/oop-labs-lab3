package oop.labs.lab3.aksate.exceptions;

@SuppressWarnings("unused")
public class AksateComponentInstantiationException extends AksateComponentException
{
    public AksateComponentInstantiationException(Class<?> clazz)
    {
        super(clazz);
    }

    public AksateComponentInstantiationException(Class<?> clazz, String message)
    {
        super(clazz, message);
    }

    public AksateComponentInstantiationException(Class<?> clazz, Throwable cause)
    {
        super(clazz, cause);
    }

    public AksateComponentInstantiationException(Class<?> clazz, String messsage, Throwable cause)
    {
        super(clazz, messsage, cause);
    }


    public static AksateComponentInstantiationException forComponent(Class<?> clazz, Throwable cause)
    {
        var msg = "Aksate container internal exception was occurred";
        msg += String.format(" instantiation of component '%s'", clazz.getName());

        return new AksateComponentInstantiationException(clazz, msg, cause);
    }
}
