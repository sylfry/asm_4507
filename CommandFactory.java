import java.util.List;
public interface CommandFactory {
    Command createAddMusicianCommand(Ensemble ensemble, Musician musician);
    Command createDeleteMusicianCommand(Ensemble ensemble, Musician musician);
    Command createChangeEnsembleNameCommand(Ensemble ensemble, String newName);
    Command createModifyInstrumentCommand(Musician musician, int newRole);
    Command createUndoCommand();
    Command createRedoCommand();
    Command createDisplayAllEnsemblesCommand(List<Ensemble> ensembles);
    Command createShowEnsembleCommand(Ensemble ensemble);
    Command createSetEnsembleCommand(Ensemble currentEnsemble, Ensemble foundEnsemble);
    Command createShowListCommand();
    Command createEnsembleCommand(EnsembleFactory factory, String eid, String ename, List<Ensemble> ensembles);
  
    
}
