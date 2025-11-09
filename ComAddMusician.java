public class ComAddMusician implements Command {
	private Ensemble ensemble;
	private Musician musician;

	public ComAddMusician(Ensemble ensemble, Musician musician) {
		this.ensemble = ensemble;
		this.musician = musician;
	}

	@Override
	public void execute() {
		ensemble.addMusician(musician);
		ensemble.updateMusicianRole();
		System.out.println("Musician(" + musician.getMID() + ") is added.");
	}

	@Override
	public void undo() {
		
			ensemble.dropMusician(musician);
			// System.out.println("Musician(" + musician.getMID() + ") is removed.");

	}

	@Override
	public String description() {
		EnsembleFactory factory = EnsembleFactoryRegistry.getFactory(ensemble);
		return "Add Musician, " + musician.getMID() + ", " + musician.getName() + ", " + factory.getRoleName(musician.getRole());
	}

	@Override
	public Ensemble getEnsemble() {
		return ensemble;
	}
}
