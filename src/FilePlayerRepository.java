import java.util.List;

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

        fileClient.writeToFile(player.toString(), pathToFile);
    }

    @Override
    public Player findById(int id) {
        List<String> players = fileClient.readAllLines(pathToFile);

        for (String player : players) {
            String[] playerData = player.split(" ");
            int playerId = Integer.parseInt(playerData[0]);
            int bankId = Integer.parseInt(playerData[1]);
            int gold = Integer.parseInt(playerData[2]);

            if (playerId == id) {
                Bank foundedBank = bankRepository.findById(bankId);
                return new Player(playerId, foundedBank, gold);
            }
        }

        throw new RuntimeException(String.format("Player with id %s not found", id));
    }
}
