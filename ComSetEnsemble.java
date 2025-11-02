public class ComSetEnsemble implements Command {
    
    private Ensemble foundEnsemble;
    private Ensemble previousEnsemble;
    public ComSetEnsemble(Ensemble previousEnsemble, Ensemble foundEnsemble) {
       this.previousEnsemble = previousEnsemble;
       this.foundEnsemble = foundEnsemble;
    }

    @Override
    public void execute() {
        
        System.out.println("Changed current ensemble to " + foundEnsemble.getEnsembleID() + ".");
    }


    @Override
    public void undo() {
       
        System.out.println("Reverted current ensemble to " + previousEnsemble.getEnsembleID() + ".");
    }
    
}
