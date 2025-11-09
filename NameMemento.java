public class NameMemento implements Memento {
    private final String originalName;
    private Ensemble ensemble;

    public NameMemento(Ensemble ensemble) {
        
        this.ensemble = ensemble;
        this.originalName = ensemble.getName();
    }

    // @Override
    // public String getOriginalState() {
    //     return originalState;
    // }
    @Override
    public void restore() {

        ensemble.setName(originalName);
    }
}


