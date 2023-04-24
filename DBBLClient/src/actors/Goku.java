package actors;

import greenfoot.Actor;
import greenfoot.GreenfootImage;

public class Goku extends Actor {

    public Goku()
    {
        GreenfootImage cImg = new GreenfootImage("gokusprite.png");
        cImg.mirrorHorizontally();
        setImage(cImg);
    }

    @Override
    public void act() {
        super.act();
    }
}
