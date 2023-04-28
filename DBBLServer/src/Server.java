import enums.AttackType;
import enums.GameState;
import requests.AttackRequest;
import requests.PlayerInfo;
import responses.GameInfo;
import responses.WaitingForAttack;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static ServerSocket serverSocket;
    private static Socket gSock;
    private static Socket vSock;
    private static PlayerInfo goku;
    private static PlayerInfo vegeta;
    private static ObjectOutputStream[] objectOutputStreams;
    private static ObjectInputStream[] objectInputStreams;
    public static void main(String[] args) throws Exception{
        
        Thread acceptThread = new Thread(new Acceptor());
        acceptThread.start();
        System.out.println("accept thread started");
        acceptThread.join();
        System.out.println("Starting Game...");
        Thread.sleep(1000);
        GameInfo game = new GameInfo(1, new PlayerInfo[]{goku, vegeta}, GameState.STARTING);
        System.out.println("Game started");
        OutputStream[] os = {gSock.getOutputStream(), vSock.getOutputStream()};
        objectOutputStreams = new ObjectOutputStream[]{new ObjectOutputStream(os[0]), new ObjectOutputStream(os[1])};
        InputStream[] is = {gSock.getInputStream(), vSock.getInputStream()};
        objectInputStreams = new ObjectInputStream[]{new ObjectInputStream(is[0]), new ObjectInputStream(is[1])};
        objectWriter(game);
        WaitingForAttack waitingForAttack = new WaitingForAttack(50);
        objectWriter(waitingForAttack);

        objectOutputStreams[0].writeObject(goku);
        objectOutputStreams[1].writeObject(vegeta);
        objectWriter(new String("Waiting for attack"));
        boolean waiting = true;
        while(waiting){
            Thread attackThread = new Thread(new AttackAcceptor());
        }
        while(true){}
    }
    public static void objectWriter(Object object) throws Exception{
        objectOutputStreams[0].writeObject(object);
        objectOutputStreams[1].writeObject(object);
        objectOutputStreams[0].flush();
        objectOutputStreams[1].flush();
    }
    public static AttackRequest[] readAttack() throws Exception{
        AttackRequest[] objects = new AttackRequest[2];
        Object gokuAttack = objectInputStreams[0].readObject();
        Object vegetaAttack = objectInputStreams[1].readObject();
        objects[0] = gokuAttack instanceof AttackRequest ? (AttackRequest) gokuAttack : null;
        objects[1] = vegetaAttack instanceof AttackRequest ? (AttackRequest) vegetaAttack : null;
//        objectInputStreams[0].flush();
//        objectInputStreams[1].flush();
        return objects;
    }
    private static class Acceptor implements Runnable{
        public Acceptor() throws Exception{
            serverSocket = new ServerSocket(50000);
        }

        public void run(){
            try{
                gSock = serverSocket.accept();
                goku = new PlayerInfo(1, gSock.getInetAddress().toString(),1000);
                System.out.println("Goku Joined!!");
                vSock = serverSocket.accept();
                vegeta = new PlayerInfo(2, vSock.getInetAddress().toString(),1000);
                System.out.println("Vegeta Joined!!");
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    private static class AttackAcceptor implements Runnable{
        private AttackRequest[] attacks;
        public AttackAcceptor(){

        }

        @Override
        public void run() {
            try{
                attacks = readAttack();
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }

}
