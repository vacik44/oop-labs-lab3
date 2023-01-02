package oop.labs.lab3.aksate.exceptions;

@SuppressWarnings("unused")
public class AksateComponentAccessibleConstructorsNotFoundException extends AksateComponentBindingException
{
    public AksateComponentAccessibleConstructorsNotFoundException(Class<?> clazz)
    {
        super(clazz);
    }

    public AksateComponentAccessibleConstructorsNotFoundException(Class<?> clazz, String message)
    {
        super(clazz, message);
    }


    public static AksateComponentAccessibleConstructorsNotFoundException forComponent(Class<?> clazz)
    {
        var msg = String.format("Unable to found accessible constructors for component '%s'.", clazz.getName());
        msg += "This exception may be caused by some internal container exception " +
                "or in case when component has only private constructors and Aksate feature" +
                "IGNORE_CONSTRUCTORS_ACCESSIBILITY disabled.";

        return new AksateComponentAccessibleConstructorsNotFoundException(clazz, msg);
    }
}
