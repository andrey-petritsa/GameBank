public class DirectDepositCommand implements DepositCommand {
    private final CommandHistory commandHistory;
    private final BankRepository bankRepository;
    private final PlayerRepository playerRepository;
    private DepositCommandContext context;

    public DirectDepositCommand(CommandHistory history, BankRepository bankRepository, PlayerRepository playerRepository) {
        this.commandHistory = history;
        this.bankRepository = bankRepository;
        this.playerRepository = playerRepository;
    }

    public void execute() {
        Player player = playerRepository.findById(context.playerId);
        Bank bank = bankRepository.findById(context.clanId);

        if (player.gold < context.gold) {
            throw new RuntimeException("Player dont has enough gold");
        }

        player.gold -= context.gold;
        bank.gold += context.gold;

        bankRepository.save(bank);
        playerRepository.save(player);

        String commandMessage = String.format("Команда положить %s золота в банк (напрямую из кармана). От игрока: %s. От клана: %s. Всего золота: %s", context.gold, context.playerId, context.clanId, bank.gold);
        this.commandHistory.push(commandMessage, this);
    }

    public void setContext(DepositCommandContext context) {
        this.context = context;
    }
}
