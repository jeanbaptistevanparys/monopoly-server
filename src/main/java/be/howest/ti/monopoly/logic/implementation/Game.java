package be.howest.ti.monopoly.logic.implementation;

import be.howest.ti.monopoly.logic.exceptions.MonopolyResourceNotFoundException;
import be.howest.ti.monopoly.logic.implementation.factories.TileFactory;
import be.howest.ti.monopoly.logic.implementation.tiles.Property;
import be.howest.ti.monopoly.logic.implementation.tiles.Tile;

import java.security.SecureRandom;
import java.util.*;
import be.howest.ti.monopoly.logic.exceptions.IllegalMonopolyActionException;

public class Game {

    private final List<Tile> tiles;

    private static int games = 1;

    private final int numberOfPlayers;
    private final String prefix;
    private final List<Player> players;
    private final List<Turn> turns;
    private final String id;
    private boolean started;
    private boolean ended;
    private boolean canRoll;
    private Player currentPlayer;
    private Player winner;
    private int availableHouses;
    private int availableHotels;

    public Game(int numberOfPlayers, String prefix) {
        this.tiles = new TileFactory().createTiles();
        this.numberOfPlayers = numberOfPlayers;
        this.prefix = prefix;
        this.players = new ArrayList<>();
        this.id = prefix + "_" + games;
        games += 1;
        this.started = false;
        this.ended = false;
        this.canRoll = true;
        this.currentPlayer = null;
        this.winner = null;
        this.availableHouses = 32;
        this.availableHotels = 12;
        this.turns = new ArrayList<>();
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
        if (canRoll && started) {
            SecureRandom random = new SecureRandom();
            int dice1 = random.nextInt(6) + 1;
            int dice2 = random.nextInt(6) + 1;
            int total = dice1 + dice2;
            Tile nextTile = getNextTile(currentPlayer.getCurrentTile(), total);
            if (checkIfGoToJail(nextTile, dice1, dice2)) {
                turnGoToJail(dice1, dice2);
            } else if (currentPlayer.isJailed()) {
                turnInJail(dice1, dice2, nextTile);
            } else {
                currentPlayer.moveTile(nextTile.getName());
                turns.add(new Turn(currentPlayer.getName(), dice1, dice2));
                if (dice1 != dice2) currentPlayer = getNextPlayer();
                if (isProperty(nextTile)) canRoll = false;
            }
        } else throw new IllegalMonopolyActionException("You can't roll your dice");
    }

    public boolean checkIfGoToJail(Tile nextTile, int dice1, int dice2) {
         if (Objects.equals(nextTile.getName(), "Go to Jail")) {
             return true;
         } else {
             Turn lastTurn = turns.get(turns.size() - 1);
             Turn secondLastTurn = turns.get(turns.size() - 2);
             if (!Objects.equals(lastTurn.getName(), currentPlayer.getName()) || !Objects.equals(secondLastTurn.getName(), currentPlayer.getName())) {
                 return false;
             } else
                 return dice1 == dice2 && lastTurn.isDouble() && secondLastTurn.isDouble();
         }
    }

    private void turnGoToJail(int dice1, int dice2) {
        currentPlayer.goToJail();
        turns.add(new Turn(currentPlayer.getName(), dice1, dice2));
        currentPlayer = getNextPlayer();
    }

    private void turnInJail(int dice1, int dice2, Tile nextTile) {
        if (dice1 == dice2) {
            currentPlayer.getOutOfJailDouble();
        } else {
            if (currentPlayer.getTriesToGetOutOfJail() < 3) {
                currentPlayer.addTrieToGetOutOfJail();
                nextTile = getTile("jail");
            } else {
                currentPlayer.getOutOfJailFine();
            }
        }
        currentPlayer.moveTile(nextTile.getName());
        turns.add(new Turn(currentPlayer.getName(), dice1, dice2));
        currentPlayer = getNextPlayer();
        if (isProperty(nextTile)) canRoll = false;
    }

    public Tile getTile(String name) {
        for (Tile tile : tiles) {
            if (Objects.equals(tile.getName(), name)) {
                return tile;
            }
        }
        throw new MonopolyResourceNotFoundException("Did not found the requested tile: " + name);
    }

    public Street getStreet(String name) {
        for (Tile tile : TILES) {
            if (Objects.equals(tile.getName(), name)) {
                return (Street) tile;
            }
        }
        throw new MonopolyResourceNotFoundException("Did not found the requested street: " + name);
    }

