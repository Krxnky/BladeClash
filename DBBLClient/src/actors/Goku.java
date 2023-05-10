package actors;

import greenfoot.Actor;
import greenfoot.GreenfootImage;

import java.util.LinkedList;
import java.util.Queue;

public class Goku extends Actor {
    Queue<GreenfootImage> punchFrames = new LinkedList<GreenfootImage>();
    Queue<GreenfootImage> idleFrames = new LinkedList<GreenfootImage>();
    int counter = 0;
    private static boolean punchActive;
    private static boolean idle;
    public Goku()
    {
        GreenfootImage cImg = new GreenfootImage("gokusprite.png");
        cImg.mirrorHorizontally();
        setImage(cImg);
        for(int i = 1; i <= 6; i++) {
            GreenfootImage temp = new GreenfootImage("gokupunch" + i + ".png");
            temp.mirrorHorizontally();
            punchFrames.add(temp);
        }
        for(int i = 1; i <= 2; i++) {
            GreenfootImage temp = new GreenfootImage("gokuidle" + i + ".png");
            temp.mirrorHorizontally();
            idleFrames.add(new GreenfootImage(temp));
        }
    }
    public void animate(Queue<GreenfootImage> frames){

        setImage(frames.peek());

    }
    public void animatePunch(){
        idle = false;
        punchActive = true;
    }
    public void deAnimatePunch(){
        punchActive = false;
        idle = true;
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
    public void setIdle(){
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
        }
    }
}
