package oop.labs.lab3.aksate;

import oop.labs.lab3.aksate.exceptions.ComponentNotFoundException;
import org.fpm.di.Binder;
import org.fpm.di.Container;
import javax.inject.Singleton;
import java.util.HashMap;
import java.util.Map;

public class AksateContainer implements Container, Binder
{
    private final Map<AksateFeatures, Boolean> features;
    protected final Map<Class<?>, Binding> bindings;


    protected AksateContainer(Map<AksateFeatures, Boolean> features)
    {
        this.bindings = new HashMap<>();
        this.features = features;
    }


    @Override public <T> void bind(Class<T> clazz)
    {
        if (clazz == null) throw new NullPointerException();

        if (clazz.isAnnotationPresent(Singleton.class)) bindings.put(clazz, new SingletonBinding(clazz));
        else bindings.put(clazz, new DefaultBinding(clazz));
    }

    @Override public <T> void bind(Class<T> clazz, Class<? extends T> implementation)
    {
        if (clazz == null || implementation == null) throw new NullPointerException();

        bindings.put(clazz, new AbstractionBinding(implementation));
    }

    @Override public <T> void bind(Class<T> clazz, T instance)
    {
        if (clazz == null || instance == null) throw new NullPointerException();

        bindings.put(clazz, new SingletonBinding(instance));
    }


    @SuppressWarnings("unchecked")
    @Override public <T> T getComponent(Class<T> clazz)
    {
        if (clazz == null) throw new NullPointerException();

        var binding = bindings.get(clazz);
        if (binding == null) throw ComponentNotFoundException.forComponent(clazz);

        return (T) binding.getInstance();
    }


    protected abstract class Binding
    {
        public abstract Object getInstance();
    }

    protected class DefaultBinding extends Binding
    {
        private Class<?> clazz;

        protected DefaultBinding() {}
        public DefaultBinding(Class<?> clazz) { this.clazz = clazz; }

        @Override public Object getInstance()
        {
            return null;
        }
    }

    protected class SingletonBinding extends DefaultBinding
    {
        private Object instance;

        public SingletonBinding(Class<?> clazz) { super(clazz); }
        public SingletonBinding(Object instance) { this.instance = instance; }

        @Override public Object getInstance()
        {
            if (instance == null) { instance = super.getInstance(); }
            return instance;
        }
    }

    protected class AbstractionBinding extends Binding
    {
        private final Class<?> abstraction;

        public AbstractionBinding(Class<?> abstraction) { this.abstraction = abstraction; }

        @Override public Object getInstance()
        {
            return bindings.get(abstraction).getInstance();
        }
    }
}
