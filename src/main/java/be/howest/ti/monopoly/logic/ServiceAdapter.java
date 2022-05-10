package be.howest.ti.monopoly.logic;

import be.howest.ti.monopoly.logic.implementation.Game;
import be.howest.ti.monopoly.logic.implementation.tiles.Tile;

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
    public Object getChance() {
        throw new UnsupportedOperationException();
    }

    public List<Game> getGames() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Game createGames(String prefix, int numberOfPlayers) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object getCommunityChest() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Game getGame(String gameId) {
        throw new UnsupportedOperationException();
    }

    public List<Game> getGames( boolean started, int numberOfPlayers, String prefix){
        throw new UnsupportedOperationException();
    }
    public Object rollDice(String gameId, String playerName) {
        throw new UnsupportedOperationException();
    }

    public Object joinGame(String playerName, String gameId) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object getOutOfJailFine() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Game getDummyGame() {
        throw new UnsupportedOperationException();
    }

    public Object declareBankruptcy(String gameId, String playerName) {
        throw new UnsupportedOperationException();
    }

    public Object clearGameList() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object getOutOfJailFree() {
        throw new UnsupportedOperationException();
    }

}
