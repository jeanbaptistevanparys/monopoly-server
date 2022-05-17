package be.howest.ti.monopoly.logic.implementation.cards;

public class MoveToTileCard extends Card{

    private final String tileName;

    public MoveToTileCard(String description, String tileName) {
        super(description);
        this.tileName = tileName;
    }
}
