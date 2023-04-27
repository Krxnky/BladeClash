package actors;

import greenfoot.Actor;
import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;

import java.util.function.Consumer;

public class Button extends Actor {
    private Runnable onClick;

    public Button(String btnImg, Runnable onClick)
    {
        this.onClick = onClick;
        setImage(new GreenfootImage(btnImg));
    }

    public void act()
    {
        if(Greenfoot.mouseClicked(this)) onClick.run();
    }
}
