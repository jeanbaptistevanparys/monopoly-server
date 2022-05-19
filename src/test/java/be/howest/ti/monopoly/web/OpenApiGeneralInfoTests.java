package be.howest.ti.monopoly.web;

import be.howest.ti.monopoly.logic.ServiceAdapter;
import be.howest.ti.monopoly.logic.implementation.tiles.Tile;
import io.vertx.junit5.VertxTestContext;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

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
        service.setDelegate(new ServiceAdapter(){
            @Override
            public List<Tile> getTiles() {
                return Collections.emptyList();
            }
        });
        get(
                testContext,
                "/tiles",
                null,
                this::assertOkResponse
        );
    }


    @Test
    void getTileByName(final VertxTestContext testContext) {
        service.setDelegate(new ServiceAdapter(){
            @Override
            public Tile getTile(String name) {
                return null;
            }
        });
        get(
                testContext,
                "/tiles/something",
                null,
                this::assertOkResponse
        );
    }

    @Test
    void getTileById(final VertxTestContext testContext) {
        service.setDelegate(new ServiceAdapter(){
            @Override
            public Tile getTile(int position) {
                return null;
            }
        });
        get(
                testContext,
                "/tiles/100",
                null,
                this::assertOkResponse
        );
    }


    @Test
    void getChance(final VertxTestContext testContext) {
        service.setDelegate(new ServiceAdapter() {
            @Override
            public List<String> getChance() {
                return Collections.emptyList();
            }
        });
        get(
                testContext,
                "/chance",
                null,
                this::assertOkResponse
        );
    }


    @Test
    void getCommunityChest(final VertxTestContext testContext) {
        service.setDelegate(new ServiceAdapter(){
            @Override
            public List<String> getCommunityChest() {
                return Collections.emptyList();
            }
        });
        get(
                testContext,
                "/community-chest",
                null,
                this::assertOkResponse
        );
    }

}
