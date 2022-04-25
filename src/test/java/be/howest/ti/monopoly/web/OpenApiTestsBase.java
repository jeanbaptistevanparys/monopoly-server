package be.howest.ti.monopoly.web;

import be.howest.ti.monopoly.web.tokens.PlainTextTokens;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.client.HttpRequest;
import io.vertx.ext.web.client.HttpResponse;
import io.vertx.ext.web.client.WebClient;
import io.vertx.junit5.VertxExtension;
import io.vertx.junit5.VertxTestContext;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.IOException;
import java.util.Objects;
import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(VertxExtension.class)
class OpenApiTestsBase {

    private int port;
    private static final String HOST = "localhost";
    TestService service;
    private Vertx vertx;
    private WebClient webClient;

    @BeforeEach
    void deploy(final VertxTestContext testContext) {
        service = new TestService();
        WebServer webServer = new WebServer(new MonopolyApiBridge(
                service,
                new PlainTextTokens()
        ));

        ObjectMapper json = new ObjectMapper();
        try {
            port = json.readTree(OpenApiTestsBase.class.getResourceAsStream("/conf/config.json"))
                    .get("port").asInt();
        } catch (IOException e) {
            testContext.failNow("Failed to read port from config");
        }

        vertx = Vertx.vertx();
        vertx.deployVerticle(
                webServer,
                testContext.succeedingThenComplete()
        );

        webClient = WebClient.create(vertx);
    }

    @AfterEach
    void close(final VertxTestContext testContext) {
        vertx.close(testContext.succeedingThenComplete());
        webClient.close();
    }

    void request(
            final VertxTestContext testContext,
            HttpRequest<Buffer> request,
            String token,
            JsonObject body,
            Consumer<HttpResponse<Buffer>> responseCheck
    ) {
        if (token != null) {
            request.bearerTokenAuthentication(token);
        }
        (Objects.isNull(body) ? request.send() : request.sendJsonObject(body))
                .onFailure(testContext::failNow)
                .onSuccess(response -> testContext.verify(() -> {
                    responseCheck.accept(response);
                    testContext.completeNow();
                }));
    }

    void post(final VertxTestContext testContext, String endpoint, String token, Consumer<HttpResponse<Buffer>> responseCheck) {
        request(testContext,
                webClient.post(port, HOST, endpoint),
                token,
                null,
                responseCheck
        );
    }

    void post(final VertxTestContext testContext, String endpoint, String token, JsonObject body, Consumer<HttpResponse<Buffer>> responseCheck) {
        request(testContext,
                webClient.post(port, HOST, endpoint),
                token,
                body,
                responseCheck
        );
    }

    void delete(final VertxTestContext testContext, String endpoint, String token, Consumer<HttpResponse<Buffer>> responseCheck) {
        request(testContext,
                webClient.delete(port, HOST, endpoint),
                token,
                null,
                responseCheck
        );
    }

    void get(final VertxTestContext testContext, String endpoint, String token, Consumer<HttpResponse<Buffer>> responseCheck) {
        request(testContext,
                webClient.get(port, HOST, endpoint),
                token,
                null,
                responseCheck
        );
    }

    void assertOkResponse(HttpResponse<Buffer> response) {
        assertEquals(200, response.statusCode());
    }


    void assertErrorResponse(HttpResponse<Buffer> response, int code) {
        assertEquals(code, response.statusCode(), response.bodyAsString());
        assertEquals(
                code,
                response.bodyAsJsonObject().getInteger("failure")
        );
        assertNotNull(
                response.bodyAsJsonObject().getString("cause")
        );
    }

    void assertNotYetImplemented(HttpResponse<Buffer> response, String operationId) {
        assertErrorResponse(response, 501);
        assertTrue(response.bodyAsJsonObject().getString("cause").contains(operationId));
    }

}

