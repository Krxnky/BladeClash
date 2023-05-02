package worlds;

import actors.*;
import classes.NetworkHandler;
import enums.AttackType;
import enums.GameState;
import greenfoot.GreenfootImage;
import greenfoot.World;
import requests.AttackRequest;
import requests.PlayerInfo;
import responses.GameInfo;

import java.util.Arrays;

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
        connectToServer();
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
        attackBar = new AttackBar(attackBarSpeed, (attackValue) -> {
            System.out.println("GOT ATTACK VALUE: " + attackValue);
            networkHandler.send(new AttackRequest(attackValue, AttackType.BASIC));
        });
        addObject(attackBar, (getBackground().getWidth()/2) - (attackBar.getImage().getWidth()/2), getHeight() - attackBar.getImage().getHeight());
    }

    public void setGameInfo(GameInfo gameInfo)
    {
        this.gameInfo = gameInfo;

        if(gameInfo.getState() == GameState.STARTING) startGame();
        else if(gameInfo.getState() == GameState.ROUND_ENDED)
        {

        }
        else if(gameInfo.getState() == GameState.GAME_OVER)
        {

        }

        updateUI();
    }

    private void updateUI()
    {
        playerHealthBar.setHealth();
        enemyHealthBar.setHealth(Arrays.stream(gameInfo.getPlayers()).filter(x -> x.getPlayerId() != localPlayerInfo.getPlayerId()).toArray(PlayerInfo[]::new)[0].getHealth());
    }

    public void setLocalPlayerInfo(PlayerInfo localPlayerInfo) {
        this.localPlayerInfo = localPlayerInfo;
    }

    public void buildUI()
    {
        // UI TESTING


        // Create Healthbar UI actors
        playerHealthBar = new HealthBar(0);
        enemyHealthBar = new HealthBar(0);

        addObject(playerHealthBar, playerHealthBar.getImage().getWidth() + 150, 30);
        addObject(enemyHealthBar, getWidth() - enemyHealthBar.getImage().getWidth() - 150, 30);

        // Create characters
        playerCharacter = new Goku();
        enemyCharacter = new Vegeta();

        addObject(playerCharacter, 100, (getHeight() - playerCharacter.getImage().getHeight()));
        addObject(enemyCharacter, getWidth() - enemyCharacter.getImage().getWidth(), (getHeight() - enemyCharacter.getImage().getHeight()));

    }
}
