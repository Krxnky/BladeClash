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

public class ServerCopy {
    private static GameInfo game;
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
        game = new GameInfo(1, new PlayerInfo[]{goku, vegeta}, GameState.STARTING);
        System.out.println("Game started");
        OutputStream[] os = {gSock.getOutputStream(), vSock.getOutputStream()};
        objectOutputStreams = new ObjectOutputStream[]{new ObjectOutputStream(os[0]), new ObjectOutputStream(os[1])};
        InputStream[] is = {gSock.getInputStream(), vSock.getInputStream()};
        objectInputStreams = new ObjectInputStream[]{new ObjectInputStream(is[0]), new ObjectInputStream(is[1])};
        objectOutputStreams[0].writeObject(goku);
        objectOutputStreams[1].writeObject(vegeta);

        while(goku.getHealth() > 0 && vegeta.getHealth() > 0){
            objectWriter(game);
            WaitingForAttack waitingForAttack = new WaitingForAttack(5);
            objectWriter(waitingForAttack);
            game.setState(GameState.WAITING_FOR_ATTACKS);

//            System.out.println("roundResult sent to clients!!");

            Thread attackThread = new Thread(new AttackAcceptor());
            attackThread.start();

            attackThread.join();
            game.setState(GameState.ROUND_ENDED);
            System.out.println("Round Ended!");
//        RoundResult roundResult = new RoundResult(game);
            System.out.println(game.getState());
        }

        while(true){}
    }
    public static void objectWriter(Object object) throws Exception{
        objectOutputStreams[0].writeObject(object);
        objectOutputStreams[1].writeObject(object);
        objectOutputStreams[0].flush();
        objectOutputStreams[1].flush();

        objectOutputStreams[0].reset();
        objectOutputStreams[1].reset();
    }
    public static void localObjectWriter(ObjectOutputStream oos, Object object) throws Exception{
        oos.writeObject(object);
        oos.flush();
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
        public AttackAcceptor(){}

        @Override
        public void run() {
            try{
                game.setState(GameState.ATTACK_PROCESS_STARTED);
                attacks = readAttack();
                PlayerInfo winner = attacks[0].getAttackValue() > attacks[1].getAttackValue() ? goku: attacks[0].getAttackValue() < attacks[1].getAttackValue() ? vegeta: null;
                game.setMostRecentWinner(winner);
                if (winner.equals(goku)) {
                    vegeta.setHealth(vegeta.getHealth() - attacks[0].getAttackValue());
                }
                else if (winner.equals(vegeta)) {
                    goku.setHealth(goku.getHealth() - attacks[1].getAttackValue());
                }
                game.setState(GameState.ATTACK_FINISHED);
                game.updatePlayerInfo(new PlayerInfo[]{goku, vegeta});
                System.out.println(game.getPlayers()[0].getHealth());
                System.out.println(game.getPlayers()[1].getHealth());
                System.out.println(game.getState());
                GameInfo update = new GameInfo(game.getGameId(), game.getPlayers(), game.getState());

                objectWriter(game);
                System.out.println("Updated players");
            }

            catch (Exception e){
                e.printStackTrace();
            }
        }
    }

}
