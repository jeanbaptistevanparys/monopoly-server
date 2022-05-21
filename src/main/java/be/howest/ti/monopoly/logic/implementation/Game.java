package be.howest.ti.monopoly.logic.implementation;

import be.howest.ti.monopoly.logic.exceptions.MonopolyResourceNotFoundException;
import be.howest.ti.monopoly.logic.implementation.enums.TaxSystems;
import be.howest.ti.monopoly.logic.implementation.factories.TileFactory;
import be.howest.ti.monopoly.logic.implementation.tiles.Property;
import be.howest.ti.monopoly.logic.implementation.tiles.Street;
import be.howest.ti.monopoly.logic.implementation.tiles.Tile;

import java.security.SecureRandom;
import java.util.*;
import be.howest.ti.monopoly.logic.exceptions.IllegalMonopolyActionException;

public class Game {

    private final List<Tile> tiles;

    private static int games = 1;

    private final int numberOfPlayers;
    private final String prefix;
    private final String id;
    private final List<Player> players;
    private final List<Turn> turns;
    private final List<Auction> auctions;
    private boolean started;
    private boolean ended;
    private boolean canRoll;
    private Player currentPlayer;
    private Player winner;
    private int availableHouses;
    private int availableHotels;

    public Game(int numberOfPlayers, String prefix) {
        this.tiles = TileFactory.createTiles();
        this.numberOfPlayers = numberOfPlayers;
        this.prefix = prefix;
        this.id = prefix + "_" + games;
        games ++;

        this.players = new ArrayList<>();
        this.turns = new ArrayList<>();
        this.auctions = new ArrayList<>();
        this.started = false;
        this.ended = false;
        this.canRoll = true;
        this.currentPlayer = null;
        this.winner = null;
        this.availableHouses = 32;
        this.availableHotels = 12;
    }

    public void addPlayer(Player player) {
        if (players.size() == numberOfPlayers) throw new IllegalMonopolyActionException("Game already full");
        if (started) throw new IllegalMonopolyActionException("Game already started");
        players.add(player);
        if (currentPlayer == null) currentPlayer = player;
        if (players.size() == numberOfPlayers) started = true;
    }

    public void addAuction(Auction auction) {
        auctions.add(auction);
    }

    public void rollDice(String playerName) {
        if (canRoll && started && currentPlayer.getName().equals(playerName)) {
            SecureRandom random = new SecureRandom();
            int dice1 = random.nextInt(6) + 1;
            int dice2 = random.nextInt(6) + 1;
            Turn turn = new Turn(currentPlayer, dice1, dice2);
            turn.executeTurn(this);
            turns.add(turn);
            if (dice1 != dice2) changeCurrentPlayer();
        } else throw new IllegalMonopolyActionException("You can't roll your dice");
    }

    private void changeCurrentPlayer() {
        currentPlayer = getNextPlayer();
        if (currentPlayer.getMoney() < 0) declareBankruptcy(currentPlayer.getName());
    }

    private Player getNextPlayer() {
        List<Player> activePlayers = getActivePlayers();
        boolean passed = false;
        for (Player player : activePlayers) {
            if (passed) {
                return player;
            }
            if (Objects.equals(player.getName(), currentPlayer.getName())) {
                passed = true;
            }
        }
        return activePlayers.get(0);
    }


    private List<Player> getActivePlayers() {
        List<Player> activePlayers = new ArrayList<>();
        for (Player player : players) {
            if (!player.isBankrupt()) {
                activePlayers.add(player);
            }
        }
        return activePlayers;
    }

    public void buyProperty(String playerName, String propertyName) {
        Player player = getPlayer(playerName);
        Tile tile = Helper.getTile(propertyName);
        if (Helper.isAlreadyOwned(getProperty(propertyName), players)) throw new IllegalMonopolyActionException("Already owned");
        player.buyProperty((Property) tile);
        setCanRoll(true);
    }

    public void buyHouse(String playerName, String propertyName) {
        if (availableHouses > 0) {
            Player player = getPlayer(playerName);
            PlayerProperty playerProperty = getPlayerProperty(player.getProperties(), propertyName);
            int amount = getStreet(playerProperty.getName()).getHousePrice();
            if (playerHasFullStreet(player, propertyName)) {
                if (playerProperty.getHouseCount() < 4) {
                    player.spendMoney(amount);
                    playerProperty.increaseHouseCount();
                    availableHouses--;
                } else {
                    throw new IllegalMonopolyActionException("You already have 4 houses on this property.");
                }
            } else {
                throw new IllegalMonopolyActionException("You don't have all the properties of the street.");
            }
        } else {
            throw new IllegalMonopolyActionException("There are no more houses left.");
        }
    }

