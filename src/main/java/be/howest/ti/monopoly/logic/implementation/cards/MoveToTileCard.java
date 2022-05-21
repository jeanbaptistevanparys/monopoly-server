package be.howest.ti.monopoly.logic.implementation.cards;

import be.howest.ti.monopoly.logic.implementation.*;

public class MoveToTileCard extends Card {

    private final String tileName;

    public MoveToTileCard(String description, String tileName) {
        super(description);
        this.tileName = tileName;
    }

    @Override
    public void executeCard(Player currentPlayer, Turn turn) {
        turn.executeTurn(Helper.getTile(tileName));
    }
}
