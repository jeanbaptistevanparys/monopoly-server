package be.howest.ti.monopoly.web;

import be.howest.ti.monopoly.logic.IService;
import be.howest.ti.monopoly.logic.ServiceAdapter;
import io.vertx.junit5.VertxTestContext;
import org.junit.jupiter.api.Test;

class OpenApiInteractionWithOtherPlayerTests extends OpenApiTestsBase {
    @Test
    void collectDebt(final VertxTestContext testContext) {
        service.setDelegate(new ServiceAdapter(){
            @Override
            public Object collectDebt(String gameId, String playerName, String propertyName, String debtorName) {
                return null;
            }
        });
        delete(
                testContext,
                "/games/1-Alice/players/Alice/properties/some-property/visitors/Bob/rent",
                "1-Alice",
                this::assertOkResponse
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
