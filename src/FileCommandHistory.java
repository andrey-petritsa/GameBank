import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileCommandHistory implements CommandHistory {
    private final Path pathToFile;

    public FileCommandHistory(String path) {
        pathToFile = Paths.get(String.format("%s/history.txt", path));
    }

    @Override
    public String get(int i) {
        try {
            return Files.readAllLines(Paths.get(pathToFile.toUri())).get(i);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void push(String command) {
        try {
            addCommandToFile(command);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void addCommandToFile(String command) throws IOException {
        Files.createDirectories(pathToFile.getParent());
        FileWriter myWriter = new FileWriter(pathToFile.toString(), true);
        myWriter.write(command);
        myWriter.write("\n");
        myWriter.close();
    }
}
