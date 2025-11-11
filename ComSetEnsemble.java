public class ComSetEnsemble implements Command {
     private final Manager manager;
     private final Ensemble foundEnsemble;
     private final Ensemble previousEnsemble;

     public ComSetEnsemble(Manager manager, Ensemble foundEnsemble) {
         this.manager = manager;
         // capture previous ensemble from manager at construction time
         this.previousEnsemble = manager.getCurrentEnsemble();
         this.foundEnsemble = foundEnsemble;
     }

    @Override
    public void execute() {
       
        if(foundEnsemble!=previousEnsemble) {
            System.out.println("Changed current ensemble to " + foundEnsemble.getEnsembleID() + ".");
        }
         manager.setCurrentEnsemble(foundEnsemble);
       
    }


    @Override
    public void undo() {
        manager.setCurrentEnsemble(previousEnsemble);
   
        System.out.println("Reverted current ensemble to " + (previousEnsemble == null ? "none" : previousEnsemble.getEnsembleID()) + ".");
    }
    @Override
    public Ensemble getEnsemble() {
        return foundEnsemble;
    }

    public Ensemble getPreviousEnsemble() {
        return previousEnsemble;
    }
    @Override
    public String description() {
        return "Set ensemble to " + (foundEnsemble == null ? "none" : foundEnsemble.getEnsembleID());
    }
    
}
