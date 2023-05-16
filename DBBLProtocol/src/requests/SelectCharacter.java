package requests;

import enums.CharacterType;

import java.io.Serializable;

public class SelectCharacter implements Serializable {
    private final CharacterType Character;
    public SelectCharacter(CharacterType character)
    {
        this.Character = character;
    }

    public CharacterType getCharacter() {
        return Character;
    }
}
