public class ComModifyInstrument implements Command {
    private Musician musician;
    private int newInstrument;
    private int oldInstrument;

    public ComModifyInstrument(Musician musician, int newInstrument) {
        this.musician = musician;
        this.newInstrument = newInstrument;
        this.oldInstrument = musician.getRole();
    }

    @Override
    public void execute() {
        musician.setRole(newInstrument);
        System.out.println("Instrument is updated.");
    }

    @Override
    public void undo() {
        musician.setRole(oldInstrument);
    }
    
}
