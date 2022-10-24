import java.util.Stack;

public class CommandHistory {
    Stack<String> stack = new Stack<>();

    public String pop() {
        return this.stack.pop();
    }

    public void push(String command) {
        this.stack.push(command);
    }
}
