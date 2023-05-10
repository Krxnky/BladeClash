package responses;

import enums.GameState;
import requests.PlayerInfo;

import java.io.Serializable;
import java.util.List;

public class GameInfo implements Serializable {
    private int GameId;
    private PlayerInfo[] Players;
    private GameState State;
    private int RoundNumber;

    private PlayerInfo mostRecentWinner;

    public GameInfo(int GameId, PlayerInfo[] Players, GameState State, int RoundNumber)
    {
        this.GameId = GameId;
        this.Players = Players;
        this.State = State;
        this.RoundNumber = RoundNumber;
    }

    public int getRoundNumber() {
        return RoundNumber;
    }

    public void setRoundNumber(int roundNumber) {
        RoundNumber = roundNumber;
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
