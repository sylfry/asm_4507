import java.util.HashMap;

public class JazzBandFactory extends AbstractEnsembleFactory {
    public JazzBandFactory() {
        super(new int[]{1,2,3}, new HashMap<>());
        ROLE_NAMES.put(1, "Pianist");
        ROLE_NAMES.put(2, "Saxophonist");
        ROLE_NAMES.put(3, "Drummer");
    }

    @Override
    public Ensemble creatEnsemble(String id) {
        return new JazzBandEnsemble(id);
    }

    @Override
    public String getEnsembleType() {
        return "Jazz Band Ensemble";
    }
}
