import java.util.List;
import javax.swing.text.Caret;
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
    public Command createUndoCommand(Caretaker caretaker) {
        return new ComUndo(caretaker);
    }
    @Override
    public Command createRedoCommand(Caretaker caretaker) {
        return new ComRedo(caretaker);
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
    public Command createShowListCommand(Caretaker caretaker) {
        return new ComShowList(caretaker);
    }
    @Override
    public Command createEnsembleCommand(EnsembleFactory factory, String eid, String ename, List<Ensemble> ensembles) {
        return new ComCreateEnsemble(factory, eid, ename, ensembles);
    }   
   
    
}
