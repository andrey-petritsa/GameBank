import java.util.ArrayList;

public class InMemoryCommandHistory implements CommandHistory {
    ArrayList<String> list = new ArrayList<>();

    @Override
    public String get(int i) {
        return this.list.get(i);
    }

    @Override
    public void push(String command) {
        this.list.add(command);
    }
}
