import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

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

        assertThat(br.readLine(), containsString("Команда запущена | NullDepositCommand"));
    }

    @Test
    void get() {
        FileCommandHistory history = new FileCommandHistory(PATH_TO_FILE, new PathFileClient());

        history.push("Команда 1 запущена", new NullDepositCommand());
        history.push("Команда 2 запущена", new NullDepositCommand());

        assertThat(history.get(0), containsString("Команда 1 запущена | NullDepositCommand"));
        assertThat(history.get(1), containsString("Команда 2 запущена | NullDepositCommand"));
    }
}
