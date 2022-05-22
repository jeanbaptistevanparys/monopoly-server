package be.howest.ti.monopoly.web.views;

import be.howest.ti.monopoly.logic.implementation.Move;
import be.howest.ti.monopoly.logic.implementation.Player;
import be.howest.ti.monopoly.logic.implementation.Turn;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TurnViewTest {

    @Test
    void getRoll() {
        Player player = new Player("Jarne");
        Turn turn = new Turn(player, 2, 2);
        TurnView turnView = new TurnView(turn);
        List<Integer> list = new ArrayList<>();
        list.add(2);
        list.add(2);
        assertEquals(list, turnView.getRoll());
    }

    @Test
    void getPlayer() {
        Player player = new Player("Jarne");
        Turn turn = new Turn(player, 2, 2);
        TurnView turnView = new TurnView(turn);
        assertEquals(player.getName(), turnView.getPlayer());
    }

    @Test
    void isDouble() {
        Player player = new Player("Jarne");
        Turn turn = new Turn(player, 2, 2);
        TurnView turnView = new TurnView(turn);
        assertTrue(turnView.isDouble());
    }

    @Test
    void getMoves() {
        Player player = new Player("Jarne");
        Turn turn = new Turn(player, 2, 2);
        TurnView turnView = new TurnView(turn);
        List<Move> list = new ArrayList<>();
        assertEquals(list, turnView.getMoves());
    }
}