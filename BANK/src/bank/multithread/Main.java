package bank.multithread;

import bank.CommandQueue;
import bank.deposit.DepositCommand;

public class Main {
    private static final int USERS_IN_SAME_TIME = 100;

    public static void main(String[] args) {
        simulateUserThreads();
        handleQueue();
    }

    private static void simulateUserThreads() {
        for (int i = 0; i < USERS_IN_SAME_TIME; i++) {
            Thread thread = new UserRequestThread();
            thread.start();
        }
    }

    private static void handleQueue() {
        CommandQueue queue = CommandQueue.getInstance();
        DepositCommand command;
        while ((command = queue.poll()) != null) {
            command.execute();
        }
    }
}
