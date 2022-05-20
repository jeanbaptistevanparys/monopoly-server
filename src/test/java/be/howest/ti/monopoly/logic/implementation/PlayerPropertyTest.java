package be.howest.ti.monopoly.logic.implementation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerPropertyTest {

    @Test
    void increaseHouseCount() {
        PlayerProperty playerProperty = new PlayerProperty("Hello");
        playerProperty.increaseHouseCount();
        assertEquals(1, playerProperty.getHouseCount());
    }

    @Test
    void decreaseHouseCount() {
        PlayerProperty playerProperty = new PlayerProperty("Hello");
        playerProperty.increaseHouseCount();
        playerProperty.increaseHouseCount();
        playerProperty.decreaseHouseCount();
        assertEquals(1, playerProperty.getHouseCount());
    }

    @Test
    void increaseHotelCount() {
        PlayerProperty playerProperty = new PlayerProperty("Hello");
        playerProperty.increaseHouseCount();
        playerProperty.increaseHotelCount();
        assertEquals(1, playerProperty.getHotelCount());
        assertEquals(0, playerProperty.getHouseCount());
    }

    @Test
    void decreaseHotelCount() {
        PlayerProperty playerProperty = new PlayerProperty("Hello");
        playerProperty.increaseHotelCount();
        playerProperty.decreaseHotelCount();
        assertEquals(0, playerProperty.getHotelCount());
        assertEquals(4, playerProperty.getHouseCount());
    }

    @Test
    void setMortgage() {
        PlayerProperty playerProperty = new PlayerProperty("Hello");
        playerProperty.setMortgage(true);
        assertTrue(playerProperty.isMortgage());
    }

    @Test
    void deleteHouses() {
        PlayerProperty playerProperty = new PlayerProperty("Hello");
        playerProperty.increaseHouseCount();
        playerProperty.increaseHouseCount();
        playerProperty.increaseHouseCount();
        playerProperty.deleteHouses();
        assertEquals(0, playerProperty.getHouseCount());
    }

    @Test
    void deleteHotels() {
        PlayerProperty playerProperty = new PlayerProperty("Hello");
        playerProperty.increaseHotelCount();
        playerProperty.deleteHotels();
        assertEquals(0, playerProperty.getHotelCount());
    }
}