package be.howest.ti.monopoly.web;

import be.howest.ti.monopoly.logic.ServiceAdapter;
import be.howest.ti.monopoly.logic.exceptions.IllegalMonopolyActionException;
import be.howest.ti.monopoly.logic.exceptions.InsufficientFundsException;
import be.howest.ti.monopoly.logic.exceptions.MonopolyResourceNotFoundException;
import be.howest.ti.monopoly.web.exceptions.ForbiddenAccessException;
import be.howest.ti.monopoly.web.exceptions.InvalidRequestException;
import be.howest.ti.monopoly.web.exceptions.NotYetImplementedException;
import io.vertx.junit5.VertxTestContext;
import org.junit.jupiter.api.Test;

import java.util.function.Supplier;


class OpenApiHttpErrorTests extends OpenApiTestsBase {

    @Test
    void throwInvalidRequestException(final VertxTestContext testContext) {
        checkErrorResponse(testContext, 400, () -> new InvalidRequestException("some-message"));
    }

    @Test
    void throwIllegalArgumentException(final VertxTestContext testContext) {
        checkErrorResponse(testContext, 400, () -> new IllegalArgumentException("some-message"));
    }

    @Test
    void throwInsufficientFundsException(final VertxTestContext testContext) {
        checkErrorResponse(testContext, 402, () -> new InsufficientFundsException("some-message"));
    }

    @Test
    void throwForbiddenAccessException(final VertxTestContext testContext) {
        checkErrorResponse(testContext, 403, () -> new ForbiddenAccessException("some-message"));
    }

    @Test
    void throwMonopolyResourceNotFoundException(final VertxTestContext testContext) {
        checkErrorResponse(testContext, 404, () -> new MonopolyResourceNotFoundException("some-message"));
    }

    @Test
    void throwIllegalMonopolyActionException(final VertxTestContext testContext) {
        checkErrorResponse(testContext, 409, () -> new IllegalMonopolyActionException("some-message"));
    }

    @Test
    void throwNotYetImplementedException(final VertxTestContext testContext) {
        checkErrorResponse(testContext, 501, () -> new NotYetImplementedException("some-message"));
    }

    void checkErrorResponse(final VertxTestContext testContext, int code, Supplier<RuntimeException> thrower) {
        service.setDelegate(new ServiceAdapter() {
            @Override
            public String getVersion() {
                throw thrower.get();
            }
        });

        get(
                testContext,
                "/",
                null,
                response -> assertErrorResponse(response, code)
        );
    }

}
