package oop.labs.lab3.aksate;

import org.fpm.di.Configuration;
import org.fpm.di.Container;
import org.fpm.di.Environment;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings({"unused", "UnusedReturnValue"})
public class AksateEnvironment implements Environment
{
    private final Map<AksateFeatures, Boolean> features;


    public AksateEnvironment()
    {
        features = new HashMap<>();

        for (var feature: AksateFeatures.class.getEnumConstants())
            features.put(feature, false);

        setup();
    }

    protected void setup()
    {
        this.enable(AksateFeatures.IGNORE_CONSTRUCTORS_ACCESSIBILITY);
    }


    public boolean state(AksateFeatures feature) { return features.get(feature); }

    public AksateEnvironment enable(AksateFeatures feature) { return set(feature, true); }
    public AksateEnvironment disable(AksateFeatures feature) { return set(feature, false); }

    public final AksateEnvironment set(AksateFeatures feature, boolean state)
    {
        features.put(feature, state);
        return this;
    }


    @Override public Container configure(Configuration configuration)
    {
        var container = new AksateContainer(new HashMap<>(features));
        configuration.configure(container);
        return container;
    }
}
