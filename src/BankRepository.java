public interface BankRepository {
    Bank findById(int id);

    void save(Bank bank);
}
