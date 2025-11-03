import java.util.List;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Iterator;
import java.util.Map;
import java.util.HashMap;

public class MEMSManager {
    
    private final List<Ensemble> ensembles = new LinkedList<>();
    private Ensemble currentEnsemble;
    private final CommandFactory commandFactory = new CommandImpl();
    private Caretaker caretaker;
    private EnsembleFactory factory;
    private Map<String, Ensemble> musicianEnsembleMap = new HashMap<>();
    private final List<Command> undoList = new LinkedList<>();
    private final List<Command> redoList = new LinkedList<>();
    private final List<Command> doList = new LinkedList<>();
    public static void main(String args[]) {
         MEMSManager manager = new MEMSManager();  
        
             
        System.out.println("Music Ensembles Management System(MEMS)");
        System.out.println("""
                            c = create ensembles, s = set current ensemble, a = add musician,
                            m = modify musician's instrument, d = delete musician, se = show ensemble, sa = display all emsembles,
                            cn = change ensemble's name, u = undo, r = redo, l = list undo/redo, x = exit system""");
        
        manager.chooseFunction();      

    }
    public void chooseFunction() {
        Scanner scanner = new Scanner(System.in);
        if(currentEnsemble!=null) {
                System.out.println("The current ensemble is " + currentEnsemble.getEnsembleID()+ " " + currentEnsemble.getName()+ ".");
            }
        System.out.print("Please enter command [ c | s | a | m | d | se | sa | cn | u | r | l | x ] :- ");
        String input = scanner.nextLine().trim().toLowerCase();
        while (!input.equals("x")) {           
            switch (input) {
                case "c":
                    handleCreateEnsemble(scanner);             
                    break;
                case "s":
                    handleSetEnsemble(scanner);
                    break;
                case "a":                  
                    handleAddMusician(scanner);
                    break;                                          
                case "m":
                    handleModifyMusician(scanner);
                    break;
                case "d":
                    handleDeleteMusician(scanner);
                    break;
                case "se":
                    handleShowEnsemble();
                    break;
                case "sa":
                    commandFactory.createDisplayAllEnsemblesCommand(ensembles).execute();
                    break;
                case "cn":
                    handleChangeEnsembleName(scanner);
                    break;
                case "u":
                    // Undo
                    break;
                case "r":
                    // Redo
                    break;
                case "l":
                    // List undo/redo
                    break;
                default:
                    System.out.println("Invalid command. Please try again.");
            }
            if(currentEnsemble!=null) {
                //   if(currentEnsemble instanceof OrchestraEnsemble) {
                //     factory = new OrchestraFactory();
                // } else if (currentEnsemble instanceof JazzBandEnsemble) {
                //     factory = new JazzBandFactory();
                // }
                factory = EnsembleFactoryRegistry.getFactory(currentEnsemble);
                System.out.println("\nThe current ensemble (" + factory.getEnsembleType() + ") is " + currentEnsemble.getEnsembleID() + " " + currentEnsemble.getName() + ".");
            }
            System.out.print("\nPlease enter command [ c | s | a | m | d | se | sa | cn | u | r | l | x ] :- ");
            input = scanner.nextLine().trim().toLowerCase();
        }
    }
    public void handleCreateEnsemble(Scanner scanner) {
        // Implementation for creating ensemble
         System.out.print("Enter music type(o = orchestra | j = jazz band) :-");
                    String type = scanner.nextLine().trim().toLowerCase();
                    System.out.print("Ensemble ID:- ");
                    String eid = scanner.nextLine().trim();
                    System.out.print("Ensemble Name:- ");
                    String ename = scanner.nextLine().trim();
                    if ("j".equals(type)) {
                        commandFactory.createEnsembleCommand(new JazzBandFactory(), eid, ename, ensembles).execute();
                    } else if ("o".equals(type)) {
                        commandFactory.createEnsembleCommand(new OrchestraFactory(), eid, ename, ensembles).execute();
                    } 
                    else {
                        System.out.println("Invalid music type. Please try again.");
                        //continue;
                        return;
                    }
                    currentEnsemble = ensembles.get(ensembles.size() - 1); 
    }
    public void handleAddMusician(Scanner scanner) {
      
         if (currentEnsemble == null) {
                        System.out.println("No current ensemble is set. Please create or set an ensemble first.");
                        //break;
                        return;
                    }
                    System.out.print("Please input musician information(id, name):- ");
                    String inputMusician = scanner.nextLine().trim();
                    String[] parts = inputMusician.split(",");
                    String musicianId = parts[0].trim();
                    String musicianName = parts[1].trim();
                    int role=0;
                    do {                      
                    System.out.print("Instrument " + factory.showRoleName() + " :-");
                    String roleInput = scanner.nextLine().trim();
                    role = Integer.parseInt(roleInput);
                    if(!factory.isValidRole(role)) {
                        System.out.println("Invalid role. Please try again.");
                    }
                    }while(!factory.isValidRole(role));
                  
                    Musician musician = factory.createMusician(musicianId);
                    musician.setName(musicianName);
                    musician.setRole(role);
                    commandFactory.createAddMusicianCommand(currentEnsemble, musician).execute();
                    musicianEnsembleMap.put(musician.getMID(), currentEnsemble);
    }
    public void handleShowEnsemble() {
        if (currentEnsemble == null) {
            System.out.println("No current ensemble is set. Please create or set an ensemble first.");
            return;
        }
        //currentEnsemble.showEnsemble();
        commandFactory.createShowEnsembleCommand(currentEnsemble).execute();
    }
    public void handleSetEnsemble(Scanner scanner) {
        System.out.print("Please input ensemble ID:- ");
        String eid = scanner.nextLine().trim();
        Ensemble foundEnsemble = null;
        for (Ensemble ensemble : ensembles) {
            if (ensemble.getEnsembleID().equals(eid)) {
                foundEnsemble = ensemble;
                break;
            }
        }
        if (foundEnsemble != null) {
            commandFactory.createSetEnsembleCommand(currentEnsemble,foundEnsemble).execute();
            currentEnsemble = foundEnsemble;
        } else {
            System.out.println("Ensemble " + eid + " is not found!");
        }
    }
    public void handleModifyMusician(Scanner scanner) {
        if (currentEnsemble == null || currentEnsemble.getMusicians() == null) {
            System.out.println("No musicians found. Please add a musician first.");
            return;
        }
        System.out.print("Please input musician ID:- ");
        String mid = scanner.nextLine().trim();
        Musician foundMusician = null;
        Iterator<Musician> iterator = currentEnsemble.getMusicians();
        while (iterator.hasNext()) {
            Musician musician = iterator.next();
            if (musician.getMID().equals(mid)) {
                foundMusician = musician;
                break;
            }
        }     
        if (foundMusician != null) {
             System.out.print("Instrument "+ factory.showRoleName() + " :-");
            String instrument = scanner.nextLine().trim();
            int newRole = Integer.parseInt(instrument);
            commandFactory.createModifyInstrumentCommand(foundMusician,newRole).execute();
        } else {
            System.out.println("Musician with ID " + mid + " not found in the current ensemble.");
        }
    }
    public void handleDeleteMusician(Scanner scanner) {
        if (currentEnsemble == null || currentEnsemble.getMusicians() == null) {
            System.out.println("No musicians found.");
            return;
        }
        System.out.print("Please input musician ID:- ");
        String mid = scanner.nextLine().trim();
        Musician foundMusician = null;
        Iterator<Musician> iterator = currentEnsemble.getMusicians();
        while (iterator.hasNext()) {
            Musician musician = iterator.next();
            if (musician.getMID().equals(mid)) {
                foundMusician = musician;
                break;
            }
        }
        if (foundMusician != null) {
            musicianEnsembleMap.remove(foundMusician.getMID(), currentEnsemble);
            commandFactory.createDeleteMusicianCommand(currentEnsemble,foundMusician).execute();

        } else {
            System.out.println("Musician with ID " + mid + " not found in the current ensemble.");
        }
    }
    public void handleChangeEnsembleName(Scanner scanner) {
        if (currentEnsemble == null) {
            System.out.println("No current ensemble is set. Please create or set an ensemble first.");
            return;
        }
        System.out.print("Please input new name of the current ensemble :- ");
        String newName = scanner.nextLine().trim();
        commandFactory.createChangeEnsembleNameCommand(currentEnsemble,newName).execute();
    }
}
