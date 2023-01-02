package oop.labs.lab3.aksate.exceptions;

import oop.labs.lab3.aksate.AksateException;

public class AksateComponentException extends AksateException
{
    private final Class<?> clazz;


    public AksateComponentException(Class<?> clazz) { this.clazz = clazz; }
    public AksateComponentException(Class<?> clazz, String message) { super(message); this.clazz = clazz; }
    public AksateComponentException(Class<?> clazz, Throwable cause) { super(cause); this.clazz = clazz; }
    public AksateComponentException(Class<?> clazz, String messsage, Throwable cause) { super(messsage, cause); this.clazz = clazz; }


    public Class<?> getComponentClazz() { return clazz; }
}
