import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DirectDepositCommandTest {
    @Test
    void execute() {
        CommandHistory history = new CommandHistory();
        DirectDepositCommand command = new DirectDepositCommand(history, new Bank());
        int gold = 100;

        command.execute(gold);

        assertEquals("Команда положить 100 золота в банк (напрямую из кармана)", history.pop());
    }
}
