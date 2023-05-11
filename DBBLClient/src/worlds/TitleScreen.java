package worlds;

import greenfoot.*;

public class TitleScreen extends World {
    private GreenfootSound backgroundMusic = new GreenfootSound("main_menu_music.mp3");
    public TitleScreen()
    {
        super(1000, 600, 1, false);

        setBackground(new GreenfootImage("title_screen_bg.jpg"));
        GreenfootImage logo = new GreenfootImage("dbbl_logo.png");
        GreenfootImage text = new GreenfootImage("Press SPACE to start", 25, Color.WHITE, null);
        getBackground().drawImage(logo, (getWidth()/2) - (logo.getWidth()/2), 25);
        getBackground().drawImage(text, (getWidth()/2) - (text.getWidth()/2), 400);

        backgroundMusic.setVolume(50);
        backgroundMusic.playLoop();
    }

    @Override
    public void act() {
        if(Greenfoot.isKeyDown("SPACE"))
        {
            backgroundMusic.stop();
            Greenfoot.setWorld(new MainGame());
        }
    }
}
