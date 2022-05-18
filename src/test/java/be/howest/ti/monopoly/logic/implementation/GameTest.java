package be.howest.ti.monopoly.logic.implementation;

import be.howest.ti.monopoly.logic.implementation.tiles.Tile;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    private Game newGame() {
        Game game = new Game(2, "test", lastDiceRoll);
        Player player1 = new Player("Jarne");
        Player player2 = new Player("Jari");
        game.addPlayer(player1);
        game.addPlayer(player2);
        game.startGame();
        return game;
    }

    @Test
    void goOutOfJailFree() {
        Game game = newGame();
        game.getCurrentPlayer().goToJail();
        assertTrue(game.getCurrentPlayer().isJailed());

        game.getCurrentPlayer().addOutOfJailFreeCards();
        game.getOutOfJailFree();
        assertFalse(game.getCurrentPlayer().isJailed());
        assertEquals(0, game.getCurrentPlayer().getOutOfJailFreeCards());
    }

    @Test
    void goOutOfJailFine() {
        Game game = newGame();
        game.getCurrentPlayer().goToJail();
        assertTrue(game.getCurrentPlayer().isJailed());

        game.getOutOfJailFine();
        assertFalse(game.getCurrentPlayer().isJailed());
        assertEquals(1450, game.getCurrentPlayer().getMoney());
    }

    @Test
    void checkIfGoToJailTrue() {
        Tile tile = new Tile("Go", 0, "Go");
        Game game = newGame();
        Turn turn1 = new Turn(game.getCurrentPlayer().getName(), 2, 2);
        Turn turn2 = new Turn(game.getCurrentPlayer().getName(), 2, 2);
        game.addTurn(turn1);
        game.addTurn(turn2);
        assertTrue(game.checkIfGoToJail(tile, 2, 2));
    }

    @Test
    void checkIfGoToJailFalseByNumber() {
        Tile tile = new Tile("Go", 0, "Go");
        Game game = newGame();
        Turn turn1 = new Turn(game.getCurrentPlayer().getName(), 3, 2);
        Turn turn2 = new Turn(game.getCurrentPlayer().getName(), 2, 2);
        game.addTurn(turn1);
        game.addTurn(turn2);
        assertFalse(game.checkIfGoToJail(tile, 2, 2));
    }

    @Test
    void checkIfGoToJailFalseByName() {
        Tile tile = new Tile("Go", 0, "Go");
        Game game = newGame();
        Turn turn1 = new Turn("JB", 2, 2);
        Turn turn2 = new Turn(game.getCurrentPlayer().getName(), 2, 2);
        game.addTurn(turn1);
        game.addTurn(turn2);
        assertFalse(game.checkIfGoToJail(tile, 2, 2));
    }

    @Test
    void buyProperty() {
        Game game = newGame();
        game.buyProperty("Jarne", "Mediterranean");
        List<PlayerProperty> properties = game.getPlayer("Jarne").getProperties();
        assertEquals(1, properties.size());
        assertEquals(1498, game.getPlayer("Jarne").getMoney());
    }

    @Test
    void buyHouse() {
        Game game = newGame();
        game.buyProperty("Jarne", "Mediterranean");
        game.buyHouse("Jarne", "Mediterranean");
        List<PlayerProperty> properties = game.getPlayer("Jarne").getProperties();
        assertEquals(1, properties.get(0).getHouseCount());
        assertEquals(1448, game.getPlayer("Jarne").getMoney());
    }

    @Test
    void buyHotel() {
        Game game = newGame();
        game.buyProperty("Jarne", "Mediterranean");
        game.buyHouse("Jarne", "Mediterranean");
        game.buyHouse("Jarne", "Mediterranean");
        game.buyHouse("Jarne", "Mediterranean");
        game.buyHouse("Jarne", "Mediterranean");
        game.buyHotel("Jarne", "Mediterranean");
        List<PlayerProperty> properties = game.getPlayer("Jarne").getProperties();
        assertEquals(1, properties.get(0).getHotelCount());
        assertEquals(1248, game.getPlayer("Jarne").getMoney());
    }

    @Test
    void collectDebt() {
        Game game = newGame();
        game.buyProperty("Jarne", "Mediterranean");
        game.buyHouse("Jarne", "Mediterranean");
        game.buyHouse("Jarne", "Mediterranean");
        game.buyHouse("Jarne", "Mediterranean");
        game.collectDebt("Jarne", "Mediterranean", "Jari");
        assertEquals(1438, game.getPlayer("Jarne").getMoney());
        assertEquals(1410, game.getPlayer("Jari").getMoney());

    }
}