import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        DepositCommandFactory factory = new DepositCommandFactory();
        Scanner in = new Scanner(System.in);
        DepositCommand command;
        Map<String, String> context = new HashMap<>();

        System.out.print("Введите название команды депозита: ");
        String commandName = in.nextLine();

        if(Objects.equals(commandName, "напрямую")) {
            System.out.print("Сколько добавить золота?");
            String gold = in.nextLine();
            System.out.print("Идентификатор добавляющего игрока?");
            String playerId = in.nextLine();
            System.out.print("Идентификатор клана куда добавить?");
            String clanId = in.nextLine();
            context = Map.of("gold", gold, "playerId", playerId, "clanId", clanId, "type", commandName);
        }

        if(Objects.equals(commandName, "арена")) {
            System.out.print("Идентефикатор победившего на арене игрока?");
            String playerId = in.nextLine();
            System.out.print("Вид победы");
            String strategy = in.nextLine();
            context = Map.of("playerId", playerId, "strategy", strategy, "type", commandName);
        }

        command = factory.create(context);
        command.execute();

        System.out.println("Команда успешно выполнена!");
    }
}
