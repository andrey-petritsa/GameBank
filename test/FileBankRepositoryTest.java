import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileBankRepositoryTest {

    public static final String PATH_TO_FILE = "tmp/banks.txt";

    @BeforeEach
    public void setUp() {
        File file = new File(PATH_TO_FILE);
        file.delete();
    }

    @Test
    void findByIdFromFile() throws IOException {
        FileBankRepository repository = new FileBankRepository("tmp");
        Bank firstBank = new Bank(1, 100);
        Bank secondBank = new Bank(2, 500);
        writeToFile(firstBank.toString(), PATH_TO_FILE);
        writeToFile(secondBank.toString(), PATH_TO_FILE);

        assertEquals("1 100", repository.findById(1).toString());
        assertEquals("2 500", repository.findById(2).toString());
    }

    private void writeToFile(String text, String stringPath) throws IOException {
        Path path = Paths.get(stringPath);
        Files.createDirectories(path.getParent());
        FileWriter myWriter = new FileWriter(path.toString(), true);
        myWriter.write(text);
        myWriter.write("\n");
        myWriter.close();
    }
}
