package be.howest.ti.monopoly.logic.implementation.factories;

import be.howest.ti.monopoly.logic.implementation.enums.Colors;
import be.howest.ti.monopoly.logic.implementation.tiles.Property;
import be.howest.ti.monopoly.logic.implementation.tiles.Street;
import be.howest.ti.monopoly.logic.implementation.tiles.Tile;

import java.util.ArrayList;
import java.util.List;

public class TileFactory {

    public static List<Tile> createTiles() {
        return  new ArrayList<>(List.of(
                new Tile("Boot", 0),
                new Street("Chrome Crib", 1, 60, 30, 2, Colors.PURPLE, List.of(2, 10, 30, 90, 160, 250), 50),
                new Tile("Community Chest I", 2),
                new Street("Firefox Fountain", 3, 60, 30, 4, Colors.PURPLE, List.of(20, 60, 180, 320, 450), 50),
                new Tile("Tax Income", 4),
                new Property("Packet Filtering FW", 5, 200, 100, 25, 4, Colors.BLACK),
                new Street("CPU Site", 6, 100, 50, 6, Colors.LIGHTBLUE, List.of(30, 90, 270, 400, 550), 50),
                new Tile("Chance I", 7),
                new Street("GPU Jail", 8, 100, 50, 3, Colors.LIGHTBLUE, List.of(6, 30, 90, 270, 400, 550), 50),
                new Street("RAM Road", 9, 120, 60, 8, Colors.LIGHTBLUE, List.of(40, 100, 300, 450, 600), 50),
                new Tile("Repair", 10),
                new Street("Linux Land", 11, 140, 70, 10, Colors.VIOLET, List.of(50, 150, 450, 625, 750), 100),
                new Property("Electric Company", 12, 150, 75, 50, 2, Colors.WHITE),
                new Street("Windows World", 13, 140, 70, 10, Colors.VIOLET, List.of(50, 150, 450, 625, 750), 100),
                new Street("MacOS Multiverse", 14, 160, 80, 12, Colors.VIOLET, List.of(60, 180, 500, 700, 900), 100),
                new Property("Circuit-Level Gateway FW", 15, 200, 100, 25, 4, Colors.BLACK),
                new Street("Java Jacuzzi", 16, 180, 90, 14, Colors.ORANGE, List.of(70, 200, 550, 750, 950), 100),
                new Tile("Community Chest II", 17),
                new Street("Python Place", 18, 180, 90, 14, Colors.ORANGE, List.of(70, 200, 550, 750, 950), 100),
                new Street("PHP Paradise", 19, 200, 100, 16, Colors.ORANGE, List.of(80, 220, 600, 800, 1000), 100),
                new Tile("Lag", 20),
                new Street("Vue Village", 21, 220, 110, 18, Colors.RED, List.of(90, 250, 700, 875, 1050), 150),
                new Tile("Chance II", 22),
                new Street("React Realm", 23, 220, 110, 18, Colors.RED, List.of(90, 250, 700, 875, 1050), 150),
                new Street("Laravel Lab", 24, 240, 120, 20, Colors.RED, List.of(100, 300, 750, 925, 1100), 150),
                new Property("Stateful Inspection FW", 25, 200, 100, 25, 4, Colors.BLACK),
                new Street("Bezos’ Business", 26, 260, 130, 22, Colors.YELLOW, List.of(110, 330, 800, 975, 1150), 150),
                new Street("Musk’s Mars", 27, 260, 130, 22, Colors.YELLOW, List.of(110, 330, 800, 975, 1150), 150),
                new Property("Water Works", 28, 150, 75, 50, 2, Colors.WHITE),
                new Street("Mark’s Metaverse", 29, 280, 140, 24, Colors.YELLOW, List.of(120, 360, 850, 1025, 1200), 150),
                new Tile("Go to Repair", 30),
                new Street("Microsoft Mine", 31, 300, 150, 26, Colors.DARKGREEN, List.of(130, 390, 900, 1100, 1275), 200),
                new Street("Apple Alley", 32, 300, 150, 26, Colors.DARKGREEN, List.of(130, 390, 900, 1100, 1275), 200),
                new Tile("Community Chest III", 33),
                new Street("Google Gate", 34, 320, 160, 28, Colors.DARKGREEN, List.of(150, 450, 1000, 1200, 1400), 200),
                new Property("Application-Level Gateway FW", 35, 200, 100, 25, 4, Colors.BLACK),
                new Tile("Chance III", 36),
                new Street("IOS Island", 37, 350, 175, 35, Colors.DARKBLUE, List.of(175, 500, 1100, 1300, 1500), 200),
                new Tile("Luxury Tax", 38),
                new Street("Android Avenue", 39, 400, 200, 50, Colors.DARKBLUE, List.of(200, 600, 1400, 1700, 2000), 200)
        ));
    }
}
