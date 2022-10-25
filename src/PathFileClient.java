import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class PathFileClient implements FileClient {
    @Override
    public void writeToFile(String text, String stringPath) {
        try {
            writeToFileByFileWriter(text, stringPath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void writeToFileByFileWriter(String text, String stringPath) throws IOException {
        Path path = Paths.get(stringPath);
        Files.createDirectories(path.getParent());
        FileWriter myWriter = new FileWriter(path.toString(), true);
        myWriter.write(text);
        myWriter.write("\n");
        myWriter.close();
    }

    @Override
    public List<String> readAllLines(String pathToFile) {
        try {
            return Files.readAllLines(Paths.get(pathToFile));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String readLine(String pathToFile, int i) {
        try {
            return Files.readAllLines(Paths.get(pathToFile)).get(i);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteFile(String pathToFile) {
        File file = new File(pathToFile);
        file.delete();
    }
}
