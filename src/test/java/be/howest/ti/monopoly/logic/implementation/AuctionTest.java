package be.howest.ti.monopoly.logic.implementation;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AuctionTest {

    @Test
    void addBidOne() {
        Auction auction = new Auction("hello", 0);
        Bid bid = new Bid("Jarne", 100);
        auction.addBid(bid);
        assertEquals(1, auction.getBids().size());
    }

    @Test
    void addBidTwo() {
        Auction auction = new Auction("hello", 0);
        Bid bid1 = new Bid("Jarne", 100);
        Bid bid2 = new Bid("Jari", 200);
        auction.addBid(bid1);
        auction.addBid(bid2);
        assertEquals(2, auction.getBids().size());
    }

    @Test
    void end() {
        List<Player> players = new ArrayList<>(List.of(
                new Player("Jarne"),
                new Player("Jari")
        ));
        Auction auction = new Auction("hello", 0);
        Bid bid = new Bid("Jarne", 100);
        auction.addBid(bid);
        auction.end(players);
        assertEquals(1, players.get(0).getProperties().size());
        assertEquals(1400, players.get(0).getMoney());
    }
}