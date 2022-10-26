package bank.repository;

import bank.Bank;
import bank.Player;

public class FilePlayerRepository implements PlayerRepository {
    private final FileClient fileClient;
    private final String pathToFile;
    private final BankRepository bankRepository;

    public FilePlayerRepository(String pathToFile, FileClient fileClient) {
        this.pathToFile = pathToFile;
        this.fileClient = fileClient;
        this.bankRepository = createBankRepository();
    }

    @Override
    public BankRepository createBankRepository() {
        return new FileBankRepository("tmp/banks.txt", new PathFileClient());
    }

    @Override
    public void save(Player player) {
        bankRepository.save(player.bank);

        String line = fileClient.findLineById(pathToFile, player.id);
        if (line.isBlank()) {
            fileClient.writeToFile(player.toString(), pathToFile);
        } else {
            fileClient.replaceLine(pathToFile, line, player.toString());
        }
    }

    @Override
    public Player findById(int id) {
        String playerLine = fileClient.findLineById(pathToFile, id);
        if (playerLine.isBlank()) {
            throw new RuntimeException(String.format("Bank with id %s not found", id));
        }

        return createPlayerFromFileLine(playerLine);
    }

    private Player createPlayerFromFileLine(String playerLine) {
        int playerId = Integer.parseInt(playerLine.split(" ")[0]);
        Bank bank = bankRepository.findById(Integer.parseInt(playerLine.split(" ")[1]));
        int playerGold = Integer.parseInt(playerLine.split(" ")[2]);
        return new Player(playerId, bank, playerGold);
    }

    @Override
    public int count() {
        return fileClient.readAllLines(pathToFile).size();
    }
}
