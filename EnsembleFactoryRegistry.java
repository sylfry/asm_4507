import java.util.HashMap;
import java.util.Map;

public class EnsembleFactoryRegistry {
    private static final Map<Class<? extends Ensemble>, EnsembleFactory> registry = new HashMap<>();
    static {
        registry.put(OrchestraEnsemble.class, new OrchestraFactory());
        registry.put(JazzBandEnsemble.class, new JazzBandFactory());
     
    }
    public static EnsembleFactory getFactory(Ensemble ensemble) {
        return registry.get(ensemble.getClass());
    }
}
