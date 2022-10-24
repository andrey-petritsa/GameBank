import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileCommandHistoryTest {
    @Test
    void push() throws IOException {
        String path = "tmp";
        FileCommandHistory history = new FileCommandHistory(path);

        history.push("Команда запущена");
        File file = new File("tmp/history.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));

        assertEquals("Команда запущена", br.readLine());
    }
}
