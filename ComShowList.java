import java.util.Stack;
public class ComShowList implements Command {
    Caretaker caretaker;
    private Stack<Command> undoList;
    private Stack<Command> redoList;
    public ComShowList(Caretaker caretaker) {
        this.caretaker = caretaker;
        this.undoList = caretaker.getUndoList();
        this.redoList = caretaker.getRedoList();
    }
    
    @Override
    public void execute() {
        // Implementation for showing the list
        undoList = caretaker.getUndoList();
        redoList = caretaker.getRedoList();
        if(undoList.isEmpty() && redoList.isEmpty()) {
            System.out.println("No commands in Undo or Redo list.");
            return;
        }
        int i=1;
        System.out.println("Undo List:");
        for (Command cmd : undoList) {
            System.out.println(i++ + ". " + cmd.description());
            }
        System.out.println("\n-- End of Undo List --\n");
        System.out.println("\nRedo List:");
        i=1;
        for (Command cmd : redoList) {
            System.out.println(i++ + ". " + cmd.description());
        }
        System.out.println("\n-- End of Redo List --");
    }

    @Override
    public void undo() {
        // Implementation for undoing the show list action, if applicable

    }

    @Override                                                       
    public String description() {
        return "Show Command List";
    }
}