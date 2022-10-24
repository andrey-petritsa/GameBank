import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ArenaFightDepositCommandTest {
    @Test
    void execute() {
        Bank bank = new Bank(1, 100);
        Player player = new Player(1, bank);
        PlayerRepository stubPlayerRepository = new PlayerRepository();
        stubPlayerRepository.player = player;
        CommandHistory history = new CommandHistory();
        ArenaFightDepositCommand command = new ArenaFightDepositCommand(history, stubPlayerRepository, new GreatArenaWinStrategy());

        command.execute(1);

        assertEquals("Команда положить 1000 золота в банк (сражаясь на арене). От игрока: 1. В клан: 1. Золота итого: 1100", history.pop());
    }
}
