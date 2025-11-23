package entities.players;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
/**
 * Class representing a Gumba enemy in the game.
 * <p>
 * The Gumba is a non-playable character (NPC) that moves around and can deal damage.
 * It has simple movement animations and a death sprite.
 */
public class Gumba extends NonPlayable {
    /** First movement, second movement and death sprites */
    BufferedImage move1, move2, dead;
    /**
     * Constructs a new Gumba instance.
     *
     * @param gamePanel Reference to the main game panel.
     */
    public Gumba(GamePanel gamePanel) {
        super(gamePanel,"Gumba#", 1,new ArrayList<Integer>(Arrays.asList(0,0,0)),1,3,2,
                false, true, 1,true, Arrays.asList("",""),Arrays.asList("/assets/ennemies/gumba/Gumba1.png",
                        "/assets/ennemies/gumba/Gumba2.png","/assets/ennemies/gumba/GumbaDeath.png"));

    }

    /**
     * Loads the Bat's sprite images from resources.
     * <p>
     * This method initializes the movement and death sprites using the
     * file paths defined in {@code spritesPaths}.
     */
    @Override
    public void getPlayerImage() {
        try{
            this.move1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(this.spritesPaths.get(0))));
            this.move2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(this.spritesPaths.get(1))));
            this.dead = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(this.spritesPaths.get(2))));
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Draws the Bat on the screen using its current sprite.
     * <p>
     * If the Bat is currently taking damage (cpdmg > 0), it will display the
     * death sprite. Otherwise, it alternates between move1 and move2 sprites
     * to create a simple animation effect.
     *
     * @param g2 Graphics2D object used for drawing the Bat.
     */
    @Override
    public void draw(Graphics2D g2) {

        BufferedImage image = null;
        if (this.cpdmg>0){
            image=dead;
        }else{
            if (spriteNum == 1) {
                image = move1;
            }
            if (spriteNum == 2) {
                image = move2;
            }
        }

        g2.drawImage(image, position.get(0), position.get(1), gamePanel.getTileSize(), gamePanel.getTileSize(), null);


    }


}
