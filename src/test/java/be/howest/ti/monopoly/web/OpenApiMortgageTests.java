package be.howest.ti.monopoly.web;

import io.vertx.junit5.VertxTestContext;
import org.junit.jupiter.api.Test;


class OpenApiMortgageTests extends OpenApiTestsBase {

    @Test
    void takeMortgage(final VertxTestContext testContext) {
        post(
                testContext,
                "/games/game-id/players/Alice/properties/some-property/mortgage",
                "some-token",
                response -> assertNotYetImplemented(response, "takeMortgage")
        );
    }

    @Test
    void takeMortgageUnauthorized(final VertxTestContext testContext) {
        post(
                testContext,
                "/games/game-id/players/Alice/properties/some-property/mortgage",
                null,
                response -> assertErrorResponse(response, 401)
        );
    }

    @Test
    void settleMortgage(final VertxTestContext testContext) {
        delete(
                testContext,
                "/games/game-id/players/Alice/properties/some-property/mortgage",
                "some-token",
                response -> assertNotYetImplemented(response, "settleMortgage")
        );
    }

    @Test
    void settleMortgageUnauthorized(final VertxTestContext testContext) {
        delete(
                testContext,
                "/games/game-id/players/Alice/properties/some-property/mortgage",
                null,
                response -> assertErrorResponse(response, 401)
        );
    }
}
