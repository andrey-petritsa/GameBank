import java.io.IOException;

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
        String bankLine = fileClient.findLineById(pathToFile, id);
        if (bankLine.isBlank()) {
            throw new RuntimeException(String.format("Bank with id %s not found", id));
        }

        int bankId = Integer.parseInt(bankLine.split(" ")[0]);
        int bankGold = Integer.parseInt(bankLine.split(" ")[1]);
        return new Bank(bankId, bankGold);
    }

    @Override
    public void save(Bank bank) {
        String line = fileClient.findLineById(pathToFile, bank.id);
        if (line.isBlank()) {
            fileClient.writeToFile(bank.toString(), pathToFile);
        } else {
            fileClient.replaceLine(pathToFile, line, bank.toString());
        }
    }
}
