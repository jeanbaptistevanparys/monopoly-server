package be.howest.ti.monopoly.logic.implementation;

import be.howest.ti.monopoly.logic.exceptions.MonopolyResourceNotFoundException;
import be.howest.ti.monopoly.logic.implementation.cards.Card;
import be.howest.ti.monopoly.logic.implementation.factories.CardFactory;
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
    private final List<Player> players;
    private final List<Turn> turns;
    private final List<Integer> lastDiceRoll;
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
        this.lastDiceRoll = new ArrayList<>();
        this.id = prefix + "_" + games;
        games ++;
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

    public void rollDice(String playerName) {
        if (canRoll && started && currentPlayer.getName().equals(playerName)) {
            SecureRandom random = new SecureRandom();
            int dice1 = random.nextInt(6) + 1;
            int dice2 = random.nextInt(6) + 1;
            int total = dice1 + dice2;
            lastDiceRoll.add(dice1);
            lastDiceRoll.add(dice2);
            Tile nextTile = getNextTile(currentPlayer.getCurrentTile(), total);
            if (checkIfGoToJail(nextTile, dice1, dice2)) {
                turnGoToJail(dice1, dice2, nextTile);
            } else if (currentPlayer.isJailed()) {
                turnInJail(dice1, dice2, nextTile);
            } else {
                turnDefault(dice1, dice2, nextTile);
            }
        } else throw new IllegalMonopolyActionException("You can't roll your dice");
    }

    public boolean checkIfGoToJail(Tile nextTile, int dice1, int dice2) {
         if (Objects.equals(nextTile.getType(), "Go to Jail")) {
             return true;
         } else {
             if (turns.size() >= 2) {
                 Turn lastTurn = turns.get(turns.size() - 1);
                 Turn secondLastTurn = turns.get(turns.size() - 2);
                 if (!Objects.equals(lastTurn.getName(), currentPlayer.getName()) || !Objects.equals(secondLastTurn.getName(), currentPlayer.getName())) {
                     return false;
                 } else
                     return dice1 == dice2 && lastTurn.isDouble() && secondLastTurn.isDouble();
             } else {
                 return false;
             }
         }
    }

    private void turnGoToJail(int dice1, int dice2, Tile nextTile) {
        currentPlayer.goToJail();
        Turn turn = new Turn(currentPlayer.getName(), dice1, dice2);
        turn.addMove(new Move(nextTile, "Go to Repair"));
        turns.add(turn);
        currentPlayer = getNextPlayer();
    }

    private void turnInJail(int dice1, int dice2, Tile nextTile) {
        if (dice1 == dice2) {
            currentPlayer.getOutOfJailDouble();
        } else {
            if (currentPlayer.getTriesToGetOutOfJail() < 3) {
                currentPlayer.addTrieToGetOutOfJail();
                nextTile = getTile("Repair");
            } else {
                currentPlayer.getOutOfJailFine();
            }
        }
        currentPlayer.moveTile(nextTile.getName());
        Turn turn = new Turn(currentPlayer.getName(), dice1, dice2);
        turn.addMove(new Move(nextTile, "In Repair"));
        currentPlayer = getNextPlayer();
        turns.add(turn);
        if (isProperty(nextTile)) canRoll = false;
    }

    private void turnDefault(int dice1, int dice2, Tile nextTile) {
        Turn turn = new Turn(currentPlayer.getName(), dice1, dice2);
        if (passedGo(nextTile.getName(), currentPlayer.getCurrentTile())) {
            currentPlayer.receiveMoney(200);
            turn.addMove(new Move(getTile("Boot"), "Passed Boot (receive $200)"));
        }
        currentPlayer.moveTile(nextTile.getName());
        if (isCard(nextTile)) {
            cardTurn(turn);
        } else if (isProperty(nextTile)) {
            propertyTurn(turn);
        } else if (isTax(nextTile)) {
            taxTurn(turn);
        }
        turns.add(turn);
    }

    private void cardTurn(Turn turn) {
        SecureRandom random = new SecureRandom();
        int number = random.nextInt(14);
        Tile nextTile = getNextTile(currentPlayer.getCurrentTile(), turn.getRoll().get(0) + turn.getRoll().get(1));
        Card card;
        if (nextTile.getType().equals("chance")) card = new CardFactory().createChances().get(number);
        else card = new CardFactory().createCommunityChests().get(number);
        card.executeCard(currentPlayer, this, turn);
        String description = card.getDescription();
        turn.addMove(new Move(nextTile, description));
    }

    private void propertyTurn(Turn turn) {
        Tile nextTile = getNextTile(currentPlayer.getCurrentTile(), turn.getRoll().get(0) + turn.getRoll().get(1));
        String description;
        if (isAlreadyOwned((Property) nextTile)) description = "Should pay rent";
        else description = "Direct sale";
        turn.addMove(new Move(nextTile, description));
        canRoll = false;
    }

    public void taxTurn(Turn turn) {
        int amount = (int) Math.round(currentPlayer.getMoney() * 0.10);
        if (currentPlayer.getCurrentTile().equals("Tax Income")) {
            if (currentPlayer.getTaxSystem().equals("ESTIMATE")) {
                amount = 100;
            }
        } else {
            if (currentPlayer.getTaxSystem().equals("ESTIMATE")) {
                amount = 200;
            }
        }
        currentPlayer.giveMoney(amount);
        String description = "Pay taxes";
        turn.addMove(new Move(getNextTile(currentPlayer.getCurrentTile(), turn.getRoll().get(0) + turn.getRoll().get(1)), description));
    }

    private boolean isCard(Tile nextTile) {
        return nextTile.getType().equals("chance") || nextTile.getType().equals("community chest");
    }

    private boolean isProperty(Tile nextTile) {
        return nextTile.getType().equals("street") || nextTile.getType().equals("utility") || nextTile.getType().equals("railroad");
    }

    private boolean isTax(Tile nextTile) {
        return nextTile.getType().equals("Luxury Tax") || nextTile.getType().equals("Income Tax");
    }

    public boolean passedGo(String nextTile, String currentTile) {
        boolean passed = false;
        for (Tile tile : getTiles()) {
            if (tile.getName().equals(currentTile)) passed = true;
            if (passed) {
                if (tile.getName().equals(nextTile)) return true;
            }
        }
        return true;
    }

    public void buyProperty(String playerName, String propertyName) {
        Player player = getPlayer(playerName);
        Tile tile = getTile(propertyName);
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
            if (playerProperty.getHouseCount() == 4 && playerProperty.getHotelCount() == 0) {
                player.spendMoney(amount);
                playerProperty.increaseHotelCount();
                availableHotels--;
                availableHouses += 4;
            } else {
                throw new IllegalMonopolyActionException("You already have a hotel or you have not enough houses on this property.");
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
        int amountOfHouses = getPlayerProperty(receiver.getProperties(), propertyName).getHouseCount();
        int amountOfHotels = getPlayerProperty(receiver.getProperties(), propertyName).getHotelCount();
        int debtValue;
        if (amountOfHotels > 0) {
            debtValue = getStreet(propertyName).getRentWithHotel();
        } else {
             debtValue = getDebtValue(amountOfHouses, propertyName);
        }
        debtor.giveMoney(debtValue);
        receiver.receiveMoney(debtValue);
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
        dividePossesions(deliverer, activePlayers);
    }

    private void dividePossesions(Player deliverer, List<Player> activePlayers) {
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

    private List<Player> getActivePlayers() {
        List<Player> activePlayers = new ArrayList<>();
        for (Player player : players) {
            if (!player.isBankrupt()) {
                activePlayers.add(player);
            }
        }
        return activePlayers;
    }

    public Tile getTile(String name) {
        for (Tile tile : tiles) {
            if (Objects.equals(tile.getName(), name)) {
                return tile;
            }
        }
        throw new MonopolyResourceNotFoundException("Did not find the requested tile: " + name);
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

    private Player getNextPlayer() {
        List<Player> activePlayers = getActivePlayers();
        boolean currentFlag = false;
        for (Player player : activePlayers) {
            if (currentFlag) return player;
            if (Objects.equals(player.getName(), currentPlayer.getName())) {
                currentFlag = true;
            }
        }
        return activePlayers.get(0);
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

    public boolean isAlreadyOwned(Property property) {
        for (Player player : players) {
            for (PlayerProperty playerProperty : player.getProperties()) {
                if (playerProperty.getName().equals(property.getName())) return true;
            }
        }
        return false;
    }

    public void useComputeTax(String playerName) {
        Player player = getPlayer(playerName);
        player.setTaxSystem("COMPUTE");
    }

    public void useEstimateTax(String playerName) {
        Player player = getPlayer(playerName);
        player.setTaxSystem("ESTIMATE");
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

    public List<Integer> getLastDiceRoll() {
        return lastDiceRoll;
    }
}
