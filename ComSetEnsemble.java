public class ComSetEnsemble implements Command {
    
    private Ensemble foundEnsemble;
    private Ensemble previousEnsemble;
    public ComSetEnsemble(Ensemble previousEnsemble, Ensemble foundEnsemble) {
       this.previousEnsemble = previousEnsemble;
       this.foundEnsemble = foundEnsemble;
    }

    @Override
    public void execute() {
        
        System.out.println("\nChanged current ensemble to " + foundEnsemble.getEnsembleID() + ".");
    }


    @Override
    public void undo() {

        System.out.println("\nReverted current ensemble to " + previousEnsemble.getEnsembleID() + ".");
    }
    
    @Override                                                       
    public String description() {
        return "";
    }

    @Override
    public Ensemble getEnsemble() {
        return foundEnsemble;
    }
}
