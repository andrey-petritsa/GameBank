public interface PlayerRepository {
    Player findPlayerById(int id);

    BankRepository createBankRepository();
}
