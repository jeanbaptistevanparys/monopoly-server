package be.howest.ti.monopoly.logic.implementation.cards;

import be.howest.ti.monopoly.logic.implementation.*;
import be.howest.ti.monopoly.logic.implementation.tiles.Tile;

public class MoveToTileCard extends Card {

    private final String tileName;

    public MoveToTileCard(String description, String tileName) {
        super(description);
        this.tileName = tileName;
    }

    @Override
    public void executeCard(Player currentPlayer, Turn turn) {
        Tile nextTile = Helper.getTile(tileName);
        currentPlayer.receiveMoney(200);
        turn.addMove(new Move(Helper.getTile("Boot"), "Passed Boot (receive $200)"));
        currentPlayer.moveTile(nextTile.getName());
        turn.addMove(new Move(nextTile, "Moved to " + tileName));
    }
}
