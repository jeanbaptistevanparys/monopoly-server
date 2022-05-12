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
    private int debt;

    public Player(String name) {
        this.name = name;
        this.currentTile = "Go";
        this.jailed = false;
        this.money = 1500;
        this.bankrupt = false;
        this.outOfJailFreeCards = 0;
        this.taxSystem = "ESTIMATE";
        this.properties = new HashSet<>();
        this.debt = 0;
    }

    public void moveTile(String tile) {
        currentTile = tile;
    }

    public void getInJail() {
        if (!jailed) {
            jailed = true;
        } else {
            throw new IllegalMonopolyActionException("Already in jail");
        }
    }

    public void getOutOfJail() {
        if (jailed) {
            jailed = false;
        } else {
            throw new IllegalMonopolyActionException("Already out of jail");
        }
    }

    public void earnMoney(int amount) {
        if (debt > 0 && debt > amount) {
            debt -= amount;
        } else if (debt > 0 && debt < amount){
            money = amount - debt;
            debt = 0;
        } else {
            money += amount;
        }
    }

    public void spendMoney(int amount) {
        if (debt > 0) {
            debt += amount;
        } else if (amount < getMoney()) {
            money -= amount;
        } else {
            debt = amount - getMoney();
            money = 0;
        }
    }

    public void goBankrupt() {
        bankrupt = true;
    }

    public void addOutOfJailFreeCard() {
        outOfJailFreeCards ++;
    }

    public void useOutOfJailFreeCard() {
        if (getOutOfJailFreeCards() > 0) {
            outOfJailFreeCards --;
        } else {
            throw new IllegalMonopolyActionException("Not enough outOfJailFreeCards");
        }
    }

    public void setTaxSystemEstimate() {
        if (Objects.equals(taxSystem, "COMPUTE")) {
            taxSystem = "ESTIMATE";
        } else {
            throw new IllegalMonopolyActionException("Already estimate");
        }
    }

    public void setTaxSystemCompute() {
        if (Objects.equals(taxSystem, "COMPUTE")) {
            taxSystem = "ESTIMATE";
        } else {
            throw new IllegalMonopolyActionException("Already compute");
        }
    }

    public void addProperty(Property property) {
        properties.add(property);
    }

    public void removeProperty(Property property) {
        if (properties.contains(property)) {
            properties.remove(property);
        } else {
            throw new MonopolyResourceNotFoundException("Did not found the property to remove");
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

    public String getTaxSystem() {
        return taxSystem;
    }

    public Set<Property> getProperties() {
        return properties;
    }

    public int getDebt() {
        return debt;
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
