package bank.repository;

import bank.Player;

public interface PlayerRepository {
    Player findById(int id);

    BankRepository createBankRepository();

    void save(Player player);

    int count();
}
