package be.howest.ti.monopoly.web;

import be.howest.ti.monopoly.logic.ServiceAdapter;
import io.vertx.junit5.VertxTestContext;
import org.junit.jupiter.api.Test;


class OpenApiMortgageTests extends OpenApiTestsBase {
    @Test
    void takeMortgage(final VertxTestContext testContext) {
        service.setDelegate(new ServiceAdapter() {
            @Override
            public Object takeMortgage() {
                return null;
            }
        });
        post(
                testContext,
                "/games/game-id/players/Alice/properties/some-property/mortgage",
                "some-token",
                this::assertOkResponse
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
        service.setDelegate(new ServiceAdapter(){
            @Override
            public Object settleMortgage() {
                return null;
            }
        });
        delete(
                testContext,
                "/games/game-id/players/Alice/properties/some-property/mortgage",
                "some-token",
                this::assertOkResponse
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
