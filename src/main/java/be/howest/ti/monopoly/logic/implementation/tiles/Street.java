package be.howest.ti.monopoly.logic.implementation.tiles;

import be.howest.ti.monopoly.logic.implementation.enums.Colors;

import java.util.List;

public class Street extends Property {

    private final List<Integer> rent;
    private final int housePrice;

    public Street(String name, int position, int cost, int mortgage, int groupSize, Colors color, List<Integer> rent, int housePrice) {
        super(name, position, cost, mortgage, rent.get(0), groupSize, color);
        this.rent = rent;
        this.housePrice = housePrice;
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
        return housePrice;
    }

    public Colors getStreetColor() {
        return super.getColor();
    }
}

