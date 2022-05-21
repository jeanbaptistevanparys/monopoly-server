package be.howest.ti.monopoly.logic.implementation.tiles;

import be.howest.ti.monopoly.logic.implementation.enums.Colors;

import java.util.List;

public class Street extends Property {

    private final List<Integer> rent;

    public Street(String name, int position, int cost, int mortgage, int groupSize, Colors color, List<Integer> rent) {
        super(name, position, cost, mortgage, rent.get(0), groupSize, color);
        this.rent = rent;
    }

    public int getRentWithOneHouse() {
        return rent.get(1);
    }

    public int getRentWithTwoHouses() {
        return rent.get(2);
    }

    public int getRentWithThreeHouses() {
        return rent.get(3);
    }

    public int getRentWithFourHouses() {
        return rent.get(4);
    }

    public int getRentWithHotel() {
        return rent.get(5);
    }

    public int getHousePrice() {
        return rent.get(6);
    }

    public Colors getStreetColor() {
        return super.getColor();
    }
}

