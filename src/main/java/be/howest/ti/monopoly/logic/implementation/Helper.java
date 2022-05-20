package be.howest.ti.monopoly.logic.implementation;

import be.howest.ti.monopoly.logic.implementation.tiles.Tile;

public abstract class Helper {

    public static boolean isRailRoad(Tile tile) {
        return tile.getType().equals("railroad");
    }
}
