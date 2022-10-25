public class ArenaFightDepositCommand implements DepositCommand {
    private final CommandHistory commandHistory;
    private final PlayerRepository playerRepository;
    private final ArenaWinStrategy arenaWinStrategy;
    private int playerId;

    public ArenaFightDepositCommand(CommandHistory history, PlayerRepository playerRepository, ArenaWinStrategy arenaWinStrategy) {
        this.commandHistory = history;
        this.playerRepository = playerRepository;
        this.arenaWinStrategy = arenaWinStrategy;
    }

    public void execute() {
        Player player = playerRepository.findPlayerById(playerId);
        player.bank.gold += arenaWinStrategy.getReward();
        String commandMessage = String.format("Команда положить %s золота в банк (сражаясь на арене). От игрока: %s. В клан: %s. Золота итого: %s", arenaWinStrategy.getReward(), player.id, player.bank.id, player.bank.gold);
        this.commandHistory.push(commandMessage, this);
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }
}
