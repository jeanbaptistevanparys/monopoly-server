package be.howest.ti.monopoly.logic;

import be.howest.ti.monopoly.logic.implementation.Game;
import be.howest.ti.monopoly.logic.implementation.Tile;

import java.util.List;

public class ServiceAdapter implements IService {

    @Override
    public String getVersion() {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Tile> getTiles() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Tile getTile(int position) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Tile getTile(String name) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Game> getGames() {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Game> getGames(boolean started, int numberOfPlayers, String prefix) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Game createGames(String prefix, int numberOfPlayers) {
        throw new UnsupportedOperationException();
    }

}
