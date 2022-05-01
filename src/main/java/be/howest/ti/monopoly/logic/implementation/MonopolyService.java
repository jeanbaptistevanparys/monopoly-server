package be.howest.ti.monopoly.logic.implementation;

import be.howest.ti.monopoly.logic.ServiceAdapter;
import be.howest.ti.monopoly.logic.exceptions.MonopolyResourceNotFoundException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


public class MonopolyService extends ServiceAdapter {

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
    public List<Game> getGames() {
        return Collections.emptyList();
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
}
