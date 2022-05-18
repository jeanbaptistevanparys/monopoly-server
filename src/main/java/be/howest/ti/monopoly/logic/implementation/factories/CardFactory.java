package be.howest.ti.monopoly.logic.implementation.factories;

import be.howest.ti.monopoly.logic.implementation.cards.*;

import java.util.ArrayList;
import java.util.List;

public class CardFactory {

    public List<Card> createChances() {
        return new ArrayList<>(List.of(
                new MoveToTileCard("Advance to Boardwalk", "Boardwalk"),
                new MoveToTileCard("Advance to Boot (Collect $200)", "Go"),
                new MoveToTileCard("Advance to Illinois Avenue. If you pass Boot, collect $200", "Illinois Avenue"),
                new MoveToTileCard("Advance to St. Charles Place. If you pass Boot, collect $200", "St. Charles Place"),
                new MoveToNearestCard("Advance to the nearest Railroad.", "railroad"),
                new MoveToNearestCard("Advance token to nearest Utility.", "utility"),
                new ChangeMoneyCard("Bank pays you dividend of $50", 50),
                new OutOfJailCard("Get Out of Repair Free"),
                new MoveToTileCard("Go to Repair", "Repair"),
                new ChangeMoneyCard("Make general repairs on all your property. Pay $200", -200),
                new ChangeMoneyCard("Speeding fine $15", -15),
                new MoveToTileCard("Take a trip to Reading Railroad. If you pass Boot, collect $200", "Reading Railroad"),
                new ChangeMoneyCard("You have been elected Chairman of the Board. Pay $100", 100),
                new ChangeMoneyCard("Your building loan matures. Collect $150", 150)
        ));
    }

    public List<Card> createCommunityChests() {
        return new ArrayList<>(List.of(
                new MoveToTileCard("Advance to Boot (Collect $200)", "Boot"),
                new ChangeMoneyCard("Bank error in your favor. Collect $200", 200),
                new ChangeMoneyCard("Doctor's fee. Pay $50", -50),
                new ChangeMoneyCard("From sale of stock you get $50", 50),
                new OutOfJailCard("Get Out of Repair Free"),
                new MoveToTileCard("Go to Repair", "Repair"),
                new ChangeMoneyCard("Holiday fund matures. Receive $100", 100),
                new ChangeMoneyCard("Income tax refund. Collect $20", 20),
                new ChangeMoneyCard("Life insurance matures. Collect $100", 100),
                new ChangeMoneyCard("Pay hospital fees of $100", -100),
                new ChangeMoneyCard("Pay school fees of $50", -50),
                new ChangeMoneyCard("Receive $25 consultancy fee", -25),
                new ChangeMoneyCard("You are assessed for street repair. Pay 200$", -200),
                new ChangeMoneyCard("You inherit $100", 100)
        ));
    }
}
