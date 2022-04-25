package be.howest.ti.monopoly.web;

import io.vertx.junit5.VertxTestContext;
import org.junit.jupiter.api.Test;


class OpenApiInteractionWithOtherPlayerTests extends OpenApiTestsBase {

    @Test
    void collectDebt(final VertxTestContext testContext) {
        delete(
                testContext,
                "/games/game-id/players/Alice/properties/some-property/visitors/Bob/rent",
                "some-token",
                response -> assertNotYetImplemented(response, "collectDebt")
        );
    }

    @Test
    void collectDebtUnauthorized(final VertxTestContext testContext) {
        delete(
                testContext,
                "/games/game-id/players/Alice/properties/some-property/visitors/Bob/rent",
                null,
                response -> assertErrorResponse(response, 401)
        );
    }


}
