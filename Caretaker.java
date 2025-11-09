import java.util.Stack;

public class Caretaker {
    private final Stack<Command> undoList;
    private final Stack<Command> redoList;

    public Caretaker() {
        undoList = new Stack<>();
        redoList = new Stack<>();
    }

    public void execute(Command cmd) {
        cmd.execute();
        undoList.push(cmd);
        redoList.clear();
    }

    public void undo() {
        if (!undoList.isEmpty()) {
            Command cmd = undoList.pop();
            String description = cmd.description();
            cmd.undo();
            redoList.push(cmd);
            System.out.println("\nCommand (" + description + ") is undone.");
            
        } else {
            System.out.println("\nNothing to undo.");
            
        }
    }

    public void redo() {
        if (!redoList.isEmpty()) {
            Command cmd = redoList.pop();
            System.out.println("\nCommand (" + cmd.description() + ") is redone.");
            cmd.execute();
            undoList.push(cmd);
        } else {
            System.out.println("\nNothing to redo.");
        }
    }

    public Stack<Command> getUndoList() {
        return undoList;
    }

    public Stack<Command> getRedoList() {
        return redoList;
    }
   
}