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
    Game getGame(String gameId);
    List<Game> getGames();
    List<Game> getGames(boolean started, int numberOfPlayers, String prefix);
    Game getDummyGame();
    Object getOutOfJailFine();
    Object getOutOfJailFree();
}
