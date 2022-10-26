package bank.unit;

import bank.Bank;
import bank.Player;
import bank.deposit.ArenaFightDepositCommand;
import bank.deposit.CommandHistory;
import bank.deposit.GreatArenaWinStrategy;
import bank.repository.InMemoryCommandHistory;
import bank.repository.InMemoryPlayerRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ArenaFightDepositCommandTest {
    @Test
    void history() {
        Bank bank = new Bank(1, 100);
        Player player = new Player(1, bank);
        InMemoryPlayerRepository stubPlayerRepository = new InMemoryPlayerRepository();
        stubPlayerRepository.player = player;
        CommandHistory history = new InMemoryCommandHistory();
        ArenaFightDepositCommand command = new ArenaFightDepositCommand(history, stubPlayerRepository, new GreatArenaWinStrategy());

        command.execute();

        assertEquals("Игрок 1 выйграл в битве на арене 1000 золота. Золото положено в Банк 1", history.get(0));
    }

    @Test
    void deposit() {
        int bankGold = 100;
        Bank bank = new Bank(1, bankGold);
        Player player = new Player(1, bank);
        InMemoryPlayerRepository mockPlayerRepository = new InMemoryPlayerRepository(player);
        ArenaFightDepositCommand command = new ArenaFightDepositCommand(new InMemoryCommandHistory(), mockPlayerRepository, new GreatArenaWinStrategy());

        command.execute();

        assertEquals(1100, mockPlayerRepository.findById(1).bank.gold);
    }
}

