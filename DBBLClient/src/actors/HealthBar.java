package actors;

import greenfoot.Actor;
import greenfoot.Color;
import greenfoot.GreenfootImage;

public class HealthBar extends Actor {
    private int health = 1000;
    private boolean isEnemy;
    private int healthBarWidth = 375;
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
        GreenfootImage healthbarBorder = new GreenfootImage("healthbar_border.png");
        setImage(healthbarBorder);
        getImage().setColor(Color.BLACK);
        getImage().drawRect(25, 5, healthBarWidth+1, healthBarHeight+1);
        getImage().setColor(Color.RED);
        getImage().fillRect(26, 6, healthBarWidth, healthBarHeight);
        getImage().setColor(Color.GREEN);
        getImage().fillRect(26, 6, (int) ((double)health*pixelsPerHealthPoint), healthBarHeight);
        if(isEnemy) getImage().mirrorHorizontally();
        GreenfootImage healhText = new GreenfootImage(String.format("%,.0f", (double)health), 15, Color.WHITE, null);
        GreenfootImage healhTextShadow = new GreenfootImage(String.format("%,.0f", (double)health), 15, new Color(0, 0, 0), null);
        getImage().drawImage(healhTextShadow, (isEnemy) ? getImage().getWidth() - healhTextShadow.getWidth() - 5 : 5, 22);
        getImage().drawImage(healhText, (isEnemy) ? getImage().getWidth() - healhText.getWidth() - 5: 5, 20);


    }
}
