package be.howest.ti.monopoly.logic.implementation;

import be.howest.ti.monopoly.logic.exceptions.MonopolyResourceNotFoundException;
import be.howest.ti.monopoly.logic.implementation.factories.TileFactory;
import be.howest.ti.monopoly.logic.implementation.tiles.Tile;

import java.util.List;

public class Helper {

    private Helper() {
        throw new IllegalStateException("Helper class");
    }

    public static Tile getNextTile(String currentTile, int total) {
        List<Tile> tiles = TileFactory.createTiles();
        for (Tile tile : tiles) {
            if (tile.getName().equals(currentTile)) {
                int nextPosition = tile.getPosition() + total;
                if (nextPosition > tiles.size()) nextPosition = 0;
                return tiles.get(nextPosition);
            }
        }
        throw new MonopolyResourceNotFoundException("Can't find next tile.");
    }

    public static boolean isDirectSale(Tile tile, List<Player> players) {
        for (Player player : players) {
            for (PlayerProperty playerProperty : player.getProperties()) {
                if (playerProperty.getName().equals(tile.getName())) return false;
            }
        }
        return true;
    }

    public static boolean isRailRoad(Tile tile) {
        return tile.getType().equals("railroad");
    }

    public static boolean isStreet(Tile tile) {
        return tile.getType().equals("street");
    }

    public static boolean isCard(Tile tile) {
        return tile.getType().equals("chance") || tile.getType().equals("community chest");
    }

    public static boolean isUtility(Tile nextTile) {
        return nextTile.getType().equals("utility");
    }

    public static boolean isTax(Tile nextTile) {
        return nextTile.getType().equals("Tax Income") || nextTile.getType().equals("Luxury Tax");
    }
}
