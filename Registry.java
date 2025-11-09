import java.util.HashMap;
import java.util.Map;

public class Registry {
    private static final Map<Class<? extends Ensemble>, EnsembleFactory> registry = new HashMap<>();
    static {
        registry.put(OrchestraEnsemble.class, new OrchestraFactory());
        registry.put(JazzBandEnsemble.class, new JazzBandFactory());
     
    }
    private static final Map<String, Ensemble> musicianEnsembleMap = new HashMap<>();
   
    public static EnsembleFactory getFactory(Ensemble ensemble) {
        return registry.get(ensemble.getClass());
    }

   

    public static Map<String, Ensemble> getMusicianEnsembleMap() {
        return musicianEnsembleMap;
    }
}
