package be.howest.ti.monopoly.logic.implementation;

import be.howest.ti.monopoly.logic.ServiceAdapter;
import be.howest.ti.monopoly.logic.exceptions.MonopolyResourceNotFoundException;

import javax.validation.metadata.ReturnValueDescriptor;
import java.util.List;


public class MonopolyService extends ServiceAdapter {

    @Override
    public String getVersion() {
        return "0.0.1";
    }

    @Override
    public List<Tile> getTiles() {
        return List.of(
            new Tile(0, "Go"),
            new Tile(1, "Mediterranean"),
            new Tile(2, "Community Chest I"),
            new Tile(3, "Baltic"),
            new Tile(4, "Tax Income")
        );
    }

    @Override
    public Tile getTile(int position) {
        for (Tile tile : getTiles()) {
            if (tile.getPosition() == position) {
                return tile;
            }
        }
        throw new MonopolyResourceNotFoundException("No such tile");
    }

    @Override
    public Tile getTile(String name) {
        for (Tile tile : getTiles()) {
            if (tile.getName().equals(name)) {
                return tile;
            }
        }
        throw new MonopolyResourceNotFoundException("No such tile");
    }

    @Override
    public List<Chance> getChance() {
        return List.of(
            new Chance("Advance to Boardwalk"),
            new Chance("Advance to Go (Collect $200)"),
            new Chance("Advance to Illinois Avenue. If you pass Go, collect $200"),
            new Chance("Advance to St. Charles Place. If you pass Go, collect $200"),
            new Chance("Advance to the nearest Railroad. If unowned, you may buy it from the Bank. If owned, pay owner twice the rental to which they are otherwise entitled")
        );
    }

    @Override
    public List<CommunityChest> getCommunityChest() {
        return List.of(
            new CommunityChest("Advance to Go (Collect $200)"),
            new CommunityChest("Bank error in your favor. Collect $200"),
            new CommunityChest("Doctor's fee. Pay $50"),
            new CommunityChest("From sale of stock you get $50"),
            new CommunityChest("Get Out of Jail Free")
        );
    }
}
