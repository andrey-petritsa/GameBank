import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DirectDepositCommandTest {
    @Test
    void execute() {
        CommandHistory history = new CommandHistory();
        Bank withGoldBank = new Bank(100);
        BankRepository mockBankRepository = new BankRepository();
        mockBankRepository.bank = withGoldBank;
        DirectDepositCommand command = new DirectDepositCommand(history, mockBankRepository);
        DepositCommandContext context = new DepositCommandContext(100, 1, 1);

        command.execute(context);

        assertEquals("Команда положить 100 золота в банк (напрямую из кармана). От игрока: 1. От клана: 1. Всего золота: 200", history.pop());
    }
}
