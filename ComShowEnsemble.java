public class ComShowEnsemble implements Command {
    private Ensemble ensemble;

    public ComShowEnsemble(Ensemble ensemble) {
        this.ensemble = ensemble;
    }

    @Override
    public void execute() {
       ensemble.showEnsemble();
    }
    @Override
    public void undo() {
        // No undo action for showing ensemble
}
}