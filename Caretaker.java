import java.util.List;
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

     public Ensemble getHistoryEnsemble(){
        return redoList.isEmpty()? null : ((UndoableCommand)redoList.peek()).getEnsemble();
    }
    //after redo, get current ensemble, as there may be a set ensemble command, need to get next ensemble
    public Ensemble getNextEnsemble(){
        return undoList.isEmpty() ? null : ((UndoableCommand)undoList.peek()).getEnsemble();
    }
    //after undo, get current ensemble, as there may be a set ensemble command, need to get previous ensemble
    public Ensemble getCurrentEnsemble(List<Ensemble> ensembles){
        Ensemble e = undoList.isEmpty() ? null : ((UndoableCommand)redoList.peek()).getPreEnsemble();
        //判断e在不在ensembles里
        if (e != null && !ensembles.contains(e)) {
            return getNextEnsemble();
        }
        return e;
    }
 
   
}