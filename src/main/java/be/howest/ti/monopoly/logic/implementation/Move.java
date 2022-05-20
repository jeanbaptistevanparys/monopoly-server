package be.howest.ti.monopoly.logic.implementation;

import be.howest.ti.monopoly.logic.implementation.tiles.Tile;

public class Move {
    private final Tile tile;
    private final String description;

    public Move(Tile tile, String description) {
        this.tile = tile;
        this.description = description;
    }

    public void executeMove(Turn turn) {
        Player player = turn.getPlayer();
        Tile nextTile = turn.getNextTile();
        player.moveTile(nextTile.getName());
    }

    public String getTile() {
        return tile.getName();
    }

    public String getDescription() {
        return description;
    }
}
