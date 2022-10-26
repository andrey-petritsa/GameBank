import java.util.Map;

public interface AbstractDepositCommandFactory {
    DepositCommand create(Map<String, String> context);
}
