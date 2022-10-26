package bank;

public class Bank {
    public int id;
    public int gold;

    public Bank() {
        this.gold = 0;
    }

    public Bank(int id, int gold) {
        this.gold = gold;
        this.id = id;
    }

    public Bank(int gold) {
        this.gold = gold;
    }

    public String toString() {
        return String.format("%s %s", id, gold);
    }
}
