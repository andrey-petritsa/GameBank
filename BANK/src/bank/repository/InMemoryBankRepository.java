package bank.repository;

import bank.Bank;

public class InMemoryBankRepository implements BankRepository {
    public Bank bank;

    public InMemoryBankRepository(Bank bank) {
        this.bank = bank;
    }

    @Override
    public Bank findById(int id) {
        return new Bank(bank.id, bank.gold);
    }

    @Override
    public void save(Bank bank) {
        this.bank = bank;
    }
}
