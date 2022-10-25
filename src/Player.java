public class Player {
    public int id;
    public Bank bank;
    public int gold;

    public Player() {
    }

    public Player(int id, Bank bank) {
        this.id = id;
        this.bank = bank;
        this.gold = 0;
    }

    public Player(int id, Bank bank, int gold) {
        this.id = id;
        this.bank = bank;
        this.gold = gold;
    }


    public String toString() {
        return String.format("%s %s", id, bank.id);
    }
}
