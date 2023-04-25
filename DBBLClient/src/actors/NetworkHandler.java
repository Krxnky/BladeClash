package actors;

import greenfoot.Actor;

import java.io.IOException;
import java.net.Socket;

public class NetworkHandler extends Actor {
    private String serverIp = "localhost";
    private int serverPort = 50000;

    private Socket server;

    public NetworkHandler()
    {

    }

    public void connect()
    {
        try {
            server = new Socket(serverIp, serverPort);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
