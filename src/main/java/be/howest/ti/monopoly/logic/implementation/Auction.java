package be.howest.ti.monopoly.logic.implementation;

import be.howest.ti.monopoly.logic.exceptions.IllegalMonopolyActionException;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Auction {
    private final String property;
    private final int min;
    private final List<Bid> bids;
    private final long startMillis;

    public Auction(String property, int min) {
        this.property = property;
        this.min = min;
        this.bids = new ArrayList<>();
        this.startMillis = System.currentTimeMillis();
    }

    public void addBid(Bid bid) {
        if (bid.getAmount() <= min) throw new IllegalMonopolyActionException("Amount is below the minimum");
        if (bids.isEmpty()) {
            bids.add(bid);
        } else {
            if (bid.getAmount() <= getHighestBid().getAmount()) {
                throw new IllegalMonopolyActionException("You have to overbid " + getHighestBid().getAmount());
            } else {
                bids.add(bid);
            }
        }
    }

    public void end(List<Player> players) {
        Bid bid = getHighestBid();
        for (Player player : players) {
            if (bid.getPlayerName().equals(player.getName())) {
                player.addProperty(property);
                player.spendMoney(bid.getAmount());
            }
        }
    }

    private Bid getHighestBid() {
        Bid highest = bids.get(0);
        for (Bid bid : bids) {
            if (bid.getAmount() > highest.getAmount()) highest = bid;
        }
        return highest;
    }

    public boolean checkEnd() {
        return System.currentTimeMillis() >= startMillis + 60 * 1000;
    }

    public String getProperty() {
        return property;
    }

    public int getMin() {
        return min;
    }

    public List<Bid> getBids() {
        return bids;
    }

    public long getStartMillis() {
        return startMillis;
    }
}
