package be.howest.ti.monopoly.logic.implementation;

import be.howest.ti.monopoly.logic.exceptions.MonopolyResourceNotFoundException;
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
        assertTrue(player1.getMoney() >= 50);
        assertEquals(1450, player1.getMoney());
    }

    @Test
    void spendMoneyError() {
        Player player1 = new Player("Jarne");
        assertThrows(
                MonopolyResourceNotFoundException.class,
                () -> player1.spendMoney(1600)
        );
    }

    @Test
    void giveMoney() {
        Player player1 = new Player("Jarne");
        player1.giveMoney(50);
        assertEquals(1450, player1.getMoney());
    }

    @Test
    void goBankrupt() {
        Player player1 = new Player("Jarne");
        player1.giveMoney(50);
        player1.giveMoney(1600);
        assertTrue(player1.isBankrupt());
    }

    @Test
    void getOutOfJailMust() {
        Player player1 = new Player("Jarne");
        player1.giveMoney(50);
        player1.addTrieToGetOutOfJail();
        player1.addTrieToGetOutOfJail();
        player1.addTrieToGetOutOfJail();
        player1.getOutOfJailFine();
        assertTrue(player1.isJailed());
        assertEquals(1450, player1.getMoney());
    }

    @Test
    void getOutOfJailFreeError() {
        Player player1 = new Player("Jarne");
        assertThrows(
                MonopolyResourceNotFoundException.class,
                player1::getOutOfJailFree
        );
    }

}