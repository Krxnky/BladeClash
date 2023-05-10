package actors;

import greenfoot.Actor;
import greenfoot.Color;
import greenfoot.Font;
import greenfoot.GreenfootImage;

public class MessageOverlay extends Actor {
    private int overlayWidth = 1000;
    private int overlayHeight = 600;
    private String message;
    private GreenfootImage messageImg;
    private int transparencyIncrement = 10;
    private long start;
    private long end;


    public MessageOverlay(GreenfootImage messageImg, int duration)
    {
        this.messageImg = messageImg;
        this.start = System.currentTimeMillis();
        this.end = start + duration;
    }

    public void act()
    {
        setImage(new GreenfootImage(overlayWidth, overlayHeight));
        getImage().setColor(new Color(0, 0, 0, 150));
        getImage().fill();
        getWorld().setPaintOrder(MessageOverlay.class);
        setLocation(overlayWidth/2, overlayHeight/2);

        if(message != null)
        {
            getImage().setFont(new Font("Calibri", true, false, 20));
            GreenfootImage text = new GreenfootImage(message, 30, Color.YELLOW, null);
            GreenfootImage textOutline = new GreenfootImage(message, 35, new Color(0, 0, 0), null);
            //getImage().drawImage(textOutline, (getWorld().getWidth()/2) - (text.getWidth()/2), (getWorld().getHeight()/2) - (text.getHeight()/2));
            getImage().drawImage(text, (getWorld().getWidth()/2) - (text.getWidth()/2), (getWorld().getHeight()/2) - (text.getHeight()/2));
        }

        if(this.messageImg.getTransparency()-transparencyIncrement <= 0 || this.messageImg.getTransparency()-transparencyIncrement >=255) this.transparencyIncrement = -transparencyIncrement;
        this.messageImg.setTransparency(this.messageImg.getTransparency()-transparencyIncrement);


        getImage().drawImage(this.messageImg, (getWorld().getWidth()/2) - (this.messageImg.getWidth()/2), (getWorld().getHeight()/2) - (this.messageImg.getHeight()/2));

        if(System.currentTimeMillis() > end) getWorld().removeObject(this);
    }
}