    public void sellHouse(String playerName, String propertyName) {
        Player player = getPlayer(playerName);
        PlayerProperty playerProperty = getPlayerProperty(player.getProperties(), propertyName);
        int amount = getStreet(playerProperty.getName()).getHousePrice();
        if (playerProperty.getHouseCount() > 0) {
            player.receiveMoney(amount);
            playerProperty.decreaseHouseCount();
            availableHouses ++;
        } else {
            throw new IllegalMonopolyActionException("You don't have any houses on this property.");
        }
    }

    public void buyHotel(String playerName, String propertyName) {
        if (availableHotels > 0) {
            Player player = getPlayer(playerName);
            PlayerProperty playerProperty = getPlayerProperty(player.getProperties(), propertyName);

            int amount = getStreet(playerProperty.getName()).getHousePrice();
            if (playerProperty.getHouseCount() == 4) {
                if (playerProperty.getHotelCount() == 0) {
                    player.spendMoney(amount);
                    playerProperty.increaseHotelCount();
                    availableHotels--;
                    availableHouses += 4;
                } else {
                    throw new IllegalMonopolyActionException("You already have a hotel or ");
                }
            } else {
                throw new IllegalMonopolyActionException("You don't have enough houses on this property.");
            }
        } else {
            throw new IllegalMonopolyActionException("There are no more hotels left.");
        }
    }

    public void sellHotel(String playerName, String propertyName) {
        Player player = getPlayer(playerName);
        PlayerProperty playerProperty = getPlayerProperty(player.getProperties(), propertyName);
        int amount = getStreet(playerProperty.getName()).getHousePrice();
        if (playerProperty.getHotelCount() == 1) {
            player.receiveMoney(amount);
            playerProperty.decreaseHotelCount();
            availableHotels ++;
            availableHouses -= 4;
        } else {
            throw new IllegalMonopolyActionException("You don't have a hotel on this property.");
        }
    }

    public boolean playerHasFullStreet(Player player, String propertyName) {
        Street streetToBuild = getStreet(propertyName);
        int groupSize = getStreet(propertyName).getGroupSize();
        int i = 0;
        for (PlayerProperty playerProperty : player.getProperties()) {
            Street street = getStreet(playerProperty.getName());
            if (street.getStreetColor() == streetToBuild.getStreetColor()) {
                i ++;
            }
        }
        return i == groupSize;
    }

    public void collectDebt(String playerName, String propertyName, String debtorName) {
        Player debtor = getPlayer(debtorName);
        Player receiver = getPlayer(playerName);
        if (checkIfYourProperty(receiver, propertyName)) {
            if (debtor.getCurrentTile().equals(propertyName)) {
                int debtValue;
                if (Helper.isRailRoad(getProperty(propertyName))) {
                    debtValue = calculateRailRoadDebt(playerName);
                } else if (getProperty(propertyName).getType().equals("utility")) {
                    debtValue = getProperty(propertyName).getRent();
                } else {
                    int amountOfHouses = getPlayerProperty(receiver.getProperties(), propertyName).getHouseCount();
                    int amountOfHotels = getPlayerProperty(receiver.getProperties(), propertyName).getHotelCount();
                    if (amountOfHotels > 0) {
                        debtValue = getStreet(propertyName).getRentWithHotel();
                    } else {
                        debtValue = getDebtValue(amountOfHouses, propertyName);
                    }
                }
                debtor.giveMoney(debtValue);
                receiver.receiveMoney(debtValue);
            } else {
                throw new IllegalMonopolyActionException("The player is not on your tile");
            }
        } else {
            throw new IllegalMonopolyActionException("Not Your property");
        }
    }

    public boolean checkIfYourProperty(Player receiver, String propertyName) {
        List<PlayerProperty> playerProperties = receiver.getProperties();
        for (PlayerProperty playerProperty : playerProperties) {
            if (playerProperty.getName().equals(propertyName)) {
                return true;
            }
        }
        return false;
    }

    public int calculateRailRoadDebt(String playerName) {
        int amountOfRailroads = 0;
        List<PlayerProperty> playerProperties = getPlayer(playerName).getProperties();
        for (PlayerProperty playerProperty : playerProperties) {
            if (Helper.isRailRoad(getProperty(playerProperty.getName()))) {
                amountOfRailroads ++;
            }
        }
        int amount = 25;
        for (int i = 0; i < amountOfRailroads - 1; i++) {
            amount *= 2;
        }
        return amount;
    }

