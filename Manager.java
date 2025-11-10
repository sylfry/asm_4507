import java.util.List;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Iterator;

import java.util.function.Function;

public class Manager {
    
    private final List<Ensemble> ensembles = new LinkedList<>();
    private Ensemble currentEnsemble;
    private final CommandFactory commandFactory = new CommandImpl();
    private Caretaker caretaker=new Caretaker();
    private EnsembleFactory factory;
    private CommandRegistry commandRegistry = new CommandRegistry();
    // Decide whether a command should be recorded by checking if it implements UndoableCommand
    
    
    public Manager() {
        // register handlers: input -> (Scanner -> Command)
        commandRegistry.registerHandler("c", this::handleCreateEnsemble);
        commandRegistry.registerHandler("a", this::handleAddMusician);
        commandRegistry.registerHandler("m", this::handleModifyMusician);
        commandRegistry.registerHandler("d", this::handleDeleteMusician);
        commandRegistry.registerHandler("cn", this::handleChangeEnsembleName);
        // show/set/list/undo/redo handlers (may execute directly and return null)
        commandRegistry.registerHandler("se", sc -> { handleShowEnsemble(); return null; });
        commandRegistry.registerHandler("sa", sc -> commandFactory.createDisplayAllEnsemblesCommand(ensembles));
        commandRegistry.registerHandler("s", sc -> { handleSetEnsemble(sc); return null; });
        commandRegistry.registerHandler("u", sc -> { caretaker.undo(); syncCurrentEnsembleFromHistory();return null; });       
        commandRegistry.registerHandler("r", sc -> { caretaker.redo();  syncCurrentEnsembleFromHistory();return null; });
        commandRegistry.registerHandler("l", sc -> { commandFactory.createShowListCommand(caretaker).execute(); return null; });
    }
    public static void main(String args[]) {
         Manager manager = new Manager();         
             
        System.out.println("Music Ensembles Management System(MEMS)");

        System.out.println(manager.commandRegistry.getCommandMenu());
        
        manager.chooseFunction();      
        // if(manager.currentEnsemble!=null) {
        //         System.out.println("The current ensemble is " + manager.currentEnsemble.getEnsembleID()+ " " + manager.currentEnsemble.getName()+ ".");
        //     }
    }
    public void chooseFunction() {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Please enter command " + commandRegistry.getCommandIDPrompt()+" :- ");
    
        String input = scanner.nextLine().trim().toLowerCase();
        while (!input.equals("x")) {
            Function<Scanner, Command> handler = commandRegistry.getHandler(input);
            if (handler != null) {
                try {
                    Command created = handler.apply(scanner);
                    if (created != null) {
                        if (created instanceof UndoableCommand) {
                            caretaker.execute(created);
                            // sync currentEnsemble from undo history after recording a mutation
                            syncCurrentEnsembleFromHistory();
                        } else {
                            created.execute();
                        }
                    }
                } catch (Exception e) {
                    System.out.println("\nError handling command: " + e.getMessage());
                }
            } else {
                System.out.println("\nInvalid command. Please try again.");
            }
            if(currentEnsemble!=null) {
     
                factory = EnsembleFactoryRegistry.getFactory(currentEnsemble);
                 System.out.println("\n--------------------------------------------------");
                System.out.println("\nThe current ensemble (" + factory.getEnsembleType() + ") is " + currentEnsemble.getEnsembleID() + " " + currentEnsemble.getName() + ".");      
            }
            System.out.print("\nPlease enter command " + commandRegistry.getCommandIDPrompt() + " :- ");
            input = scanner.nextLine().trim().toLowerCase();
        }
    }

    // Synchronize Manager.currentEnsemble with the top of the undo history (if any).
    private void syncCurrentEnsembleFromHistory() {
        Ensemble next = null;
        if (!caretaker.getUndoList().isEmpty()) {
            next = caretaker.getUndoList().peek().getEnsemble();
        }
        String currentId = currentEnsemble == null ? null : currentEnsemble.getEnsembleID();
        String nextId = next == null ? null : next.getEnsembleID();
        boolean changed = (currentId == null && nextId != null) || (currentId != null && nextId == null) || (currentId != null && !currentId.equals(nextId));
        if (changed) {
            currentEnsemble = next;
            if (currentEnsemble == null) {
                System.out.println("\nThe current ensemble is changed to none.");
            } else {
                factory = EnsembleFactoryRegistry.getFactory(currentEnsemble);
                System.out.println("\nThe current ensemble is changed to " + currentEnsemble.getEnsembleID() + " " + currentEnsemble.getName() + ".");
            }
        }
    }

    // Allow commands to set current ensemble via receiver pattern
    public void setCurrentEnsemble(Ensemble ensemble) {
        this.currentEnsemble = ensemble;
        if (currentEnsemble == null) {
            System.out.println("\nThe current ensemble is changed to none.");
        } else {
            factory = EnsembleFactoryRegistry.getFactory(currentEnsemble);
            System.out.println("\nThe current ensemble is changed to " + currentEnsemble.getEnsembleID() + " " + currentEnsemble.getName() + ".");
        }
    }

