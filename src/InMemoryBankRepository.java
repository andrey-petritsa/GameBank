public class InMemoryBankRepository implements BankRepository {
    public Bank bank;

    @Override
    public Bank findById(int id) {
        return bank;
    }
}
