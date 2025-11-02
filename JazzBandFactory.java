import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class JazzBandFactory implements EnsembleFactory {
    @Override
    public Ensemble creatEnsemble(String id) {
        return new JazzBandEnsemble(id);
    }

    @Override
    public Musician createMusician(String id) {
        return new Musician(id);
    }

    @Override
    public boolean isValidRole(int role) {
        return role >= 1 && role <= 3;
    }

    @Override
    public String getRoleName(int role) {
        switch(role) {
            case 1:
                return "Pianist";
            case 2:
                return "Saxophonist";
            case 3:
                return "Drummer";

            default:
                return "Unknown Role";
        }
    }
    @Override
    public String showRoleName() {
        return "(1 = pianist | 2 = saxophonist | 3 = drummer)";   
    }
        @Override
    public int[] getAllRoles(Ensemble ensemble) {
        
        int[] roleValues = new int[ensemble.getClass().getDeclaredFields().length];
        int idx = 0;
        for(Field field : ensemble.getClass().getDeclaredFields()) {
            if (field.getName().contains("ROLE")) {
                field.setAccessible(true); // 如果是 private
                try {
                    roleValues[idx++] = (int) field.get(ensemble);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return roleValues;
    }
    @Override
    public String getEnsembleType() {
        return "Jazz Band Ensemble";
    }
     @Override
    public void showEnsembleInfo(Ensemble ensemble) {
    
     int[] roleValue = getAllRoles(ensemble);

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
