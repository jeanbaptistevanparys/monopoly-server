package be.howest.ti.monopoly.web;

import be.howest.ti.monopoly.logic.ServiceAdapter;
import io.vertx.junit5.VertxTestContext;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class OpenApiGeneralInfoTests extends OpenApiTestsBase {

    @Test
    void getInfo(final VertxTestContext testContext) {
        String versionForTest = "test-version";
        service.setDelegate(new ServiceAdapter() {
            @Override
            public String getVersion() {
                return versionForTest;
            }
        });

        get(
                testContext,
                "/",
                null,
                response -> {
                    assertEquals(200, response.statusCode());
                    assertEquals("monopoly", response.bodyAsJsonObject().getString("name"));
                    assertEquals(versionForTest, response.bodyAsJsonObject().getString("version"));
                }
        );
    }

    @Test
    void getTiles(final VertxTestContext testContext) {
        get(
                testContext,
                "/tiles",
                null,
                response -> assertNotYetImplemented(response, "getTiles")
        );
    }


    @Test
    void getTileByName(final VertxTestContext testContext) {
        get(
                testContext,
                "/tiles/something",
                null,
                response -> assertNotYetImplemented(response, "getTile")
        );
    }

    @Test
    void getTileById(final VertxTestContext testContext) {
        get(
                testContext,
                "/tiles/100",
                null,
                response -> assertNotYetImplemented(response, "getTile")
        );
    }


    @Test
    void getChance(final VertxTestContext testContext) {
        get(
                testContext,
                "/chance",
                null,
                response -> assertNotYetImplemented(response, "getChance")
        );
    }


    @Test
    void getCommunityChest(final VertxTestContext testContext) {
        get(
                testContext,
                "/community-chest",
                null,
                response -> assertNotYetImplemented(response, "getCommunityChest")
        );
    }

}
