package be.howest.ti.monopoly.web.tokens;

import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.impl.UserImpl;

public class MonopolyUser extends UserImpl {

    public MonopolyUser(String gameId, String playerName) {
        super(new JsonObject()
                .put("gameId", gameId)
                .put("playerName", playerName)
        );
    }

    public String getGameId() {
        return this.principal().getString("gameId");
    }

    public String getPlayerName() {
        return this.principal().getString("playerName");
    }

}
