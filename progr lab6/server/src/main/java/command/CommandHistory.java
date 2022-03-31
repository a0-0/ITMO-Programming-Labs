package command;

import java.util.List;
import java.util.Stack;

public class CommandHistory {
    private final Stack<String> history = new Stack<>();

    public void pushHistory(String command) {
        if(history.size() > 11) {
            history.remove(history.size() - 1);
        }
        history.push(command.toLowerCase());
    }

    public List<String> getHistory() {
        return history;
    }
}
