package be.howest.ti.monopoly.logic.implementation.factories;

import be.howest.ti.monopoly.logic.implementation.Chance;
import be.howest.ti.monopoly.logic.implementation.CommunityChest;

import java.util.ArrayList;
import java.util.List;

public class CardFactory {

    public List<Chance> createChances() {
        return new ArrayList<>(List.of(
                new Chance("Go to jail Go directly to jail. Do not pass go, do not collect $200."),
                new Chance("Advance to Boardwalk"),
                new Chance("Advance to Go (Collect $200)"),
                new Chance("Advance to Illinois Avenue. If you pass Go, collect $200"),
                new Chance("Advance to St. Charles Place. If you pass Go, collect $200"),
                new Chance("Advance to the nearest Railroad. If unowned, you may buy it from the Bank. If owned, pay owner twice the rental to which they are otherwise entitled"),
                new Chance("Advance token to nearest Utility. If unowned, you may buy it from the Bank. If owned, throw dice and pay owner a total ten times amount thrown."),
                new Chance("Bank pays you dividend of $50"),
                new Chance("Get Out of Jail Free"),
                new Chance("Go Back 3 Spaces"),
                new Chance("Go to Jail. Go directly to Jail, do not pass Go, do not collect $200"),
                new Chance("Make general repairs on all your property. For each house pay $25. For each hotel pay $100"),
                new Chance("Speeding fine $15"),
                new Chance("Take a trip to Reading Railroad. If you pass Go, collect $200"),
                new Chance("You have been elected Chairman of the Board. Pay each player $50"),
                new Chance("Your building loan matures. Collect $150")
        ));
    }

    public List<CommunityChest> createCommunityChests() {
        return new ArrayList<>(List.of(
                new CommunityChest("Advance to Go (Collect $200)"),
                new CommunityChest("Bank error in your favor. Collect $200"),
                new CommunityChest("Doctor's fee. Pay $50"),
                new CommunityChest("From sale of stock you get $50"),
                new CommunityChest("Get Out of Jail Free"),
                new CommunityChest("Go to Jail. Go directly to jail, do not pass Go, do not collect $200"),
                new CommunityChest("Holiday fund matures. Receive $100"),
                new CommunityChest("Income tax refund. Collect $20"),
                new CommunityChest("It is your birthday. Collect $10 from every player"),
                new CommunityChest("Life insurance matures. Collect $100"),
                new CommunityChest("Pay hospital fees of $100 "),
                new CommunityChest("Pay school fees of $50"),
                new CommunityChest("Receive $25 consultancy fee "),
                new CommunityChest("You are assessed for street repair. $40 per house. $115 per hotel"),
                new CommunityChest("You have won second prize in a beauty contest. Collect $10"),
                new CommunityChest("You inherit $100")
        ));
    }
}
