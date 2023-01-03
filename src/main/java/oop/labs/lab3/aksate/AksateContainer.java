package oop.labs.lab3.aksate;

import oop.labs.lab3.aksate.exceptions.AksateComponentAccessibleConstructorsNotFoundException;
import oop.labs.lab3.aksate.exceptions.AksateComponentHasMissingDependenciesException;
import oop.labs.lab3.aksate.exceptions.AksateComponentInstantiationException;
import oop.labs.lab3.aksate.exceptions.AksateComponentNotFoundException;
import org.fpm.di.Binder;
import org.fpm.di.Container;
import javax.inject.Inject;
import javax.inject.Singleton;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

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

        bindings.put(clazz, new AbstractionBinding(clazz, implementation));
    }

    @Override public <T> void bind(Class<T> clazz, T instance)
    {
        if (clazz == null || instance == null) throw new NullPointerException();

        bindings.put(clazz, new SingletonBinding(clazz, instance));
    }


    @SuppressWarnings("unchecked")
    @Override public <T> T getComponent(Class<T> clazz)
    {
        return (T) getComponentObj(clazz);
    }

    public Object getComponentObj(Class<?> clazz)
    {
        if (clazz == null) throw new NullPointerException();

        var binding = bindings.get(clazz);
        if (binding == null) throw AksateComponentNotFoundException.forComponent(clazz);

        return binding.getInstance();
    }


    public boolean containsComponent(Class<?> clazz) { return bindings.containsKey(clazz); }
    public boolean containsComponents(Collection<Class<?>> classes) { return bindings.keySet().containsAll(classes); }
    public boolean containsComponents(Class<?>[] classes) { return Arrays.stream(classes).allMatch(this::containsComponent); }


    protected static abstract class Binding
    {
        protected final Class<?> clazz;
        protected Binding(Class<?> clazz) { this.clazz = clazz; }
        public abstract Object getInstance();
    }

    protected class DefaultBinding extends Binding
    {
        private final List<Constructor<?>> candidateConstructors;


        public DefaultBinding(Class<?> clazz)
        {
            super(clazz);
            candidateConstructors = getCandidateConstructors(clazz);
        }

        protected DefaultBinding(Class<?> clazz, boolean initialize)
        {
            super(clazz);
            candidateConstructors = initialize ? getCandidateConstructors(clazz) : null;
        }


        protected void aggregateConstructor(List<Constructor<?>> lst, Constructor<?> constructor)
        {
            if (constructor.canAccess(null) ||
                    (features.get(AksateFeatures.IGNORE_CONSTRUCTORS_ACCESSIBILITY)
                    && constructor.trySetAccessible()))
                lst.add(constructor);
        }

        protected List<Constructor<?>> getCandidateConstructors(Class<?> clazz)
        {
            var constructors = new ArrayList<Constructor<?>>();

            for (var constructor: clazz.getDeclaredConstructors())
                if (constructor.isAnnotationPresent(Inject.class))
                    aggregateConstructor(constructors, constructor);

            if (!constructors.isEmpty()) return constructors;

            for (var constructor: clazz.getDeclaredConstructors())
                aggregateConstructor(constructors, constructor);

            if (!constructors.isEmpty()) return constructors;
            throw AksateComponentAccessibleConstructorsNotFoundException.forComponent(clazz);
        }


        @Override public Object getInstance()
        {
            var constructor = getInjectionConstructor(candidateConstructors);

            try
            {
                if (constructor.getParameterCount() == 0) return constructor.newInstance();

                var parameterTypes = constructor.getParameterTypes();
                var parameterValues = new Object[parameterTypes.length];

                for (var i = 0; i < constructor.getParameterCount(); i++)
                    parameterValues[i] = getComponentObj(parameterTypes[i]);

                return constructor.newInstance(parameterValues);
            }
            catch (AksateComponentNotFoundException | AksateComponentHasMissingDependenciesException e)
            {
                throw AksateComponentHasMissingDependenciesException.forComponent(clazz, e.getComponentClazz());
            }
            catch (InvocationTargetException | InstantiationException | IllegalAccessException e)
            {
                throw AksateComponentInstantiationException.forComponent(clazz, e);
            }
        }

        protected Constructor<?> getInjectionConstructor(List<Constructor<?>> constructors)
        {
            for (var constructor: constructors)
                if (containsComponents(constructor.getParameterTypes()))
                    return constructor;

            throw AksateComponentHasMissingDependenciesException.forComponent(clazz);
        }
    }

    protected class SingletonBinding extends DefaultBinding
    {
        private Object instance;


        public SingletonBinding(Class<?> clazz) { super(clazz); }
        public SingletonBinding(Class<?> clazz, Object instance)
        {
            super(clazz, false);
            this.instance = instance;
        }


        @Override public Object getInstance()
        {
            if (instance == null) { instance = super.getInstance(); }
            return instance;
        }
    }

    protected class AbstractionBinding extends Binding
    {
        private final Class<?> abstraction;


        public AbstractionBinding(Class<?> clazz, Class<?> abstraction)
        {
            super(clazz);
            this.abstraction = abstraction;
        }


        @Override public Object getInstance()
        {
            try
            {
                return getComponentObj(abstraction);
            }
            catch (AksateComponentNotFoundException e)
            {
                throw AksateComponentHasMissingDependenciesException.forComponent(clazz, e.getComponentClazz());
            }
        }
    }
}