    public int getDebtValue(int amountOfHouses, String propertyName) {
        int debtValue;
        switch (amountOfHouses) {
            case 1:
                debtValue = getStreet(propertyName).getRentWithOneHouse();
                break;
            case 2:
                debtValue = getStreet(propertyName).getRentWithTwoHouses();
                break;
            case 3:
                debtValue = getStreet(propertyName).getRentWithThreeHouses();
                break;
            case 4:
                debtValue = getStreet(propertyName).getRentWithFourHouses();
                break;
            default:
                debtValue = getStreet(propertyName).getRent();
                break;
        }
        return debtValue;
    }

    public void takeMortgage(String playerName, String propertyName) {
        Player player = getPlayer(playerName);
        Property property = getProperty(propertyName);
        PlayerProperty playerProperty = getPlayerProperty(player.getProperties(), propertyName);
        if (!playerProperty.isMortgage()) {
            if (playerProperty.getHotelCount() == 0 && playerProperty.getHouseCount() == 0) {
                int amount = property.getMortgage();
                player.receiveMoney(amount);
                playerProperty.setMortgage(true);
            } else {
                throw new MonopolyResourceNotFoundException("You first have to sell all the houses end hotels.");
            }
        } else {
            throw new MonopolyResourceNotFoundException("Already mortgaged.");
        }
    }

    public void settleMortgage(String playerName, String propertyName) {
            Player player = getPlayer(playerName);
            Property property = getProperty(propertyName);
            PlayerProperty playerProperty = getPlayerProperty(player.getProperties(), propertyName);
        if (playerProperty.isMortgage()) {
            int amount = property.getMortgage();
            player.spendMoney((int) Math.round(amount + (amount * 0.10)));
            playerProperty.setMortgage(false);
        } else {
            throw new MonopolyResourceNotFoundException("Not mortgaged.");
        }
    }


    public void declareBankruptcy(String playerName) {
        Player deliverer = getPlayer(playerName);
        deliverer.goBankrupt();
        List<Player> activePlayers = getActivePlayers();
        if (activePlayers.size() == 1) {
            endGame();
            winner = activePlayers.get(0);
        }
        dividePossessions(deliverer, activePlayers);
    }

    private void dividePossessions(Player deliverer, List<Player> activePlayers) {
        int amountOfActivePlayers = activePlayers.size();
        int playerCount = 0;
        for (PlayerProperty playerProperty : deliverer.getProperties()) {
            availableHouses += playerProperty.getHouseCount();
            playerProperty.deleteHouses();
            availableHotels += playerProperty.getHotelCount();
            playerProperty.deleteHotels();

            Player receiver = activePlayers.get(playerCount);
            receiver.receiveProperty(playerProperty);

            playerCount ++;
            if (playerCount == amountOfActivePlayers) {
                playerCount = 0;
            }
        }
        deliverer.deleteProperties();

        divideOutOfJailFreeCards(deliverer, activePlayers , playerCount);
    }

    private void divideOutOfJailFreeCards(Player deliverer, List<Player> activePlayers, int playerCount) {
        int amountOfActivePlayers = activePlayers.size();
        for (int i = 0; i < deliverer.getOutOfJailFreeCards(); i++) {
            Player receiver = activePlayers.get(playerCount);
            receiver.addOutOfJailFreeCard();

            playerCount ++;
            if (playerCount == amountOfActivePlayers) {
                playerCount = 0;
            }
        }
        deliverer.deleteOutOfJailFreeCards();
    }

    public Property getProperty(String name) {
        for (Tile tile : tiles) {
            if (Objects.equals(tile.getName(), name)) {
                return (Property) tile;
            }
        }
        throw new MonopolyResourceNotFoundException("Did not find the requested property: " + name);
    }

    public Street getStreet(String name) {
        for (Tile tile : tiles) {
            if (Objects.equals(tile.getName(), name)) {
                return (Street) tile;
            }
        }
        throw new MonopolyResourceNotFoundException("Did not find the requested street: " + name);
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

    public void useComputeTax(String playerName) {
        Player player = getPlayer(playerName);
        player.setTaxSystem(TaxSystems.COMPUTE);
    }

    public void useEstimateTax(String playerName) {
        Player player = getPlayer(playerName);
        player.setTaxSystem(TaxSystems.ESTIMATE);
    }

    public void endGame() {
        ended = true;
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

    public List<Auction> getAuctions() {
        for (Auction auction: auctions) {
            if(auction.checkEnd()) {
                auction.end(players);
                auctions.remove(auction);
            }
        }
        return auctions;
    }

    public List<Integer> getLastDiceRoll() {
        try {
            return getTurns().get(getTurns().size()-1).getRoll();
        } catch (Exception ex) {
            return Collections.emptyList();
        }
    }

    public String getDirectSale() {
        if (canRoll) return null;
        Turn turn = getTurns().get(turns.size()-1);
        Move move = turn.getMoves().get(turn.getMoves().size()-1);
        return move.getTile();
    }
}
