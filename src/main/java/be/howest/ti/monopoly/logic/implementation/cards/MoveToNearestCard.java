package be.howest.ti.monopoly.logic.implementation.cards;

import be.howest.ti.monopoly.logic.exceptions.MonopolyResourceNotFoundException;
import be.howest.ti.monopoly.logic.implementation.Game;
import be.howest.ti.monopoly.logic.implementation.Move;
import be.howest.ti.monopoly.logic.implementation.Player;
import be.howest.ti.monopoly.logic.implementation.Turn;
import be.howest.ti.monopoly.logic.implementation.tiles.Tile;

public class MoveToNearestCard extends Card {

    private final String nearestType;

    public MoveToNearestCard(String description, String nearestType) {
        super(description);
        this.nearestType = nearestType;
    }

    @Override
    public void executeCard(Player currentPlayer, Game game, Turn turn) {
        Tile nextTile = null;
        boolean passedSelf = false;
        for (Tile tile : game.getTiles()) {
            if (tile.getName().equals(currentPlayer.getCurrentTile())) passedSelf = true;
            if (passedSelf && tile.getType().equals(nearestType)) {
                nextTile = tile;
                break;
            }
        }
        if (nextTile == null) {
            for (Tile tile : game.getTiles()) {
                if (tile.getType().equals(nearestType)) {
                    nextTile = tile;
                    break;
                }
            }
        }
        if (nextTile == null) throw new MonopolyResourceNotFoundException("No such type");
        if (game.passedGo(nextTile.getName(), currentPlayer.getCurrentTile())) {
            currentPlayer.receiveMoney(200);
            turn.addMove(new Move(game.getTile("Boot"), "Passed Boot (receive $200)"));
        }
        currentPlayer.moveTile(nextTile.getName());
        turn.addMove(new Move(nextTile, "Moved to nearest " + nearestType));
    }
}
