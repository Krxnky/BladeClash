package classes;

import enums.GameState;
import greenfoot.Actor;
import requests.PlayerInfo;
import responses.GameInfo;
import responses.RoundResult;
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

    public NetworkHandler(MainGame mainGame)
    {
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
            e.printStackTrace();
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
            try {
                while(!socket.isClosed())
                {
                    Object obj = objectInputStream.readObject();

                    System.out.println("GOT MESSAGE FROM SERVER " + obj.toString() + " " + obj);
                    if(obj instanceof GameInfo)
                    {
                        GameInfo gameInfo = (GameInfo) obj;
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
                    }
                }
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
