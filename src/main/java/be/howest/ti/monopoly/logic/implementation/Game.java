package be.howest.ti.monopoly.logic.implementation;

import be.howest.ti.monopoly.logic.exceptions.MonopolyResourceNotFoundException;
import be.howest.ti.monopoly.logic.implementation.tiles.Property;
import be.howest.ti.monopoly.logic.implementation.tiles.Street;
import be.howest.ti.monopoly.logic.implementation.tiles.Tile;

import java.security.SecureRandom;
import java.util.*;
import be.howest.ti.monopoly.logic.exceptions.IllegalMonopolyActionException;

public class Game {

    private static final List<Tile> TILES = new ArrayList<>(List.of(
            new Tile("Go", 0, "Go"),
            new Street("Mediterranean", 1, "street", 2, 60, 30, 2, Colors.PURPLE, 10, 30, 90, 160, 250, 50),
            new Tile("Community Chest I", 2, "community chest"),
            new Street("Baltic", 3, "street", 2, 60, 30, 4, Colors.PURPLE, 20, 60, 180, 320, 450, 50),
            new Tile("Tax Income", 4, "Tax Income"),
            new Property("Reading RR", 5, "railroad", 4, 200, 100, 25, Colors.BLACK),
            new Street("Oriental", 6, "street", 3, 100, 50, 6, Colors.LIGHTBLUE, 30, 90, 270, 400, 550, 50),
            new Tile("Chance I", 7, "chance"),
            new Street("Vermont", 8, "street", 3, 100, 50, 6, Colors.LIGHTBLUE, 30, 90, 270, 400, 550, 50),
            new Street("Connecticut", 9, "street", 3, 120, 60, 8, Colors.LIGHTBLUE, 40, 100, 300, 450, 600, 50),
            new Tile("Jail", 10, "Jail"),
            new Street("Saint Charles Place", 11, "street", 3, 140, 70, 10, Colors.VIOLET, 50, 150, 450, 625, 750, 100),
            new Property("Electric Company", 12, "utility", 2, 150, 75, 0, Colors.WHITE),
            new Street("States", 13, "street", 3, 140, 70, 10, Colors.VIOLET, 50, 150, 450, 625, 750, 100),
            new Street("Virginia", 14, "street", 3, 160, 80, 12, Colors.VIOLET, 60, 180, 500, 700, 900, 100),
            new Property("Pennsylvania RR", 15, "railroad", 4, 200, 100, 25, Colors.BLACK),
            new Street("Saint James", 16, "street", 3, 180, 90, 14, Colors.ORANGE, 70, 200, 550, 750, 950, 100),
            new Tile("Community Chest II", 17, "community chest"),
            new Street("Tennessee", 18, "street", 3, 180, 90, 14, Colors.ORANGE, 70, 200, 550, 750, 950, 100),
            new Street("New York", 19, "street", 3, 200, 100, 16, Colors.ORANGE, 80, 220, 600, 800, 1000, 100),
            new Tile("Free Parking", 20, "Free Parking"),
            new Street("Kentucky Avenue", 21, "street", 3, 220, 110, 18, Colors.RED, 90, 250, 700, 875, 1050, 150),
            new Tile("Chance II", 22, "chance"),
            new Street("Indiana Avenue", 23, "street", 3, 220, 110, 18, Colors.RED, 90, 250, 700, 875, 1050, 150),
            new Street("Illinois Avenue", 24, "street", 3, 240, 120, 20, Colors.RED, 100, 300, 750, 925, 1100, 150),
            new Property("Baltimore and Ohio RR", 25, "railroad", 4, 200, 100, 25, Colors.BLACK),
            new Street("Atlantic", 26, "street", 3, 260, 130, 22, Colors.YELLOW, 110, 330, 800, 975, 1150, 150),
            new Street("Ventnor", 27, "street", 3, 260, 130, 22, Colors.YELLOW, 110, 330, 800, 975, 1150, 150),
            new Property("Water Works", 28, "utility", 2, 150, 75, 0, Colors.WHITE),
            new Street("Marvin Gardens", 29, "street", 3, 280, 140, 24, Colors.YELLOW, 120, 360, 850, 1025, 1200, 150),
            new Tile("Go to Jail", 30, "Go to Jail"),
            new Street("Pacific", 31, "street", 3, 300, 150, 26, Colors.DARKGREEN, 130, 390, 900, 1100, 1275, 200),
            new Street("North Carolina", 32, "street", 3, 300, 150, 26, Colors.DARKGREEN, 130, 390, 900, 1100, 1275, 200),
            new Tile("Community Chest III", 33, "community chest"),
            new Street("Pennsylvania", 34, "street", 3, 320, 160, 28, Colors.DARKGREEN, 150, 450, 1000, 1200, 1400, 200),
            new Property("Short Line RR", 35, "railroad", 4, 200, 100, 25, Colors.BLACK),
            new Tile("Chance III", 36, "chance"),
            new Street("Park Place", 37, "street", 2, 350, 175, 35, Colors.DARKBLUE, 175, 500, 1100, 1300, 1500, 200),
            new Tile("Luxury Tax", 38, "Luxury Tax"),
            new Street("Boardwalk", 39, "street", 2, 400, 200, 50, Colors.DARKBLUE, 200, 600, 1400, 1700, 2000, 200)
    ));

    private static int games = 1;

    private final int numberOfPlayers;
    private final String prefix;
    private final List<Player> players;
    private final String id;
    private boolean started;
    private boolean ended;
    private boolean canRoll;
    private Player currentPlayer;
    private Player winner;
    private int availableHouses;
    private int availableHotels;
    private List<Turn> turns;

    public Game(int numberOfPlayers, String prefix) {
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
        turns = new ArrayList<>();
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
        for (Tile tile : TILES) {
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
        for (Tile tile : TILES) {
            if (tile.getName().equals(currentTile)) {
                int nextPosition = tile.getPosition() + total;
                if (nextPosition > TILES.size()) nextPosition = 0;
                return TILES.get(nextPosition);
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

    public static List<Tile> getTiles() {
        return TILES;
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
