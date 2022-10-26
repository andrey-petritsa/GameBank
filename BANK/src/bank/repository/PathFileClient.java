package bank.repository;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

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
    public void replaceLine(String pathToFile, String oldLine, String newLine) {
        List<String> fileContent = readAllLines(pathToFile);

        for (int i = 0; i < fileContent.size(); i++) {
            if (fileContent.get(i).equals(oldLine)) {
                fileContent.set(i, newLine);
                break;
            }
        }

        try {
            Files.write(Paths.get(pathToFile), fileContent, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void cleanFile(String pathToFile) {
        try {
            PrintWriter pw = new PrintWriter(pathToFile);
            pw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String findLineById(String pathToFile, int searchId) {
        try {
            File file = new File(pathToFile);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                int id = Integer.parseInt(line.split(" ")[0]);
                if (searchId == id) {
                    return line;
                }
            }
        } catch (FileNotFoundException ignored) {
        }

        return "";
    }
}
