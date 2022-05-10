package be.howest.ti.monopoly.logic.implementation;

import be.howest.ti.monopoly.logic.ServiceAdapter;
import be.howest.ti.monopoly.logic.exceptions.MonopolyResourceNotFoundException;
import be.howest.ti.monopoly.web.exceptions.ForbiddenAccessException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import javax.validation.metadata.ReturnValueDescriptor;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


public class MonopolyService extends ServiceAdapter {

    private List<Game> games = new ArrayList<>();

    @Override
    public String getVersion() {
        return "0.0.1";
    }

    @Override
    public List<Tile> getTiles() {
        return List.of(
            new Tile(0, "Go"),
            new Tile(1, "Mediterranean"),
            new Tile(2, "Community Chest I"),
            new Tile(3, "Baltic"),
            new Tile(4, "Tax Income")
        );
    }

    @Override
    public Tile getTile(int position) {
        for (Tile tile : getTiles()) {
            if (tile.getPosition() == position) {
                return tile;
            }
        }
        throw new MonopolyResourceNotFoundException("No such tile");
    }

    @Override
    public Tile getTile(String name) {
        for (Tile tile : getTiles()) {
            if (tile.getName().equals(name)) {
                return tile;
            }
        }
        throw new MonopolyResourceNotFoundException("No such tile");
    }

    @Override
    public List<Game> getGames(boolean started, int numberOfPlayers, String prefix) {
        List<Game> games = List.of(
            new Game(2, "group12"),
            new Game(3, "group12"),
            new Game(4, "group12")
        );
        List<Game> res = new ArrayList<>();
        for (Game game : games) {
            if (game.isStarted() == started && game.getNumberOfPlayers() == numberOfPlayers && game.getPrefix() == prefix) {
                res.add(game);
            }
        }
        return res;
    }

    @Override
    public Game createGames(String prefix, int numberOfPlayers) {
        Game game = new Game(3, prefix);
        games.add(game);
        return game;
    }

    @Override
    public List<Chance> getChance() {
        return List.of(
            new Chance("Advance to Boardwalk"),
            new Chance("Advance to Go (Collect $200)"),
            new Chance("Advance to Illinois Avenue. If you pass Go, collect $200"),
            new Chance("Advance to St. Charles Place. If you pass Go, collect $200"),
            new Chance("Advance to the nearest Railroad. If unowned, you may buy it from the Bank. If owned, pay owner twice the rental to which they are otherwise entitled")
        );
    }

    @Override
    public List<CommunityChest> getCommunityChest() {
        return List.of(
                new CommunityChest("Advance to Go (Collect $200)"),
                new CommunityChest("Bank error in your favor. Collect $200"),
                new CommunityChest("Doctor's fee. Pay $50"),
                new CommunityChest("From sale of stock you get $50"),
                new CommunityChest("Get Out of Jail Free")
        );
    }

    @Override
    public List<Auction> getBankAuctions() {
        return List.of(
                new Auction(new Player("Jarne"), 30),
                new Auction(new Player("Jari"), 40),
                new Auction(new Player("Guust"), 10),
                new Auction(new Player("JB"), 500)
                );
    }

    @Override
    public Object placeBidOnBankAuction() {
        return 0;
    }

    @Override
    public List<Auction> getPlayerAuctions() {
        return List.of(
                new Auction(new Player("Jarne"), 30),
                new Auction(new Player("Jarne"), 40),
                new Auction(new Player("Jarne"), 10),
                new Auction(new Player("Jarne"), 500)
        );
    }

    @Override
    public Object startPlayerAuction() {
        return 0;
    }

    @Override
    public Object placeBidOnPlayerAuction() {
        return 0;
    }

    @Override
    public Game getGame(String gameId) {
        return null;
    }

    public Object rollDice(String gameId, String playerName) {
        return null;
    }

    @Override
    public Object joinGame(String playerName, String gameId) {
        return null;
    }

    @Override
    public Object getOutOfJailFine() {
        return null;
    }

    @Override
    public Game getDummyGame() {
        Game dummy = new Game(2,"group-12");
        dummy.addPlayer(new Player("jari meneerke"));
        dummy.addPlayer(new Player("jean meneerke"));
        dummy.addPlayer(new Player("jarne meneerke"));
        return dummy;
    }

    @Override
    public Object clearGameList() {
        games.clear();
        return null;
    }

    @Override
    public Object getOutOfJailFree() {
        return null;
    }

    @Override
    public Object buyProperty(String gameId, String playerName, String propertyName) {
        return null;
    }

    @Override
    public Object declareBankruptcy(String gameId, String playerName) {
        return null;
    }

    @Override
    public Object dontBuyProperty(String gameId, String playerName, String propertyName) {
        return null;
    }

    @Override
    public Object takeMortgage() {
        return null;
    }

    @Override
    public Object buyHouse(String gameId, String playerName, String propertyName) {
        return null;
    }

    @Override
    public Object useEstimateTax(String gameId, String playerName) {
        return null;
    }

    @Override
    public Object sellHouse(String gameId, String playerName, String propertyName) {
        return null;
    }

    @Override
    public Object useComputeTax(String gameId, String playerName) {
        return null;
    }

    @Override
    public Object buyHotel(String gameId, String playerName, String propertyName) {
        return null;
    }

    @Override
    public Object sellHotel(String gameId, String playerName, String propertyName) {
        return null;
    }

    @Override
    public Object settleMortgage() {
        return null;
    }
}
