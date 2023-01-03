package oop.labs.lab3.mimics;

public class ExtendsDependsOnAB extends DependsOnAB
{
    private final SimpleC c;


    public ExtendsDependsOnAB(SimpleA a, SimpleB b, SimpleC c)
    {
        super(a, b);
        this.c = c;
    }


    public SimpleC getC() { return c; }
}
