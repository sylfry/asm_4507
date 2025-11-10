import java.util.List;
public interface CommandFactory {
    Command createAddMusicianCommand(Ensemble ensemble, Musician musician);
    Command createDeleteMusicianCommand(Ensemble ensemble, Musician musician);
    Command createChangeEnsembleNameCommand(Ensemble ensemble, String newName);
    Command createModifyInstrumentCommand(Musician musician, int newRole);
    Command createUndoCommand(Caretaker caretaker);
    Command createRedoCommand(Caretaker caretaker);
    Command createDisplayAllEnsemblesCommand(List<Ensemble> ensembles);
    Command createShowEnsembleCommand(Ensemble ensemble);
    Command createSetEnsembleCommand(Manager manager, Ensemble foundEnsemble);
    Command createShowListCommand(Caretaker caretaker);
    Command createEnsembleCommand(EnsembleFactory factory, String eid, String ename, List<Ensemble> ensembles);
  
    
}
