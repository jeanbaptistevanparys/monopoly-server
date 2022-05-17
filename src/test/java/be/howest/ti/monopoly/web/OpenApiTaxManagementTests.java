package be.howest.ti.monopoly.web;

import be.howest.ti.monopoly.logic.ServiceAdapter;
import io.vertx.junit5.VertxTestContext;
import org.junit.jupiter.api.Test;


class OpenApiTaxManagementTests extends OpenApiTestsBase {

    @Test
    void useEstimateTax(final VertxTestContext testContext) {
        service.setDelegate(new ServiceAdapter() {
            @Override
            public Object useEstimateTax(String gameId, String playerName) {
                return null;
            }
        });
        post(
                testContext,
                "/games/group12_1/players/Alice/tax/estimate",
                "group12_1-Alice",
                this::assertOkResponse
        );
    }

    @Test
    void useEstimateTaxUnauthorized(final VertxTestContext testContext) {
        post(
                testContext,
                "/games/game-id/players/Alice/tax/estimate",
                null,
                response -> assertErrorResponse(response, 401)
        );
    }

    @Test
    void useComputeTax(final VertxTestContext testContext) {
        service.setDelegate(new ServiceAdapter() {
            @Override
            public Object useComputeTax(String gameId, String playerName) {
                return null;
            }
        });
        post(
                testContext,
                "/games/group12_1/players/Alice/tax/compute",
                "group12_1-Alice",
                this::assertOkResponse
        );
    }

    @Test
    void useComputeTaxUnauthorized(final VertxTestContext testContext) {
        post(
                testContext,
                "/games/game-id/players/Alice/tax/compute",
                null,
                response -> assertErrorResponse(response, 401)
        );
    }
}
