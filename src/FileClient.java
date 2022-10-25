import java.util.List;

public interface FileClient {
    void writeToFile(String text, String stringPath);

    List<String> readAllLines(String pathToFile);

    String readLine(String pathToFile, int i);

    void deleteFile(String pathToFile);
}
