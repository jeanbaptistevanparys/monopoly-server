package be.howest.ti.monopoly.web;

import be.howest.ti.monopoly.logic.ServiceAdapter;
import be.howest.ti.monopoly.logic.implementation.Auction;
import be.howest.ti.monopoly.logic.implementation.Tile;
import io.vertx.core.json.JsonObject;
import io.vertx.junit5.VertxTestContext;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;


class OpenApiAuctionsTests extends OpenApiTestsBase {

    @Test
    void getBankAuctions(final VertxTestContext testContext) {
        service.setDelegate(new ServiceAdapter(){
            @Override
            public List<Auction> getBankAuctions() {
                return Collections.emptyList();
            }
        });
        get(
                testContext,
                "/games/game-id/bank/auctions",
                "some-token",
                this::assertOkResponse
        );
    }

    @Test
    void getBankAuctionsUnauthorized(final VertxTestContext testContext) {
        get(
                testContext,
                "/games/game-id/bank/auctions",
                null,
                response -> assertErrorResponse(response, 401)
        );
    }

    @Test
    void placeBidOnBankAuction(final VertxTestContext testContext) {
        service.setDelegate(new ServiceAdapter(){
            @Override
            public List<Auction> placeBidOnBankAuction() {
                return Collections.emptyList();
            }
        });
        service.setDelegate(new ServiceAdapter(){
            @Override
            public Object placeBidOnBankAuction() {
                return Collections.emptyList();
            }
        });
        post(
                testContext,
                "/games/game-id/bank/auctions/some-property/bid",
                "some-token",
                new JsonObject()
                        .put("bidder", "Alice")
                        .put("amount", 100),
                this::assertOkResponse
        );
    }

    @Test
    void placeBidOnBankAuctionWithEmptyBody(final VertxTestContext testContext) {
        post(
                testContext,
                "/games/game-id/bank/auctions/some-property/bid",
                "some-token",
                new JsonObject(),
                response -> assertErrorResponse(response, 400)
        );
    }

    @Test
    void placeBidOnBankAuctionWithoutBody(final VertxTestContext testContext) {
        post(
                testContext,
                "/games/game-id/bank/auctions/some-property/bid",
                "some-token",
                response -> assertErrorResponse(response, 400)
        );
    }

    @Test
    void placeBidOnBankAuctionUnauthorized(final VertxTestContext testContext) {
        post(
                testContext,
                "/games/game-id/bank/auctions/some-property/bid",
                null,
                response -> assertErrorResponse(response, 401)
        );
    }


    @Test
    void getPlayerAuctions(final VertxTestContext testContext) {
        service.setDelegate(new ServiceAdapter(){
            @Override
            public List<Auction> getPlayerAuctions() {
                return Collections.emptyList();
            }
        });
        get(
                testContext,
                "/games/game-id/players/Alice/auctions",
                "some-token",
                this::assertOkResponse
        );
    }

    @Test
    void getPlayerAuctionsUnauthorized(final VertxTestContext testContext) {
        get(
                testContext,
                "/games/game-id/players/Alice/auctions",
                null,
                response -> assertErrorResponse(response, 401)
        );
    }

    @Test
    void startPlayerAuction(final VertxTestContext testContext) {
        service.setDelegate(new ServiceAdapter(){
            @Override
            public Object startPlayerAuction() {
                return null;
            }
        });
        post(
                testContext,
                "/games/game-id/players/Alice/auctions/some-property",
                "some-token",
                this::assertOkResponse
        );
    }

    @Test
    void startPlayerAuctionUnauthorized(final VertxTestContext testContext) {
        post(
                testContext,
                "/games/game-id/players/Alice/auctions/some-property",
                null,
                response -> assertErrorResponse(response, 401)
        );
    }

    @Test
    void placeBidOnPlayerAuction(final VertxTestContext testContext) {
        service.setDelegate(new ServiceAdapter(){
            @Override
            public Object placeBidOnPlayerAuction() {
                return null;
            }
        });
        post(
                testContext,
                "/games/game-id/players/Alice/auctions/some-property/bid",
                "some-token",
                new JsonObject()
                        .put("bidder", "Bob")
                        .put("amount", 100),
                this::assertOkResponse
        );
    }

    @Test
    void placeBidOnPlayerAuctionWithoutBody(final VertxTestContext testContext) {
        post(
                testContext,
                "/games/game-id/players/Alice/auctions/some-property/bid",
                "some-token",
                response -> assertErrorResponse(response, 400)
        );
    }

    @Test
    void placeBidOnPlayerAuctionWithEmptyBody(final VertxTestContext testContext) {
        post(
                testContext,
                "/games/game-id/players/Alice/auctions/some-property/bid",
                "some-token",
                new JsonObject(),
                response -> assertErrorResponse(response, 400)
        );
    }

    @Test
    void placeBidOnPlayerAuctionUnauthorized(final VertxTestContext testContext) {
        post(
                testContext,
                "/games/game-id/players/Alice/auctions/some-property/bid",
                null,
                response -> assertErrorResponse(response, 401)
        );
    }

}
