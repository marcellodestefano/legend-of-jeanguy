package entities.equipements.armes;

import main.GamePanel;

import javax.imageio.IIOException;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
/**
 * Represents a wooden shield ("Bouclier en bois") that can be equipped
 * by the player. This shield is a defensive piece of equipment and is
 * considered an {@link Armes} object with a minimal attack range.
 *
 * <p>The shield has a predefined sprite, a default price, and starts at a
 * predefined position on the map. Its sprite is loaded from the
 * resources folder when the object is created.</p>
 *
 * <p>This class extends {@link Armes}, inheriting general equipment
 * behavior (sprite loading, position, name) and adds:</p>
 * <ul>
 *     <li>An in-game price</li>
 *     <li>A customized starting position</li>
 *     <li>A custom sprite loading method</li>
 * </ul>
 */

public class BouclierBois extends Armes{


    /** Price of the shield in the in-game currency. */
    protected int prix=10;

    /**
     * Creates a new wooden shield.
     *
     * @param gp the {@link GamePanel} instance used to access tile size and rendering context
     *
     * <p>This constructor initializes:</p>
     * <ul>
     *     <li>drop = false (the shield is not dropped by default)</li>
     *     <li>name = "Bouclier_en_bois"</li>
     *     <li>unit = 1</li>
     *     <li>sprite path = "/assets/equipments/weapons/bouclierbois.png"</li>
     *     <li>isMelee = true</li>
     *     <li>range = 1</li>
     *     <li>position = {0, 0, 0} (updated later)</li>
     * </ul>
     */
    public BouclierBois(GamePanel gp){
        super(gp,false, "Bouclier_en_bois", 1, new ArrayList<String>(List.of("/assets/equipments/weapons/bouclierbois.png")), true, 1,  new ArrayList<Integer>(Arrays.asList(0,0,0)));

        getSpriteImage();
        startPosition();
    }
    /**
     * Loads the sprite image of the wooden shield from resources.
     * Overrides the parent method to ensure resource non-nullity.
     */

    public void getSpriteImage(){
        try{
            this.equipementImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(this.spritePath.get(0))));
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    /**
     * Returns the shop price of the shield.
     *
     * @return price of the shield
     */

    public int getPrix(){
        return prix;
    }
    /**
     * Sets the shield's initial position on the map.
     * Coordinates are hardcoded for now.
     */
    public void startPosition(){
        this.position.set(0,220);
        this.position.set(1,285);
    }

    /**
     * Draws the shield at its current position using the provided {@link Graphics2D} context.
     *
     * @param g2 the graphics context used to render the sprite
     */
    @Override
    public void draw(Graphics2D g2) {
        g2.drawImage(equipementImage, this.position.get(0), this.position.get(1), gp.getTileSize(), gp.getTileSize(), null);
    }
}
