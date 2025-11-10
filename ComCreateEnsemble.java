import java.util.List;
public class ComCreateEnsemble implements UndoableCommand {
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
        if (createdEnsemble == null) {
            createdEnsemble = factory.creatEnsemble(ensembleId);
            createdEnsemble.setName(ensembleName);
        }
        // Ensure the same instance is (re-)added to the ensembles list on redo
        if (!ensembles.contains(createdEnsemble)) {
            ensembles.add(createdEnsemble);
        }
        System.out.println("\n" + factory.getEnsembleType() + " " + createdEnsemble.getEnsembleID() + " is created.");
       // System.out.println("Current ensemble is changed to " + ensembleId + ".");
    }

    @Override
    public void undo() {
        //只是从列表中移除，并不销毁对象
        ensembles.remove(createdEnsemble);
       // System.out.println("Ensemble is removed.");
    }
    public Ensemble getCreatedEnsemble() {
        return createdEnsemble;
    }
    @Override
    public Ensemble getEnsemble() {
        return createdEnsemble;
    }
    @Override                                                       
    public String description() {
        return "Create " +factory.getEnsembleType()+", "+ ensembleId + ", " + ensembleName;
}
}