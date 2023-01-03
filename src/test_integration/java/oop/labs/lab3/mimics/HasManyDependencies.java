package oop.labs.lab3.mimics;

public class HasManyDependencies
{
    private final SimpleA a;
    private final SimpleB b;
    private final SimpleC c;
    private final DependsOnAB ab;
    private final AnnotatedSingleton ac;


    public HasManyDependencies(SimpleA a, SimpleB b, SimpleC c, DependsOnAB ab, AnnotatedSingleton ac) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.ab = ab;
        this.ac = ac;
    }


    public SimpleA getA() { return a; }
    public SimpleB getB() { return b; }
    public SimpleC getC() { return c; }
    public DependsOnAB getAb() { return ab; }
    public AnnotatedSingleton getAc() { return ac; }
}
