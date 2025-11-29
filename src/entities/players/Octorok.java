package entities.players;


import entities.bullets.Bullets;
import entities.bullets.Octorokatk;
import main.GamePanel;
import utils.AlgorithmMovementRange;
import utils.CollisionsNpcMap;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

/**
 * Class representing the "Octorok" non-playable character (NPC).
 * <p>
 * This NPC has ranged attack capabilities and moves according to an
 * algorithm based on the target player's position. It inherits behavior
 * from {@link NonPlayable} and overrides movement, attack, sprite loading,
 * and drawing logic.
 */
public class Octorok extends NonPlayable{
    /** Sprite images for movement in each direction */
    protected BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    /** Counter for attack cooldown */
    protected int cpAtk=0;
    /**
     * Constructor for Octorok NPC.
     * Initializes the NPC's stats, initial position, and sprite file paths.
     *
     * @param panel Reference to the GamePanel
     */
    public Octorok(GamePanel panel) {
        super(panel,"Octorok", 1, new ArrayList<Integer>(Arrays.asList(5,5,0)), 5, 1,2,false,false,200,true, Arrays.asList("",""),
                Arrays.asList("/assets/ennemies/shootingmob/up1.png",
                        "/assets/ennemies/shootingmob/up2.png","/assets/ennemies/shootingmob/down1.png","/assets/ennemies/shootingmob/down2.png",
                        "/assets/ennemies/shootingmob/left1.png", "/assets/ennemies/shootingmob/left2.png","/assets/ennemies/shootingmob/right1.png",
                        "/assets/ennemies/shootingmob/right2.png"));

    }

    /**
     * Loads the sprite images for this NPC from the resource paths.
     * Overrides the abstract method from {@link NonPlayable}.
     */

    @Override
    public void getPlayerImage() {
        try{
            this.up1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(this.spritesPaths.get(0))));
            this.up2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(this.spritesPaths.get(1))));
            this.down1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(this.spritesPaths.get(2))));
            this.down2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(this.spritesPaths.get(3))));
            this.left1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(this.spritesPaths.get(4))));
            this.left2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(this.spritesPaths.get(5))));
            this.right1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(this.spritesPaths.get(6))));
            this.right2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(this.spritesPaths.get(7))));
        }catch(Exception e){
            e.printStackTrace();
        }

    }
    /**
     * Updates the Octorok NPC logic each frame.
     * <p>
     * Handles movement towards the target, ranged attack generation,
     * collision detection, and sprite animation.
     */

    @Override
    public void update() {
        String dir = AlgorithmMovementRange.algorithmMovementRange(gamePanel,this, cible);
        if (attackSpeed==0){
            attackSpeed=200;
            Bullets b = new Octorokatk(gamePanel, this, cible);
            gamePanel.getBullets().add(b);
        }

        if(!this.isDead()){
            String respass = CollisionsNpcMap.collisionsNpcMap(this,dir ,gamePanel.getTileM().getPathTiles(), gamePanel.getTileM().getMapTiles(), gamePanel, gamePanel.getTileM().getTiles());
            if (dir.contains("up")&&canPass(respass)) {
                direction = "up";
                spriteCounter++;
                if(!(dir.contains("atk"))){
                    position.set(1, Math.max(0,position.get(1) - checkSpeed()));
                }
            }
            if (dir.contains("down")&&canPass(respass)) {
                direction = "down";
                spriteCounter++;
                if(!(dir.contains("atk"))){
                    position.set(1, Math.min(gamePanel.getHeight() - gamePanel.getTileSize(),position.get(1) + checkSpeed()));
                }
            }
            if (dir.contains("left")&&canPass(respass)) {
                direction = "left";
                spriteCounter++;
                if(!(dir.contains("atk"))){
                    position.set(0, Math.max(0,position.get(0) - checkSpeed()));
                }
            }
            if (dir.contains("right")&&canPass(respass)) {
                direction = "right";
                spriteCounter++;
                if(!(dir.contains("atk"))){
                    position.set(0, Math.min(gamePanel.getWidth() - gamePanel.getTileSize(),position.get(0) + checkSpeed()));
                }
            }
            this.attackSpeed--;

            if (spriteCounter > 12) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }

        }

    }

    /**
     * Draws the Octorok NPC on the screen using the current sprite based on its direction.
     * Overrides the abstract method from {@link NonPlayable}.
     *
     * @param g2 Graphics2D object used to draw the NPC
     */
    @Override
    public void draw(Graphics2D g2) {

        BufferedImage image = null;
        switch(direction) {
            case "up":
                if (spriteNum == 1) {
                    image = up1;
                }
                if (spriteNum == 2) {
                    image = up2;
                }
                break;
            case "down":
                if (spriteNum == 1) {
                    image = down1;
                }
                if (spriteNum == 2) {
                    image = down2;
                }
                break;
            case "left":
                if (spriteNum == 1) {
                    image = left1;
                }
                if (spriteNum == 2) {
                    image = left2;
                }
                break;
            case "right":
                if (spriteNum == 1) {
                    image = right1;
                }
                if (spriteNum == 2) {
                    image = right2;
                }
                break;
        }
        g2.drawImage(image, position.get(0), position.get(1), gamePanel.getTileSize(), gamePanel.getTileSize(), null);
    };
}


