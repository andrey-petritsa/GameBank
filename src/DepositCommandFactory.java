import java.util.Map;
import java.util.Objects;

public class DepositCommandFactory implements AbstractDepositCommandFactory {
    private final CommandHistory commandHistory;
    private final FileBankRepository bankRepository;
    private final FilePlayerRepository playerRepository;
    private String log;

    public DepositCommandFactory() {
        PathFileClient fileClient = new PathFileClient();
        commandHistory = new FileCommandHistory("storage/history.txt", fileClient);
        bankRepository = new FileBankRepository("storage/banks.txt", fileClient);
        playerRepository = new FilePlayerRepository("storage/players.txt", fileClient);
    }

    @Override
    public DepositCommand create(Map<String, String> context) {
        if (Objects.equals(context.get("type"), "напрямую")) {
            return createDirectDepositCommand(context);
        }

        if (Objects.equals(context.get("type"), "арена")) {
            return createArenaDepositCommand(context);
        }

        throw new RuntimeException(String.format("Команда %s не реализована", context.get("type")));
    }

    private DirectDepositCommand createDirectDepositCommand(Map<String, String> context) {
        DepositCommandContext depositCommandContext = new DepositCommandContext();
        depositCommandContext.playerId = Integer.parseInt(context.get("playerId"));
        depositCommandContext.clanId = Integer.parseInt(context.get("clanId"));
        depositCommandContext.gold = Integer.parseInt(context.get("gold"));
        DirectDepositCommand directDepositCommand = new DirectDepositCommand(commandHistory, bankRepository, playerRepository);
        directDepositCommand.setContext(depositCommandContext);
        String commandName = directDepositCommand.getClass().getSimpleName();
        this.log = String.format("Создана команда %s", commandName);
        return directDepositCommand;
    }

    private ArenaFightDepositCommand createArenaDepositCommand(Map<String, String> context) {
        ArenaWinStrategy strategy = selectStrategy(context);
        int playerId = Integer.parseInt(context.get("playerId"));
        ArenaFightDepositCommand arenaFightDepositCommand = new ArenaFightDepositCommand(commandHistory, playerRepository, strategy);
        arenaFightDepositCommand.setPlayerId(playerId);

        String commandName = arenaFightDepositCommand.getClass().getSimpleName();
        String strategyName = strategy.getClass().getSimpleName();
        this.log = String.format("Создана команда %s с стратегией %s. Id игрока: %s", commandName, strategyName, playerId);

        return arenaFightDepositCommand;
    }

    private static ArenaWinStrategy selectStrategy(Map<String, String> context) {
        ArenaWinStrategy strategy = null;
        String strategyType = context.get("strategy");
        if (Objects.equals(strategyType, "великая-победа")) {
            strategy = new GreatArenaWinStrategy();
        }
        if (Objects.equals(strategyType, "обычная-победа")) {
            strategy = new CommonArenaWinStrategy();
        }
        return strategy;
    }

    public String log() {
        return this.log;
    }
}
