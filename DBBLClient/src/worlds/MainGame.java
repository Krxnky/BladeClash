package worlds;

import actors.*;
import classes.NetworkHandler;
import greenfoot.GreenfootImage;
import greenfoot.World;
import requests.PlayerInfo;
import responses.GameInfo;

public class MainGame extends World  {
    // UI
    HealthBar playerHealthBar;
    HealthBar enemyHealthBar;

    Button attackBtn;
    AttackBar attackBar;

    Goku playerCharacter;
    Vegeta enemyCharacter;

    // NETWORK
    NetworkHandler networkHandler;
    PlayerInfo localPlayerInfo;
    GameInfo gameInfo;

    public MainGame()
    {
        super(1000, 600, 1, false);

        GreenfootImage bg = new GreenfootImage("MV5BMWM3MmY2YWEtNjVmZi00NmZlLTliN2EtYTc0YzhhOTQ3MzE2XkEyXkFqcGdeQXVyNzgxMzc3OTc@._V1_.png");
        bg.scale(1000, 600);
        setBackground(bg);

        buildUI();
        //connectToServer();
    }

    public void connectToServer()
    {
        networkHandler = new NetworkHandler(this);
        new Thread(networkHandler).start();
    }

    public void startGame()
    {
        System.out.println("GAME STARTED");

    }

    public void waitingForAttack(int attackBarSpeed)
    {
        System.out.println("WAITING FOR ATTACK STARTED SPEED: " + attackBarSpeed);
        attackBar = new AttackBar(2);
        addObject(attackBar, 400, getHeight() - attackBar.getImage().getHeight());
    }

    public void updateGameInfo(GameInfo gameInfo)
    {
        this.gameInfo = gameInfo;
    }

    public void buildUI()
    {
        // UI TESTING
        attackBar = new AttackBar(5);
        addObject(attackBar, (getBackground().getWidth()/2) - (attackBar.getImage().getWidth()/2), getHeight() - attackBar.getImage().getHeight());

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
