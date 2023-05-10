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

public class MainGameCopy extends World  {
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

    public MainGameCopy()
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

        System.out.println(attackBar.getImage().getWidth());
        System.out.println(attackBar.getImage().getHeight());
        System.out.println(getWidth());
        System.out.println(getHeight());
        addObject(attackBar, 1000/2, 600/2);
    }

    public void pauseSequence() throws Exception{
        enemyCharacter.deAnimatePunch();
        playerCharacter.deAnimatePunch();
//        enemyCharacter.setIdle();
        Thread.sleep(3000);
        System.out.println("Going idle....");
    }

    public void setGameInfo(GameInfo gameInfo)
    {
        this.gameInfo = gameInfo;

        System.out.println(gameInfo.getState());
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

        System.out.println("UI PLAYER HEALTH: " + Arrays.stream(gameInfo.getPlayers()).filter(x -> x.getPlayerId() == localPlayerInfo.getPlayerId()).toArray(PlayerInfo[]::new)[0].getHealth());
        System.out.println("UI ENEMNY HEALTH: " + Arrays.stream(gameInfo.getPlayers()).filter(x -> x.getPlayerId() != localPlayerInfo.getPlayerId()).toArray(PlayerInfo[]::new)[0].getHealth());
        playerHealthBar.setHealth(Arrays.stream(gameInfo.getPlayers()).filter(x -> x.getPlayerId() == localPlayerInfo.getPlayerId()).toArray(PlayerInfo[]::new)[0].getHealth());
        enemyHealthBar.setHealth(Arrays.stream(gameInfo.getPlayers()).filter(x -> x.getPlayerId() != localPlayerInfo.getPlayerId()).toArray(PlayerInfo[]::new)[0].getHealth());
    }

    public void setLocalPlayerInfo(PlayerInfo localPlayerInfo) {
        this.localPlayerInfo = localPlayerInfo;
    }

    public void buildUI()
    {
        // UI TESTING
        //addObject(new AttackBar(5, (t) -> {}), 600, 400);

        // Create Healthbar UI actors
        playerHealthBar = new HealthBar(1000, false);
        enemyHealthBar = new HealthBar(1000, true);

        addObject(playerHealthBar, playerHealthBar.getImage().getWidth() + 200, 30);
        addObject(enemyHealthBar, getWidth() - enemyHealthBar.getImage().getWidth() - 200, 30);

        // Create characters
        playerCharacter = new Goku();
        enemyCharacter = new Vegeta();

        addObject(playerCharacter, 100, (getHeight() - playerCharacter.getImage().getHeight()));
        addObject(enemyCharacter, getWidth() - enemyCharacter.getImage().getWidth(), (getHeight() - enemyCharacter.getImage().getHeight()));

    }

    public Vegeta getEnemyCharacter() {
        return enemyCharacter;
    }

    public Goku getPlayerCharacter() {
        return playerCharacter;
    }
}
