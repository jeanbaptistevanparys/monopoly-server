package be.howest.ti.monopoly.web.views;

import be.howest.ti.monopoly.logic.implementation.Game;
import be.howest.ti.monopoly.logic.implementation.Player;

import java.util.ArrayList;
import java.util.List;

public class GameStateView {

    private final Game game;

    public GameStateView(Game game) {
        this.game = game;
    }

    public int getNumberOfPlayers() {
        return game.getNumberOfPlayers();
    }

    public List<Player> getPlayers() {
        return game.getPlayers();
    }

    public boolean isStarted() {
        return game.isStarted();
    }

    public String getId() {
        return game.getId();
    }
}
