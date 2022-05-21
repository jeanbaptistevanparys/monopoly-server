package be.howest.ti.monopoly.logic.implementation;

import be.howest.ti.monopoly.logic.implementation.tiles.Tile;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MonopolyServiceTest {

    MonopolyService service = new MonopolyService();

    @Test
    void joinGame() {
        Game game = service.createGames("test", 2);
        service.joinGame("Name", game.getId());
        assertEquals("Name", game.getPlayers().get(0).getName());
    }

    @Test
    void getTilePosition() {
        MonopolyService service = new MonopolyService();
        assertEquals("Boot", service.getTile(0).getName());
    }

    @Test
    void getTileName() {
        MonopolyService service = new MonopolyService();
        assertEquals(0, service.getTile("Boot").getPosition());
    }
}