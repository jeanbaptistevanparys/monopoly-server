package be.howest.ti.monopoly.logic.implementation;

import be.howest.ti.monopoly.logic.ServiceAdapter;
import be.howest.ti.monopoly.logic.exceptions.IllegalMonopolyActionException;
import be.howest.ti.monopoly.logic.exceptions.MonopolyResourceNotFoundException;
import be.howest.ti.monopoly.logic.implementation.cards.Card;
import be.howest.ti.monopoly.logic.implementation.factories.CardFactory;
import be.howest.ti.monopoly.logic.implementation.factories.TileFactory;
import be.howest.ti.monopoly.logic.implementation.tiles.Property;
import be.howest.ti.monopoly.logic.implementation.tiles.Tile;
import io.vertx.core.json.JsonObject;

import java.util.ArrayList;
import java.util.List;

public class MonopolyService extends ServiceAdapter {

    private final List<Game> games = new ArrayList<>();
    private final List<Card> cards = new CardFactory().createChances();
    private final List<Card> communityChests = new CardFactory().createCommunityChests();

    @Override
    public String getVersion() {
        return "0.0.1";
    }

    @Override
    public List<Tile> getTiles() {
        return TileFactory.createTiles();
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
        for (Card card : cards) {
            chanceDescriptions.add(card.getDescription());
        }
        return chanceDescriptions;
    }

    @Override
    public List<String> getCommunityChest() {
        List<String> communityChestDescriptions = new ArrayList<>();
        for (Card chance : communityChests) {
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
                if (game.isStarted()) throw new IllegalMonopolyActionException("Game has already started");
                for (Player player : game.getPlayers()) {
                    if (player.getName().equals(playerName)) throw new IllegalMonopolyActionException("Name is already taken");
                }
                game.addPlayer(new Player(playerName));
                return null;
            }
        }
        throw new MonopolyResourceNotFoundException("Game does not exist");
    }

    @Override
    public Object clearGameList() {
        games.clear();
        return null;
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

    @Override
    public Game getDummyGame() {
        Game dummy = new Game(3,"group-12");
        Player player1 = new Player("Jamie");
        Player player2 = new Player("Walter");
        List<Tile> tiles = TileFactory.createTiles();
        player1.buyProperty((Property) tiles.get(8));
        player2.buyProperty((Property) tiles.get(5));
        player2.buyProperty((Property) tiles.get(19));
        dummy.addPlayer(player1);
        dummy.addPlayer(player2);
        return dummy;
    }

    @Override
    public Object rollDice(String gameId, String playerName) {
        Game game = getGame(gameId);
        game.rollDice(playerName);
        return game;
    }

    @Override
    public Object declareBankruptcy(String gameId, String playerName) {
        Game game = getGame(gameId);
        game.declareBankruptcy(playerName);
        return null;
    }

    @Override
    public Object useEstimateTax(String gameId, String playerName) {
        Game game = getGame(gameId);
        game.useEstimateTax(playerName);
        return null;
    }

    @Override
    public Object useComputeTax(String gameId, String playerName) {
        Game game = getGame(gameId);
        game.useComputeTax(playerName);
        return null;
    }

    @Override
    public Object buyProperty(String gameId, String playerName, String propertyName) {
        propertyName = propertyName.replaceAll("_", " ");
        Game game = getGame(gameId);
        game.buyProperty(playerName, propertyName);
        return new JsonObject()
                .put("property", propertyName)
                .put("purchased", true);
    }

    @Override
    public Object dontBuyProperty(String gameId, String playerName, String propertyName) {
        Game game = getGame(gameId);
        game.setCanRoll(true);
        game.addAuction(new Auction(propertyName, 0));
        return null;
    }

    @Override
    public List<Auction> getBankAuctions(String gameId) {
        Game game = getGame(gameId);
        return game.getAuctions();
    }

    @Override
    public Object placeBidOnBankAuction(String gameId, String propertyName, String bidder, int amount) {
        Game game = getGame(gameId);
        List<Auction> auctions = game.getAuctions();
        for (Auction auction : auctions) {
            if (auction.getProperty().equals(propertyName)) {
                auction.addBid(new Bid(bidder, amount));
                return null;
            }
        }
        throw new MonopolyResourceNotFoundException("No such auction");
    }

    @Override
    public Object getOutOfJailFine(String gameId, String playerName) {
        Game game = getGame(gameId);
        game.getOutOfJailFine();
        return null;
    }

    @Override
    public Object getOutOfJailFree(String gameId, String playerName) {
        Game game = getGame(gameId);
        game.getOutOfJailFree();
        return null;
    }

    @Override
    public Object takeMortgage(String gameId, String playerName, String propertyName) {
        propertyName = propertyName.replaceAll("_", " ");
        Game game = getGame(gameId);
        game.takeMortgage(playerName, propertyName);
        return null;
    }

    @Override
    public Object buyHouse(String gameId, String playerName, String propertyName) {
        propertyName = propertyName.replaceAll("_", " ");
        Game game = getGame(gameId);
        game.buyHouse(playerName, propertyName);
        return null;
    }

    @Override
    public Object sellHouse(String gameId, String playerName, String propertyName) {
        propertyName = propertyName.replaceAll("_", " ");
        Game game = getGame(gameId);
        game.sellHouse(playerName, propertyName);
        return null;
    }

    @Override
    public Object buyHotel(String gameId, String playerName, String propertyName) {
        propertyName = propertyName.replaceAll("_", " ");
        Game game = getGame(gameId);
        game.buyHotel(playerName, propertyName);
        return null;
    }

    @Override
    public Object sellHotel(String gameId, String playerName, String propertyName) {
        propertyName = propertyName.replaceAll("_", " ");
        Game game = getGame(gameId);
        game.sellHotel(playerName, propertyName);
        return null;
    }

    @Override
    public Object settleMortgage(String gameId, String playerName, String propertyName) {
        propertyName = propertyName.replaceAll("_", " ");
        Game game = getGame(gameId);
        game.settleMortgage(playerName, propertyName);
        return null;
    }

    @Override
    public Object collectDebt(String gameId, String playerName, String propertyName, String debtorName) {
        propertyName = propertyName.replaceAll("_", " ");
        Game game = getGame(gameId);
        game.collectDebt(playerName, propertyName, debtorName);
        return null;
    }

    @Override
    public Object trade(String gameId, String playerName) {
        return null;
    }
}
