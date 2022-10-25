
public class FileCommandHistory implements CommandHistory {
    private final String pathToFile;
    private final FileClient fileClient;

    public FileCommandHistory(String path, FileClient fileClient) {
        pathToFile = path;
        this.fileClient = fileClient;
    }

    @Override
    public String get(int i) {
        return fileClient.readLine(pathToFile, i);
    }

    public void push(String commandMessage, DepositCommand command) {
        String commandMessageWithCommandInstance = String.format("%s | %s", commandMessage, command.getClass().getSimpleName());
        fileClient.writeToFile(commandMessageWithCommandInstance, pathToFile);
    }
}
