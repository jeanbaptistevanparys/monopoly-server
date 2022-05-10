package be.howest.ti.monopoly.logic.implementation;

public class Auction {
    private Player seller;
    private int value;

    public Auction(Player seller, int value) {
        this.seller = seller;
        this.value = value;
    }

    public Player getSeller() {
        return seller;
    }

    public int getValue() {
        return value;
    }
}
