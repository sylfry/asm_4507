import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class EnsembleFactoryRegistry {
    private static final Map<Class<? extends Ensemble>, EnsembleFactory> registry = new HashMap<>();
    private static final Map<String, EnsembleFactory> keyRegistry = new LinkedHashMap<>();
    static {
        registry.put(OrchestraEnsemble.class, new OrchestraFactory());
        registry.put(JazzBandEnsemble.class, new JazzBandFactory());
        // register short keys for interactive selection
        keyRegistry.put("o", new OrchestraFactory());
        keyRegistry.put("j", new JazzBandFactory());
     
    }
    public static EnsembleFactory getFactory(Ensemble ensemble) {
        return registry.get(ensemble.getClass());
    }

    public static EnsembleFactory getFactoryByKey(String key) {
        if (key == null) return null;
        return keyRegistry.get(key.toLowerCase());
    }

    public static void registerFactory(String key, EnsembleFactory factory) {
        if (key == null || factory == null) return;
        keyRegistry.put(key.toLowerCase(), factory);
    }

    public static Set<String> getAvailableKeys() {
        return keyRegistry.keySet();
    }
}
