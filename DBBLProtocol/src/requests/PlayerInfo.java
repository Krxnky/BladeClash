package requests;

import enums.CharacterType;

import java.io.Serializable;

public class PlayerInfo implements Serializable {
    private final int playerId;
    private final String ip;
    private int health;
    private CharacterType Character;

    public PlayerInfo(int playerId, String ip, int health, CharacterType character)
    {
        this.playerId = playerId;
        this.ip = ip;
        this.health = health;
        this.Character = character;
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

    public CharacterType getCharacter() {
        return Character;
    }
}
