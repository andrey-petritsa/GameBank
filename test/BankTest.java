import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BankTest {
    @Test
    void deposit() {
        Bank bank = new Bank();
        int gold = 100;

        bank.deposit(gold);

        assertEquals(100, bank.getGold());
    }

    @Test
    void zeroGold() {
        Bank bank = new Bank();

        assertEquals(0, bank.getGold());
    }
}
