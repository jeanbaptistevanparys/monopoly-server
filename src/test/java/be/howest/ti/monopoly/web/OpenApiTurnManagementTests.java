package be.howest.ti.monopoly.web;

import be.howest.ti.monopoly.logic.ServiceAdapter;
import io.vertx.junit5.VertxTestContext;
import org.junit.jupiter.api.Test;


class OpenApiTurnManagementTests extends OpenApiTestsBase {

    @Test
    void rollDice(final VertxTestContext testContext) {

        service.setDelegate(new ServiceAdapter(){
            @Override
            public Object rollDice(String gameId, String playerName) {
                return null;
            }
        });
        post(
                testContext,
                "/games/game-id/players/Alice/dice",
                "some-token",
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
                "/games/game-id/players/Alice/bankruptcy",
                "some-token",
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
