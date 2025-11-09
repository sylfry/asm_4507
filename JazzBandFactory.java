import java.util.HashMap;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class JazzBandFactory implements EnsembleFactory {
    private final int[] ROLES = {1, 2, 3};
      private final Map<Integer,String> ROLE_NAMES = new HashMap<>();

    public JazzBandFactory() {
        ROLE_NAMES.put(1, "Pianist");
        ROLE_NAMES.put(2, "Saxophonist");
        ROLE_NAMES.put(3, "Drummer");
    }

    @Override
    public Ensemble creatEnsemble(String id) {
        return new JazzBandEnsemble(id);
    }

    @Override
    public Musician createMusician(String id) {
        Musician m = new Musician(id);
        return m;
    }

    @Override
    public boolean isValidRole(int role) {
        for(int r: ROLES) {
            if(r == role) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String getRoleName(int role) {
       return ROLE_NAMES.getOrDefault(role, "Unknown Role");
    }
    @Override
    public String showRoleName() {
          StringBuilder sb = new StringBuilder("(");
        for (int i = 0; i < ROLES.length; i++) {
            int r = ROLES[i];
            sb.append(r).append(" = ").append(getRoleName(r));
            if (i+1 < ROLES.length) sb.append(" | ");
        }
        sb.append(")");
        return sb.toString(); 
    }
        @Override
    public int[] getAllRoles() {
             return ROLES.clone();  //ROLES是数组，引用类型属于可变对象，返回副本更安全
    }
    @Override
    public String getEnsembleType() {
        return "Jazz Band Ensemble";
    }
    @Override
    public void showEnsembleInfo(Ensemble ensemble) {

     int[] roleValue = getAllRoles();

     for(int i=0;i<roleValue.length;i++) {
        String roleName = getRoleName(roleValue[i]);
        System.out.println(roleName + ":");
        Iterator<Musician> iterator = ensemble.getMusicians();
        List<Musician> musicianList = new ArrayList<>();
        while (iterator.hasNext()) {
         musicianList.add(iterator.next());
        }
        boolean hasinstrument = false;
        for(Musician musician : musicianList) {
            if(musician.getRole() == roleValue[i]) {
                System.out.println(musician.getMID()+", " + musician.getName());
                hasinstrument = true;
            }
        }
        if(!hasinstrument) {
            System.out.println("NIL");
        }
    }
    }
}
