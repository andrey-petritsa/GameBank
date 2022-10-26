import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FilePlayerRepositoryTest {
    FileClient fileClient = new PathFileClient();
    String pathToPlayers = "tmp/players.txt";
    private final FilePlayerRepository playerRepository = new FilePlayerRepository(pathToPlayers, new PathFileClient());
    String pathToBanks = "tmp/banks.txt";
    private final FileBankRepository bankRepository = new FileBankRepository(pathToBanks, new PathFileClient());

    @BeforeEach
    void setUp() {
        fileClient.deleteFile(pathToPlayers);
        fileClient.deleteFile(pathToBanks);
    }

    @Test
    void saveToFile() {
        Bank bank = new Bank(1, 100);
        Player player = new Player(1, bank, 500);

        playerRepository.save(player);

        assertEquals("1 1 500", playerRepository.findById(1).toString());
        assertEquals(100, playerRepository.findById(1).bank.gold);
    }
}
