package responses;

import enums.GameState;
import requests.PlayerInfo;

import java.io.Serializable;
import java.util.List;

public class GameInfo implements Serializable {
    private int GameId;
    private PlayerInfo[] Players;
    private GameState State;

    private PlayerInfo mostRecentWinner;

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

    public void setState(GameState gs){
        State = gs;
    }
    public void updatePlayerInfo(PlayerInfo[] playerInfos){
        Players = playerInfos;
    }

    public void setMostRecentWinner(PlayerInfo mostRecentWinner) {
        this.mostRecentWinner = mostRecentWinner;
    }

    public PlayerInfo getMostRecentWinner() {
        return mostRecentWinner;
    }
}
