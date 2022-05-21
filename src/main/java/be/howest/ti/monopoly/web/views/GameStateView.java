package be.howest.ti.monopoly.web.views;

import be.howest.ti.monopoly.logic.implementation.Game;
import be.howest.ti.monopoly.logic.implementation.Player;
import be.howest.ti.monopoly.logic.implementation.Turn;

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

    public int getAvailableHouses() {
        return game.getAvailableHouses();
    }

    public int getAvailableHotels() {
        return game.getAvailableHotels();
    }

    public Object getTurns() {
        List<TurnView> turns = new ArrayList<>();
        for (Turn turn: game.getTurns()) {
            turns.add(new TurnView(turn));
        }
        return turns;
    }

    public List<Integer> getLastDiceRoll() {
        return game.getLastDiceRoll();
    }

    public boolean isCanRoll() {
        return game.isCanRoll();
    }

    public boolean isEnded() {
        return game.isEnded();
    }

    public String getDirectSale() {
        return game.getDirectSale();
    }

    public String getCurrentPlayer() {
        try {
            return game.getCurrentPlayer().getName();
        } catch (Exception ex) {
            return null;
        }
    }

    public String getWinner() {
        try {
            return game.getWinner().getName();
        } catch (Exception ex) {
            return null;
        }
    }
}
