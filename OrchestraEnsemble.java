public class OrchestraEnsemble extends Ensemble {
    private final int VIOLINIST_ROLE = 1;
    private final int CELLIST_ROLE = 2;

    public OrchestraEnsemble(String eID){
        super(eID);
    }

    public void updateMusicianRole(){
        EnsembleFactory factory = EnsembleFactoryRegistry.getFactory(this);
        java.util.Iterator<Musician> it = super.getMusicians();
        boolean anyInvalid = false;
        while (it.hasNext()) {
            Musician m = it.next();
            int role = m.getRole();
            if (!factory.isValidRole(role)) {
                System.out.println("Warning: Musician " + m.getMID() + " has invalid role " + role + ", resetting to 0.");
                m.setRole(0);
                anyInvalid = true;
            }
        }
        // if (!anyInvalid) {
        //     System.out.println("All musician roles are valid for this orchestra.");
        // }
    }
    @Override
    public void showEnsemble(){
                
        // OrchestraFactory factory = new OrchestraFactory();
        EnsembleFactory factory = EnsembleFactoryRegistry.getFactory(this);
        System.out.println(factory.getEnsembleType() + " " + super.getName()+" (" + super.getEnsembleID() + ")");
        factory.showEnsembleInfo(this);
        //int[] roleValue = factory.getAllRoles(this);
        //  for(int i=0;i<roleValue.length;i++) {
        //     String roleName = factory.getRoleName(roleValue[i]);
        //     System.out.println(roleName + ":");
        //     Iterator<Musician> iterator = super.getMusicians();
        //     List<Musician> musicianList = new ArrayList<>();
        //     while (iterator.hasNext()) {
        //      musicianList.add(iterator.next());
        //     }
        //     boolean hasinstrument = false;
        //     for(Musician musician : musicianList) {
        //         if(musician.getRole() == roleValue[i]) {
        //             System.out.println(musician.getMID()+", " + musician.getName());
        //             hasinstrument = true;
        //         }
        //     }
        //     if(!hasinstrument) {
        //         System.out.println("NIL");
        //     }
        // }
        }
    }

