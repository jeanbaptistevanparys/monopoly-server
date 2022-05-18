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
                new Tile("Boot", 0, "Go"),
                new Street("Chrome Crib", 1, "street", 60, 30, 2, 2, Colors.PURPLE, 10, 30, 90, 160, 250, 50),
                new Tile("Community Chest I", 2, "community chest"),
                new Street("Firefox Fountain", 3, "street", 60, 30, 4, 2, Colors.PURPLE, 20, 60, 180, 320, 450, 50),
                new Tile("Tax Income", 4, "Tax Income"),
                new Property("Packet Filtering FW", 5, "railroad", 200, 100, 25, 4, Colors.BLACK),
                new Street("CPU Site", 6, "street", 100, 50, 6, 3, Colors.LIGHTBLUE, 30, 90, 270, 400, 550, 50),
                new Tile("Chance I", 7, "chance"),
                new Street("GPU Jail", 8, "street", 100, 50, 6, 3, Colors.LIGHTBLUE, 30, 90, 270, 400, 550, 50),
                new Street("RAM Road", 9, "street", 120, 60, 8, 3, Colors.LIGHTBLUE, 40, 100, 300, 450, 600, 50),
                new Tile("Repair", 10, "Jail"),
                new Street("Linux Land", 11, "street", 140, 70, 10, 3, Colors.VIOLET, 50, 150, 450, 625, 750, 100),
                new Property("Electric Company", 12, "utility", 150, 75, 50, 2, Colors.WHITE),
                new Street("Windows World", 13, "street", 140, 70, 10, 3, Colors.VIOLET, 50, 150, 450, 625, 750, 100),
                new Street("MacOS Multiverse", 14, "street", 160, 80, 12, 3, Colors.VIOLET, 60, 180, 500, 700, 900, 100),
                new Property("Circuit-Level Gateway FW", 15, "railroad", 200, 100, 25, 4, Colors.BLACK),
                new Street("Java Jacuzzi", 16, "street", 180, 90, 14, 3, Colors.ORANGE, 70, 200, 550, 750, 950, 100),
                new Tile("Community Chest II", 17, "community chest"),
                new Street("Python Place", 18, "street", 180, 90, 14, 3, Colors.ORANGE, 70, 200, 550, 750, 950, 100),
                new Street("PHP Paradise", 19, "street", 200, 100, 16, 3, Colors.ORANGE, 80, 220, 600, 800, 1000, 100),
                new Tile("Lag", 20, "Free Parking"),
                new Street("Vue Village", 21, "street", 220, 110, 18, 3, Colors.RED, 90, 250, 700, 875, 1050, 150),
                new Tile("Chance II", 22, "chance"),
                new Street("React Realm", 23, "street", 220, 110, 18, 3, Colors.RED, 90, 250, 700, 875, 1050, 150),
                new Street("Laravel Lab", 24, "street", 240, 120, 20, 3, Colors.RED, 100, 300, 750, 925, 1100, 150),
                new Property("Stateful Inspection FW", 25, "railroad", 200, 100, 25, 4, Colors.BLACK),
                new Street("Bezos’ Business", 26, "street", 260, 130, 22, 3, Colors.YELLOW, 110, 330, 800, 975, 1150, 150),
                new Street("Musk’s Mars", 27, "street", 260, 130, 22, 3, Colors.YELLOW, 110, 330, 800, 975, 1150, 150),
                new Property("Water Works", 28, "utility", 150, 75, 50, 2, Colors.WHITE),
                new Street("Mark’s Metaverse", 29, "street", 280, 140, 24, 3, Colors.YELLOW, 120, 360, 850, 1025, 1200, 150),
                new Tile("Go to Repair", 30, "Go to Jail"),
                new Street("Microsoft Mine", 31, "street", 300, 150, 26, 3, Colors.DARKGREEN, 130, 390, 900, 1100, 1275, 200),
                new Street("Apple Alley", 32, "street", 300, 150, 26, 3, Colors.DARKGREEN, 130, 390, 900, 1100, 1275, 200),
                new Tile("Community Chest III", 33, "community chest"),
                new Street("Google Gate", 34, "street", 320, 160, 28, 3, Colors.DARKGREEN, 150, 450, 1000, 1200, 1400, 200),
                new Property("Application-Level Gateway FW", 35, "railroad", 200, 100, 25, 4, Colors.BLACK),
                new Tile("Chance III", 36, "chance"),
                new Street("IOS Island", 37, "street", 350, 175, 35, 2, Colors.DARKBLUE, 175, 500, 1100, 1300, 1500, 200),
                new Tile("Luxury Tax", 38, "Luxury Tax"),
                new Street("Android Avenue", 39, "street", 400, 200, 50, 2, Colors.DARKBLUE, 200, 600, 1400, 1700, 2000, 200)
        ));
    }
}
