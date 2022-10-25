public interface CommandHistory {

    String get(int i);

    void push(String command);
}
