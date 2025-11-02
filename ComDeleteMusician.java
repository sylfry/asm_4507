public class ComDeleteMusician implements Command {
    
    private Musician deletedMusician;
    private Ensemble currentEnsemble;

    public ComDeleteMusician(Ensemble currentEnsemble, Musician deletedMusician) {
        this.currentEnsemble = currentEnsemble;
        this.deletedMusician = deletedMusician;
    }

    @Override
    public void execute() {
        currentEnsemble.dropMusician(deletedMusician);
        System.out.println("Musician is deleted.");
    }

    @Override
    public void undo() {
        currentEnsemble.addMusician(deletedMusician);
        System.out.println("Restored musician " + deletedMusician.getMID() + " to ensemble " + currentEnsemble.getEnsembleID() + ".");
    }
    
}
