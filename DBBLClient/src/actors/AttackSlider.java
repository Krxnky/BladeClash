package actors;

import greenfoot.Actor;
import greenfoot.Color;
import greenfoot.GreenfootImage;

public class AttackSlider extends Actor {
    private int sliderWidth = 100;
    private int sliderHeight = 200;

    public AttackSlider()
    {
        setImage(new GreenfootImage(sliderWidth, sliderHeight));
        getImage().setColor(Color.BLACK);
        getImage().drawRect(0, 0, sliderWidth+1, sliderHeight+1);

    }

    public void act()
    {

    }
}
