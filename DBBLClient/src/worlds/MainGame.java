package worlds;

import actors.Goku;
import actors.HealthBar;
import actors.NetworkHandler;
import actors.Vegeta;
import greenfoot.GreenfootImage;
import greenfoot.World;

public class MainGame extends World  {
    HealthBar playerHealthBar;
    HealthBar enemyHealthBar;

    Goku playerCharacter;
    Vegeta enemyCharacter;

    NetworkHandler networkHandler;

    public MainGame()
    {
        super(1000, 600, 1, false);

        GreenfootImage bg = new GreenfootImage("MV5BMWM3MmY2YWEtNjVmZi00NmZlLTliN2EtYTc0YzhhOTQ3MzE2XkEyXkFqcGdeQXVyNzgxMzc3OTc@._V1_.png");
        bg.scale(1000, 600);
        setBackground(bg);

        buildUI();
        buildNetwork();
    }

    public void buildNetwork()
    {
        networkHandler = new NetworkHandler(this);
        networkHandler.connect();
    }

    public void buildUI()
    {
        // Create Healthbar UI actors
        playerHealthBar = new HealthBar(100);
        enemyHealthBar = new HealthBar(100);

        addObject(playerHealthBar, playerHealthBar.getImage().getWidth() + 150, 30);
        addObject(enemyHealthBar, getWidth() - enemyHealthBar.getImage().getWidth() - 150, 30);

        // Create characters
        playerCharacter = new Goku();
        enemyCharacter = new Vegeta();

        addObject(playerCharacter, 100, (getHeight() - playerCharacter.getImage().getHeight()));
        addObject(enemyCharacter, getWidth() - enemyCharacter.getImage().getWidth(), (getHeight() - enemyCharacter.getImage().getHeight()));

    }
}
