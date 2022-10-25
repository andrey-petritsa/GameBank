import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileBankRepositoryTest {
    private final String PATH_TO_FILE = "tmp/banks.txt";
    private final FileClient fileClient = new PathFileClient();

    @BeforeEach
    public void setUp() {
        fileClient.deleteFile(PATH_TO_FILE);
    }

    @Test
    void findByIdFromFile() {
        FileBankRepository repository = new FileBankRepository(PATH_TO_FILE, new PathFileClient());
        Bank firstBank = new Bank(1, 100);
        Bank secondBank = new Bank(2, 500);
        fileClient.writeToFile(firstBank.toString(), PATH_TO_FILE);
        fileClient.writeToFile(secondBank.toString(), PATH_TO_FILE);

        assertEquals("1 100", repository.findById(1).toString());
        assertEquals("2 500", repository.findById(2).toString());
    }
}
