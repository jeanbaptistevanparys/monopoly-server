package be.howest.ti.monopoly.logic;

import be.howest.ti.monopoly.logic.implementation.Player;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @Test
    void spendMoneyNormal() {
        Player p = new Player("Jarne");
        p.spendMoney(500);
        assertEquals(1000, p.getMoney());
    }

    @Test
    void spendMoneyGoInDebt() {
        Player p = new Player("Jarne");
        p.spendMoney(2000);
        assertEquals(500, p.getDebt());
        assertEquals(0, p.getMoney());
    }

    @Test
    void spendMoneyGoOutOfDebt() {
        Player p = new Player("Jarne");
        p.spendMoney(3000);
        p.earnMoney(2000);
        assertEquals(500, p.getMoney());
        assertEquals(0, p.getDebt());
    }

    @Test
    void spendMoneyLowerDebt() {
        Player p = new Player("Jarne");
        p.spendMoney(3000);
        p.earnMoney(500);
        assertEquals(0, p.getMoney());
        assertEquals(1000, p.getDebt());
    }
}