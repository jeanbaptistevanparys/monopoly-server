package be.howest.ti.monopoly.logic.implementation.cards;

import be.howest.ti.monopoly.logic.implementation.*;
import be.howest.ti.monopoly.logic.implementation.factories.CardFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChangeMoneyCardTest {

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
        Turn turn = new Turn(game.getCurrentPlayer(), 0, 0);
        Move move = new Move(Helper.getTile("Chance I"), "");
        turn.addMove(move);
        Card card = new CardFactory().createChances().get(6);
        card.executeCard(game.getCurrentPlayer(), turn);
        game.addTurn(turn);
        assertEquals(1550, game.getCurrentPlayer().getMoney());
    }
}