package actors;

import greenfoot.Actor;
import greenfoot.GreenfootImage;

import java.util.LinkedList;
import java.util.Queue;

public class Goku extends Actor {
    Queue<GreenfootImage> punchFrames = new LinkedList<GreenfootImage>();
    Queue<GreenfootImage> idleFrames = new LinkedList<GreenfootImage>();
    Queue<GreenfootImage> victoryFrames = new LinkedList<GreenfootImage>();
    private static int counter = 0;
    private static boolean punchActive;
    private static boolean idle;
    private static boolean victoryAnimation;
    private static int playerID = 1;
    public Goku()
    {
        GreenfootImage cImg = new GreenfootImage("gokusprite.png");
        cImg.mirrorHorizontally();
        setImage(cImg);
        for(int i = 1; i <= 6; i++) {
            GreenfootImage temp = new GreenfootImage("gokupunch" + i + ".png");
            temp.mirrorHorizontally();
            //getImage().scale(temp.getWidth()*2, temp.getHeight()*2);
            punchFrames.add(temp);
        }
        for(int i = 1; i <= 2; i++) {
            GreenfootImage temp = new GreenfootImage("gokuidle" + i + ".png");
            temp.mirrorHorizontally();
            //getImage().scale(temp.getWidth()*2, temp.getHeight()*2);
            idleFrames.add(new GreenfootImage(temp));
        }
    }
    public void animate(Queue<GreenfootImage> frames){

        setImage(frames.peek());

    }
    public void moveInto(){
        try {
            while (getX() < 450) {
                Thread.sleep(100/30);
                setLocation(getX() + 5, getY());
                System.out.println("moving right");
            }
        } catch (Exception e){
            e.printStackTrace();
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
            for (int i = 1; i <= 13; i++) {
                GreenfootImage temp = new GreenfootImage("gokuvictory" + i + ".png");
                temp.mirrorHorizontally();
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
            System.out.println("Goku Victory Frame");
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
    public void animationResolver(){
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
        }
        else if(victoryAnimation){
            buildVictoryFrames();
//            counterReset();
            animate(victoryFrames);
            nextVictoryFrame();
        }
    }
}
