package requests;

import java.io.Serializable;

public class PlayerInfo implements Serializable {
    private final int playerId;
    private final String ip;
    public PlayerInfo(int playerId){
        this.playerId = playerId;
    }
    public PlayerInfo(int playerId, String ip)
    {
        this.playerId = playerId;
        this.ip = ip;
    }

    public int getPlayerId() {
        return playerId;
    }

    public String getIp() {
        return ip;
    }
}
