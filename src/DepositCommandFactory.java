import java.util.Map;
import java.util.Objects;

public class DepositCommandFactory implements AbstractDepositCommandFactory {
    private final CommandHistory commandHistory;

    public DepositCommandFactory() {
        commandHistory = new FileCommandHistory("storage/history.txt", new PathFileClient());
    }

    public DepositCommand create(Map<String, String> context) {
        if (Objects.equals(context.get("type"), "напрямую")) {
            DepositCommandContext depositCommandContext = new DepositCommandContext();
            depositCommandContext.playerId = Integer.parseInt(context.get("playerId"));
            depositCommandContext.clanId = Integer.parseInt(context.get("clanId"));
            depositCommandContext.gold = Integer.parseInt(context.get("gold"));
            InMemoryBankRepository bankRepository = new InMemoryBankRepository(new Bank());
            DirectDepositCommand directDepositCommand = new DirectDepositCommand(commandHistory, bankRepository, new InMemoryPlayerRepository());
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
