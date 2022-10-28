package bank.repository;

public class TestableFilePlayerRepository extends FilePlayerRepository {
    public TestableFilePlayerRepository(String pathToFile, FileClient fileClient) {
        super(pathToFile, fileClient);
    }

    public BankRepository createBankRepository() {
        return new FileBankRepository("tmp/banks.txt", new PathFileClient());
    }
}
