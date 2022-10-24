public class DirectDepositCommand {
    private final CommandHistory commandHistory;
    private final BankRepository bankRepository;

    public DirectDepositCommand(CommandHistory history, BankRepository bankRepository) {
        this.commandHistory = history;
        this.bankRepository = bankRepository;
    }

    public void execute(DepositCommandContext context) {
        Bank bank = bankRepository.findById(context.clanId);
        bank.deposit(context.gold);
        String command = String.format("Команда положить %s золота в банк (напрямую из кармана). От игрока: %s. От клана: %s. Всего золота: %s", context.gold, context.playerId, context.clanId, bank.getGold());
        this.commandHistory.push(command);

    }
}
