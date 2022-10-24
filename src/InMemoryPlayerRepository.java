public class InMemoryPlayerRepository implements PlayerRepository {
    public Player player;

    @Override
    public Player findPlayerById(int id) {
        return player;
    }
}
