public class ArenaFightDepositCommand {
    public static final int ARENA_FIGHT_GOLD = 100;
    private final CommandHistory commandHistory;
    private final BankRepository bankRepository;
    private final PlayerRepository playerRepository;

    public ArenaFightDepositCommand(CommandHistory history, BankRepository bankRepository, PlayerRepository playerRepository) {
        this.commandHistory = history;
        this.bankRepository = bankRepository;
        this.playerRepository = playerRepository;
    }

    public void execute(int playerId) {
        Player clanPlayer = playerRepository.findPlayerById(playerId);
        Bank bank = bankRepository.findById(clanPlayer.clanBankId);
        bank.deposit(ARENA_FIGHT_GOLD);
        String command = String.format("Команда положить %s золота в банк (сражаясь на арене). От игрока: %s. В клан: %s. Золота итого: %s", ARENA_FIGHT_GOLD, clanPlayer.id, clanPlayer.clanBankId, bank.getGold());
        this.commandHistory.push(command);
    }
}
