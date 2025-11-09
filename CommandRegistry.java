import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;
import java.util.LinkedHashMap;

public class CommandRegistry {
    private String comID;
    private String comName;
    private Command comHandle;
    private final Map<String, Function<Scanner, Command>> handlerMap = new HashMap<>();
    private final Map<String, String> IDNameMap = new LinkedHashMap<>();
    public CommandRegistry() {
        IDNameMap.put("c", "create ensemble");
        IDNameMap.put("s", "set current ensemble");
        IDNameMap.put("a", "add musician");
        IDNameMap.put("m", "modify musician's instrument");
        IDNameMap.put("d", "delete musician");
        IDNameMap.put("se", "show ensemble");
        IDNameMap.put("sa", "display all ensembles");
        IDNameMap.put("cn", "change ensemble's name");
        IDNameMap.put("u", "undo");
        IDNameMap.put("r", "redo");      
        IDNameMap.put("l", "undo/redo");
        IDNameMap.put("x", "exit system");
    }
    // 根据输入的字母注册对应的处理函数
    public void registerHandler(String id, Function<Scanner, Command> handler) {
        handlerMap.put(id, handler);
    }   
    public Function<Scanner, Command> getHandler(String id) {
        return handlerMap.get(id);
    }
    public void setComID(String id) {
        this.comID = id;
    }
    public String getComID() {
        return comID;
    }
    public void setComName(String name) {
        this.comName = name;
    }
    public String getComName() {
        return comName;
    }
    public void setComHandle(Command handle) {
        this.comHandle = handle;
    }
    public Command getComHandle() {
        return comHandle;
    }

    public String getCommandMenu() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : IDNameMap.entrySet()) {
            sb.append(entry.getKey())
            .append(" = ")
            .append(entry.getValue())
            .append(", ");
        }
    
        if (sb.length() >= 2) {
            sb.setLength(sb.length() - 2);
        }
        return sb.toString();
    }

    public String getCommandIDPrompt() {
        StringBuilder sb = new StringBuilder("[ ");
        for (String id : IDNameMap.keySet()) {
            sb.append(id).append(" | ");
        }
    
        if (sb.length() >= 3) {
            sb.setLength(sb.length() - 3);
        }
        sb.append(" ]");
        return sb.toString();
    }

}
