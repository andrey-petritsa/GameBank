package bank;

import bank.deposit.DepositCommand;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class CommandQueue {
    private static CommandQueue instance;
    private Queue<DepositCommand> queue = new ConcurrentLinkedQueue<>();

    private CommandQueue() {
    }

    synchronized public static CommandQueue getInstance() {
        if (instance == null) {
            instance = new CommandQueue();
        }

        return instance;
    }

    public void add(DepositCommand command) {
        queue.offer(command);
    }

    public DepositCommand poll() {
        return queue.poll();
    }
}
