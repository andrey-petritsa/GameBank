package bank.repository;

import java.util.List;

public interface FileClient {
    void writeToFile(String text, String stringPath);

    List<String> readAllLines(String pathToFile);

    String readLine(String pathToFile, int i);

    void replaceLine(String pathToFile, String oldLine, String newLine);

    void cleanFile(String pathToFile);

    String findLineById(String pathToFile, int searchId);
}
