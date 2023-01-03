package oop.labs.lab3.mimics;

public class DependsOnAB
{
    private final SimpleA a;
    private final SimpleB b;


    public DependsOnAB(SimpleA a, SimpleB b)
    {
        this.a = a;
        this.b = b;
    }


    public SimpleA getA() { return a; }
    public SimpleB getB() { return b; }
}
