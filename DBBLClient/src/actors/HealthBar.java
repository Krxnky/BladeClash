package actors;

import greenfoot.Actor;
import greenfoot.Color;
import greenfoot.GreenfootImage;

public class HealthBar extends Actor {
    private int health = 1000;
    private boolean isEnemy;
    private int healthBarWidth = 300;
    private int healthBarHeight = 15;
    private double pixelsPerHealthPoint = (double)healthBarWidth/(double)health;

    public HealthBar(int health, boolean isEnemy)
    {
        this.health = health;
        this.isEnemy = isEnemy;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    @Override
    public void act()
    {
        setImage(new GreenfootImage(healthBarWidth+2, healthBarHeight+25));
        getImage().setColor(Color.BLACK);
        getImage().drawRect(0, 20, healthBarWidth+1, healthBarHeight+1);
        getImage().setColor(Color.RED);
        getImage().fillRect(1, 21, healthBarWidth, healthBarHeight);
        getImage().setColor(Color.GREEN);
        getImage().fillRect(1, 21, (int) ((double)health*pixelsPerHealthPoint), healthBarHeight);
        if(isEnemy) getImage().mirrorHorizontally();
        GreenfootImage healhText = new GreenfootImage(String.format("%,.0f", (double)health), 20, Color.WHITE, null);
        GreenfootImage healhTextShadow = new GreenfootImage(String.format("%,.0f", (double)health), 20, new Color(0, 0, 0), null);
        getImage().drawImage(healhTextShadow, (isEnemy) ? getImage().getWidth() - healhTextShadow.getWidth() : 0, 2);
        getImage().drawImage(healhText, (isEnemy) ? getImage().getWidth() - healhText.getWidth() : 0, 0);


    }
}
