import java.util.Map;
import java.util.Objects;

public class DepositCommandFactory implements AbstractDepositCommandFactory {
    private final CommandHistory commandHistory;

    public DepositCommandFactory() {
        commandHistory = new FileCommandHistory("storage/history.txt", new PathFileClient());
    }

    @Override
    public DepositCommand create(Map<String, String> context) {
        if (Objects.equals(context.get("type"), "напрямую")) {
            DepositCommandContext depositCommandContext = new DepositCommandContext();
            depositCommandContext.playerId = Integer.parseInt(context.get("playerId"));
            depositCommandContext.clanId = Integer.parseInt(context.get("clanId"));
            depositCommandContext.gold = Integer.parseInt(context.get("gold"));
            BankRepository bankRepository = new FileBankRepository("storage/banks.txt", new PathFileClient());
            PlayerRepository playerRepository = new FilePlayerRepository("storage/players.txt", new PathFileClient());
            DirectDepositCommand directDepositCommand = new DirectDepositCommand(commandHistory, bankRepository, playerRepository);
            directDepositCommand.setContext(depositCommandContext);
            return directDepositCommand;
        }

        if(Objects.equals(context.get("type"), "арена")) {
            InMemoryPlayerRepository playerRepository = new InMemoryPlayerRepository();
            Player player = new Player();
            player.bank = new Bank(100);
            playerRepository.player = player;
            ArenaFightDepositCommand arenaFightDepositCommand = new ArenaFightDepositCommand(commandHistory, playerRepository, new GreatArenaWinStrategy());
            arenaFightDepositCommand.setPlayerId(1);
            return arenaFightDepositCommand;
        }

        throw new RuntimeException(String.format("Команда %s не реализована", context.get("type")));
    }
}
