import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;
import greenfoot.World;

public class Init extends World {
    public Init()
    {
        super(1000, 600, 1, false);

        TitleScreen titleScreen = new TitleScreen();
        Greenfoot.setWorld(titleScreen);
    }
}
