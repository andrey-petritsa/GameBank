import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    private static final Scanner in = new Scanner(System.in);
    private static final String[] supportedCommands = {"напрямую", "арена"};
    private static final String[] supportedStrategies = {"великая-победа", "обычная-победа"};

    public static void main(String[] args) {
        DepositCommandFactory factory = new DepositCommandFactory();

        DepositCommand command = factory.create(getCommandContext());
        command.execute();

        System.out.println("Команда успешно выполнена! Проверьте историю");
    }

    private static Map<String, String> getCommandContext() {
        String commandName = askUserToProvideCommandName();

        Map<String, String> context = new HashMap<>();
        if (Objects.equals(commandName, "напрямую")) {
            context = askUserToMakeDirectCommandContext(commandName);
        }

        if (Objects.equals(commandName, "арена")) {
            context = askUserToMakeArenaCommandContext(commandName);
        }
        return context;
    }

    private static String askUserToProvideCommandName() {
        System.out.printf("Тип команды депозита?. Доступные: %s ", String.join(" ", supportedCommands));
        return in.nextLine();
    }

    private static Map<String, String> askUserToMakeDirectCommandContext(String commandName) {
        System.out.print("Сколько добавить золота? ");
        String gold = in.nextLine();
        System.out.print("Идентификатор добавляющего игрока? ");
        String playerId = in.nextLine();
        System.out.print("Идентификатор клана куда добавить? ");
        String clanId = in.nextLine();
        return Map.of("gold", gold, "playerId", playerId, "clanId", clanId, "type", commandName);
    }

    private static Map<String, String> askUserToMakeArenaCommandContext(String commandName) {
        System.out.print("Идентефикатор победившего на арене игрока? ");
        String playerId = in.nextLine();
        System.out.printf("Тип победы?. Доступные: %s ", String.join(" ", supportedStrategies));
        String strategy = in.nextLine();
        return Map.of("playerId", playerId, "strategy", strategy, "type", commandName);
    }
}
