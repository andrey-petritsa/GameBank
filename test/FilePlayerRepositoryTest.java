import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FilePlayerRepositoryTest {
    FileClient fileClient = new PathFileClient();

    @BeforeEach
    void setUp() {
        fileClient.deleteFile("tmp/players.txt");
        fileClient.deleteFile("tmp/banks.txt");
    }

    @Test
    void findById() {
        FilePlayerRepository repository = new FilePlayerRepository("tmp/players.txt", new PathFileClient());
        Bank bank1 = new Bank(1, 100);
        Bank bank2 = new Bank(2, 200);
        Player playerId1 = new Player(1, bank1);
        Player playerId2 = new Player(2, bank2);
        fileClient.writeToFile(playerId1.toString(), "tmp/players.txt");
        fileClient.writeToFile(playerId2.toString(), "tmp/players.txt");
        fileClient.writeToFile(bank1.toString(), "tmp/banks.txt");
        fileClient.writeToFile(bank2.toString(), "tmp/banks.txt");

        assertEquals("1 1", repository.findPlayerById(1).toString());
        assertEquals(100, repository.findPlayerById(1).bank.gold);
        assertEquals("2 2", repository.findPlayerById(2).toString());
        assertEquals(200, repository.findPlayerById(2).bank.gold);
    }
}
