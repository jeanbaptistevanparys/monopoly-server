package be.howest.ti.monopoly.web;

import be.howest.ti.monopoly.logic.IService;
import be.howest.ti.monopoly.logic.ServiceAdapter;
import be.howest.ti.monopoly.logic.implementation.Game;
import be.howest.ti.monopoly.logic.implementation.Tile;

import java.util.List;


public class TestService implements IService {

    IService delegate = new ServiceAdapter();

    void setDelegate(IService delegate) {
        this.delegate = delegate;
    }

    @Override
    public String getVersion() {
        return delegate.getVersion();
    }

    @Override
    public List<Tile> getTiles() {
        return delegate.getTiles();
    }

    @Override
    public Tile getTile(int position) {
        return delegate.getTile(position);
    }

    @Override
    public Tile getTile(String name) {
        return delegate.getTile(name);
    }

    @Override
    public Object getChance() {
        return delegate.getChance();
    }

    @Override
    public Object getCommunityChest() {
        return delegate.getCommunityChest();
    }

    @Override
    public Game getGame(String gameId) {
        return delegate.getGame(gameId);
    }

    public List<Game> getGames() {
        return delegate.getGames();
    }

    @Override
    public List<Game> getGames(boolean started, int numberOfPlayers, String prefix) {
        return delegate.getGames(started, numberOfPlayers, prefix);
    }

    @Override
    public Game getDummyGame() {
        return delegate.getDummyGame();
    }
}
