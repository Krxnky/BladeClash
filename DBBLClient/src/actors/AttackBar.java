package actors;

import greenfoot.Actor;
import greenfoot.Color;
import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;

import java.util.function.Consumer;

public class AttackBar extends Actor {
    private int barWidth = 75;
    private int barHeight = 300;

    private int attackValue = 0;
    private int barSpeed;
    private Consumer<Integer> onClick;

    public AttackBar(int barSpeed, Consumer<Integer> onClick)
    {
        this.barSpeed = barSpeed;
        this.onClick = onClick;

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
        attackValue += 1;
        if(attackValue > barHeight) attackValue = 0;

        getImage().setColor(Color.GRAY);
        getImage().fillRect(1, 1, barWidth, barHeight);

        getImage().setColor(Color.ORANGE);
        getImage().fillRect(1, 1, barWidth, attackValue);
    }

    public void act()
    {
        if(Greenfoot.mouseClicked(null))
        {
            onClick.accept(attackValue/2);
            getWorld().removeObject(this);
        };
        for(int i=0; i<barSpeed; i++)
        {
            updateBar();
        }
    }
}
