public class ComDeleteMusician implements UndoableCommand {
    
    private Musician deletedMusician;
    private Ensemble currentEnsemble;

    public ComDeleteMusician(Ensemble currentEnsemble, Musician deletedMusician) {
        this.currentEnsemble = currentEnsemble;
        this.deletedMusician = deletedMusician;
    }

    @Override
    public void execute() {
        currentEnsemble.dropMusician(deletedMusician);
        System.out.println("\nMusician is deleted.");
    }

    @Override
    public void undo() {
        currentEnsemble.addMusician(deletedMusician);
        //System.out.println("\nRestored musician " + deletedMusician.getMID() + " to ensemble " + currentEnsemble.getEnsembleID() + ".");
    }
    public Ensemble getEnsemble() {
        return currentEnsemble;
    }

    @Override                                                       
    public String description() {
     
        return "Delete musician, " + deletedMusician.getMID(); 
}
}