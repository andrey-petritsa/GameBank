import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DirectDepositCommandTest {
    @Test
    void history() {
        int bankGold = 100;
        int playerGold = 222;
        int depositGold = 100;
        Bank bank = new Bank(1, bankGold);
        Player player = new Player(1, bank, playerGold);
        CommandHistory mockHistory = new InMemoryCommandHistory();
        DirectDepositCommand command = new DirectDepositCommand(mockHistory, new InMemoryBankRepository(bank), new InMemoryPlayerRepository(player));

        command.setContext(new DepositCommandContext(depositGold, 1, 1));
        command.execute();

        assertEquals("Команда положить 100 золота в банк (напрямую из кармана). От игрока: 1. От клана: 1. Всего золота: 200", mockHistory.get(0));
    }

    @Test
    void cantDeposit() {
        int bankGold = 100;
        int playerGold = 1;
        int depositGold = 9999;
        Bank bank = new Bank(1, bankGold);
        Player player = new Player(1, bank, playerGold);
        CommandHistory mockHistory = new InMemoryCommandHistory();
        DirectDepositCommand command = new DirectDepositCommand(mockHistory, new InMemoryBankRepository(bank), new InMemoryPlayerRepository(player));

        command.setContext(new DepositCommandContext(depositGold, 1, 1));
        assertThrows(RuntimeException.class, command::execute);
    }

    @Test
    void deposit() {
        int bankGold = 100;
        int playerGold = 1000;
        int depositGold = 100;
        Bank bank = new Bank(1, bankGold);
        Player player = new Player(1, bank, playerGold);
        InMemoryPlayerRepository mockPlayerRepository = new InMemoryPlayerRepository(player);
        InMemoryBankRepository mockBankRepository = new InMemoryBankRepository(bank);
        DirectDepositCommand command = new DirectDepositCommand(new InMemoryCommandHistory(), mockBankRepository, mockPlayerRepository);

        command.setContext(new DepositCommandContext(depositGold, 1, 1));
        command.execute();

        assertEquals(900, mockPlayerRepository.findById(1).gold);
        assertEquals(200, mockBankRepository.findById(1).gold);
    }
}
