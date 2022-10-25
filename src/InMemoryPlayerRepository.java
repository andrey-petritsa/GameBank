public class InMemoryPlayerRepository implements PlayerRepository {
    public Player player;

    public InMemoryPlayerRepository() {
    }

    public InMemoryPlayerRepository(Player player) {
        this.player = player;
    }

    @Override
    public Player findById(int id) {
        return new Player(this.player.id, this.player.bank, this.player.gold);
    }

    @Override
    public BankRepository createBankRepository() {
        return null;
    }

    @Override
    public void save(Player player) {
        this.player = player;
    }
}
