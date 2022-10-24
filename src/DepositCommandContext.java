public class DepositCommandContext {
    public int gold;
    public int playerId;
    public int clanId;

    public DepositCommandContext(int gold, int playerId, int clanId) {
        this.gold = gold;
        this.playerId = playerId;
        this.clanId = clanId;
    }

    public DepositCommandContext() {}
}