    // provide read access for commands to capture previous state
    public Ensemble getCurrentEnsemble() {
        return this.currentEnsemble;
    }
    public Command handleCreateEnsemble(Scanner scanner) {
        // Implementation for creating ensemble
         System.out.print("\nEnter music type(o = orchestra | j = jazz band) :-");
                    String type = scanner.nextLine().trim().toLowerCase();
                    System.out.print("\nEnsemble ID:- ");
                    String eid = scanner.nextLine().trim();
                    // Prevent duplicate ensemble IDs
                    // if(!ensembles.isEmpty()){
                    // for (Ensemble e : ensembles) {
                    //     if (e.getEnsembleID().equals(eid)) {
                    //         System.out.println("\nEnsemble ID '" + eid + "' already exists. Please choose a different ID.");
                    //         return null;
                    //     }
                    // }
                    // }
                    System.out.print("\nEnsemble Name:- ");
                    String ename = scanner.nextLine().trim();
                    
                    EnsembleFactory chosenFactory = EnsembleFactoryRegistry.getFactoryByKey(type);
                    if (chosenFactory == null) {
                        System.out.println("\nInvalid music type. Available types: " + EnsembleFactoryRegistry.getAvailableKeys());
                        return null;
                    }
                    if(!ensembles.isEmpty()){
                        for (Ensemble e : ensembles) {
                            if (e.getEnsembleID().equals(eid)) {
                               e.setName(ename);
                               return null;
                            }
                        }
                        }
                    return commandFactory.createEnsembleCommand(chosenFactory, eid, ename, ensembles);
    }
    public Command handleAddMusician(Scanner scanner) {
      
         if (currentEnsemble == null) {
                        System.out.println("\nNo current ensemble is set. Please create or set an ensemble first.");
                        //break;
                        return null;
                    }
                    String inputMusician="";

                    do {
                        System.out.print("\nPlease input musician information(id, name):- ");
                        inputMusician = scanner.nextLine().trim();
                        if (inputMusician.isEmpty() || !inputMusician.contains(",") || inputMusician.split(",").length != 2) {
                            System.out.println("\nMusician format is invalid. Please use the format (id, name).");
                        }
                    } while (inputMusician.isEmpty() || !inputMusician.contains(",") || inputMusician.split(",").length != 2);
                    factory = EnsembleFactoryRegistry.getFactory(currentEnsemble);
                    String[] parts = inputMusician.split(",");
                    String musicianId = parts[0].trim();
                    String musicianName = parts[1].trim();
                    int role=0;
                    do {                      
                    System.out.print("\nInstrument " + factory.showRoleName() + " :-");
                    String roleInput = scanner.nextLine().trim();
                    role = Integer.parseInt(roleInput);
                    if(!factory.isValidRole(role)) {
                        System.out.println("\nInvalid role. Please try again.");
                    }
                    }while(!factory.isValidRole(role));
                  
                    Musician musician = factory.createMusician(musicianId);
                    musician.setName(musicianName);
                    musician.setRole(role);
                   // commandFactory.createAddMusicianCommand(currentEnsemble, musician).execute();
                    return commandFactory.createAddMusicianCommand(currentEnsemble, musician);
                   
    }
    public void handleShowEnsemble() {
        if (currentEnsemble == null) {
            System.out.println("\nNo current ensemble is set. Please create or set an ensemble first.");
            return;
        }
        //currentEnsemble.showEnsemble();
        commandFactory.createShowEnsembleCommand(currentEnsemble).execute();
    }
    public void handleSetEnsemble(Scanner scanner) {
        System.out.print("\nPlease input ensemble ID:- ");
        String eid = scanner.nextLine().trim();
        Ensemble foundEnsemble = null;
        for (Ensemble ensemble : ensembles) {
            if (ensemble.getEnsembleID().equals(eid)) {
                foundEnsemble = ensemble;
                break;
            }
        }
        if (foundEnsemble != null) {
            commandFactory.createSetEnsembleCommand(this, foundEnsemble).execute();
        } else {
            System.out.println("\nEnsemble " + eid + " is not found!");
        }
    }
    public Command handleModifyMusician(Scanner scanner) {
        if (currentEnsemble == null || currentEnsemble.getMusicians() == null) {
            System.out.println("\nNo musicians found. Please add a musician first.");
            return null;
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
            System.out.print("\nInstrument " + factory.showRoleName() + " :-");
            String instrument = scanner.nextLine().trim();
            int newRole = Integer.parseInt(instrument);
            if(!factory.isValidRole(newRole)) {
                System.out.println("\nInvalid role. Please try again.");
            }
            return commandFactory.createModifyInstrumentCommand(foundMusician,newRole);
        } else {
            System.out.println("\nMusician with ID " + mid + " not found in the current ensemble.");
            return null;
        }
    }
    public Command handleDeleteMusician(Scanner scanner) {
        if (currentEnsemble == null || currentEnsemble.getMusicians() == null) {
            System.out.println("\nNo musicians found.");
            return null;
        }
        System.out.print("\nPlease input musician ID:- ");
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
           // musicianEnsembleMap.remove(foundMusician.getMID(), currentEnsemble);
            return commandFactory.createDeleteMusicianCommand(currentEnsemble,foundMusician);

        } else {
            System.out.println("\nMusician with ID " + mid + " not found in the current ensemble.");
            return null;
        }
    }
    public Command handleChangeEnsembleName(Scanner scanner) {
        if (currentEnsemble == null) {
            System.out.println("\nNo current ensemble is set. Please create or set an ensemble first.");
            return null;
        }
        System.out.print("\nPlease input new name of the current ensemble :- ");
        String newName = scanner.nextLine().trim();
        return commandFactory.createChangeEnsembleNameCommand(currentEnsemble,newName);
    }

  
}
