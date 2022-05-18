package be.howest.ti.monopoly.logic.implementation.tiles;

import be.howest.ti.monopoly.logic.implementation.enums.Colors;

public class Property extends Tile{
    private final int cost;
    private final int mortgage;
    private final int rent;
    private final int groupSize;
    private final Colors color;

    public Property(String name, int position, String type, int cost, int mortgage, int rent, int groupSize, Colors color) {
        super(name, position, type);
        this.cost = cost;
        this.mortgage = mortgage;
        this.rent = rent;
        this.groupSize = groupSize;
        this.color = color;
    }

    public int getCost() {
        return cost;
    }

    public int getMortgage() {
        return mortgage;
    }

    public int getRent() {
        return rent;
    }

    public int getGroupSize() {
        return groupSize;
    }

    public Colors getColor() {
        return color;
    }
}

