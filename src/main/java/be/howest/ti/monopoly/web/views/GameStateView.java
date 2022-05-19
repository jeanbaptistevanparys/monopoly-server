package be.howest.ti.monopoly.web.views;

import be.howest.ti.monopoly.logic.implementation.Game;
import be.howest.ti.monopoly.logic.implementation.Player;
import be.howest.ti.monopoly.logic.implementation.tiles.Property;
import be.howest.ti.monopoly.logic.implementation.tiles.Tile;

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
        return game.getTurns();
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

    public boolean isDirectSale() {
        try {
            Tile tile = game.getTile(game.getCurrentPlayer().getCurrentTile());
            if (!game.isProperty(tile)) return false;
            if (game.isAlreadyOwned((Property) tile)) return false;
            return true;
        } catch (Exception ex) {
            return false;
        }
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
