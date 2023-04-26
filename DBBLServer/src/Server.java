import requests.PlayerInfo;

import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static ServerSocket serverSocket;
    Socket gSock;
    Socket vSock;
    PlayerInfo goku;
    PlayerInfo vegeta;
    public static void main(String[] args) throws Exception{
        
        Thread acceptThread = new Thread(new Acceptor());
        acceptThread.join();
        System.out.println();

    }

    private static class Acceptor implements Runnable{

        public Acceptor() throws Exception{
            serverSocket = new ServerSocket(50000);
        }

        public void run(){

            try{
                gSock = serverSocket.accept();
                goku = new PlayerInfo(1, gSock.getInetAddress().toString());
                vSock = serverSocket.accept();
                goku = new PlayerInfo(2, vSock.getInetAddress().toString());
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }


}
