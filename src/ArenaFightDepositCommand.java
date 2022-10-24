public class ArenaFightDepositCommand {
    public static final int ARENA_FIGHT_GOLD = 100;
    private final CommandHistory commandHistory;
    private final PlayerRepository playerRepository;

    public ArenaFightDepositCommand(CommandHistory history, PlayerRepository playerRepository) {
        this.commandHistory = history;
        this.playerRepository = playerRepository;
    }

    public void execute(int playerId) {
        Player player = playerRepository.findPlayerById(playerId);
        player.bank.gold += ARENA_FIGHT_GOLD;
        String command = String.format("Команда положить %s золота в банк (сражаясь на арене). От игрока: %s. В клан: %s. Золота итого: %s", ARENA_FIGHT_GOLD, player.id, player.bank.id, player.bank.gold);
        this.commandHistory.push(command);
    }
}
