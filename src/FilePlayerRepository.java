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
    public Player findPlayerById(int id) {
        List<String> players = fileClient.readAllLines(pathToFile);

        for (String player : players) {
            int playerId = Integer.parseInt(player.split(" ")[0]);
            int bankId = Integer.parseInt(player.split(" ")[1]);

            if (playerId == id) {
                Bank foundedBank = bankRepository.findById(bankId);
                return new Player(playerId, foundedBank);
            }
        }

        throw new RuntimeException(String.format("Player with id %s not found", id));
    }
}
