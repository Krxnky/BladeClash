package actors;

import greenfoot.Actor;
import greenfoot.Color;
import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;

import java.util.function.Consumer;

public class AttackBar extends Actor {
    private int barWidth = 40;
    private int barHeight = 359;

    private int xOffset = 8;
    private int yOffset = 8;

    private int attackValue = 0;
    private int barSpeed;
    private Consumer<Integer> onClick;
    private boolean active = true;

    public AttackBar(int barSpeed, Consumer<Integer> onClick)
    {
        this.barSpeed = barSpeed;
        this.onClick = onClick;

        setImage(new GreenfootImage("attackbar.png"));
        getImage().setColor(Color.BLACK);
        getImage().drawRect(xOffset-1, yOffset-1, barWidth+1, barHeight+1);
        getImage().setColor(Color.GRAY);
        getImage().fillRect(xOffset, yOffset, barWidth, barHeight);

        setRotation(-90);
    }

    private void updateBar()
    {
        attackValue += 1;
        if(attackValue > barHeight) attackValue = 0;

        getImage().setColor(Color.GRAY);
        getImage().fillRect(xOffset, yOffset, barWidth, barHeight);

        getImage().setColor(new Color(255, 135, 0));
        getImage().fillRect(xOffset, yOffset, barWidth, attackValue);

        GreenfootImage valueText = new GreenfootImage(Integer.toString(attackValue), 20, Color.WHITE, null);
        GreenfootImage valueTextShadow = new GreenfootImage(Integer.toString(attackValue), 20, new Color(0, 0, 0), null);

        valueTextShadow.rotate(90);
        valueText.rotate(90);

        getImage().drawImage(valueTextShadow, (getImage().getWidth()/2) - (valueTextShadow.getWidth()/2)-1, 16);
        getImage().drawImage(valueText, (getImage().getWidth()/2) - (valueText.getWidth()/2), 15);

    }

    public void act()
    {
        if(Greenfoot.mouseClicked(null) && active)
        {
            onClick.accept(attackValue/2);
            active = false;
        }
        else if(active)
        {
            for(int i=0; i<barSpeed; i++)
            {
                updateBar();
            }
        }
    }
}
