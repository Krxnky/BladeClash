package classes;

import greenfoot.GreenfootSound;

import java.util.HashMap;
import java.util.Random;

public class SoundEffectHandler {
    public static void SoundEffectHandlerInitiator(){
        map.put("vegetaready", 6);
        map.put("gokuready", 5);
        map.put("gokuvictory", 4);
        map.put("vegetavictory", 3);
        map.put("vegetaloss", 5);
        map.put("vegetaroundloss", 4);
        map.put("vegetaattack", 6);
        map.put("gokuattack", 10);
        map.put("gokuroundloss", 4);
        map.put("gokuloss", 5);
    }
    public static HashMap<String, Integer> map = new HashMap<String, Integer>();
    private static Random rand = new Random();

    public static GreenfootSound randomSound(String s){
        return new GreenfootSound(s + (rand.nextInt(map.get(s))+1) + ".mp3");
    }
    public static GreenfootSound gokuIntro = new GreenfootSound("goku_intro.mp3");


    public static GreenfootSound gokuWinRound = new GreenfootSound("gokuwinround.mp3");

    public static GreenfootSound SimpleBattle_Win = new GreenfootSound("007_sys_SimpleBattle_win.mp3");
    public static GreenfootSound SimpleBattle_Lose = new GreenfootSound("006_sys_SimpleBattle_lose.mp3");
    public static GreenfootSound Punch = new GreenfootSound("f7180_004_str_se_hit_s.mp3");
    public static GreenfootSound Explosion = new GreenfootSound("f3712_001_str_se_explosion_near.mp3");
    public static GreenfootSound Swoosh = new GreenfootSound("f3527_030_giantswing.mp3");
    public static GreenfootSound GameStarted = new GreenfootSound("021_sys_guild_PointMatch_start.mp3");
}
