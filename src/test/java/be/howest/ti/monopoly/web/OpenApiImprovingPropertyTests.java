package be.howest.ti.monopoly.web;

import be.howest.ti.monopoly.logic.ServiceAdapter;
import io.vertx.junit5.VertxTestContext;
import org.junit.jupiter.api.Test;


class OpenApiImprovingPropertyTests extends OpenApiTestsBase {

    @Test
    void buyHouse(final VertxTestContext testContext) {
        service.setDelegate(new ServiceAdapter(){
            @Override
            public Object buyHouse(String gameId, String playerName, String propertyName) {
                return null;
            }
        });
        post(
                testContext,
                "/games/game-id/players/Alice/properties/some-property/houses",
                "some-token",
                this::assertOkResponse
        );
    }

    @Test
    void buyHouseUnauthorized(final VertxTestContext testContext) {
        post(
                testContext,
                "/games/game-id/players/Alice/properties/some-property/houses",
                null,
                response -> assertErrorResponse(response, 401)
        );
    }

    @Test
    void sellHouse(final VertxTestContext testContext) {
        service.setDelegate(new ServiceAdapter(){
            @Override
            public Object sellHouse(String gameId, String playerName, String propertyName) {
                return null;
            }
        });
        delete(
                testContext,
                "/games/game-id/players/Alice/properties/some-property/houses",
                "some-token",
                this::assertOkResponse
        );
    }

    @Test
    void sellHouseUnauthorized(final VertxTestContext testContext) {
        delete(
                testContext,
                "/games/game-id/players/Alice/properties/some-property/houses",
                null,
                response -> assertErrorResponse(response, 401)
        );
    }

    //
    @Test
    void buyHotel(final VertxTestContext testContext) {
        service.setDelegate(new ServiceAdapter(){
            @Override
            public Object buyHotel(String gameId, String playerName, String propertyName) {
                return null;
            }
        });
        post(
                testContext,
                "/games/game-id/players/Alice/properties/some-property/hotel",
                "some-token",
                this::assertOkResponse
        );
    }

    @Test
    void buyHotelUnauthorized(final VertxTestContext testContext) {
        post(
                testContext,
                "/games/game-id/players/Alice/properties/some-property/hotel",
                null,
                response -> assertErrorResponse(response, 401)
        );
    }

    @Test
    void sellHotel(final VertxTestContext testContext) {
        service.setDelegate(new ServiceAdapter(){
            @Override
            public Object sellHotel(String gameId, String playerName, String propertyName) {
                return null;
            }
        });
        delete(
                testContext,
                "/games/game-id/players/Alice/properties/some-property/hotel",
                "some-token",
                this::assertOkResponse
        );
    }

    @Test
    void sellHotelUnauthorized(final VertxTestContext testContext) {
        delete(
                testContext,
                "/games/game-id/players/Alice/properties/some-property/hotel",
                null,
                response -> assertErrorResponse(response, 401)
        );
    }
}
