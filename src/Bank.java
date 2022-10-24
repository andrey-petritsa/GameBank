public class Bank {
    private int gold;

    public Bank() {
        this.gold = 0;
    }

    public void deposit(int gold) {
        this.gold += gold;
    }

    public int getGold() {
        return gold;
    }
}
