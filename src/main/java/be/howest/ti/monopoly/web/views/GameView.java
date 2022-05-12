package be.howest.ti.monopoly.web.views;

import be.howest.ti.monopoly.logic.implementation.Game;
import be.howest.ti.monopoly.logic.implementation.Player;

import java.util.ArrayList;
import java.util.List;

public class GameView {
    private final Game game;

    public GameView(Game game) {
        this.game = game;
    }

    public List<String> getPlayers() {
        List<String> playerNames = new ArrayList<>();
        for (Player player : game.getPlayers()) {
            playerNames.add(player.getName());
        }
        return playerNames;
    }

    public int getNumberOfPlayers() {
        return game.getNumberOfPlayers();
    }

    public boolean isStarted() {
        return game.isStarted();
    }

    public String getId() {
        return game.getId();
    }
}
