import enums.GameState;
import requests.PlayerInfo;
import responses.GameInfo;

import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static ServerSocket serverSocket;
    private static Socket gSock;
    private static Socket vSock;
    private static PlayerInfo goku;
    private static PlayerInfo vegeta;
    public static void main(String[] args) throws Exception{
        
        Thread acceptThread = new Thread(new Acceptor());
        acceptThread.start();
        System.out.println("accept thread started");
        acceptThread.join();
        System.out.println();
        GameInfo game = new GameInfo(1, new PlayerInfo[]{goku, vegeta}, GameState.STARTING);
//        OutputStream[] os = serverSocket.getout
        while(true){}
    }

    private static class Acceptor implements Runnable{

        public Acceptor() throws Exception{
            serverSocket = new ServerSocket(50000);
        }

        public void run(){
            try{
                gSock = serverSocket.accept();
                goku = new PlayerInfo(1, gSock.getInetAddress().toString());
                System.out.println("Goku Joined!!");
                vSock = serverSocket.accept();
                vegeta = new PlayerInfo(2, vSock.getInetAddress().toString());
                System.out.println("Vegeta Joined!!");
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }


}
