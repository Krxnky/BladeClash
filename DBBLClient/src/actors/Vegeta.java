package actors;

import classes.SoundEffectHandler;
import greenfoot.Actor;
import greenfoot.GreenfootImage;

import java.util.LinkedList;
import java.util.Queue;

public class Vegeta extends Actor {
    Queue<GreenfootImage> punchFrames = new LinkedList<GreenfootImage>();
    Queue<GreenfootImage> idleFrames = new LinkedList<GreenfootImage>();
    Queue<GreenfootImage> victoryFrames = new LinkedList<GreenfootImage>();
    Queue<GreenfootImage> lossFrames = new LinkedList<GreenfootImage>();
    private static int counter = 0;
    private static boolean punchActive;
    private static boolean idle;
    private static boolean victoryAnimation;
    private static int playerID = 2;

    public Vegeta()
    {
//        GreenfootImage cImg = new GreenfootImage("vegetasprite.png");
//        setImage(cImg);
        setIdle();
        for(int i = 1; i <= 6; i++) {
            GreenfootImage temp = new GreenfootImage("punchframe" + i + ".png");
            punchFrames.add(temp);
        }
        for(int i = 1; i <= 2; i++) {
            GreenfootImage temp = new GreenfootImage("vegetaidle" + i + ".png");
            idleFrames.add(temp);
        }
        for (int i = 1; i <= 9; i++){
            GreenfootImage temp = new GreenfootImage("vegetaloss" + i + ".png");
            lossFrames.add(temp);
        }
    }
    public void animate(Queue<GreenfootImage> frames){
        setImage(frames.peek());
    }
    public void moveInto(){
        try {
            while (getX() > 550) {

                Thread.sleep(100/30);
                setLocation(getX() - 5, getY());
//                System.out.println("moving left");
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    public void moveBack(){
        while (getX() < 875){
            try {
                System.out.println("Move back");
                Thread.sleep(100 / 30);
                setLocation(getX() + 5, getY());
//                System.out.println("vegeta moving ");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void moveBackLoss(){
        int x = 0;
        punchActive = false;
        idle = false;
        victoryAnimation = false;
        while (getX() < 875){
            System.out.println("move back loss");
            try {
                Thread.sleep(100 / 30);
                if (lossFrames.size() > 1)setImage(lossFrames.poll());
                System.out.println("X:" + x);
                setLocation(getX() + 5, getY());
//                System.out.println("vegeta moving ");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public void animatePunch(){
        idle = false;
        punchActive = true;
    }

    public void deAnimatePunch(){
        punchActive = false;
        idle = true;
    }
    public void buildVictoryFrames(){
        if(victoryFrames.isEmpty()) {
            for (int i = 1; i <= 9; i++) {
                GreenfootImage temp = new GreenfootImage("vegetavictory" + i + ".png");
                victoryFrames.add(temp);
            }
        }
    }

    public void animateVictory(){
        idle = false;
        punchActive = false;
        victoryAnimation = true;
    }
    public void deAnimateVictory(){
        victoryAnimation = false;
        idle = true;
    }
    public void nextVictoryFrame(){
        if(counter % 15 == 0){
            victoryFrames.add(victoryFrames.poll());
            GreenfootImage temp = victoryFrames.peek();
            System.out.println("Vegeta Victory Frame/n Height: " + temp.getHeight() + "\n Width: "+ temp.getWidth());
        }
    }
    public void nextPunchFrame(){
        if (counter > 175 && counter % 5 == 0) {
            punchFrames.add(punchFrames.poll());
            System.out.println("Punchframe updated");
        }
        else if (counter % 10 == 0) {
            punchFrames.add(punchFrames.poll());
            System.out.println("Punchframe updated");
        }

    }
    public void nextIdleFrame(){
        if(counter % 15 == 0){
            idleFrames.add(idleFrames.poll());
            System.out.println("Idle frame updates");
        }

    }
    public static void counterReset(){
        counter = 0;
    }

    public void setIdle(){
        punchActive =false;
        victoryAnimation = false;
        idle = true;
    }

    @Override
    public void act() {
        counter++;
        super.act();
        if(idle){
            animate(idleFrames);
            nextIdleFrame();
        }
        else if(punchActive) {
            animate(punchFrames);
            nextPunchFrame();
            SoundEffectHandler.Punch.play();
        }
        else if(victoryAnimation){
            buildVictoryFrames();
//            counterReset();
            animate(victoryFrames);
            nextVictoryFrame();
        }
    }

}
