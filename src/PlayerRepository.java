public interface PlayerRepository {
    Player findById(int id);

    BankRepository createBankRepository();

    void save(Player player);
}
