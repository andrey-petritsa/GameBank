import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DirectDepositCommandTest {
    @Test
    void execute() {
        CommandHistory history = new StackCommandHistory();
        Bank withGoldBank = new Bank(100);
        InMemoryBankRepository mockBankRepository = new InMemoryBankRepository();
        mockBankRepository.bank = withGoldBank;
        DirectDepositCommand command = new DirectDepositCommand(history, mockBankRepository);

        command.setContext(new DepositCommandContext(100, 1, 1));
        command.execute();

        assertEquals("Команда положить 100 золота в банк (напрямую из кармана). От игрока: 1. От клана: 1. Всего золота: 200", history.pop());
    }
}
