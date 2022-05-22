package be.howest.ti.monopoly.logic.implementation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TurnTest {

    @Test
    void executeTurnGoToJail() {
        Game game = new Game(2, "test");
        Player player1 = new Player("Jarne");
        Player player2 = new Player("Jari");
        game.addPlayer(player1);
        game.addPlayer(player2);
        Turn turn = new Turn(player1, 5, 5);
        turn.executeTurn(game);
        assertEquals("Repair", player1.getCurrentTile());
    }

    @Test
    void executeTurnVisitingParking() {
        Game game = new Game(2, "test");
        Player player1 = new Player("Jarne");
        Player player2 = new Player("Jari");
        game.addPlayer(player1);
        game.addPlayer(player2);
        Turn turn1 = new Turn(player1, 5, 5);
        Turn turn2 = new Turn(player1, 5, 5);
        turn1.executeTurn(game);
        turn2.executeTurn(game);
        assertEquals("Repair", player1.getCurrentTile());
        assertEquals("Just visiting", turn1.getMoves().get(0).getDescription());
    }

    @Test
    void executeTurnRepair() {
        Game game = new Game(2, "test");
        Player player1 = new Player("Jarne");
        Player player2 = new Player("Jari");
        game.addPlayer(player1);
        game.addPlayer(player2);
        player1.goToJail();
        Turn turn1 = new Turn(player1, 6, 2);
        turn1.executeTurn(game);
        assertEquals("Stay in Repair", turn1.getMoves().get(0).getDescription());
    }

    @Test
    void executeTurnRepairDouble() {
        Game game = new Game(2, "test");
        Player player1 = new Player("Jarne");
        Player player2 = new Player("Jari");
        game.addPlayer(player1);
        game.addPlayer(player2);
        player1.goToJail();
        Turn turn1 = new Turn(player1, 5, 5);
        turn1.executeTurn(game);
        assertTrue(turn1.isDouble());
        assertEquals("Lag", turn1.getNextTile().getName());
    }

    @Test
    void executeTurnGetOutFine() {
        Game game = new Game(2, "test");
        Player player1 = new Player("Jarne");
        Player player2 = new Player("Jari");
        game.addPlayer(player1);
        game.addPlayer(player2);
        player1.goToJail();
        Turn turn1 = new Turn(player1, 6, 2);
        Turn turn2 = new Turn(player1, 6, 2);
        Turn turn3 = new Turn(player1, 6, 2);
        turn1.executeTurn(game);
        turn2.executeTurn(game);
        turn3.executeTurn(game);
        assertEquals("Python Place", turn1.getNextTile().getName());
    }

    @Test
    void executeTurnCard() {
        Game game = new Game(2, "test");
        Player player1 = new Player("Jarne");
        Player player2 = new Player("Jari");
        game.addPlayer(player1);
        game.addPlayer(player2);
        Turn turn1 = new Turn(player1, 1, 1);
        turn1.executeTurn(game);
        assertEquals("Community Chest I", turn1.getNextTile().getName());
    }

    @Test
    void executeTurnProperty() {
        Game game = new Game(2, "test");
        Player player1 = new Player("Jarne");
        Player player2 = new Player("Jari");
        game.addPlayer(player1);
        game.addPlayer(player2);
        Turn turn1 = new Turn(player1, 2, 1);
        turn1.executeTurn(game);
        assertEquals("Firefox Fountain", turn1.getNextTile().getName());
        assertEquals("Direct sale", turn1.getMoves().get(0).getDescription());
    }

    @Test
    void executeTurnPropertyPayRent() {
        Game game = new Game(2, "test");
        Player player1 = new Player("Jarne");
        Player player2 = new Player("Jari");
        game.addPlayer(player1);
        game.addPlayer(player2);
        Turn turn1 = new Turn(player1, 2, 1);
        Turn turn2 = new Turn(player2, 2, 1);
        turn1.executeTurn(game);
        game.buyProperty("Jari", "Firefox Fountain");
        turn2.executeTurn(game);
        assertEquals("Firefox Fountain", turn2.getNextTile().getName());
        assertEquals("Should pay rent", turn2.getMoves().get(0).getDescription());
    }
}