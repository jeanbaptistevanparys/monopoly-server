package be.howest.ti.monopoly.logic.implementation;

import be.howest.ti.monopoly.logic.exceptions.MonopolyResourceNotFoundException;
import be.howest.ti.monopoly.logic.implementation.factories.TileFactory;
import be.howest.ti.monopoly.logic.implementation.tiles.Property;
import be.howest.ti.monopoly.logic.implementation.tiles.Tile;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HelperTest {

    private Game newGame() {
        Game game = new Game(2, "test");
        Player player1 = new Player("Jarne");
        Player player2 = new Player("Jari");
        game.addPlayer(player1);
        game.addPlayer(player2);
        return game;
    }

    @Test
    void isAlreadyOwnedTrue() {
        Game game = newGame();
        game.getCurrentPlayer().buyProperty((Property) Helper.getTile("Chrome Crib"));
        assertTrue(Helper.isAlreadyOwned((Property) Helper.getTile("Chrome Crib"), game.getPlayers()));
    }

    @Test
    void isAlreadyOwnedFalse() {
        Game game = newGame();
        game.getCurrentPlayer().buyProperty((Property) Helper.getTile("Firefox Fountain"));
        assertFalse(Helper.isAlreadyOwned((Property) Helper.getTile("Chrome Crib"), game.getPlayers()));
    }

    @Test
    void isDirectSaleTrue() {
        Game game = newGame();
        game.getCurrentPlayer().buyProperty((Property) Helper.getTile("Chrome Crib"));
        assertFalse(Helper.isDirectSale(Helper.getTile("Chrome Crib"), game.getPlayers()));
    }

    @Test
    void isDirectSaleFalse() {
        Game game = newGame();
        game.getCurrentPlayer().buyProperty((Property) Helper.getTile("Firefox Fountain"));
        assertTrue(Helper.isDirectSale(Helper.getTile("Chrome Crib"), game.getPlayers()));
    }

    @Test
    void isGoToJailTileTrue() {
        Game game = newGame();
        Turn turn = new Turn(game.getCurrentPlayer(), 2, 1);
        game.addTurn(turn);
        Tile nextTile = Helper.getNextTile("Musk Mars", 3);
        assertTrue(Helper.isGoToJail(nextTile, game));
    }

    @Test
    void isGoToJailTurnsTrue() {
        Game game = newGame();
        Turn turn = new Turn(game.getCurrentPlayer(), 2, 2);
        game.addTurn(turn);
        Turn turn2 = new Turn(game.getCurrentPlayer(), 2, 2);
        game.addTurn(turn2);
        Turn turn3 = new Turn(game.getCurrentPlayer(), 2, 2);
        game.addTurn(turn3);
        Tile nextTile = Helper.getNextTile("Musk Mars", 3);
        assertTrue(Helper.isGoToJail(nextTile, game));
    }

    @Test
    void isGoToJailTurnsFalse() {
        Game game = newGame();
        Turn turn = new Turn(game.getCurrentPlayer(), 2, 2);
        game.addTurn(turn);
        Tile nextTile = Helper.getNextTile("Boot", 3);
        assertFalse(Helper.isGoToJail(nextTile, game));
    }

    @Test
    void isProperty() {
        assertTrue(Helper.isProperty(TileFactory.createTiles().get(3)));
        assertTrue(Helper.isProperty(TileFactory.createTiles().get(15)));
        assertFalse(Helper.isProperty(TileFactory.createTiles().get(0)));
    }

    @Test
    void isRailRoad() {
        assertTrue(Helper.isRailRoad(TileFactory.createTiles().get(15)));
        assertTrue(Helper.isRailRoad(TileFactory.createTiles().get(35)));
        assertFalse(Helper.isRailRoad(TileFactory.createTiles().get(37)));
    }

    @Test
    void isStreet() {
        assertTrue(Helper.isStreet(TileFactory.createTiles().get(31)));
        assertTrue(Helper.isStreet(TileFactory.createTiles().get(32)));
        assertFalse(Helper.isStreet(TileFactory.createTiles().get(0)));
    }

    @Test
    void isUtility() {
        assertTrue(Helper.isUtility(TileFactory.createTiles().get(12)));
        assertTrue(Helper.isUtility(TileFactory.createTiles().get(28)));
        assertFalse(Helper.isUtility(TileFactory.createTiles().get(29)));
    }

    @Test
    void isTax() {
        assertTrue(Helper.isTax(TileFactory.createTiles().get(4)));
        assertTrue(Helper.isTax(TileFactory.createTiles().get(38)));
        assertFalse(Helper.isTax(TileFactory.createTiles().get(37)));
    }

    @Test
    void isGo() {
        assertTrue(Helper.isGo(TileFactory.createTiles().get(0)));
        assertFalse(Helper.isGo(TileFactory.createTiles().get(39)));
    }

    @Test
    void isCard() {
        assertTrue(Helper.isCard(TileFactory.createTiles().get(2)));
        assertTrue(Helper.isCard(TileFactory.createTiles().get(17)));
        assertFalse(Helper.isCard(TileFactory.createTiles().get(1)));
    }

    @Test
    void NotFoundTile() {
        assertThrows(
                MonopolyResourceNotFoundException.class,
                () -> Helper.getTile("hello")
        );
    }

    @Test
    void NotFoundNextTile() {
        assertThrows(
                MonopolyResourceNotFoundException.class,
                () -> Helper.getNextTile("hello", 2)
        );
    }
}