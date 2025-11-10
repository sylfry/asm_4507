import java.util.HashMap;

public class OrchestraFactory extends AbstractEnsembleFactory {
    public OrchestraFactory() {
        super(new int[]{1,2}, new HashMap<>());
        ROLE_NAMES.put(1, "Violinist");
        ROLE_NAMES.put(2, "Cellist");
    }

    @Override
    public Ensemble creatEnsemble(String id) {
        return new OrchestraEnsemble(id);
    }

    @Override
    public String getEnsembleType() {
        return "Orchestra Ensemble";
    }
}