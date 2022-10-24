import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ArenaFightDepositCommandTest {
    @Test
    void test() {
        BankRepository stubBankRepository = new BankRepository();
        PlayerRepository stubPlayerRepository = new PlayerRepository();
        stubBankRepository.bank = new Bank();
        Player clanPlayer = new Player();
        clanPlayer.id = 1;
        clanPlayer.clanBankId = 1;
        stubPlayerRepository.player = clanPlayer;
        CommandHistory history = new CommandHistory();
        ArenaFightDepositCommand command = new ArenaFightDepositCommand(history, stubBankRepository, stubPlayerRepository);

        command.execute(1);

        assertEquals("Команда положить 100 золота в банк (сражаясь на арене). От игрока: 1. В клан: 1. Золота итого: 100", history.pop());
    }
}
