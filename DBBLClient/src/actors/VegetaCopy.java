package actors;

import greenfoot.Actor;
import greenfoot.GreenfootImage;

import java.util.LinkedList;
import java.util.Queue;

public class VegetaCopy extends Actor {
    Queue<GreenfootImage> punchFrames = new LinkedList<GreenfootImage>();
    Queue<GreenfootImage> idleFrames = new LinkedList<GreenfootImage>();
    int counter = 0;
    private static boolean punchActive;
    private static boolean idle;
    public VegetaCopy()
    {
        GreenfootImage cImg = new GreenfootImage("vegetasprite.png");
        setImage(cImg);
        for(int i = 1; i <= 6; i++) {
            punchFrames.add(new GreenfootImage("punchframe" + i + ".png"));
        }
        for(int i = 1; i <= 2; i++) {
            idleFrames.add(new GreenfootImage("vegetaidle" + i + ".png"));
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
