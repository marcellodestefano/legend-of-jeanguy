package entities.equipements.soins;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Class representing the "Heart" healing item.
 * Restores the player's health when collected.
 */
public class Coeur extends Soins{
    /**
     * Constructor for Coeur.
     *
     * @param gp Reference to the GamePanel
     */
    public Coeur(GamePanel gp)
    {
        super(gp,true, 30, "Coeur", 1, new ArrayList<String>(List.of("/assets/equipments/coeurdrop/coeurdrop.png")), new ArrayList<Integer>(Arrays.asList(200,300,0)));
        this.getSpriteImage();

    }
    /**
     * Draws the Coeur item on the screen.
     * @param g2 Graphics2D used for drawing
     */

    @Override
    public void draw(Graphics2D g2) {
        g2.drawImage(equipementImage, this.position.get(0), this.position.get(1), gp.getTileSize(), gp.getTileSize(), null);
    }
}
