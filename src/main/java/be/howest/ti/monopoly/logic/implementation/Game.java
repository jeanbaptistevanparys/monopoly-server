package be.howest.ti.monopoly.logic.implementation;

import be.howest.ti.monopoly.logic.exceptions.MonopolyResourceNotFoundException;
import be.howest.ti.monopoly.logic.implementation.tiles.Street;
import be.howest.ti.monopoly.logic.implementation.tiles.Tile;

import java.util.*;
import be.howest.ti.monopoly.logic.exceptions.IllegalMonopolyActionException;

public class Game {

    private static final Map<Integer, Tile> TILES = new HashMap<>(Map.of(
            0, new Tile("Go", 0, "Go"),
            1, new Street("Mediterranean", 1, "street", 60, 30, 10, 2, Colors.PURPLE, 10,30, 90, 160, 250, 50)
    ));

    private static int games = 1;

    private final int numberOfPlayers;
    private final String prefix;
    private boolean started;
    private final List<Player> players;
    private final String id;
    private Player currentPlayer;
    private boolean canRoll;

    public Game(int numberOfPlayers, String prefix) {
        this.numberOfPlayers = numberOfPlayers;
        this.prefix = prefix;
        this.started = false;
        this.players = new ArrayList<>();
        this.id = prefix + "_" + games;
        games += 1;
        this.currentPlayer = null;
        this.canRoll = true;
    }

    public void startGame() {
        this.started = true;
    }

    public void addPlayer(Player player) {
        if (players.size() == numberOfPlayers) throw new IllegalMonopolyActionException("Game already full");
        if (started) throw new IllegalMonopolyActionException("Game already started");
        players.add(player);
        if (currentPlayer == null) currentPlayer = player;
        if (players.size() == numberOfPlayers) { started = true; }
    }

    public void rollDice() {
        int dice1 = (int) ((Math.random() * (12 - 1)) + 1);
        int dice2 = (int) ((Math.random() * (12 - 1)) + 1);
        int total = dice1 + dice2;
        String nextTile = getNextTile(currentPlayer.getCurrentTile(), total);
        currentPlayer.moveTile(nextTile);
        if (dice1 != dice2) currentPlayer = getNextPlayer();
        if (isProperty(nextTile)) canRoll = false;
    }

    private boolean isProperty(String nextTile) {
        return true;
    }

    private Player getNextPlayer() {
        return null;
    }

    private String getNextTile(String currentTile, int total) {
        for (Tile tile : TILES.values()) {
            if (tile.getName().equals(currentTile)) {
                int nextPosition = tile.getPosition() + total;
                if (nextPosition > TILES.size()) nextPosition = 0;
                return TILES.get(nextPosition).getName();
            }
        }
        throw new MonopolyResourceNotFoundException("Can't find next tile");
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public String getPrefix() {
        return prefix;
    }

    public boolean isStarted() {
        return started;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public String getId() { return id; }
}
