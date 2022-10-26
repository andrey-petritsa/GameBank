import java.text.SimpleDateFormat;

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
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
        String commandMessageWithCommandInstance = String.format("%s | %s | %s", commandMessage, command.getClass().getSimpleName(), timeStamp);
        fileClient.writeToFile(commandMessageWithCommandInstance, pathToFile);
    }
}
