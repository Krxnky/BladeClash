package classes;

import enums.GameState;
import greenfoot.Actor;
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

    public class TalkThread implements Runnable {

        @Override
        public void run() {
            try {
                while(!socket.isClosed())
                {
                    Object obj = objectInputStream.readObject();
                    System.out.println("GOT MESSAGE FROM SERVER" + obj.toString());
                    if(obj instanceof GameInfo)
                    {
                        GameInfo gameInfo = (GameInfo) obj;
                        mainGame.updateGameInfo(gameInfo);

                        if(gameInfo.getState() == GameState.STARTING)
                        {
                            mainGame.startGame();
                        }
                    }
                    else if(obj instanceof WaitingForAttack)
                    {

                    }

                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public class ListenThread implements Runnable {

        @Override
        public void run() {

        }
    }
}
