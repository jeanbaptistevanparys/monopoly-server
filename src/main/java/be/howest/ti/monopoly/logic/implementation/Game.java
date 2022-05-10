package be.howest.ti.monopoly.logic.implementation;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private static int games = 0;

    private final int numberOfPlayers;
    private final String prefix;
    private boolean started;
    private List<Player> players;
    private final String id;

    public Game(int numberOfPlayers, String prefix) {
        this.numberOfPlayers = numberOfPlayers;
        this.prefix = prefix;
        this.started = false;
        this.players = new ArrayList<>();
        this.id = prefix + "-" + games;
        games += 1;
    }

    public void addPlayer(Player player) {
        players.add(player);
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
