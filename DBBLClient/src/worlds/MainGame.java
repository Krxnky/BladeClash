package worlds;

import actors.*;
import classes.NetworkHandler;
import classes.SoundEffectHandler;
import enums.AttackType;
import enums.GameState;
import greenfoot.Color;
import greenfoot.GreenfootImage;
import greenfoot.GreenfootSound;
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
    String serverIp;
    NetworkHandler networkHandler;
    PlayerInfo localPlayerInfo;
    GameInfo gameInfo;

    GreenfootSound backgroundMusic = new GreenfootSound("battle_bg_music.mp3");

    public MainGame(String serverIp)
    {
        super(1000, 600, 1, false);

        this.serverIp = serverIp;

        GreenfootImage bg = new GreenfootImage("MV5BMWM3MmY2YWEtNjVmZi00NmZlLTliN2EtYTc0YzhhOTQ3MzE2XkEyXkFqcGdeQXVyNzgxMzc3OTc@._V1_.png");
        bg.scale(1000, 600);
        setBackground(bg);

        buildUI();
        connectToServer();

        backgroundMusic.play();
        backgroundMusic.setVolume(25);
    }

    public void connectToServer()
    {
        networkHandler = new NetworkHandler(serverIp, this);
        new Thread(networkHandler).start();
    }

    public void startGame()
    {
        System.out.println("GAME STARTED");

    }

    public void waitingForAttack(int attackBarSpeed)
    {
        createMessageOverlay("attackText.png", 2000);
        movePlayersInto();
        System.out.println("WAITING FOR ATTACK STARTED SPEED: " + attackBarSpeed);
        attackBar = new AttackBar(attackBarSpeed, (attackValue) -> {
            System.out.println("GOT ATTACK VALUE: " + attackValue);
            networkHandler.send(new AttackRequest(attackValue, AttackType.BASIC));
        });

        System.out.println(attackBar.getImage().getWidth());
        System.out.println(attackBar.getImage().getHeight());
        System.out.println(getWidth());
        System.out.println(getHeight());
        addObject(attackBar, 500, 550);
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
        if(gameInfo.getState() == GameState.STARTING)
        {
            createMessageOverlay("gameStartingText.png", 2000);
            startGame();
            SoundEffectHandler.GameStarted.play();
        }
        else if(gameInfo.getState() == GameState.ROUND_ENDED)
        {
            if(gameInfo.getRecentWinner().getPlayerId() == localPlayerInfo.getPlayerId()) SoundEffectHandler.SimpleBattle_Win.play();
            else SoundEffectHandler.SimpleBattle_Lose.play();
        }
        else if(gameInfo.getState() == GameState.GAME_OVER)
        {
            if(gameInfo.getRecentWinner().getPlayerId() == localPlayerInfo.getPlayerId()) createMessageOverlay("victoryText.png", 2000);
            else createMessageOverlay("defeatText.png", 2000);
            createMessageOverlay("gameOverText.png", 2000);
        }

        updateUI();
    }

    public void createMessageOverlay(String image, int duration)
    {
        addObject(new MessageOverlay(new GreenfootImage(image), duration), 0, 0);
        try {
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void updateUI()
    {

        System.out.println("UI PLAYER HEALTH: " + Arrays.stream(gameInfo.getPlayers()).filter(x -> x.getPlayerId() == localPlayerInfo.getPlayerId()).toArray(PlayerInfo[]::new)[0].getHealth());
        System.out.println("UI ENEMY HEALTH: " + Arrays.stream(gameInfo.getPlayers()).filter(x -> x.getPlayerId() != localPlayerInfo.getPlayerId()).toArray(PlayerInfo[]::new)[0].getHealth());

        playerHealthBar.setHealth(Arrays.stream(gameInfo.getPlayers()).filter(x -> x.getPlayerId() == localPlayerInfo.getPlayerId()).toArray(PlayerInfo[]::new)[0].getHealth());
        enemyHealthBar.setHealth(Arrays.stream(gameInfo.getPlayers()).filter(x -> x.getPlayerId() != localPlayerInfo.getPlayerId()).toArray(PlayerInfo[]::new)[0].getHealth());
    }

    public void setLocalPlayerInfo(PlayerInfo localPlayerInfo) {
        this.localPlayerInfo = localPlayerInfo;
    }

    public void buildUI()
    {
        // UI TESTING
        //addObject(new MessageOverlay(new GreenfootImage("attackText.png"), 300), 0, 0);
        //addObject(new AttackBar(3, (t) -> {}), 500, 550);
        // Create Healthbar UI actors
        playerHealthBar = new HealthBar(1000, false);
        enemyHealthBar = new HealthBar(1000, true);

        addObject(playerHealthBar, playerHealthBar.getImage().getWidth() + 200, 30);
        addObject(enemyHealthBar, getWidth() - enemyHealthBar.getImage().getWidth() - 200, 30);

        // Create characters
        playerCharacter = new Goku();
        enemyCharacter = new Vegeta();

//        addObject(playerCharacter, 100, (getHeight() - playerCharacter.getImage().getHeight()));
//        addObject(enemyCharacter, getWidth() - enemyCharacter.getImage().getWidth(), (getHeight() - enemyCharacter.getImage().getHeight()));
        addObject(playerCharacter, 100, 350);
        addObject(enemyCharacter, 875, 350);
    }
    public void movePlayersBack(){
        playerCharacter.moveBack();
        enemyCharacter.moveBack();

    }
    public void movePlayersInto(){
        playerCharacter.moveInto();
        enemyCharacter.moveInto();
    }
    public Vegeta getEnemyCharacter() {
        return enemyCharacter;
    }

    public Goku getPlayerCharacter() {
        return playerCharacter;
    }
}
