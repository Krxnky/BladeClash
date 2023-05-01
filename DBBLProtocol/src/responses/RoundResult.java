package responses;

import requests.PlayerInfo;

import java.io.Serializable;

public class RoundResult implements Serializable {
    private final GameInfo UpdatedGameInfo;
//    private final PlayerInfo UpdatedLocalPlayerInfo;

    public RoundResult(GameInfo updatedGameInfo/* PlayerInfo updatedLocalPlayerInfo*/)
    {
        this.UpdatedGameInfo = updatedGameInfo;
//        this.UpdatedLocalPlayerInfo = updatedLocalPlayerInfo;
    }

    public GameInfo getUpdatedGameInfo() {
        return UpdatedGameInfo;
    }

//    public PlayerInfo getUpdatedLocalPlayerInfo() {
//        return UpdatedLocalPlayerInfo;
//    }
}
