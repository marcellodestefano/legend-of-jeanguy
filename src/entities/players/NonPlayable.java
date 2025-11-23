package entities.players;


import entities.equipements.Equipements;
import entities.equipements.soins.Coeur;
import entities.equipements.soins.CoeurMax;
import main.GamePanel;
import utils.AlgorithmMovement;
import utils.Collisions;
import utils.CollisionsNpcMap;
import utils.CreationMonstres;

import java.util.*;
/**
 * Abstract class representing a non-playable character (NPC) in the game.
 * <p>
 * Extends the {@link Players} class and adds behavior specific to NPCs,
 * such as targeting a playable character, autonomous movement, handling
 * damage, and potential item drops.
 * </p>
 */
public abstract class NonPlayable extends Players{
    /** Unique identifier for each NPC */
    protected int id;
    /** Static counter to assign unique IDs */
    protected static int counter = 0;
    /** Current target playable character */
    protected Playable cible;
    /** Direction from which damage was received */
    protected String dmgdir;
    /** Damage cooldown counter */
    protected int cpdmg = 0;
    /** Original speed of the NPC */
    protected int oldspeed;
    /** Maximum gold/loot value */
    protected int maxvalue=10;
    /** List of possible items that can drop from this NPC */
    private List<Equipements> possibleDrops;



    /**
     * Constructs a non-playable character.
     *
     * @param panel GamePanel reference
     * @param name NPC name
     * @param damage Base damage of the NPC
     * @param position Initial position [x, y]
     * @param range Attack range
     * @param hp Initial health points
     * @param speed Movement speed
     * @param isDead Initial dead state
     * @param isMelee Whether NPC uses melee attacks
     * @param attackSpeed Speed of attacks
     * @param killable Whether the NPC can be killed
     * @param soundPaths List of sound file paths
     * @param spritePaths List of sprite image paths
     */
    public NonPlayable(GamePanel panel , String name, int damage, List<Integer> position, int range, int hp, int speed, boolean isDead, boolean isMelee, int attackSpeed, boolean killable, List<String> soundPaths, List<String> spritePaths) {
        super (panel, name, damage, position, range,  hp,  speed, isDead,  isMelee,  attackSpeed,  killable,  soundPaths,spritePaths);
        id = counter++;
        this.name = name + this.id;
        oldspeed = speed;
        this.possibleDrops = new ArrayList<>();

        possibleDrops.add(new Coeur(gamePanel));
        possibleDrops.add(new CoeurMax(gamePanel));
        this.startPosition();
    }

    /**
     * Randomly sets the starting position of the NPC,
     * ensuring it does not collide with other monsters.
     */
    public void startPosition(){
        while (!CreationMonstres.creationMonstres(this.gamePanel,this)) {
            Random r = new Random();
            int x;
            int y;
            x = r.nextInt(gamePanel.getScreenWidth() - gamePanel.getTileSize())+1;
            y = r.nextInt(gamePanel.getScreenHeight()/2)+1;
            position.set(0, x);
            position.set(1, y);
        }
    }

    /**
     * Sets the current target playable character.
     *
     * @param cible The playable character to target
     */
    public void cible(Playable cible){
        this.cible = cible;
    }
    /** Returns the current target playable character */
    public Playable getCible(){
        return this.cible;
    }
    /** Returns the NPC's unique ID */
    public int getId() {
        return id;
    }

    /**
     * Returns the current speed, considering damage cooldown.
     *
     * @return Speed value
     */
    public int checkSpeed(){
        if (this.cpdmg!=0){
            return this.speed = 5;
        }else{
            return this.speed=oldspeed;
        }
    }
    /**
     * Returns a random gold/loot value up to {@link #maxvalue}.
     *
     * @return Random value
     */
    public int getValue(){
        Random r = new Random();
        return r.nextInt(maxvalue);
    }
    /** Returns the direction of the last received damage */
    public String getDmgdir(){

        return this.dmgdir;
    }
    /** Returns the current damage cooldown counter */
    public int getCpdmg(){
        return this.cpdmg;
    }
    /** Decreases the damage cooldown counter by 1 */
    public void setCpdmg(){
        this.cpdmg--;
    }
    /**
     * Checks if the NPC can pass on a given terrain.
     *
     * @param respass The result from collision detection
     * @return True if NPC can pass
     */
    public boolean canPass(String respass){
        return respass=="path";
    }
    /** Returns the list of possible item drops */
    public List<Equipements> getPossibleDrops(){
        return this.possibleDrops;
    }


    /**
     * Handles receiving damage from attacks.
     * Sets damage direction, damage cooldown, and prevents immediate kill.
     *
     * @param damage Amount of damage received
     * @param dir Direction from which damage was received
     */
    @Override
    public void receiveDamage(int damage, String dir) {
        this.hp = Math.max(0, this.hp-damage);
        this.dmgdir = dir;
        this.cpdmg = 8;
        this.setKillable(false);
    }

    /**
     * Updates the NPC's state each frame.
     * Handles movement towards target, attacks, damage, and sprite animation.
     */
    @Override
    public void update() {
        String dir = AlgorithmMovement.movements(gamePanel,this, cible);
        String atk = Collisions.collisions(gamePanel.getPersonnages(), this.direction, this, gamePanel.getTileSize());
        String respass = CollisionsNpcMap.collisionsNpcMap(this,dir ,gamePanel.getTileM().getPathTiles(), gamePanel.getTileM().getMapTiles(), gamePanel, gamePanel.getTileM().getTiles());
        if(!(this.isDead())){
            if(atk=="down-player"||atk=="up-player"||atk=="left-player"||atk=="right-player"){
                if (cible.isKillable()){
                cible.receiveDamage(this, this.damage,atk);
            }}
            if (dir.contains("up")&&canPass(respass)) {
                direction = "up";
                spriteCounter++;
                position.set(1, Math.max(0,position.get(1) - checkSpeed()));
            }
            if (dir.contains("down")&&canPass(respass)) {
                direction = "down";
                spriteCounter++;
                position.set(1, Math.min(gamePanel.getHeight() - gamePanel.getTileSize(),position.get(1) + checkSpeed()));
            }
            if (dir.contains("left")&&canPass(respass)) {
                direction = "left";
                spriteCounter++;
                position.set(0, Math.max(0,position.get(0) - checkSpeed()));
            }
            if (dir.contains("right")&&canPass(respass)) {
                direction = "right";
                spriteCounter++;
                position.set(0, Math.min(gamePanel.getWidth() - gamePanel.getTileSize(),position.get(0) + checkSpeed()));
            }

            if (spriteCounter > 12) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }}
    }





}
