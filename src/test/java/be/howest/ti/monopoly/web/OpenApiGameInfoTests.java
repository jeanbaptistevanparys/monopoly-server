package be.howest.ti.monopoly.web;

import io.vertx.junit5.VertxTestContext;
import org.junit.jupiter.api.Test;


class OpenApiGameInfoTests extends OpenApiTestsBase {

    @Test
    void getGame(final VertxTestContext testContext) {
        get(
                testContext,
                "/games/game-id",
                "some-token",
                response -> assertNotYetImplemented(response, "getGame")
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
        get(
                testContext,
                "/games/dummy",
                null,
                response -> assertNotYetImplemented(response, "getDummyGame")
        );
    }
}
