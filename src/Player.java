public class Player {
    public int id;
    public Bank bank;

    public Player(int id, Bank bank) {
        this.id = id;
        this.bank = bank;
    }

    public Player() {
    }

    public String toString() {
        return String.format("%s %s", id, bank.id);
    }
}
