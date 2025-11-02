public class ComAddMusician implements Command {
	private Ensemble ensemble;

	private Musician musician;

	public ComAddMusician(Ensemble ensemble, Musician musician) {
		this.ensemble = ensemble;
		this.musician= musician;
		
	}

	@Override
	public void execute() {
	
		ensemble.addMusician(musician);
		System.out.println("Musician is added.");
	
	}

	@Override
	public void undo() {
		if (musician != null) {
			ensemble.dropMusician(musician);
			System.out.println("Musician is removed.");
		}
	}
}
