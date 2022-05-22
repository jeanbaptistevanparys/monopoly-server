package be.howest.ti.monopoly.logic.implementation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @Test
    void getOutOfJailFine() {
        Player player1 = new Player("Jarne");
        player1.goToJail();
        player1.getOutOfJailFine();
        assertFalse(player1.isJailed());
    }

    @Test
    void getOutOfJailFree() {
        Player player1 = new Player("Jarne");
        player1.goToJail();
        player1.addOutOfJailFreeCard();
        player1.getOutOfJailFine();
        assertFalse(player1.isJailed());
    }

    @Test
    void addOutOfJailFreeCard() {
        Player player1 = new Player("Jarne");
        player1.addOutOfJailFreeCard();
        assertEquals(1, player1.getGetOutOfJailFreeCards());
    }

    @Test
    void getOutOfJailDouble() {
        Player player1 = new Player("Jarne");
        player1.goToJail();
        player1.getOutOfJailDouble();
        assertFalse(player1.isJailed());
    }

    @Test
    void spendMoney() {
        Player player1 = new Player("Jarne");
        player1.spendMoney(50);
        assertEquals(1450, player1.getMoney());
    }

    @Test
    void giveMoney() {
        Player player1 = new Player("Jarne");
        player1.giveMoney(50);
        assertEquals(1450, player1.getMoney());
    }
}