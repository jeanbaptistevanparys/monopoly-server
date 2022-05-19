package be.howest.ti.monopoly.web.views;

import be.howest.ti.monopoly.logic.implementation.Auction;
import be.howest.ti.monopoly.logic.implementation.Bid;

import java.util.List;

public class AuctionView {

    private final Auction auction;

    public AuctionView(Auction auction) {
        this.auction = auction;
    }

    public String getProperty() {
        return auction.getProperty();
    }

    public int getMin() {
        return auction.getMin();
    }

    public List<Bid> getBids() {
        return auction.getBids();
    }
}
