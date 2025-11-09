public class ComChangeEnsembleName implements Command {
    private Ensemble ensemble;
    private String newName;
    //private String oldName;
    private NameMemento memento;

    public ComChangeEnsembleName(Ensemble ensemble, String newName) {
        this.ensemble = ensemble;
        this.newName = newName;
       // this.oldName = ensemble.getName();
    }

    @Override
    public void execute() {
        memento = new NameMemento(ensemble);
        ensemble.setName(newName);
        System.out.println("Ensemble's name is updated.");
    }

    @Override
    public void undo() {
        memento.restore();
    }
    @Override                                                       
    public String description() {
       
        return "Change ensemble's name, " + ensemble.getEnsembleID() + ", "+ newName;
}
    public Ensemble getEnsemble() {
        return ensemble;
    }
}
