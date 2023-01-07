package oop.labs.lab3.aksate.exceptions;


public class AksateComponentBindingException extends AksateComponentException
{
    public AksateComponentBindingException(Class<?> clazz)
    {
        super(clazz);
    }

    public AksateComponentBindingException(Class<?> clazz, String message)
    {
        super(clazz, message);
    }

    public AksateComponentBindingException(Class<?> clazz, Throwable cause)
    {
        super(clazz, cause);
    }

    public AksateComponentBindingException(Class<?> clazz, String messsage, Throwable cause)
    {
        super(clazz, messsage, cause);
    }
}
