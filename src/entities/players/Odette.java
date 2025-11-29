package entities.players;


import main.GamePanel;
import utils.AlgorithmMovement;
import utils.Collisions;
import utils.CollisionsNpcMap;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;
/**
 * Class representing the NPC "Odette" in the game.
 * <p>
 * Odette is a non-playable character that can move in the game world
 * and has a scripted initial descent down stairs. She cannot take damage.
 * She also triggers victory when colliding with the player.
 */
public class Odette extends NonPlayable {
    /** Sprite for the first and second frame of movement */

    protected BufferedImage move1, move2;
    /** Countdown for the initial stair descent */
    protected int cpDesEsc=120;
    /**
     * Constructs a new Odette NPC.
     *
     * @param gamePanel Reference to the main {@link GamePanel}.
     */
    public Odette(GamePanel gamePanel) {
        super(gamePanel,"Odette", 0, new ArrayList<Integer>(Arrays.asList(10,10,0)), 0, 1,3,false,false,0,false, Arrays.asList("",""),Arrays.asList("/assets/world/pnj/odette/odettebas1.png","/assets/world/pnj/odette/odettebas2.png"));

    }

    /**
     * Overrides damage reception. Odette cannot take damage.
     *
     * @param damage Amount of damage (ignored)
     * @param dir Direction of the attack (ignored)
     */
    @Override
    public void receiveDamage(int damage, String dir) {
    }
    /**
     * Sets the initial fixed position of Odette in the game world.
     */
    @Override
    public void startPosition(){
        this.position.set(0,335);
        this.position.set(1,200);
    }
    /**
     * Loads the movement sprites for Odette.
     */
    @Override
    public void getPlayerImage() {
        try{
            this.move1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(this.spritesPaths.get(0))));
            this.move2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(this.spritesPaths.get(1))));

        }catch(Exception e){
            e.printStackTrace();
        }
    }
    /**
     * Moves Odette down the stairs during the initial countdown.
     */
    protected void descendEscaliers(){
        position.set(1, Math.min(gamePanel.getHeight() - gamePanel.getTileSize(),position.get(1) + 1));
    }
    /**
     * Updates Odette's behavior each frame.
     * <p>
     * Handles the stair descent for the first 120 frames, movement using
     * {@link AlgorithmMovement} and collision detection, and triggers
     * victory if colliding with the player.
     */

    @Override
    public void update() {
        if(cpDesEsc!=0){
            descendEscaliers();
            cpDesEsc--;
        }
        else{
        String win = Collisions.collisions(gamePanel.getPersonnages(), this.direction, this, gamePanel.getTileSize());
        String dir = AlgorithmMovement.movements(gamePanel,this, cible);
        String respass = CollisionsNpcMap.collisionsNpcMap(this,dir ,gamePanel.getTileM().getPathTiles(), gamePanel.getTileM().getMapTiles(), gamePanel, gamePanel.getTileM().getTiles());

        if (win.contains("player")){
            gamePanel.setVictory(true);
        }
        else{

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
        }}}
    }

    /**
     * Draws Odette on the screen.
     *
     * @param g2 Graphics2D object used for rendering
     */
    @Override
    public void draw(Graphics2D g2) {

        BufferedImage image = null;
        if (spriteNum == 1) {
            image = move1;
        }
        if (spriteNum == 2) {
            image = move2;
        }

        g2.drawImage(image, position.get(0), position.get(1), gamePanel.getTileSize(), gamePanel.getTileSize(), null);


    }



}
