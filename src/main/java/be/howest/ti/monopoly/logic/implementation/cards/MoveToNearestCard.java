package be.howest.ti.monopoly.logic.implementation.cards;

import be.howest.ti.monopoly.logic.exceptions.MonopolyResourceNotFoundException;
import be.howest.ti.monopoly.logic.implementation.*;
import be.howest.ti.monopoly.logic.implementation.tiles.Tile;

public class MoveToNearestCard extends Card {

    private final String nearestType;

    public MoveToNearestCard(String description, String nearestType) {
        super(description);
        this.nearestType = nearestType;
    }

    @Override
    public void executeCard(Player currentPlayer, Turn turn) {
        Tile nextTile = null;
        boolean passedSelf = false;
        for (Tile tile : Helper.getTiles()) {
            if (tile.getName().equals(currentPlayer.getCurrentTile())) passedSelf = true;
            if (passedSelf && tile.getType().equals(nearestType)) {
                nextTile = tile;
                break;
            }
        }
        if (nextTile == null) {
            for (Tile tile : Helper.getTiles()) {
                if (tile.getType().equals(nearestType)) {
                    nextTile = tile;
                    break;
                }
            }
        }
        if (nextTile == null) throw new MonopolyResourceNotFoundException("No such type");
        turn.executeTurn(Helper.getTile(nextTile.getName()));
    }
}
