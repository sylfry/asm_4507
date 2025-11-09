public class InstrumentMemento implements Memento{
    private final int originalInstrument;
    private Musician musician;

    public InstrumentMemento(Musician musician) {
        
        this.musician = musician;
        this.originalInstrument = musician.getRole();
    }
    // @Override
    // public  getOriginalState() {
    //     return originalInstrument;
    // }
    @Override
    public void restore() {
        musician.setRole(originalInstrument);
}
}