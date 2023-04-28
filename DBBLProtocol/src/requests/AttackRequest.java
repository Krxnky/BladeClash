package requests;

import enums.AttackType;

import java.io.Serializable;

public class AttackRequest implements Serializable {
    private final int AttackValue;
    private final AttackType Attack;

    public AttackRequest(int attackValue, AttackType attack)
    {
        this.AttackValue = attackValue;
        this.Attack = attack;
    }

    public int getAttackValue() {
        return AttackValue;

    }

    public AttackType getAttack() {
        return Attack;
    }
}
