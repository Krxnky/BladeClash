package responses;

import java.io.Serializable;

public class WaitingForAttack implements Serializable {
    private final int AttackBarSpeed;

    public WaitingForAttack(int attackBarSpeed)
    {
        this.AttackBarSpeed = attackBarSpeed;
    }

    public int getAttackBarSpeed() {
        return AttackBarSpeed;
    }
}
