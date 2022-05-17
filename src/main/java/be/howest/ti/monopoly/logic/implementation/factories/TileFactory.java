package be.howest.ti.monopoly.logic.implementation.factories;

import be.howest.ti.monopoly.logic.implementation.enums.Colors;
import be.howest.ti.monopoly.logic.implementation.tiles.Property;
import be.howest.ti.monopoly.logic.implementation.tiles.Street;
import be.howest.ti.monopoly.logic.implementation.tiles.Tile;

import java.util.ArrayList;
import java.util.List;

public class TileFactory {

    public List<Tile> createTiles() {
        return  new ArrayList<>(List.of(
                new Tile("Go", 0, "Go"),
                new Street("Mediterranean", 1, "street", 2, 60, 30, 2, Colors.PURPLE, 10, 30, 90, 160, 250, 50),
                new Tile("Community Chest I", 2, "community chest"),
                new Street("Baltic", 3, "street", 2, 60, 30, 4, Colors.PURPLE, 20, 60, 180, 320, 450, 50),
                new Tile("Tax Income", 4, "Tax Income"),
                new Property("Reading RR", 5, "railroad", 4, 200, 100, 25, Colors.BLACK),
                new Street("Oriental", 6, "street", 3, 100, 50, 6, Colors.LIGHTBLUE, 30, 90, 270, 400, 550, 50),
                new Tile("Chance I", 7, "chance"),
                new Street("Vermont", 8, "street", 3, 100, 50, 6, Colors.LIGHTBLUE, 30, 90, 270, 400, 550, 50),
                new Street("Connecticut", 9, "street", 3, 120, 60, 8, Colors.LIGHTBLUE, 40, 100, 300, 450, 600, 50),
                new Tile("Jail", 10, "Jail"),
                new Street("Saint Charles Place", 11, "street", 3, 140, 70, 10, Colors.VIOLET, 50, 150, 450, 625, 750, 100),
                new Property("Electric Company", 12, "utility", 2, 150, 75, 0, Colors.WHITE),
                new Street("States", 13, "street", 3, 140, 70, 10, Colors.VIOLET, 50, 150, 450, 625, 750, 100),
                new Street("Virginia", 14, "street", 3, 160, 80, 12, Colors.VIOLET, 60, 180, 500, 700, 900, 100),
                new Property("Pennsylvania RR", 15, "railroad", 4, 200, 100, 25, Colors.BLACK),
                new Street("Saint James", 16, "street", 3, 180, 90, 14, Colors.ORANGE, 70, 200, 550, 750, 950, 100),
                new Tile("Community Chest II", 17, "community chest"),
                new Street("Tennessee", 18, "street", 3, 180, 90, 14, Colors.ORANGE, 70, 200, 550, 750, 950, 100),
                new Street("New York", 19, "street", 3, 200, 100, 16, Colors.ORANGE, 80, 220, 600, 800, 1000, 100),
                new Tile("Free Parking", 20, "Free Parking"),
                new Street("Kentucky Avenue", 21, "street", 3, 220, 110, 18, Colors.RED, 90, 250, 700, 875, 1050, 150),
                new Tile("Chance II", 22, "chance"),
                new Street("Indiana Avenue", 23, "street", 3, 220, 110, 18, Colors.RED, 90, 250, 700, 875, 1050, 150),
                new Street("Illinois Avenue", 24, "street", 3, 240, 120, 20, Colors.RED, 100, 300, 750, 925, 1100, 150),
                new Property("Baltimore and Ohio RR", 25, "railroad", 4, 200, 100, 25, Colors.BLACK),
                new Street("Atlantic", 26, "street", 3, 260, 130, 22, Colors.YELLOW, 110, 330, 800, 975, 1150, 150),
                new Street("Ventnor", 27, "street", 3, 260, 130, 22, Colors.YELLOW, 110, 330, 800, 975, 1150, 150),
                new Property("Water Works", 28, "utility", 2, 150, 75, 0, Colors.WHITE),
                new Street("Marvin Gardens", 29, "street", 3, 280, 140, 24, Colors.YELLOW, 120, 360, 850, 1025, 1200, 150),
                new Tile("Go to Jail", 30, "Go to Jail"),
                new Street("Pacific", 31, "street", 3, 300, 150, 26, Colors.DARKGREEN, 130, 390, 900, 1100, 1275, 200),
                new Street("North Carolina", 32, "street", 3, 300, 150, 26, Colors.DARKGREEN, 130, 390, 900, 1100, 1275, 200),
                new Tile("Community Chest III", 33, "community chest"),
                new Street("Pennsylvania", 34, "street", 3, 320, 160, 28, Colors.DARKGREEN, 150, 450, 1000, 1200, 1400, 200),
                new Property("Short Line RR", 35, "railroad", 4, 200, 100, 25, Colors.BLACK),
                new Tile("Chance III", 36, "chance"),
                new Street("Park Place", 37, "street", 2, 350, 175, 35, Colors.DARKBLUE, 175, 500, 1100, 1300, 1500, 200),
                new Tile("Luxury Tax", 38, "Luxury Tax"),
                new Street("Boardwalk", 39, "street", 2, 400, 200, 50, Colors.DARKBLUE, 200, 600, 1400, 1700, 2000, 200)
        ));
    }
}
