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
 * Class representing the NPC "Marchand" (merchant) in the game.
 * <p>
 * This is a non-playable character that does not attack and has a fixed position.
 * It handles basic sprite animation for movement and overrides certain
 * behaviors from {@link NonPlayable} to suit its NPC role.
 */
public class Marchand extends NonPlayable {
    /** Sprite for the first and second frame of movement */
    protected BufferedImage move1, move2;
    /**
     * Constructs a new Marchand NPC.
     *
     * @param panel Reference to the main {@link GamePanel}.
     */
    public Marchand(GamePanel panel) {
        super(panel,"Marchand", 0, new ArrayList<Integer>(Arrays.asList(12,12,0)), 0, 1,0,false,false,0,false, Arrays.asList("",""),Arrays.asList("/assets/world/pnj/marchand/marchantbas1.png","/assets/world/pnj/marchand/marchantbas2.png"));
    }
    /**
     * Overrides damage reception. Marchand cannot take damage.
     *
     * @param damage Amount of damage (ignored)
     * @param dir Direction of the attack (ignored)
     */
    @Override
    public void receiveDamage(int damage, String dir) {
    }

    /**
     * Sets the initial fixed position of the Marchand in the game world.
     */
    @Override
    public void startPosition(){
        this.position.set(0,500);
        this.position.set(1,200);
    }

    /**
     * Loads the movement sprites for the Marchand NPC.
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
     * Updates the Marchand NPC each frame.
     * <p>
     * Handles simple animation by alternating between two sprites.
     */

    @Override
    public void update() {
        spriteCounter++;
        if (spriteCounter > 12) {
            if (spriteNum == 1) {
                spriteNum = 2;
            } else if (spriteNum == 2) {
                spriteNum = 1;
            }spriteCounter = 0;
        }
    }
    /**
     * Draws the Marchand NPC on the screen.
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
