package oop.labs.lab3.mimics;

import javax.inject.Singleton;

@Singleton
public class AnnotatedSingleton
{
    private final SimpleA a;
    private final SimpleC c;


    public AnnotatedSingleton(SimpleA a, SimpleC c)
    {
        this.a = a;
        this.c = c;
    }


    public SimpleC getC() { return c; }
    public SimpleA getA() { return a; }
}
