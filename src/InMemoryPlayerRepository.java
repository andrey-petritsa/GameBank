public class InMemoryPlayerRepository implements PlayerRepository {
    public Player player;

    @Override
    public Player findById(int id) {
        return player;
    }

    @Override
    public BankRepository createBankRepository() {
        return null;
    }

    @Override
    public void save(Player player) {

    }
}
