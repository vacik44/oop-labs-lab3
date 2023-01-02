package oop.labs.lab3.aksate;

public class AksateException extends RuntimeException
{
    public AksateException() {}
    public AksateException(String message) { super(message); }
    public AksateException(Throwable cause) { super(cause); }
    public AksateException(String messsage, Throwable cause) { super(messsage, cause); }
}
