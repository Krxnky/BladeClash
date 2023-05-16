package requests;

import java.io.Serializable;

public class PlayerInfo implements Serializable {
    private final int playerId;
    private final String ip;
    private int health;

    public PlayerInfo(int playerId, String ip, int health)
    {
        this.playerId = playerId;
        this.ip = ip;
        this.health = health;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health){ this.health = health;}
    public int getPlayerId() {
        return playerId;
    }

    public String getIp() {
        return ip;
    }
}
