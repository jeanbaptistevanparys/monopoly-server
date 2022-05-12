package be.howest.ti.monopoly.logic.implementation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MonopolyServiceTest {

    MonopolyService service = new MonopolyService();

    @Test
    void joinGame() {
        Game game = service.createGames("test", 2);
        service.joinGame("Name", game.getId());
        assertEquals("Name", game.getPlayers().get(0).getName());
    }
}