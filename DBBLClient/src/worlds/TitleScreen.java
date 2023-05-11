package worlds;

import actors.TextBox;
import greenfoot.*;

public class TitleScreen extends World {
    private GreenfootSound backgroundMusic = new GreenfootSound("main_menu_music.mp3");
    private TextBox serverIpBox;

    public TitleScreen()
    {
        super(1000, 600, 1, false);

        setBackground(new GreenfootImage("title_screen_bg.jpg"));
        GreenfootImage logo = new GreenfootImage("dbbl_logo.png");
        GreenfootImage text = new GreenfootImage("Press SPACE to start", 25, Color.WHITE, null);
        getBackground().drawImage(logo, (getWidth()/2) - (logo.getWidth()/2), 25);
        getBackground().drawImage(text, (getWidth()/2) - (text.getWidth()/2), 400);

        GreenfootImage ipBoxLabel = new GreenfootImage("Server IP", 15, Color.WHITE, null);
        serverIpBox = new TextBox(200, 35, Color.DARK_GRAY, Color.WHITE, "localhost");
        getBackground().drawImage(ipBoxLabel, (getWidth()/2) - (text.getWidth()/2), 320);
        addObject(serverIpBox, ((getWidth()/2) - (serverIpBox.getImage().getWidth()/2)) + serverIpBox.getImage().getWidth()/2, 350);

        backgroundMusic.setVolume(50);
        backgroundMusic.playLoop();
    }

    @Override
    public void act() {
        if(Greenfoot.isKeyDown("SPACE"))
        {
            backgroundMusic.stop();
            Greenfoot.setWorld(new MainGame(this.serverIpBox.getText()));
        }
    }
}
