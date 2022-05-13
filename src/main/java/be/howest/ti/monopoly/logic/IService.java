package be.howest.ti.monopoly.logic;

import be.howest.ti.monopoly.logic.implementation.Auction;
import be.howest.ti.monopoly.logic.implementation.Game;
import be.howest.ti.monopoly.logic.implementation.tiles.Tile;

import java.util.List;
import java.util.Map;

public interface IService {
    String getVersion();
    List<Tile> getTiles();
    Tile getTile(int position);
    Tile getTile(String name);
    List<String> getChance();
    Object getCommunityChest();

    List<Auction> getBankAuctions();
    Object placeBidOnBankAuction();
    List<Auction> getPlayerAuctions();
    Object startPlayerAuction();
    Object placeBidOnPlayerAuction();

    Game getGame(String gameId);
    List<Game> getGames(boolean started, int numberOfPlayers, String prefix);
    Game getDummyGame();

    Object rollDice(String gameId, String playerName);
    Object declareBankruptcy(String gameId, String playerName);
    Game createGames(String prefix, int numberOfPlayers);
    Object joinGame(String playerName, String gameId);
    Object clearGameList();
    Object getOutOfJailFine();
    Object getOutOfJailFree();

    Object buyProperty(String gameId, String playerName, String propertyName);
    Object dontBuyProperty(String gameId, String playerName, String propertyName);

    Object buyHouse(String gameId, String playerName, String propertyName);
    Object sellHouse(String gameId, String playerName, String propertyName);
    Object buyHotel(String gameId, String playerName, String propertyName);
    Object sellHotel(String gameId, String playerName, String propertyName);

    Object useEstimateTax(String gameId, String playerName);
    Object useComputeTax(String gameId, String playerName);
    Object takeMortgage();
    Object settleMortgage();
}
