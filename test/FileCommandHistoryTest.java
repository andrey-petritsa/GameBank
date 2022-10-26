import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileCommandHistoryTest {
    private final String PATH_TO_FILE = "tmp/history.txt";
    private final FileClient fileClient = new PathFileClient();

    @BeforeEach
    public void setUp() {
        fileClient.cleanFile(PATH_TO_FILE);
    }

    @Test
    void push() throws IOException {
        FileCommandHistory history = new FileCommandHistory(PATH_TO_FILE, new PathFileClient());

        history.push("Команда запущена", new NullDepositCommand());
        File file = new File("tmp/history.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));

        assertEquals("Команда запущена | NullDepositCommand", br.readLine());
    }

    @Test
    void get() {
        FileCommandHistory history = new FileCommandHistory(PATH_TO_FILE, new PathFileClient());

        history.push("Команда 1 запущена", new NullDepositCommand());
        history.push("Команда 2 запущена", new NullDepositCommand());

        assertEquals("Команда 1 запущена | NullDepositCommand", history.get(0));
        assertEquals("Команда 2 запущена | NullDepositCommand", history.get(1));
    }
}
