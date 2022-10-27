package bank.multithread;

import bank.CommandQueue;
import bank.deposit.DepositCommand;
import bank.deposit.DepositCommandFactory;

import java.util.Map;
import java.util.Random;

public class UserRequestThread extends Thread {
    DepositCommandFactory factory = new DepositCommandFactory();
    private String[] playerIds = {"1", "2", "3", "4", "5"};
    private String[] strategies = {"великая-победа", "обычная-победа"};

    @Override
    public void run() {
        CommandQueue queue = CommandQueue.getInstance();
        DepositCommand command = factory.create(createRandomCommandContext());
        queue.add(command);
    }

    private Map<String, String> createRandomCommandContext() {
        int randomIndex = new Random().nextInt(2);
        if (randomIndex == 1) {
            return createDirectCommandContext();
        } else return createArenaCommandContext();
    }

    private Map<String, String> createDirectCommandContext() {
        return Map.of("gold", "100", "playerId", getRandomPlayerId(), "clanId", "1", "type", "напрямую");
    }

    private Map<String, String> createArenaCommandContext() {
        return Map.of("playerId", "1", "type", "арена", "strategy", getRandomStrategy());
    }

    private String getRandomPlayerId() {
        int randomIndex = new Random().nextInt(playerIds.length);
        return playerIds[randomIndex];
    }

    private String getRandomStrategy() {
        int randomIndex = new Random().nextInt(strategies.length);
        return strategies[randomIndex];
    }
}