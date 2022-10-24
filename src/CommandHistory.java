public interface CommandHistory {
    String pop();

    void push(String command);
}
