package bank.integrational;

import bank.Bank;
import bank.repository.FileBankRepository;
import bank.repository.FileClient;
import bank.repository.PathFileClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileBankRepositoryTest {
    private final String PATH_TO_FILE = "tmp/banks.txt";
    private final FileClient fileClient = new PathFileClient();

    @BeforeEach
    public void setUp() {
        fileClient.cleanFile(PATH_TO_FILE);
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

    @Test
    void saveBankToFile() {
        FileBankRepository repository = new FileBankRepository(PATH_TO_FILE, new PathFileClient());

        repository.save(new Bank(1, 100));
        repository.save(new Bank(2, 200));

        assertEquals("1 100", repository.findById(1).toString());
        assertEquals("2 200", repository.findById(2).toString());
    }
}
