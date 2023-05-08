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
        if(health < 0) health = 0;
        this.health = health;
    }

    @Override
    public void act()
    {
        GreenfootImage healthbarBorder = new GreenfootImage("healthbar_border.png");
        setImage(healthbarBorder);
        getImage().setColor(Color.BLACK);
        getImage().drawRect(25, 5, healthBarWidth+1, healthBarHeight+1);
        getImage().setColor(new Color(175, 0, 0));
        getImage().fillRect(26, 6, healthBarWidth, healthBarHeight);
        getImage().setColor(new Color(0, 185, 0));
        getImage().fillRect(26, 6, (int) ((double)health*pixelsPerHealthPoint), healthBarHeight);
        if(isEnemy) getImage().mirrorHorizontally();
        GreenfootImage healthText = new GreenfootImage(String.format("%,.0f", (double)health), 15, Color.WHITE, null);
        GreenfootImage healthTextShadow = new GreenfootImage(String.format("%,.0f", (double)health), 15, new Color(0, 0, 0), null);
        getImage().drawImage(healthTextShadow, (isEnemy) ? getImage().getWidth() - healthTextShadow.getWidth() - 5 : 5, 22);
        getImage().drawImage(healthText, (isEnemy) ? getImage().getWidth() - healthText.getWidth() - 5: 5, 20);


    }
}
