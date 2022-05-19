package be.howest.ti.monopoly.logic.implementation;

public class Bid {

    private final String playerName;
    private final int amount;

    public Bid(String playerName, int amount) {
        this.playerName = playerName;
        this.amount = amount;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getAmount() {
        return amount;
    }
}
