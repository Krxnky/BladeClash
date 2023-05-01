package responses;

import requests.PlayerInfo;

import java.io.Serializable;

public class RoundResult implements Serializable {
    private final GameInfo UpdatedGameInfo;

    public RoundResult(GameInfo updatedGameInfo)
    {
        this.UpdatedGameInfo = updatedGameInfo;
    }

    public GameInfo getUpdatedGameInfo() {
        return UpdatedGameInfo;
    }
}
