package be.howest.ti.monopoly.logic.implementation;

import be.howest.ti.monopoly.logic.exceptions.IllegalMonopolyActionException;
import be.howest.ti.monopoly.logic.exceptions.MonopolyResourceNotFoundException;
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
    private final Set<Property> properties;

    public Player(String name) {
        this.name = name;
        this.currentTile = "Go";
        this.jailed = false;
        this.money = 1500;
        this.bankrupt = false;
        this.outOfJailFreeCards = 0;
        this.taxSystem = "ESTIMATE";
        this.properties = new HashSet<>();
    }

    public void moveTile(String tile) {
        currentTile = tile;
    }

    public void addProperty(Property property) {
        properties.add(property);
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

    public String getTaxSystem() {
        return taxSystem;
    }

    public Set<Property> getProperties() {
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
