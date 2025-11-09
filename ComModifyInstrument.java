public class ComModifyInstrument implements Command {
    private Musician musician;
    private int newInstrument;
    private int oldInstrument;
    private InstrumentMemento memento;
    private Ensemble ensemble;

    public ComModifyInstrument(Musician musician, int newInstrument) {
        this.musician = musician;
        this.newInstrument = newInstrument;
        this.oldInstrument = musician.getRole();
        this.ensemble = Registry.getMusicianEnsembleMap().get(musician.getMID());
    }

    @Override
    public void execute() {
        memento = new InstrumentMemento(musician);
        musician.setRole(newInstrument);
        ensemble.updateMusicianRole();
        System.out.println("Instrument is updated.");
    }

    @Override
    public void undo() {
        memento.restore();
    }
    public Ensemble getEnsemble() {
        return ensemble;
    }

    @Override                                                       
    public String description() {
        EnsembleFactory factory = EnsembleFactoryRegistry.getFactory(ensemble);
        return "Modify musician's instrument, " + musician.getMID() + ", " + factory.getRoleName(newInstrument);
      
    }
}
