package bank.repository;

import bank.deposit.CommandHistory;
import bank.deposit.DepositCommand;

import java.util.ArrayList;

public class InMemoryCommandHistory implements CommandHistory {
    ArrayList<String> list = new ArrayList<>();

    @Override
    public String get(int i) {
        return this.list.get(i);
    }

    @Override
    public void push(String commandMessage, DepositCommand command) {
        this.list.add(commandMessage);
    }
}
