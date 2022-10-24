import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommonArenaWinStrategyTest {
    @Test
    void reward() {
        CommonArenaWinStrategy strategy = new CommonArenaWinStrategy();

        assertEquals(100, strategy.getReward());
    }
}
