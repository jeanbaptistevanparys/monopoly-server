package be.howest.ti.monopoly.logic.implementation;

import be.howest.ti.monopoly.logic.exceptions.InsufficientFundsException;
import be.howest.ti.monopoly.logic.implementation.tiles.Property;

import java.util.*;

public class Player {

    private final String name;
    private String currentTile;
    private boolean jailed;
    private int money;
    private boolean bankrupt;
    private int outOfJailFreeCards;
    private String taxSystem;
    private final List<PlayerProperty> properties;

    public Player(String name) {
        this.name = name;
        this.currentTile = "Go";
        this.jailed = false;
        this.money = 1500;
        this.bankrupt = false;
        this.outOfJailFreeCards = 0;
        this.properties = new ArrayList<>();
    }

    public void moveTile(String tile) {
        currentTile = tile;
    }

    public void buyProperty(Property property) {
        if (money >= property.getCost()) {
            properties.add(new PlayerProperty(property.getName()));
            money -= property.getCost();
        } else {
            throw new InsufficientFundsException("Not enough money to buy" + property.getName());
        }
    }

    public String getName() {
        return name;
    }

    public String getCurrentTile() {
        return currentTile;
    }

    public boolean isJailed() {
        return jailed;
    }

    public int getMoney() {
        return money;
    }

    public boolean isBankrupt() {
        return bankrupt;
    }

    public int getOutOfJailFreeCards() {
        return outOfJailFreeCards;
    }

    public List<PlayerProperty> getProperties() {
        return properties;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(name, player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
