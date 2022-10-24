import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileCommandHistory {
    private final Path pathToFile;

    public FileCommandHistory(String path) {
        pathToFile = Paths.get(String.format("%s/history.txt", path));
    }

    public void push(String command) throws IOException {
        Files.createDirectories(pathToFile.getParent());
        FileWriter myWriter = new FileWriter(pathToFile.toString(), true);
        myWriter.write(command);
        myWriter.write("\n");
        myWriter.close();
    }
}
