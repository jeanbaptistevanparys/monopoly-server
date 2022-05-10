package be.howest.ti.monopoly.logic;

import be.howest.ti.monopoly.logic.implementation.Game;
import be.howest.ti.monopoly.logic.implementation.Tile;

import java.util.List;

public interface IService {
    String getVersion();
    List<Tile> getTiles();
    Tile getTile(int position);
    Tile getTile(String name);
    Object getChance();
    Object getCommunityChest();

    Object rollDice(String gameId, String playerName);
    Object declareBankruptcy(String gameId, String playerName);

    List<Game> getGames(boolean started, int numberOfPlayers, String prefix);
    Game createGames(String prefix, int numberOfPlayers);
    Object joinGame(String playerName, String gameId);
    Object clearGameList();

    Object getOutOfJailFine();
    Object getOutOfJailFree();
    Object useEstimateTax(String gameId, String playerName);
    Object useComputeTax(String gameId, String playerName);
}
