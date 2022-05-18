package be.howest.ti.monopoly.logic.implementation;

import be.howest.ti.monopoly.logic.implementation.tiles.Tile;

public class Move {
    private final Tile tile;
    private String description;

    public Move(Tile tile, String description) {
        this.tile = tile;
        this.description = description;
    }

    public Tile getTile() {
        return tile;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
