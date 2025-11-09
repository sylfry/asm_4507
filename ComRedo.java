import java.util.Stack;

public class ComRedo implements Command {
    private Caretaker caretaker;

    public ComRedo(Caretaker caretaker) {
        this.caretaker = caretaker;
    }

    @Override
    public void execute() {
        caretaker.redo();
       
    }

    @Override
    public void undo() {
        
    }

    @Override
    public String description() {
        return "Redo last undone command";
    }
}