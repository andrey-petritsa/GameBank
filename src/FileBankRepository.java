import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FileBankRepository implements BankRepository {
    private final Path path;

    public FileBankRepository(String pathToFile) {
        path = Paths.get(String.format("%s/banks.txt", pathToFile));
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
        List<String> banks = Files.readAllLines(path);

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
