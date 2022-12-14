package bank.integrational;

import bank.Bank;
import bank.Player;
import bank.repository.FileClient;
import bank.repository.FilePlayerRepository;
import bank.repository.PathFileClient;
import bank.repository.TestableFilePlayerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FilePlayerRepositoryTest {
    FileClient fileClient = new PathFileClient();
    String pathToPlayers = "tmp/players.txt";
    String pathToBanks = "tmp/banks.txt";
    private final FilePlayerRepository playerRepository = new TestableFilePlayerRepository(pathToPlayers, new PathFileClient());

    @BeforeEach
    void setUp() {
        fileClient.cleanFile(pathToPlayers);
        fileClient.cleanFile(pathToBanks);
    }

    @Test
    void saveToFile() {
        Bank bank = new Bank(1, 100);
        Player player = new Player(1, bank, 500);

        playerRepository.save(player);

        assertEquals("1 1 500", playerRepository.findById(1).toString());
        assertEquals(100, playerRepository.findById(1).bank.gold);
    }

    @Test
    void saveToFileRewriteWithExistedId() {
        Bank bank = new Bank(1, 100);
        Player player = new Player(1, bank, 500);
        Player rewritedPlayer = new Player(1, new Bank(1, 200), 1000);

        playerRepository.save(player);
        playerRepository.save(rewritedPlayer);
        Player savedPlayer = playerRepository.findById(1);

        assertEquals("1 1 1000", savedPlayer.toString());
        assertEquals(200, savedPlayer.bank.gold);
        assertEquals(1, playerRepository.count());
    }
}
