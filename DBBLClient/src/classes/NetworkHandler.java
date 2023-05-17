package classes;

import enums.GameState;
import requests.PlayerInfo;
import responses.GameInfo;
import responses.WaitingForAttack;
import worlds.MainGame;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class NetworkHandler implements Runnable {
    private String serverIp = "localhost";
    private int serverPort = 50000;

    private MainGame mainGame;

    private Socket socket;
    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;

    private Thread listenThread;
    private Thread talkThread;
    private GameInfo game;

    public NetworkHandler(String serverIp, MainGame mainGame)
    {
        this.serverIp = serverIp;
        this.mainGame = mainGame;
    }

    public void run()
    {
        try {
            socket = new Socket(serverIp, serverPort);
            System.out.println("Connected to server...");

            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectInputStream = new ObjectInputStream(socket.getInputStream());

            listenThread = new Thread(new ListenThread());
            talkThread = new Thread(new TalkThread());

            listenThread.start();
            talkThread.start();
        } catch (IOException e) {
            System.out.println("ERROR: SERVER REFUSED TO CONNECT, RETRYING IN 5 SECONDS...");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
            run();
        }
    }

    public void send(Object obj)
    {
        try {
            objectOutputStream.writeObject(obj);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public class ListenThread implements Runnable {

        @Override
        public void run() {
//
            try {
//                System.out.println(game.getState());
                while(!socket.isClosed())
                {
                    Object obj = objectInputStream.readObject();

                    System.out.println("GOT MESSAGE FROM SERVER " + obj.toString() + " " + obj);
                    if(obj instanceof GameInfo)
                    {
                        GameInfo gameInfo = (GameInfo) obj;

                        game = gameInfo; // to get state
                        if(gameInfo.getState() == GameState.ROUND_ENDED) {
                            try {
                                mainGame.movePlayersBack();
                                mainGame.pauseSequence();
                                System.out.println("Pausing....");
                            } catch (Exception e){
                                e.printStackTrace();
                            }
                        }

                        if (gameInfo.getState() == GameState.GAME_OVER){
                            mainGame.movePlayersBack();
                            if(gameInfo.getMostRecentWinner().getPlayerId() == 1){
                                mainGame.getPlayerCharacter().animateVictory();
                                mainGame.getEnemyCharacter().setIdle();
                            }
                            else{
                                mainGame.getEnemyCharacter().animateVictory();
                                mainGame.getPlayerCharacter().setIdle();
                            }
                        }
                        mainGame.setGameInfo(gameInfo);
                        System.out.println(gameInfo.getPlayers()[0].getHealth());
                        System.out.println(gameInfo.getPlayers()[1].getHealth());
                    }
                    else if(obj instanceof PlayerInfo)
                    {
                        PlayerInfo playerInfo = (PlayerInfo) obj;
                        mainGame.setLocalPlayerInfo(playerInfo);
                    }
                    else if(obj instanceof WaitingForAttack)
                    {
                        WaitingForAttack waitingForAttack = (WaitingForAttack) obj;
                        mainGame.waitingForAttack(waitingForAttack.getAttackBarSpeed());
                        mainGame.getEnemyCharacter().animatePunch();
                        mainGame.getPlayerCharacter().animatePunch();
                    }
                }
                //ANIMATE
//                switch (game.getState()){
//                    case WAITING_FOR_ATTACKS:
//                        mainGame.getEnemyCharacter().animatePunch();
//                    case ROUND_ENDED:
//                        try {
//                            mainGame.pauseSequence();
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                        System.out.println("going idle...");
//                    default: break;
//                }

            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public class TalkThread implements Runnable {

        @Override
        public void run() {

        }
    }
}
