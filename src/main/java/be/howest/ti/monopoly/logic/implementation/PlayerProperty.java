package be.howest.ti.monopoly.logic.implementation;

public class PlayerProperty {

    private final String name;
    private boolean mortgage;
    private int houseCount;
    private int hotelCount;

    public PlayerProperty(String name) {
        this.name = name;
        this.mortgage = false;
        this.houseCount = 0;
        this.hotelCount = 0;
    }

    public void increaseHouseCount() {
        houseCount++;
    }

    public void decreaseHouseCount() {
        houseCount--;
    }

    public void increaseHotelCount() {
        hotelCount++;
        houseCount = 0;
    }

    public void decreaseHotelCount() {
        hotelCount--;
        houseCount = 4;
    }

    public void setMortgage(boolean mortgage) {
        this.mortgage = mortgage;
    }

    public void deleteHouses() {
        houseCount = 0;
    }

    public void deleteHotels() {
        hotelCount = 0;
    }
    public String getName() {
        return name;
    }

    public boolean isMortgage() {
        return mortgage;
    }

    public int getHouseCount() {
        return houseCount;
    }

    public int getHotelCount() {
        return hotelCount;
    }

}
