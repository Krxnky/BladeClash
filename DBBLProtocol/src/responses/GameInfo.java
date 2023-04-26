package responses;

import enums.GameState;
import requests.PlayerInfo;

import java.io.Serializable;

public class GameInfo implements Serializable {
    private final int GameId;
    private final PlayerInfo[] Players;
    private final GameState State;

    public GameInfo(int GameId, PlayerInfo[] Players, GameState State)
    {
        this.GameId = GameId;
        this.Players = Players;
        this.State = State;
    }

    public int getGameId() {
        return GameId;
    }

    public PlayerInfo[] getPlayers() {
        return Players;
    }

    public GameState getState() {
        return State;
    }
}
