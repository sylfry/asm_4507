import java.util.List;
public class ComCreateEnsemble implements Command {
    private EnsembleFactory factory;
    private List<Ensemble> ensembles;
    private String ensembleId;
    private String ensembleName;
    private Ensemble createdEnsemble;

   public ComCreateEnsemble(EnsembleFactory factory, String ensembleId, String ensembleName, List<Ensemble> ensembles) {
        this.factory = factory;
        this.ensembleId = ensembleId;
        this.ensembleName = ensembleName;
        this.ensembles = ensembles;
    }

    @Override
    public void execute() {
        createdEnsemble = factory.creatEnsemble(ensembleId);
        createdEnsemble.setName(ensembleName);
        ensembles.add(createdEnsemble);
        if (factory instanceof OrchestraFactory) {
            System.out.println("Orchestra ensemble is created.");
        } else if (factory instanceof JazzBandFactory) {
            System.out.println("Jazz band ensemble is created.");
        }
        System.out.println("Current ensemble is changed to " + ensembleId + ".");
    }

    @Override
    public void undo() {
        ensembles.remove(createdEnsemble);
        System.out.println("Ensemble is removed.");
    }
}
