import java.util.Stack;

public class StackCommandHistory implements CommandHistory {
    Stack<String> stack = new Stack<>();

    @Override
    public String pop() {
        return this.stack.pop();
    }

    @Override
    public void push(String command) {
        this.stack.push(command);
    }
}
