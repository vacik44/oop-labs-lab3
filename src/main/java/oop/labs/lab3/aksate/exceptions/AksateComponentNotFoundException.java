package oop.labs.lab3.aksate.exceptions;

public class AksateComponentNotFoundException extends AksateComponentException
{
    public AksateComponentNotFoundException(Class<?> clazz)
    {
        super(clazz);
    }

    public AksateComponentNotFoundException(Class<?> clazz, String message)
    {
        super(clazz, message);
    }


    public static AksateComponentNotFoundException forComponent(Class<?> clazz)
    {
        var msg = String.format("Component binding '%s' seems to be missing in handled container.", clazz.getName());

        return new AksateComponentNotFoundException(clazz, msg);
    }
}
