package actors;

import greenfoot.Actor;
import greenfoot.Color;
import greenfoot.GreenfootImage;

public class HealthBar extends Actor {
    private int health = 0;
    private int healthBarWidth = 300;
    private int healthBarHeight = 50;

    public HealthBar(int health)
    {
        this.health = health;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    @Override
    public void act()
    {
        setImage(new GreenfootImage(healthBarWidth+2, healthBarHeight+2));
        getImage().setColor(Color.BLACK);
        getImage().drawRect(0, 0, healthBarWidth+1, healthBarHeight+1);
        getImage().setColor(Color.RED);
        getImage().fillRect(1, 1, health*3, healthBarHeight);
    }
}
