package be.howest.ti.monopoly.logic.implementation;

import be.howest.ti.monopoly.logic.exceptions.IllegalMonopolyActionException;
import be.howest.ti.monopoly.logic.exceptions.MonopolyResourceNotFoundException;
import be.howest.ti.monopoly.logic.implementation.enums.Colors;
import be.howest.ti.monopoly.logic.implementation.enums.TaxSystems;
import be.howest.ti.monopoly.logic.implementation.factories.TileFactory;
import be.howest.ti.monopoly.logic.implementation.tiles.Tile;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    private Game newGame() {
        Game game = new Game(2, "test");
        Player player1 = new Player("Jarne");
        Player player2 = new Player("Jari");
        game.addPlayer(player1);
        game.addPlayer(player2);
        return game;
    }

    @Test
    void startGame() {
        Game game = newGame();
        assertTrue(game.isStarted());
    }

    @Test
    void addPlayer() {
        Game game = newGame();
        assertEquals(2, game.getPlayers().size());
    }

    @Test
    void addPlayerToMuch() {
        Game game = newGame();
        Player player3 = new Player("JB");
        assertThrows(
                IllegalMonopolyActionException.class,
                () -> game.addPlayer(player3)
        );
    }

    @Test
    void changeCurrentPlayerBankrupt() {
        Game game = newGame();
        game.getPlayer("Jarne").giveMoney(1600);
        game.changeCurrentPlayer();
        assertTrue(game.getPlayer("Jarne").isBankrupt());
    }

    @Test
    void GetOutOfJailDouble() {
        Game game = newGame();
        game.getCurrentPlayer().goToJail();
        String name = game.getCurrentPlayer().getName();
        game.rollDice(name);
        if (game.getLastDiceRoll().get(0).equals(game.getLastDiceRoll().get(1))) {
            assertFalse(game.getPlayer(name).isJailed());
        } else {
            assertTrue(game.getPlayer(name).isJailed());
        }
        assertFalse(game.getCurrentPlayer().isJailed());
    }


    @Test
    void goOutOfJailFree() {
        Game game = newGame();
        game.getCurrentPlayer().goToJail();
        assertTrue(game.getCurrentPlayer().isJailed());

        game.getCurrentPlayer().addOutOfJailFreeCard();
        game.getOutOfJailFree();
        assertFalse(game.getCurrentPlayer().isJailed());
        assertEquals(0, game.getCurrentPlayer().getGetOutOfJailFreeCards());
    }

    @Test
    void goOutOfJailFine() {
        Game game = newGame();
        game.getCurrentPlayer().goToJail();
        assertTrue(game.getCurrentPlayer().isJailed());

        game.getOutOfJailFine();
        assertFalse(game.getCurrentPlayer().isJailed());
        assertEquals(1450, game.getCurrentPlayer().getMoney());
    }

    @Test
    void checkIfGoToJailTrue() {
        Tile tile = new Tile("Boot", 0);
        Game game = newGame();
        Turn turn1 = new Turn(game.getCurrentPlayer(), 2, 2);
        Turn turn2 = new Turn(game.getCurrentPlayer(), 2, 2);
        Turn turn3 = new Turn(game.getCurrentPlayer(), 2, 2);
        Turn turn4 = new Turn(game.getCurrentPlayer(), 2, 2);
        game.addTurn(turn1);
        game.addTurn(turn2);
        game.addTurn(turn3);
        game.addTurn(turn4);
        assertTrue(Helper.isGoToJail(tile, game));
    }

    @Test
    void checkIfGoToJailFalseByNumber() {
        Tile tile = new Tile("Boot", 0);
        Game game = newGame();
        Turn turn1 = new Turn(game.getCurrentPlayer(), 3, 2);
        Turn turn2 = new Turn(game.getCurrentPlayer(), 2, 2);
        game.addTurn(turn1);
        game.addTurn(turn2);
        assertFalse(Helper.isGoToJail(tile, game));
    }

    @Test
    void buyProperty() {
        Game game = newGame();
        game.buyProperty("Jarne", "Chrome Crib");
        List<PlayerProperty> properties = game.getPlayer("Jarne").getProperties();
        assertEquals(1, properties.size());
        assertEquals(1440, game.getPlayer("Jarne").getMoney());
    }

    @Test
    void buyPropertyAlreadyOwned() {
        Game game = newGame();
        game.buyProperty("Jarne", "Chrome Crib");
        assertThrows(
                IllegalMonopolyActionException.class,
                () -> game.buyProperty("Jari", "Chrome Crib")
        );
    }

    @Test
    void buyHouse() {
        Game game = newGame();
        game.buyProperty("Jarne", "Chrome Crib");
        game.buyProperty("Jarne", "Firefox Fountain");
        game.buyHouse("Jarne", "Chrome Crib");
        List<PlayerProperty> properties = game.getPlayer("Jarne").getProperties();
        assertEquals(1, properties.get(0).getHouseCount());
        assertEquals(1330, game.getPlayer("Jarne").getMoney());
    }

    @Test
    void buyHouseAlready4() {
        Game game = newGame();
        game.buyProperty("Jarne", "Chrome Crib");
        game.buyProperty("Jarne", "Firefox Fountain");
        game.buyHouse("Jarne", "Chrome Crib");
        game.buyHouse("Jarne", "Chrome Crib");
        game.buyHouse("Jarne", "Chrome Crib");
        game.buyHouse("Jarne", "Chrome Crib");
        assertThrows(
                IllegalMonopolyActionException.class,
                () -> game.buyHouse("Jarne", "Chrome Crib")
        );
    }

    @Test
    void buyHouseNotFullHouse() {
        Game game = newGame();
        game.buyProperty("Jarne", "Chrome Crib");
        assertThrows(
                IllegalMonopolyActionException.class,
                () -> game.buyHouse("Jarne", "Chrome Crib")
        );
    }

    @Test
    void buyHouseNoHousesLeft() {
        Game game = newGame();
        game.setAvailableHouses(0);
        game.buyProperty("Jarne", "Chrome Crib");
        game.buyProperty("Jarne", "Firefox Fountain");
        assertThrows(
                IllegalMonopolyActionException.class,
                () -> game.buyHouse("Jarne", "Chrome Crib")
        );
    }

    @Test
    void buyHotelAlreadyHave() {
        Game game = newGame();
        game.buyProperty("Jarne", "Chrome Crib");
        game.buyProperty("Jarne", "Firefox Fountain");
        game.buyHouse("Jarne", "Chrome Crib");
        game.buyHouse("Jarne", "Chrome Crib");
        game.buyHouse("Jarne", "Chrome Crib");
        game.buyHouse("Jarne", "Chrome Crib");
        game.buyHotel("Jarne", "Chrome Crib");
        assertThrows(
                IllegalMonopolyActionException.class,
                () -> game.buyHotel("Jarne", "Chrome Crib")
        );
    }

    @Test
    void buyHotelNot4Houses() {
        Game game = newGame();
        game.buyProperty("Jarne", "Chrome Crib");
        game.buyProperty("Jarne", "Firefox Fountain");
        game.buyHouse("Jarne", "Chrome Crib");
        game.buyHouse("Jarne", "Chrome Crib");
        game.buyHouse("Jarne", "Chrome Crib");
        assertThrows(
                IllegalMonopolyActionException.class,
                () -> game.buyHotel("Jarne", "Chrome Crib")
        );
    }

    @Test
    void buyHotelNoHotelsLeft() {
        Game game = newGame();
        game.setAvailableHotels(0);
        game.buyProperty("Jarne", "Chrome Crib");
        game.buyProperty("Jarne", "Firefox Fountain");
        game.buyHouse("Jarne", "Chrome Crib");
        game.buyHouse("Jarne", "Chrome Crib");
        game.buyHouse("Jarne", "Chrome Crib");
        game.buyHouse("Jarne", "Chrome Crib");
        assertThrows(
                IllegalMonopolyActionException.class,
                () -> game.buyHotel("Jarne", "Chrome Crib")
        );
    }

    @Test
    void buyHotel() {
        Game game = newGame();
        game.buyProperty("Jarne", "Chrome Crib");
        game.buyProperty("Jarne", "Firefox Fountain");
        game.buyHouse("Jarne", "Chrome Crib");
        game.buyHouse("Jarne", "Chrome Crib");
        game.buyHouse("Jarne", "Chrome Crib");
        game.buyHouse("Jarne", "Chrome Crib");
        game.buyHotel("Jarne", "Chrome Crib");
        List<PlayerProperty> properties = game.getPlayer("Jarne").getProperties();
        assertEquals(1, properties.get(0).getHotelCount());
        assertEquals(1130, game.getPlayer("Jarne").getMoney());
    }

    @Test
    void playerHasFullStreet() {
        Game game = newGame();
        game.buyProperty("Jarne", "Chrome Crib");
        game.buyProperty("Jarne", "Firefox Fountain");
        assertTrue(game.playerHasFullStreet(game.getPlayer("Jarne"), "Chrome Crib"));
    }

    @Test
    void collectDebt1House() {
        Game game = newGame();
        game.buyProperty("Jarne", "Chrome Crib");
        game.buyProperty("Jarne", "Firefox Fountain");
        game.buyHouse("Jarne", "Chrome Crib");
        game.getPlayer("Jari").moveTile("Chrome Crib");
        game.collectDebt("Jarne", "Chrome Crib", "Jari");
        assertEquals(1340, game.getPlayer("Jarne").getMoney());
        assertEquals(1490, game.getPlayer("Jari").getMoney());
    }

    @Test
    void collectDebt2Houses() {
        Game game = newGame();
        game.buyProperty("Jarne", "Chrome Crib");
        game.buyProperty("Jarne", "Firefox Fountain");
        game.buyHouse("Jarne", "Chrome Crib");
        game.buyHouse("Jarne", "Chrome Crib");
        game.getPlayer("Jari").moveTile("Chrome Crib");
        game.collectDebt("Jarne", "Chrome Crib", "Jari");
        assertEquals(1310, game.getPlayer("Jarne").getMoney());
        assertEquals(1470, game.getPlayer("Jari").getMoney());
    }

    @Test
    void collectDebt3Houses() {
        Game game = newGame();
        game.buyProperty("Jarne", "Chrome Crib");
        game.buyProperty("Jarne", "Firefox Fountain");
        game.buyHouse("Jarne", "Chrome Crib");
        game.buyHouse("Jarne", "Chrome Crib");
        game.buyHouse("Jarne", "Chrome Crib");
        game.getPlayer("Jari").moveTile("Chrome Crib");
        game.collectDebt("Jarne", "Chrome Crib", "Jari");
        assertEquals(1320, game.getPlayer("Jarne").getMoney());
        assertEquals(1410, game.getPlayer("Jari").getMoney());
    }

    @Test
    void collectDebt4Houses() {
        Game game = newGame();
        game.buyProperty("Jarne", "Chrome Crib");
        game.buyProperty("Jarne", "Firefox Fountain");
        game.buyHouse("Jarne", "Chrome Crib");
        game.buyHouse("Jarne", "Chrome Crib");
        game.buyHouse("Jarne", "Chrome Crib");
        game.buyHouse("Jarne", "Chrome Crib");
        game.getPlayer("Jari").moveTile("Chrome Crib");
        game.collectDebt("Jarne", "Chrome Crib", "Jari");
        assertEquals(1340, game.getPlayer("Jarne").getMoney());
        assertEquals(1340, game.getPlayer("Jari").getMoney());
    }

    @Test
    void collectDebtRailRoad() {
        Game game = newGame();
        game.buyProperty("Jarne", "Circuit-Level Gateway FW");
        game.buyProperty("Jarne", "Packet Filtering FW");
        game.buyProperty("Jarne", "Stateful Inspection FW");
        game.getPlayer("Jari").moveTile("Packet Filtering FW");
        game.collectDebt("Jarne", "Packet Filtering FW", "Jari");
        assertEquals(1000, game.getPlayer("Jarne").getMoney());
        assertEquals(1400, game.getPlayer("Jari").getMoney());
    }

    @Test
    void collectDebtUtility() {
        Game game = newGame();
        game.buyProperty("Jarne", "Electric Company");
        game.getPlayer("Jari").moveTile("Electric Company");
        game.collectDebt("Jarne", "Electric Company", "Jari");
        assertEquals(1400, game.getPlayer("Jarne").getMoney());
        assertEquals(1450, game.getPlayer("Jari").getMoney());
    }

    @Test
    void collectDebtDefault() {
        Game game = newGame();
        game.buyProperty("Jarne", "Chrome Crib");
        game.getPlayer("Jari").moveTile("Chrome Crib");
        game.collectDebt("Jarne", "Chrome Crib", "Jari");
        assertEquals(1442, game.getPlayer("Jarne").getMoney());
        assertEquals(1498, game.getPlayer("Jari").getMoney());
    }

    @Test
    void calculateRailRoadDebtTwo() {
        Game game = newGame();
        game.buyProperty("Jarne", "Circuit-Level Gateway FW");
        game.buyProperty("Jarne", "Packet Filtering FW");
        assertEquals(50, game.calculateRailRoadDebt("Jarne"));
    }

    @Test
    void calculateRailRoadDebtFour() {
        Game game = newGame();
        game.buyProperty("Jarne", "Circuit-Level Gateway FW");
        game.buyProperty("Jarne", "Packet Filtering FW");
        game.buyProperty("Jarne", "Stateful Inspection FW");
        game.buyProperty("Jarne", "Application-Level Gateway FW");
        assertEquals(200, game.calculateRailRoadDebt("Jarne"));
    }

    @Test
    void checkIfYourPropertyTrue() {
        Game game = newGame();
        Player player = game.getPlayer("Jarne");
        game.buyProperty("Jarne", "Chrome Crib");
        game.buyProperty("Jarne", "Microsoft Mine");
        game.buyProperty("Jarne", "Android Avenue");
        assertTrue(game.checkIfYourProperty(player, "Microsoft Mine"));
    }

    @Test
    void checkIfYourPropertyFalse() {
        Game game = newGame();
        Player player = game.getPlayer("Jarne");
        game.buyProperty("Jarne", "Chrome Crib");
        game.buyProperty("Jarne", "Microsoft Mine");
        game.buyProperty("Jarne", "Android Avenue");
        assertFalse(game.checkIfYourProperty(player, "Musk’s Mars"));
    }

    @Test
    void takeMortgage() {
        Game game = newGame();
        game.buyProperty("Jarne", "Chrome Crib");
        game.takeMortgage("Jarne", "Chrome Crib");
        assertEquals(1470, game.getPlayer("Jarne").getMoney());
        assertTrue(game.getPlayerProperty(game.getPlayer("Jarne").getProperties(), "Chrome Crib").isMortgage());
    }

    @Test
    void takeMortgageWithImprovements() {
        Game game = newGame();
        game.buyProperty("Jarne", "Chrome Crib");
        game.buyProperty("Jarne", "Firefox Fountain");
        game.buyHouse("Jarne", "Chrome Crib");
        assertThrows(
                IllegalMonopolyActionException.class,
                () -> game.takeMortgage("Jarne", "Chrome Crib")
        );
    }

    @Test
    void takeMortgageAlreadyMortgaged() {
        Game game = newGame();
        game.buyProperty("Jarne", "Chrome Crib");
        game.takeMortgage("Jarne", "Chrome Crib");
        assertThrows(
                IllegalMonopolyActionException.class,
                () -> game.takeMortgage("Jarne", "Chrome Crib")
        );
    }

    @Test
    void settleMortgage() {
        Game game = newGame();
        game.buyProperty("Jarne", "Chrome Crib");
        game.takeMortgage("Jarne", "Chrome Crib");
        game.settleMortgage("Jarne","Chrome Crib");
        assertEquals(1437, game.getPlayer("Jarne").getMoney());
        assertFalse(game.getPlayerProperty(game.getPlayer("Jarne").getProperties(), "Chrome Crib").isMortgage());
    }

    @Test
    void settleMortgageNotMortgaged() {
        Game game = newGame();
        game.buyProperty("Jarne", "Chrome Crib");
        assertThrows(
                IllegalMonopolyActionException.class,
                () -> game.settleMortgage("Jarne","Chrome Crib")
        );
    }

    @Test
    void declareBankruptcy() {
        Game game = new Game(3, "test");
        Player player1 = new Player("Jarne");
        Player player2 = new Player("Jari");
        Player player3 = new Player("JB");
        game.addPlayer(player1);
        game.addPlayer(player2);
        game.addPlayer(player3);
        game.buyProperty("Jarne", "Chrome Crib");
        game.buyProperty("Jarne", "Firefox Fountain");
        game.getPlayer("Jarne").addOutOfJailFreeCard();
        game.getPlayer("Jarne").addOutOfJailFreeCard();
        game.getPlayer("Jarne").addOutOfJailFreeCard();
        game.declareBankruptcy("Jarne");
        assertTrue(game.getPlayer("Jarne").isBankrupt());
        assertEquals(2, game.getActivePlayers().size());
        assertEquals(1, game.getPlayer("Jari").getProperties().size());
        assertEquals(1, game.getPlayer("JB").getProperties().size());
        assertEquals(2, game.getPlayer("Jari").getGetOutOfJailFreeCards());
    }

    @Test
    void declareBankruptcyWithWinner() {
        Game game = new Game(3, "test");
        Player player1 = new Player("Jarne");
        Player player2 = new Player("Jari");
        game.addPlayer(player1);
        game.addPlayer(player2);
        game.declareBankruptcy("Jari");
        assertEquals(game.getPlayer("Jarne"), game.getWinner());
        assertTrue(game.isEnded());
    }

    @Test
    void declareBankruptcyDeleteHouses() {
        Game game = new Game(3, "test");
        Player player1 = new Player("Jarne");
        Player player2 = new Player("Jari");
        Player player3 = new Player("JB");
        game.addPlayer(player1);
        game.addPlayer(player2);
        game.addPlayer(player3);
        game.buyProperty("Jarne", "Chrome Crib");
        game.buyProperty("Jarne", "Firefox Fountain");
        game.buyHouse("Jarne", "Chrome Crib");
        game.getPlayer("Jarne").addOutOfJailFreeCard();
        game.declareBankruptcy("Jarne");
        assertTrue(game.getPlayer("Jarne").isBankrupt());
        assertEquals(1, game.getPlayer("Jari").getProperties().size());
        assertEquals(1, game.getPlayer("JB").getProperties().size());
        assertEquals(1, game.getPlayer("Jari").getGetOutOfJailFreeCards());
        assertEquals(0, game.getPlayerProperty(game.getPlayer("Jari").getProperties(), "Chrome Crib").getHouseCount());
    }

    @Test
    void payTaxIncome() {
        Game game = newGame();
        int dice1 = 2;
        int dice2 = 2;
        Turn turn = new Turn(game.getCurrentPlayer(), dice1, dice2);
        turn.executeTurn(game);
        assertEquals(1400, game.getPlayer("Jarne").getMoney());
    }

    @Test
    void payTaxLuxury() {
        Game game = newGame();
        int dice1 = 19;
        int dice2 = 19;
        Turn turn = new Turn(game.getCurrentPlayer(), dice1, dice2);
        turn.executeTurn(game);
        assertEquals(1300, game.getPlayer("Jarne").getMoney());
    }

    @Test
    void payTaxCompute() {
        Game game = newGame();
        int dice1 = 2;
        int dice2 = 2;
        Turn turn = new Turn(game.getCurrentPlayer(), dice1, dice2);
        game.getPlayer("Jarne").setTaxSystem(TaxSystems.COMPUTE);
        turn.executeTurn(game);
        assertEquals(1350, game.getPlayer("Jarne").getMoney());
    }

    @Test
    void useComputeTax() {
        Game game = newGame();
        game.useComputeTax("Jarne");
        assertEquals(TaxSystems.COMPUTE, game.getPlayer("Jarne").getTaxSystem());
    }

    @Test
    void useEstimateTax() {
        Game game = newGame();
        game.useEstimateTax("Jarne");
        assertEquals(TaxSystems.ESTIMATE, game.getPlayer("Jarne").getTaxSystem());
    }

    @Test
    void addAuction() {
        Game game = newGame();
        game.addAuction(new Auction("Linux Land", 0));
        assertEquals(Collections.emptyList(), game.getAuctions().get(0).getBids());
        assertEquals("Linux Land", game.getAuctions().get(0).getProperty());
    }

    @Test
    void sellHouse() {
        Game game = newGame();
        game.buyProperty("Jarne", "Chrome Crib");
        game.buyProperty("Jarne", "Firefox Fountain");
        game.buyHouse("Jarne", "Chrome Crib");
        game.sellHouse("Jarne", "Chrome Crib");
        List<PlayerProperty> properties = game.getPlayer("Jarne").getProperties();
        assertEquals(0, properties.get(0).getHouseCount());
        assertEquals(1355, game.getPlayer("Jarne").getMoney());
    }

    @Test
    void sellHouseNoHouses() {
        Game game = newGame();
        game.buyProperty("Jarne", "Chrome Crib");
        game.buyProperty("Jarne", "Firefox Fountain");
        assertThrows(
                IllegalMonopolyActionException.class,
                () -> game.sellHouse("Jarne", "Chrome Crib")
        );
    }

    @Test
    void sellHotel() {
        Game game = newGame();
        game.buyProperty("Jarne", "Chrome Crib");
        game.buyProperty("Jarne", "Firefox Fountain");
        game.buyHouse("Jarne", "Chrome Crib");
        game.buyHouse("Jarne", "Chrome Crib");
        game.buyHouse("Jarne", "Chrome Crib");
        game.buyHouse("Jarne", "Chrome Crib");
        assertThrows(
                IllegalMonopolyActionException.class,
                () -> game.sellHotel("Jarne", "Chrome Crib")
        );
    }

    @Test
    void sellHotelNoHotel() {
        Game game = newGame();
        game.buyProperty("Jarne", "Chrome Crib");
        game.buyProperty("Jarne", "Firefox Fountain");
        game.buyHouse("Jarne", "Chrome Crib");
        game.buyHouse("Jarne", "Chrome Crib");
        game.buyHouse("Jarne", "Chrome Crib");
        game.buyHouse("Jarne", "Chrome Crib");
        game.buyHotel("Jarne", "Chrome Crib");
        game.sellHotel("Jarne", "Chrome Crib");
        List<PlayerProperty> properties = game.getPlayer("Jarne").getProperties();
        assertEquals(0, properties.get(0).getHotelCount());
        assertEquals(1155, game.getPlayer("Jarne").getMoney());
    }

    @Test
    void getDirectSaleProperty() {
        Game game = newGame();
        Turn turn = new Turn(game.getCurrentPlayer(), 1, 2);
        game.addTurn(turn);
        turn.executeTurn(game);
        assertEquals("Firefox Fountain", game.getDirectSale());
    }

    @Test
    void getDirectSaleNull() {
        Game game = newGame();
        Turn turn = new Turn(game.getCurrentPlayer(), 5, 5);
        game.addTurn(turn);
        turn.executeTurn(game);
        assertNull(game.getDirectSale());
    }

    @Test
    void getProperty() {
        Game game = newGame();
        assertEquals(2, game.getProperty("Chrome Crib").getGroupSize());
        assertEquals(60, game.getProperty("Chrome Crib").getCost());
        assertEquals(Colors.PURPLE, game.getProperty("Chrome Crib").getColor());
        assertEquals(1, game.getProperty("Chrome Crib").getPosition());
        assertEquals(30, game.getProperty("Chrome Crib").getMortgage());
    }

    @Test
    void getPropertyNotFound() {
        Game game = newGame();
        assertThrows(
                MonopolyResourceNotFoundException.class,
                () -> game.getProperty("Nope")
        );
    }

    @Test
    void endGame() {
        Game game = newGame();
        game.endGame();
        assertTrue(game.isEnded());
    }

    @Test
    void getStreetError() {
        Game game = newGame();
        assertThrows(
                MonopolyResourceNotFoundException.class,
                () -> game.getStreet("hello")
        );
    }

    @Test
    void getPlayerError() {
        Game game = newGame();
        assertThrows(
                MonopolyResourceNotFoundException.class,
                () -> game.getPlayer("hello")
        );
    }

    @Test
    void getPlayerPropertyError() {
        Game game = newGame();
        assertThrows(
                MonopolyResourceNotFoundException.class,
                () -> game.getPlayerProperty(game.getPlayer("Jarne").getProperties(), "hello")
        );
    }

    @Test
    void getPrefix() {
        Game game = newGame();
        assertEquals("test", game.getPrefix());
    }

    @Test
    void getTiles() {
        Game game = newGame();

        assertEquals(TileFactory.createTiles().size(), game.getTiles().size());
    }
}
