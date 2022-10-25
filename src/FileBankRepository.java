import java.io.IOException;
import java.util.List;

public class FileBankRepository implements BankRepository {
    private final String pathToFile;
    private final FileClient fileClient;

    public FileBankRepository(String pathToFile, FileClient fileClient) {
        this.fileClient = fileClient;
        this.pathToFile = pathToFile;
    }

    @Override
    public Bank findById(int id) {
        try {
            return createBankFromFileRecord(id);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Bank createBankFromFileRecord(int id) throws IOException {
        List<String> banks = fileClient.readAllLines(pathToFile);

        for (String bank : banks) {
            int bankId = Integer.parseInt(bank.split(" ")[0]);
            int bankGold = Integer.parseInt(bank.split(" ")[1]);

            if (bankId == id) {
                return new Bank(bankId, bankGold);
            }
        }

        throw new RuntimeException(String.format("Bank with id %s not found", id));
    }
}
