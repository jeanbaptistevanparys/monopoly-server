package be.howest.ti.monopoly.logic.implementation.cards;

import be.howest.ti.monopoly.logic.implementation.Game;
import be.howest.ti.monopoly.logic.implementation.Player;
import be.howest.ti.monopoly.logic.implementation.Turn;
import be.howest.ti.monopoly.logic.implementation.factories.CardFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MoveToTileCardTest {
    private Game newGame() {
        Game game = new Game(2, "test");
        Player player1 = new Player("Jarne");
        Player player2 = new Player("Jari");
        game.addPlayer(player1);
        game.addPlayer(player2);
        return game;
    }

    @Test
    void executeCard() {
        Game game = newGame();
        Turn turn = new Turn(new Player("Jarne"), 4, 3);
        game.getCurrentPlayer().moveTile("Chance I");
        Card card = new CardFactory().createChances().get(1);
        card.executeCard(game.getCurrentPlayer(), turn);
        assertEquals("Boot" ,game.getCurrentPlayer().getCurrentTile());
        assertEquals(1700, game.getCurrentPlayer().getMoney());
    }
}