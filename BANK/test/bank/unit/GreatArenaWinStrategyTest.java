package bank.unit;

import bank.deposit.GreatArenaWinStrategy;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GreatArenaWinStrategyTest {
    @Test
    void reward() {
        GreatArenaWinStrategy strategy = new GreatArenaWinStrategy();

        assertEquals(1000, strategy.getReward());
    }
}
