package be.howest.ti.monopoly.logic.implementation;

import be.howest.ti.monopoly.logic.exceptions.MonopolyResourceNotFoundException;
import be.howest.ti.monopoly.logic.implementation.factories.TileFactory;
import be.howest.ti.monopoly.logic.implementation.tiles.Property;
import be.howest.ti.monopoly.logic.implementation.tiles.Tile;

import java.util.List;
import java.util.Objects;

public class Helper {

    private Helper() {
        throw new IllegalStateException("Helper class");
    }

    public static List<Tile> getTiles() {
        return TileFactory.createTiles();
    }

    public static Tile getTile(String tileName) {
        for (Tile tile : getTiles()) {
            if (Objects.equals(tile.getName(), tileName)) {
                return tile;
            }
        }
        throw new MonopolyResourceNotFoundException("Did not find the requested tile: " + tileName);
    }

    public static Tile getNextTile(String currentTile, int total) {
        List<Tile> tiles = TileFactory.createTiles();
        for (Tile tile : tiles) {
            if (tile.getName().equals(currentTile)) {
                int nextPosition = tile.getPosition() + total;
                if (nextPosition >= tiles.size()) nextPosition = nextPosition - tiles.size();
                return tiles.get(nextPosition);
            }
        }
        throw new MonopolyResourceNotFoundException("Can't find next tile.");
    }

    public static boolean isAlreadyOwned(Property property, List<Player> players) {
        for (Player player : players) {
            for (PlayerProperty playerProperty : player.getProperties()) {
                if (playerProperty.getProperty().equals(property.getName())) return true;
            }
        }
        return false;
    }

    public static boolean passedGo(String nextTile, String currentTile) {
        return getTile(nextTile).getPosition() <= getTile(currentTile).getPosition() && !getTile(currentTile).getType().equals("Go");
    }

    public static boolean isDirectSale(Tile tile, List<Player> players) {
        for (Player player : players) {
            for (PlayerProperty playerProperty : player.getProperties()) {
                if (playerProperty.getProperty().equals(tile.getName())) return false;
            }
        }
        return true;
    }

    public static boolean isProperty(Tile tile) {
        return isRailRoad(tile) || isUtility(tile) || isStreet(tile);
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

    public static boolean isGoToJail(Tile nextTile) {
        return nextTile.getType().equals("Go to Jail");
    }

    public static boolean isGo(Tile nextTile) {
        return nextTile.getType().equals("Go");
    }
}
