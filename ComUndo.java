import java.util.Stack;
import javax.swing.text.Caret;

public class ComUndo implements Command {
    private Caretaker caretaker;

    public ComUndo(Caretaker caretaker) {
        this.caretaker = caretaker;
    }

    @Override
    public void execute() {
       caretaker.undo();
    
    }

    @Override
    public void undo() {
      
    }

    @Override
    public String description() {
            Command cmd = caretaker.getRedoList().isEmpty() ? null : caretaker.getRedoList().peek();
            if (cmd != null) {
                return "Command (" + cmd.description() + ") undone.";
            } else {    
            return "Undo last command";
        }
    }
 

}