    private boolean isProperty(Tile nextTile) {
        try {
            Property property = (Property) nextTile;
            int cost = property.getCost();
            return cost >= 0;
        } catch (Exception ex) {
            return false;
        }
    }

    private Player getNextPlayer() {
        boolean currentFlag = false;
        for (Player player : players) {
            if (currentFlag) return player;
            if (player.getName() == currentPlayer.getName()) {
                currentFlag = true;
            }
        }
        return players.get(0);
    }

    private Tile getNextTile(String currentTile, int total) {
        for (Tile tile : tiles) {
            if (tile.getName().equals(currentTile)) {
                int nextPosition = tile.getPosition() + total;
                if (nextPosition > tiles.size()) nextPosition = 0;
                return tiles.get(nextPosition);
            }
        }
        throw new MonopolyResourceNotFoundException("Can't find next tile.");
    }

    public void buyHouse(String playerName, String propertyName) {
        Player player = getPlayer(playerName);
        PlayerProperty playerProperty = getPlayerProperty(player.getProperties(), propertyName);
        int amount = getStreet(playerProperty.getName()).getHousePrice();
        if (playerProperty.getHouseCount() < 4) {
            if (player.spendMoney(amount)) {
                playerProperty.increaseHouseCount();
            } else {
                throw new MonopolyResourceNotFoundException("Not enough money to buy a house.");
            }
        } else {
            throw new IllegalMonopolyActionException("You already have 4 houses on this property.");
        }
    }

    public void sellHouse(String playerName, String propertyName) {
        Player player = getPlayer(playerName);
        PlayerProperty playerProperty = getPlayerProperty(player.getProperties(), propertyName);
        int amount = getStreet(playerProperty.getName()).getHousePrice();
        if (playerProperty.getHouseCount() > 0) {
            player.receiveMoney(amount);
            playerProperty.decreaseHouseCount();
        } else {
            throw new IllegalMonopolyActionException("You don't have any houses on this property.");
        }
    }

    public void buyHotel(String playerName, String propertyName) {
        Player player = getPlayer(playerName);
        PlayerProperty playerProperty = getPlayerProperty(player.getProperties(), propertyName);
        int amount = getStreet(playerProperty.getName()).getHousePrice();
        if (playerProperty.getHouseCount() == 4 && playerProperty.getHotelCount() == 0) {
            if (player.spendMoney(amount)) {
                playerProperty.increaseHotelCount();
            } else {
                throw new MonopolyResourceNotFoundException("Not enough money to buy a hotel.");
            }
        } else {
            throw new IllegalMonopolyActionException("You already have a hotel or you have not enough houses on this property.");
        }
    }

    public void sellHotel(String playerName, String propertyName) {
        Player player = getPlayer(playerName);
        PlayerProperty playerProperty = getPlayerProperty(player.getProperties(), propertyName);
        int amount = getStreet(playerProperty.getName()).getHousePrice();
        if (playerProperty.getHotelCount() == 1) {
            player.receiveMoney(amount);
            playerProperty.decreaseHotelCount();
        } else {
            throw new IllegalMonopolyActionException("You don't have a hotel on this property.");
        }
    }

    public Player getPlayer(String playerName) {
        for (Player player : players) {
            if (player.getName().equals(playerName)) {
                return player;
            }
        }
        throw new MonopolyResourceNotFoundException("Did not found the requested player.");
    }

    public PlayerProperty getPlayerProperty(List<PlayerProperty> playerProperties, String propertyName) {
        for (PlayerProperty playerProperty : playerProperties) {
            if (playerProperty.getName().equals(propertyName)) {
                return playerProperty;
            }
        }
        throw new MonopolyResourceNotFoundException("Did not found the requested playerProperty.");
    }

    public void setCanRoll(boolean canRoll) {
        this.canRoll = canRoll;
    }

    public void addTurn(Turn turn) {
        turns.add(turn);
    }

    public List<Tile> getTiles() {
        return tiles;
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

    public static int getGames() {
        return games;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public boolean isCanRoll() {
        return canRoll;
    }

    public boolean isEnded() {
        return ended;
    }

    public Player getWinner() {
        return winner;
    }

    public int getAvailableHouses() {
        return availableHouses;
    }

    public int getAvailableHotels() {
        return availableHotels;
    }

    public List<Turn> getTurns() {
        return turns;
    }

    public void getOutOfJailFine() {
        currentPlayer.getOutOfJailFine();
    }

    public void getOutOfJailFree() {
        currentPlayer.getOutOfJailFree();
    }
}
