package oop.labs.lab3.mimics;

import javax.inject.Inject;

@SuppressWarnings("unused")
public class HasMultipleInjectConstructors
{
    private final AnnotatedSingleton singleton;


    @Inject private HasMultipleInjectConstructors(AnnotatedSingleton singleton) { this.singleton = singleton; }
    @Inject private HasMultipleInjectConstructors(ExternalB ignored) { this.singleton = null; }


    public AnnotatedSingleton getSingleton() { return singleton; }
}
