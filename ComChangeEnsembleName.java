public class ComChangeEnsembleName implements Command {
    private Ensemble ensemble;
    private String newName;
    private String oldName;

    public ComChangeEnsembleName(Ensemble ensemble, String newName) {
        this.ensemble = ensemble;
        this.newName = newName;
        this.oldName = ensemble.getName();
    }

    @Override
    public void execute() {
        ensemble.setName(newName);
        System.out.println("Ensemble's name is updated.");
    }

    @Override
    public void undo() {
        ensemble.setName(oldName);
    }
    
}
