package actors;

import greenfoot.Actor;
import worlds.MainGame;

import java.io.IOException;
import java.net.Socket;

public class NetworkHandler extends Actor {
    private String serverIp = "localhost";
    private int serverPort = 50000;

    private Socket server;
    private MainGame mainGame;

    public NetworkHandler(MainGame mainGame)
    {
        this.mainGame = mainGame;
    }

    public void connect()
    {
        try {
            server = new Socket(serverIp, serverPort);
            System.out.println("Connected to server...");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public class TalkThread implements Runnable {
        public TalkThread()
        {

        }

        @Override
        public void run() {

        }
    }

    public class ListenThread implements Runnable {
        public ListenThread()
        {

        }

        @Override
        public void run() {

        }
    }
}
