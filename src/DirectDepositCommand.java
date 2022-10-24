public class DirectDepositCommand {
    private final CommandHistory commandHistory;
    private final Bank bank;

    public DirectDepositCommand(CommandHistory history, Bank bank) {
        this.commandHistory = history;
        this.bank = bank;
    }

    public void execute(int gold) {
        String command = String.format("Команда положить %s золота в банк (напрямую из кармана)", gold);
        this.commandHistory.push(command);
        bank.deposit(gold);
    }
}
