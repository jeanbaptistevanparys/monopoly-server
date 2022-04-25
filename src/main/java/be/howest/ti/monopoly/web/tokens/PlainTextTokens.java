package be.howest.ti.monopoly.web.tokens;

public class PlainTextTokens implements TokenManager {

    private static final int TOKEN_GAME_ID_PART = 0;
    private static final int TOKEN_PLAYER_PART = 1;
    private static final int TOKEN_EXPECTED_PARTS = 2;

    @Override
    public String createToken(MonopolyUser user) {
        return String.format("%s-%s", user.getGameId(), user.getPlayerName());
    }

    @Override
    public MonopolyUser createUser(String token) {
        String[] parts = token.split("-");

        if (parts.length != TOKEN_EXPECTED_PARTS) {
            throw new InvalidTokenException();
        }
        return new MonopolyUser(
                parts[TOKEN_GAME_ID_PART],
                parts[TOKEN_PLAYER_PART]
        );
    }

    @Override
    public boolean isValidPlayerName(String playerName) {
        return !playerName.contains("-");
    }

}

