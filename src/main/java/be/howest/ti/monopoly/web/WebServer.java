package be.howest.ti.monopoly.web;

import io.vertx.config.ConfigRetriever;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.CorsHandler;
import io.vertx.ext.web.openapi.RouterBuilder;

import java.util.logging.Level;
import java.util.logging.Logger;


public class WebServer extends AbstractVerticle {
    private static final Logger LOGGER = Logger.getLogger(WebServer.class.getName());
    private final MonopolyApiBridge bridge;
    private Promise<Void> startPromise;

    public WebServer(MonopolyApiBridge bridge) {
        this.bridge = bridge;
    }

    public WebServer() {
        this(new MonopolyApiBridge());
    }

    @Override
    public void start(Promise<Void> startPromise) {
        this.startPromise = startPromise;
        ConfigRetriever.create(vertx).getConfig()
                .onFailure(cause -> shutDown("Failed to load configuration", cause))
                .onSuccess(configuration -> {
                    LOGGER.log(Level.INFO, "Configuration loaded: {0}", configuration);
                    final int port = configuration.getInteger("port");
                    final String apiUrl = configuration.getString("openApi3Spec");
                    LOGGER.log(Level.INFO, "Server will be listening at port {0}", port);

                    RouterBuilder.create(vertx, apiUrl)
                            .onFailure(cause -> shutDown("Failed to load API specification", cause))
                            .onSuccess(routerBuilder -> {
                                LOGGER.log(Level.INFO, "API specification loaded: {0}",
                                        routerBuilder.getOpenAPI().getOpenAPI().getJsonObject("info").getString("version"));

                                Router mainRouter = Router.router(vertx);
                                mainRouter.route().handler(createCorsHandler());
                                Router openApiRouter = bridge.buildRouter(routerBuilder);
                                mainRouter.mountSubRouter("/", openApiRouter);

                                vertx.createHttpServer()
                                        .requestHandler(mainRouter)
                                        .listen(port)
                                        .onFailure(cause -> shutDown("Failed to start server", cause))
                                        .onSuccess(server -> {
                                            LOGGER.log(Level.INFO, "Server is listening on port: {0}", server.actualPort());
                                            startPromise.complete();
                                        });
                            });
                });

    }


    private void shutDown(String message, Throwable cause) {
        LOGGER.log(Level.SEVERE, message, cause);
        LOGGER.info("Shutting down");
        vertx.close();
        startPromise.fail(cause);
    }

    private CorsHandler createCorsHandler() {
        return CorsHandler.create(".*.")
                .allowedHeader("x-requested-with")
                .allowedHeader("Access-Control-Allow-Origin")
                .allowedHeader("Access-Control-Allow-Credentials")
                .allowCredentials(true)
                .allowedHeader("origin")
                .allowedHeader("Content-Type")
                .allowedHeader("Authorization")
                .allowedHeader("accept")
                .allowedMethod(HttpMethod.HEAD)
                .allowedMethod(HttpMethod.GET)
                .allowedMethod(HttpMethod.POST)
                .allowedMethod(HttpMethod.OPTIONS)
                .allowedMethod(HttpMethod.PATCH)
                .allowedMethod(HttpMethod.DELETE)
                .allowedMethod(HttpMethod.PUT);
    }
}
