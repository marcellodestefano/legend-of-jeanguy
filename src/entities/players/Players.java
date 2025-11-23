package entities.players;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.List;

import entities.equipements.Equipements;
import input.KeyHandler;
import main.GamePanel;
/**
 * Abstract class representing a player or non-player character in the game.
 * This class contains all common properties and methods for characters, such as
 * health, position, movement, attack, sprites, and interaction with items.
 */
public abstract class Players implements Actions{
    /** Name of the character */
    protected String name;
    /** Damage dealt by the character */
    protected int damage;
    /** Position of the character as [x, y] coordinates */
    protected List<Integer> position = new ArrayList<Integer>();
    /** Attack range of the character */
    protected int range;
    /** Health points of the character */
    protected int hp;
    /** Movement speed of the character */
    protected int speed;
    /** Whether the character is dead */
    protected boolean Dead;
    /** Whether the character uses melee attacks */
    protected boolean isMelee;
    /** Attack speed of the character */
    protected int attackSpeed;
    /** Whether the character can be killed */
    protected boolean killable;
    /** List of sound file paths for actions or effects */
    protected List<String> soundPaths = new ArrayList<>();
    /** List of sprite file paths for character animations */
    protected List<String> spritesPaths = new ArrayList<>();
    /** Current facing direction */
    protected String direction = "down";
    /** Counter for sprite animation */
    protected int spriteCounter = 0;
    /** Current sprite number */
    protected int spriteNum = 1;
    /** Reference to the main GamePanel */
    protected GamePanel gamePanel;
    /** Current attack cooldown or points */
    protected int cpAtk;




    /**
     * Constructor for a player or NPC.
     *
     * @param panel Reference to the GamePanel
     * @param name Character name
     * @param damage Damage dealt
     * @param position Initial position [x, y]
     * @param range Attack range
     * @param hp Health points
     * @param speed Movement speed
     * @param Dead Whether the character starts dead
     * @param isMelee Whether the character is melee
     * @param attackSpeed Attack speed
     * @param killable Whether the character can be killed
     * @param soundPaths List of sound file paths
     * @param spritesPaths List of sprite image paths
     */

    public Players(GamePanel panel,String name, int damage, List<Integer> position, int range, int hp, int speed,boolean Dead,boolean isMelee, int attackSpeed, boolean killable, List<String> soundPaths, List<String> spritesPaths ) {
        this.name = name;
        this.damage = damage;
        this.position = position;
        this.range = range;
        this.hp = hp;
        this.speed = speed;
        this.Dead = Dead;
        this.isMelee = isMelee;
        this.attackSpeed = attackSpeed;
        this.killable = killable;
        this.soundPaths = soundPaths;
        this.spritesPaths = spritesPaths;
        this.getPlayerImage();
        this.gamePanel = panel;
    }


    /**
     * getters and setters
     */

    public String getName() {
        return this.name;
    }
    public int getDamage() {
        return this.damage;
    }
    public List<Integer> getPosition() {
        return this.position;

    }
    public List<String> getSpritePaths(){
        return this.spritesPaths;
    }
    public int getAttackSpeed(){
        return this.attackSpeed;
    }
    public int getRange() {
        return this.range;
    }

    public int getHp() {
        return this.hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public float getSpeed() {
        return this.speed;
    }

    public boolean isMelee() {
        return this.isMelee;
    }

    public boolean isDead() {
        if (this.hp<= 0){
            this.Dead = true;
        }
        return this.Dead;
    }

    public void setDead(boolean dead) {
        this.Dead = dead;
    }


    public boolean isKillable() {
        return this.killable;
    }
    public void setKillable(boolean kill){
        this.killable = kill;
    }

    public List<String> getSoundPaths() {
        return this.soundPaths;
    }

    public String getDirection() {
        return this.direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    /**
     * Reduces HP when the player is hit.
     * @param damage Amount of damage
     * @param dir Direction from which the damage comes
     */
    public void receiveDamage(int damage, String dir){
        this.hp = Math.max(0, this.hp-damage);
    }

    public void receiveDamage(Players sender, int damage, String dir){}

    /**
     * Loads character sprites. Override in subclasses.
     */
    public void getPlayerImage() {
    }
    /**
     * Checks if the character should drop an item. Override in subclasses.
     * @param players Target player
     */
    public void checkDrop(Players players){}
    /**
     * Drops an item in the game world. Override in subclasses.
     * @param equipements Equipment to drop
     */
    public void dropItem(Equipements equipements){}
    /**
     * Updates character logic each frame. Override in subclasses.
     */
    public void update(){

    }

    /**
     * Draws the character on the screen. Override in subclasses.
     * @param g2 Graphics2D used for drawing
     */
    public void draw(Graphics2D g2) {}

}









