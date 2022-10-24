public class ImMemoryBankRepository implements BankRepository {
    public Bank bank;

    @Override
    public Bank findById(int id) {
        return bank;
    }
}
