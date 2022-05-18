package be.howest.ti.monopoly.web;

import be.howest.ti.monopoly.logic.ServiceAdapter;
import be.howest.ti.monopoly.logic.implementation.Game;
import io.vertx.junit5.VertxTestContext;
import org.junit.jupiter.api.Test;


class OpenApiTurnManagementTests extends OpenApiTestsBase {

    @Test
    void rollDice(final VertxTestContext testContext) {

        service.setDelegate(new ServiceAdapter(){
            @Override
            public Object rollDice(String gameId, String playerName) {
                return new Game(3, "group12");
            }
        });
        post(
                testContext,
                "/games/group12_1/players/Alice/dice",
                "group12_1-Alice",
                this::assertOkResponse
        );
    }

    @Test
    void rollDiceUnauthorized(final VertxTestContext testContext) {
        post(
                testContext,
                "/games/game-id/players/Alice/dice",
                null,
                response -> assertErrorResponse(response, 401)
        );
    }

    @Test
    void declareBankruptcy(final VertxTestContext testContext) {
        service.setDelegate(new ServiceAdapter(){
            @Override
            public Object declareBankruptcy(String gameId, String playerName) {
                return null;
            }
        });
        post(
                testContext,
                "/games/group12_1/players/Alice/bankruptcy",
                "group12_1-Alice",
                this::assertOkResponse
        );
    }

    @Test
    void declareBankruptcyUnauthorized(final VertxTestContext testContext) {
        post(
                testContext,
                "/games/game-id/players/Alice/bankruptcy",
                null,
                response -> assertErrorResponse(response, 401)
        );
    }
}
