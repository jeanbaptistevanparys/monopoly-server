package be.howest.ti.monopoly.web;

import be.howest.ti.monopoly.logic.ServiceAdapter;
import be.howest.ti.monopoly.logic.implementation.Game;
import io.vertx.junit5.VertxTestContext;
import org.junit.jupiter.api.Test;


class OpenApiGameInfoTests extends OpenApiTestsBase {

    @Test
    void getGame(final VertxTestContext testContext) {
        service.setDelegate(new ServiceAdapter(){
            @Override
            public Game getGame(String gameId) {
                return new Game(3, "group12", lastDiceRoll);
            }
        });
        get(
                testContext,
                "/games/group12_1",
                "group12_1-Jamie",
                this::assertOkResponse
        );
    }

    @Test
    void getGameUnauthorized(final VertxTestContext testContext) {
        get(
                testContext,
                "/games/game-id",
                null,
                response -> assertErrorResponse(response, 401)
        );
    }

    @Test
    void getDummyGame(final VertxTestContext testContext) {
        service.setDelegate(new ServiceAdapter(){
            @Override
            public Game getDummyGame() {
                return new Game(3, "group12", lastDiceRoll);
            }});
        get(
                testContext,
                "/games/dummy",
                null,
                this::assertOkResponse
        );
    }
}
