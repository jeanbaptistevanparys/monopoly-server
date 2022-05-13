package be.howest.ti.monopoly.logic.implementation.tiles;

public class Tile {
    private final String name;
    private final int position;
    private final String type;

    public Tile(String name, int position, String type) {
        this.name = name;
        this.position = position;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    public String getType() {
        return type;
    }

    public String getNameAsPathParameter() {
        return name.replaceAll("\\s+", "_");
    }
}
