package bank.deposit;

import bank.Player;
import bank.repository.PlayerRepository;

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
        Player player = playerRepository.findById(playerId);
        player.bank.gold += arenaWinStrategy.getReward();
        playerRepository.save(player);
        String format = "Игрок %s выйграл в битве на арене %s золота. Золото положено в Банк %s";
        String commandMessage = String.format(format, player.id, arenaWinStrategy.getReward(), player.bank.id);
        this.commandHistory.push(commandMessage, this);
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }
}
