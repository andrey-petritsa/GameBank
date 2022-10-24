public class ArenaFightDepositCommand {
    private final CommandHistory commandHistory;
    private final PlayerRepository playerRepository;
    private final ArenaWinStrategy arenaWinStrategy;

    public ArenaFightDepositCommand(CommandHistory history, PlayerRepository playerRepository, ArenaWinStrategy arenaWinStrategy) {
        this.commandHistory = history;
        this.playerRepository = playerRepository;
        this.arenaWinStrategy = arenaWinStrategy;
    }

    public void execute(int playerId) {
        Player player = playerRepository.findPlayerById(playerId);
        player.bank.gold += arenaWinStrategy.getReward();
        String command = String.format("Команда положить %s золота в банк (сражаясь на арене). От игрока: %s. В клан: %s. Золота итого: %s", arenaWinStrategy.getReward(), player.id, player.bank.id, player.bank.gold);
        this.commandHistory.push(command);
    }
}
