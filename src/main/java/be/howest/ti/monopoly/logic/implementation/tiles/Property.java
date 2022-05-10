package be.howest.ti.monopoly.logic.implementation.tiles;

public class Property extends Tile{
    private final int cost;
    private final int mortgage;
    private final int rent;
    private final int groupSize;
    private final String color;

    public Property(String name, int position, String type, int groupSize, int cost, int mortgage, int rent, String color) {
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

    public String getColor() {
        return color;
    }
}

