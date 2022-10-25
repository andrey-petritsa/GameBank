import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ArenaFightDepositCommandTest {
    @Test
    void execute() {
        Bank bank = new Bank(1, 100);
        Player player = new Player(1, bank);
        InMemoryPlayerRepository stubPlayerRepository = new InMemoryPlayerRepository();
        stubPlayerRepository.player = player;
        CommandHistory history = new InMemoryCommandHistory();
        ArenaFightDepositCommand command = new ArenaFightDepositCommand(history, stubPlayerRepository, new GreatArenaWinStrategy());

        command.execute();

        assertEquals("Команда положить 1000 золота в банк (сражаясь на арене). От игрока: 1. В клан: 1. Золота итого: 1100", history.get(0));
    }
}
