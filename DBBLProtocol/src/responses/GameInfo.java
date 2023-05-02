package responses;

import enums.GameState;
import requests.PlayerInfo;

import java.io.Serializable;
import java.util.List;

public class GameInfo implements Serializable {
    private final int GameId;
    private final List<PlayerInfo> Players;
    private /*final*/ GameState State;
    private int round;
    private int attackSpeed;

    public GameInfo(int GameId, List<PlayerInfo> Players, GameState State)
    {
        this.GameId = GameId;
        this.Players = Players;
        this.State = State;
        round = 1;
    }

    public int getGameId() {
        return GameId;
    }

    public List<PlayerInfo> getPlayers() {
        return Players;
    }

    public GameState getState() {
        return State;
    }
    public void setState(GameState gs){
        State =gs;
    }

}
