import java.util.Map;
import java.util.LinkedHashMap;
import java.util.List;

public class ComDisplayAllEnsembles implements Command {
    private List<Ensemble> ensembles;

    public ComDisplayAllEnsembles(List<Ensemble> ensembles) {
        this.ensembles = ensembles;
    }

    @Override
    public void execute() {
        Map<String, StringBuilder> typeMap = new LinkedHashMap<>();
        for (Ensemble ensemble : ensembles) {
            EnsembleFactory factory = EnsembleFactoryRegistry.getFactory(ensemble);
            String typeName = factory.getEnsembleType();
            typeMap.putIfAbsent(typeName, new StringBuilder(typeName + " "));
            typeMap.get(typeName).append(ensemble.getName())
                                 .append("(").append(ensemble.getEnsembleID()).append(") ");
        }
        for (StringBuilder sb : typeMap.values()) {
            System.out.println(sb.toString().trim());
        }
    }
       
    @Override
    public void undo() {
        // No undo action for displaying all ensembles
    }
    
}
