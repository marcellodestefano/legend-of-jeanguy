package entities.bullets;

import entities.players.Playable;
import entities.players.Players;
import main.GamePanel;
import utils.BulletCollisions;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Abstract class representing a projectile (bullet, fireball, arrow, etc.)
 * fired by an entity in the game. A bullet has a position, a sender, an optional
 * receiver target, damage, movement speed, and a sprite used for rendering.
 *
 * <p>The bullet automatically calculates its movement vector based on the
 * sender → receiver direction when created. Each update it moves, checks for
 * collisions, and eventually applies damage.</p>
 */
public abstract class Bullets {

    /** Bullet position in the world, stored as doubles for smooth movement. */
    protected List<Double> position = new ArrayList<>();
    /** List of sprite paths used to load the bullet image. */
    protected List<String> spritesPaths;
    /** Amount of damage inflicted when the bullet hits a target. */
    protected int damage;
    /** The entity that fired this bullet. */
    protected Players sender;
    /** The entity that the bullet is targeting (may be null). */
    protected Players receiver;
    /** Reference to the main GamePanel for world information. */
    protected GamePanel gamePanel;
    /** The sprite image of the bullet. */
    protected BufferedImage image;
    /** Movement speed of the bullet. */
    protected int speed;
    /**
     * Bullet state:
     * <ul>
     *     <li>"ok": still flying</li>
     *     <li>"touche": collision with target</li>
     *     <li>"changeChunk": removed when changing map chunk</li>
     * </ul>
     */
    protected String isActive = "ok";
    /** Difference in X/Y between sender and receiver. */
    protected int diffX, diffY;
    /** Distance between sender and receiver. */
    protected double ipten;
    /** Normalized speed components on X and Y axis. */
    protected double speedX, speedY;
    /**
     * Whether the bullet is allowed to persist when changing chunks.
     * If false, the bullet is immediately destroyed.
     */
    protected boolean changeChunk=true;

    /**
     * Creates a new bullet.
     *
     * @param gamePanel      reference to the main game panel
     * @param sender         the entity that fired the bullet
     * @param receiver       the target entity (may be null)
     * @param spritesPaths   list of image paths for loading the sprite
     * @param damage         damage inflicted on hit
     * @param speed          movement speed of the projectile
     */
    public Bullets(GamePanel gamePanel, Players sender, Players receiver, List<String> spritesPaths,int damage, int speed ) {
        this.gamePanel = gamePanel;
        for (int val : sender.getPosition()) {
            position.add((double) val);
        }
        this.sender = sender;
        this.receiver = receiver;
        this.damage = damage;
        this.speed = speed;
        this.spritesPaths = spritesPaths;
        this.getPlayerImage();
        this.calcSpeed();
    }
    /**
     * Loads the bullet sprite from the first path in {@code spritesPaths}.
     * Prints a stack trace if the resource cannot be found.
     */
    public void getPlayerImage() {
        try {
            this.image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(this.spritesPaths.get(0))));
        }catch(Exception e){
            e.printStackTrace();
        }

    }
    /**
     * Defines whether the bullet should be removed when changing map chunks.
     *
     * @param changeChunk true to keep the bullet, false to destroy it
     */

    public void setChangeChunk(boolean changeChunk) {
        this.changeChunk = changeChunk;
    }
    /**
     * Calculates the movement vector of the bullet based on the direction
     * from the sender to the receiver.
     * Does nothing if {@code receiver} is null.
     */
    public void calcSpeed(){
        if (receiver != null) {
            this.diffX = receiver.getPosition().get(0) - sender.getPosition().get(0);
            this.diffY = receiver.getPosition().get(1) - sender.getPosition().get(1);
            this.ipten = Math.sqrt(diffX*diffX+diffY*diffY);
            this.speedX = speed*diffX/ipten;
            this.speedY = speed*diffY/ipten;

        }

    }

    /**
     * Sets the bullet's position manually.
     *
     * @param x new X coordinate
     * @param y new Y coordinate
     */
    public void setPosition(double x, double y){
        this.position.set(0,x);
        this.position.set(1,y);
    }
    /**
     * Updates {@code isActive} based on collision detection.
     * Uses {@link BulletCollisions#bulletCollisions(GamePanel, Bullets, Playable)}.
     */
    public void setIsActive() {
        String act = BulletCollisions.bulletCollisions(gamePanel, this, gamePanel.getJeanGuy());
        this.isActive = act;
    }

    /**
     * Returns the current activity state of the bullet.
     *
     * @return "ok", "touche", or "changeChunk"
     */

    public String getIsActive(){
        return this.isActive;
    }


    /**
     * Returns the bullet's current world position.
     *
     * @return list containing X and Y coordinates
     */
    public List<Double> getPosition() {
        return this.position;
    }

    /**
     * Updates bullet behavior:
     * <ul>
     *     <li>moves the bullet</li>
     *     <li>checks for collisions</li>
     *     <li>applies damage on impact</li>
     *     <li>removes bullet when changing chunks</li>
     * </ul>
     */

    public void update(){
        this.setIsActive();
        if(isActive.equals("ok")){
            this.position.set(0, position.get(0) + speedX);
            this.position.set(1, position.get(1) + speedY);
        }else if(isActive.equals("touche")) {
            if (Math.abs(speedX)>=Math.abs(speedY)){
                if(speedX>=0){
                    receiver.receiveDamage(this.sender,this.damage, "right-player");
                } else {
                    receiver.receiveDamage(this.sender,this.damage, "left-player");
                }
            }
            else if (Math.abs(speedX)<Math.abs(speedY)) {
                if(speedY>=0){
                    receiver.receiveDamage(this.sender,this.damage, "down-player");
                } else {
                    receiver.receiveDamage(this.sender, this.damage, "up-player");
                }
            }
        }
        if (!changeChunk) {
            this.isActive = "changeChunk";
        }
    }
    /**
     * Draws the bullet sprite on the screen.
     *
     * @param g2 the graphics context
     */
    public void draw(Graphics g2){
        g2.drawImage(this.image, (int) Math.round(position.get(0)), (int) Math.round(position.get(1)), gamePanel.getTileSize(), gamePanel.getTileSize(),null);

    }
}
