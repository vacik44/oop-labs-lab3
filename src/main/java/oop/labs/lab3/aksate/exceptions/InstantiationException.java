package oop.labs.lab3.aksate.exceptions;

import oop.labs.lab3.aksate.AksateException;

public class InstantiationException extends AksateException
{
    public InstantiationException() {}
    public InstantiationException(String message) { super(message); }
    public InstantiationException(Throwable cause) { super(cause); }
}
