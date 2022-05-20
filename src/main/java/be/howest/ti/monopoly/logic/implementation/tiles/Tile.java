package be.howest.ti.monopoly.logic.implementation.tiles;

import java.util.List;
import java.util.Map;

public class Tile {
    private static final List<String> COMMUNITY_CHESTS = List.of(
            "Community Chest I", "Community Chest II", "Community Chest III"
    );
    private static final List<String> CHANCES = List.of(
            "Chance I", "Chance II", "Chance III"
    );
    private static final List<String> RAILROADS = List.of(
            "Packet Filtering FW", "Circuit-Level Gateway FW", "Stateful Inspection FW", "Application-Level Gateway FW"
    );
    private static final List<String> UTILITIES = List.of(
            "Electric Company", "Water Works"
    );
    private static final Map<String, String> NAME_TO_TYPE = Map.of(
            "Boot", "Go",
            "Tax Income", "Tax Income",
            "Repair", "Jail",
            "Lag", "Free Parking",
            "Go to Repair", "Go to Jail",
            "Luxury Tax", "Luxury Tax"
    );

    private final String name;
    private final int position;

    public Tile(String name, int position) {
        this.name = name;
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    public String getType() {
        if (COMMUNITY_CHESTS.contains(name)) return "community chest";
        else if (CHANCES.contains(name)) return "chance";
        else if (RAILROADS.contains(name)) return "railroad";
        else if (UTILITIES.contains(name)) return "utility";
        else return NAME_TO_TYPE.getOrDefault(name, "street");
    }

    public String getNameAsPathParameter() {
        return name.replaceAll("\\s+", "_");
    }
}
