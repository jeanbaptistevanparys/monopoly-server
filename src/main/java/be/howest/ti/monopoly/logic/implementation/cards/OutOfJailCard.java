package be.howest.ti.monopoly.logic.implementation.cards;

import be.howest.ti.monopoly.logic.implementation.Game;
import be.howest.ti.monopoly.logic.implementation.Player;
import be.howest.ti.monopoly.logic.implementation.Turn;

public class OutOfJailCard extends Card {

    public OutOfJailCard(String description) {
        super(description);
    }

    @Override
    public void executeCard(Player currentPlayer, Game game, Turn turn) {
        currentPlayer.addOutOfJailFreeCard();
    }
}
