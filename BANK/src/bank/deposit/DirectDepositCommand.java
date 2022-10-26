package bank.deposit;

import bank.Bank;
import bank.Player;
import bank.repository.BankRepository;
import bank.repository.PlayerRepository;

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

        String commandMessage = "Игрок %s Положил %s золота В банк %s";
        String formattedMessage = String.format(commandMessage, context.playerId, context.gold, context.clanId);
        this.commandHistory.push(formattedMessage, this);
    }

    public void setContext(DepositCommandContext context) {
        this.context = context;
    }
}
