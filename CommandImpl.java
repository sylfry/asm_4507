import java.util.List;
public  class CommandImpl implements CommandFactory {
    @Override
    public Command createAddMusicianCommand(Ensemble ensemble, Musician musician) {
        return new ComAddMusician(ensemble, musician);
    }   
    @Override
    public Command createDeleteMusicianCommand(Ensemble ensemble, Musician musician) {
        return new ComDeleteMusician(ensemble, musician);
    }
    @Override
    public Command createChangeEnsembleNameCommand(Ensemble ensemble, String newName) {
        return new ComChangeEnsembleName(ensemble, newName);
    }
    @Override
    public Command createModifyInstrumentCommand(Musician musician, int newRole) {
        return new ComModifyInstrument(musician, newRole);
    }
    @Override
    public Command createUndoCommand() {
        return new ComUndo();
    }
    @Override
    public Command createRedoCommand() {
        return new ComRedo();
    }
    @Override
    public Command createDisplayAllEnsemblesCommand(List<Ensemble> ensembles) {
        return new ComDisplayAllEnsembles(ensembles);
    }
    @Override
    public Command createShowEnsembleCommand(Ensemble ensemble) {
        return new ComShowEnsemble(ensemble);
    }
    @Override
    public Command createSetEnsembleCommand(Ensemble currentEnsemble, Ensemble foundEnsemble) {
        return new ComSetEnsemble(currentEnsemble, foundEnsemble);
    }
    @Override
    public Command createShowListCommand() {
        return new ComShowList();
    }
    @Override
    public Command createEnsembleCommand(EnsembleFactory factory, String eid, String ename, List<Ensemble> ensembles) {
        return new ComCreateEnsemble(factory, eid, ename, ensembles);
    }   
   
    
}
