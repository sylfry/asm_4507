public interface Command {
    void execute();
    void undo();
    String description();

    default Ensemble getEnsemble() { return null; }
    default Ensemble getPreEnsemble() { return null; }
   
}
