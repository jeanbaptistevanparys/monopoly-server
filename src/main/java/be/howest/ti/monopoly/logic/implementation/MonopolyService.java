package be.howest.ti.monopoly.logic.implementation;

import be.howest.ti.monopoly.logic.ServiceAdapter;
import be.howest.ti.monopoly.logic.exceptions.MonopolyResourceNotFoundException;
import be.howest.ti.monopoly.logic.implementation.tiles.Tile;
;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class MonopolyService extends ServiceAdapter {

    private final List<Game> games = new ArrayList<>();
    private final List<Chance> chances = new ArrayList<>(List.of(
            new Chance("Go to jail Go directly to jail. Do not pass go, do not collect $200."),
            new Chance("Advance to Boardwalk"),
            new Chance("Advance to Go (Collect $200)"),
            new Chance("Advance to Illinois Avenue. If you pass Go, collect $200"),
            new Chance("Advance to St. Charles Place. If you pass Go, collect $200"),
            new Chance("Advance to the nearest Railroad. If unowned, you may buy it from the Bank. If owned, pay owner twice the rental to which they are otherwise entitled"),
            new Chance("Advance token to nearest Utility. If unowned, you may buy it from the Bank. If owned, throw dice and pay owner a total ten times amount thrown."),
            new Chance("Bank pays you dividend of $50"),
            new Chance("Get Out of Jail Free"),
            new Chance("Go Back 3 Spaces"),
            new Chance("Go to Jail. Go directly to Jail, do not pass Go, do not collect $200"),
            new Chance("Make general repairs on all your property. For each house pay $25. For each hotel pay $100"),
            new Chance("Speeding fine $15"),
            new Chance("Take a trip to Reading Railroad. If you pass Go, collect $200"),
            new Chance("You have been elected Chairman of the Board. Pay each player $50"),
            new Chance("Your building loan matures. Collect $150")
            ));

    private final List<CommunityChest> communityChests = new ArrayList<>(List.of(
            new CommunityChest("Advance to Go (Collect $200)"),
            new CommunityChest("Bank error in your favor. Collect $200"),
            new CommunityChest("Doctor's fee. Pay $50"),
            new CommunityChest("From sale of stock you get $50"),
            new CommunityChest("Get Out of Jail Free"),
            new CommunityChest("Go to Jail. Go directly to jail, do not pass Go, do not collect $200"),
            new CommunityChest("Holiday fund matures. Receive $100"),
            new CommunityChest("Income tax refund. Collect $20"),
            new CommunityChest("It is your birthday. Collect $10 from every player"),
            new CommunityChest("Life insurance matures. Collect $100"),
            new CommunityChest("Pay hospital fees of $100 "),
            new CommunityChest("Pay school fees of $50"),
            new CommunityChest("Receive $25 consultancy fee "),
            new CommunityChest("You are assessed for street repair. $40 per house. $115 per hotel"),
            new CommunityChest("You have won second prize in a beauty contest. Collect $10"),
            new CommunityChest("You inherit $100")
    ));

    @Override
    public String getVersion() {
        return "0.0.1";
    }

    @Override
    public List<Tile> getTiles() {
        return Game.getTiles();
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
            if (tile.getNameAsPathParameter().equals(name)) {
                return tile;
            }
        }
        throw new MonopolyResourceNotFoundException("No such tile");
    }

    @Override
    public List<String> getChance() {
        List<String> chanceDescriptions = new ArrayList<>();
        for (Chance chance : chances) {
            chanceDescriptions.add(chance.getDescription());
        }
        return chanceDescriptions;
    }

    @Override
    public List<String> getCommunityChest() {
        List<String> communityChestDescriptions = new ArrayList<>();
        for (CommunityChest chance : communityChests) {
            communityChestDescriptions.add(chance.getDescription());
        }
        return communityChestDescriptions;
    }

    @Override
    public List<Game> getGames(boolean started, int numberOfPlayers, String prefix) {
        List<Game> res = new ArrayList<>();
        for (Game game : games) {
            if (game.isStarted() == started && game.getNumberOfPlayers() == numberOfPlayers && game.getPrefix().equals(prefix)) {
                res.add(game);
            }
        }
        return res;
    }

    @Override
    public Game createGames(String prefix, int numberOfPlayers) {
        Game game = new Game(numberOfPlayers, prefix);
        games.add(game);
        return game;
    }

    @Override
    public Object joinGame(String playerName, String gameId) {
        for (Game game : games) {
            if (game.getId().equals(gameId)) {
                game.addPlayer(new Player(playerName));
            }
        }
        return null;
    }

    @Override
    public Object clearGameList() {
        games.clear();
        return null;
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
        for (Game game : games) {
            if (game.getId().equals(gameId)) {
                return game;
            }
        }
        throw new MonopolyResourceNotFoundException("No such game");
    }

    public Object rollDice(String gameId, String playerName) {
        Game game = getGame(gameId);
        game.rollDice();
        return null;
    }

    @Override
    public Object getOutOfJailFine() {
        return null;
    }

    @Override
    public Game getDummyGame() {
        Game dummy = new Game(3,"group-12");
        dummy.addPlayer(new Player("jari meneerke"));
        dummy.addPlayer(new Player("jean meneerke"));
        dummy.addPlayer(new Player("jarne meneerke"));
        return dummy;
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
