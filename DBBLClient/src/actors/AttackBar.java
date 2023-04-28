package actors;

import greenfoot.Actor;
import greenfoot.Color;
import greenfoot.GreenfootImage;

public class AttackBar extends Actor {
    private int barWidth = 75;
    private int barHeight = 300;

    private int attackValue = 0;
    private int barSpeed;

    public AttackBar(int barSpeed)
    {
        this.barSpeed = barSpeed;

        setImage(new GreenfootImage(barWidth+2, barHeight+2));
        getImage().setColor(Color.BLACK);
        getImage().drawRect(0, 0, barWidth+1, barHeight+1);
        getImage().setColor(Color.GRAY);
        getImage().fillRect(1, 1, barWidth, barHeight);
        setRotation(180);
    }

    public int getAttackValue()
    {
        getWorld().removeObject(this);
        return attackValue / 2;
    }

    private void updateBar()
    {
        System.out.println(attackValue);
        attackValue += 1;
        if(attackValue > barHeight) attackValue = 0;

        getImage().setColor(Color.GRAY);
        getImage().fillRect(1, 1, barWidth, barHeight);

        getImage().setColor(Color.ORANGE);
        getImage().fillRect(1, 1, barWidth, attackValue);
    }

    public void act()
    {
        for(int i=0; i<barSpeed; i++)
        {
            updateBar();
        }
    }
}
