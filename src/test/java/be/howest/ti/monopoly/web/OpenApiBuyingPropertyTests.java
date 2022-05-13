package be.howest.ti.monopoly.web;

import be.howest.ti.monopoly.logic.ServiceAdapter;
import be.howest.ti.monopoly.logic.implementation.Game;
import io.vertx.junit5.VertxTestContext;
import org.junit.jupiter.api.Test;


class OpenApiBuyingPropertyTests extends OpenApiTestsBase {

    @Test
    void buyProperty(final VertxTestContext testContext) {
        service.setDelegate(new ServiceAdapter(){
            @Override
            public Object buyProperty(String gameId, String playerName, String propertyName) {
                return null;
            }
        });
        post(
                testContext,
                "/games/group12_1/players/Alice/properties/some-property",
                "group12_1-Alice",
                this::assertOkResponse
        );
    }

    @Test
    void buyPropertyUnauthorized(final VertxTestContext testContext) {
        post(
                testContext,
                "/games/game-id/players/Alice/properties/some-property",
                null,
                response -> assertErrorResponse(response, 401)
        );
    }

    @Test
    void dontBuyProperty(final VertxTestContext testContext) {
        service.setDelegate(new ServiceAdapter(){
            @Override
            public Object dontBuyProperty(String gameId, String playerName, String propertyName) {
                return null;
            }
        });
        delete(
                testContext,
                "/games/group12_1/players/Alice/properties/some-property",
                "group12_1-Alice",
                this::assertOkResponse
        );
    }

    @Test
    void dontBuyPropertyUnauthorized(final VertxTestContext testContext) {
        delete(
                testContext,
                "/games/game-id/players/Alice/properties/some-property",
                null,
                response -> assertErrorResponse(response, 401)
        );
    }
}
