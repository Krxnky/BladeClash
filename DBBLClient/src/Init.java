import worlds.TitleScreen;
import greenfoot.Greenfoot;
import greenfoot.World;

public class Init extends World {
    TitleScreen _titleScreen;

    public Init()
    {
        super(1000, 600, 1, false);

        _titleScreen = new TitleScreen();
        Greenfoot.setWorld(_titleScreen);
    }
}
