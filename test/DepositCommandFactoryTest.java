import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DepositCommandFactoryTest {
    @Test
    void createDirectDeposit() {
        DepositCommandFactory factory = new DepositCommandFactory();

        Map<String, String> context = Map.of("gold", "100", "playerId", "1", "clanId", "1", "type", "напрямую");
        DepositCommand command = factory.create(context);

        assertEquals(command.getClass(), DirectDepositCommand.class);
    }

    @Test
    void createArenaDeposit() {
        DepositCommandFactory factory = new DepositCommandFactory();

        Map<String, String> context = Map.of("gold", "100", "playerId", "1", "clanId", "1", "type", "арена");
        DepositCommand command = factory.create(context);

        assertEquals(command.getClass(), ArenaFightDepositCommand.class);
    }
}
