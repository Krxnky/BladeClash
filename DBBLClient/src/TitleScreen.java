import greenfoot.Color;
import greenfoot.GreenfootImage;
import greenfoot.World;
import javafx.scene.layout.Background;

public class TitleScreen extends World {
    public TitleScreen()
    {
        super(1000, 600, 1, false);

        setBackground(new GreenfootImage("title_screen_bg.jpg"));
        GreenfootImage logo = new GreenfootImage("dbbl_logo.png");
        getBackground().drawImage(logo, (getWidth()/2) - (logo.getWidth()/2), 25);
    }
}
