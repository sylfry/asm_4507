import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;


public class OrchestraEnsemble extends Ensemble {
    private final int VIOLINIST_ROLE = 1;
    private final int CELLIST_ROLE = 2;

    public OrchestraEnsemble(String eID){
        super(eID);
    }

    public void updateMusicianRole(){
      
        
    }
    public void showEnsemble(){
                
        OrchestraFactory factory = new OrchestraFactory();
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

