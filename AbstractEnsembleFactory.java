import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

public abstract class AbstractEnsembleFactory implements EnsembleFactory {
    protected final int[] ROLES;
    protected final Map<Integer, String> ROLE_NAMES;

    protected AbstractEnsembleFactory(int[] roles, Map<Integer, String> roleNames) {
        this.ROLES = roles == null ? new int[0] : roles.clone();
        this.ROLE_NAMES = roleNames;
    }

    @Override
    public Musician createMusician(String id) {
        return new Musician(id);
    }

    @Override
    public boolean isValidRole(int role) {
        for (int r : ROLES) {
            if (r == role) return true;
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
            if (i + 1 < ROLES.length) sb.append(" | ");
        }
        sb.append(")");
        return sb.toString();
    }

    @Override
    public int[] getAllRoles() {
        return ROLES.clone();
    }

    @Override
    public void showEnsembleInfo(Ensemble ensemble) {
        int[] roleValue = getAllRoles();

        for (int i = 0; i < roleValue.length; i++) {
            String roleName = getRoleName(roleValue[i]);
            System.out.println(roleName + ":");
            Iterator<Musician> iterator = ensemble.getMusicians();
            List<Musician> musicianList = new ArrayList<>();
            while (iterator.hasNext()) {
                musicianList.add(iterator.next());
            }
            boolean hasinstrument = false;
            for (Musician musician : musicianList) {
                if (musician.getRole() == roleValue[i]) {
                    System.out.println(musician.getMID() + ", " + musician.getName());
                    hasinstrument = true;
                }
            }
            if (!hasinstrument) {
                System.out.println("NIL");
            }
        }
    }

}
