package entities.equipements.soins;

import entities.players.JeanGuy;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.*;

/**
 * Class representing the "MaxHeart" healing item.
 * Increases the player's maximum health when collected.
 */
public class CoeurMax extends Soins{

    /**
     * Constructor for CoeurMax.
     *
     * @param gp Reference to the GamePanel
     */
    public CoeurMax(GamePanel gp)
    {
        super(gp,true, 10, "CoeurMax", 1, new ArrayList<String>(Arrays.asList("/assets/equipments/coeurmaxdrop/coeurmax.png")),new ArrayList<Integer>(Arrays.asList(100,250,0)));

        this.getSpriteImage();
    }
    /**
     * Draws the CoeurMax item on the screen.
     * @param g2 Graphics2D used for drawing
     */
    @Override
    public void draw(Graphics2D g2) {
        g2.drawImage(equipementImage, this.position.get(0), this.position.get(1), gp.getTileSize(), gp.getTileSize(), null);
    }
}
