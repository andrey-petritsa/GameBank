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
        assertEquals("Создана команда DirectDepositCommand", factory.log());
    }

    @Test
    void createArenaDepositWithGreatWinStrategy() {
        DepositCommandFactory factory = new DepositCommandFactory();

        Map<String, String> context = Map.of("gold", "100", "playerId", "1", "clanId", "1", "type", "арена", "strategy", "великая-победа");
        DepositCommand command = factory.create(context);

        assertEquals(command.getClass(), ArenaFightDepositCommand.class);
        assertEquals("Создана команда ArenaFightDepositCommand с стратегией GreatArenaWinStrategy. Id игрока: 1", factory.log());
    }

    @Test
    void createArenaDepositWithCommonArenaWinStrategy() {
        DepositCommandFactory factory = new DepositCommandFactory();

        Map<String, String> context = Map.of("gold", "100", "playerId", "2", "clanId", "1", "type", "арена", "strategy", "обычная-победа");
        DepositCommand command = factory.create(context);

        assertEquals(command.getClass(), ArenaFightDepositCommand.class);
        assertEquals("Создана команда ArenaFightDepositCommand с стратегией CommonArenaWinStrategy. Id игрока: 2", factory.log());
    }
}
