package entities.equipements.armes;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

/**
 * Represents a wooden sword (“Épée en bois”), a basic melee weapon.
 * <p>
 * This class loads eight sword sprites, one for each attack direction:
 * up, down, left, right, and four diagonal slashes.
 * All loaded {@link BufferedImage} instances are stored in {@code swordImages}.
 * </p>
 *
 * <p>
 * The sword does not drop on the ground and has a range of 1 tile.
 * Its initial position is defined in the constructor.
 * </p>
 */

public class EpeeBois extends Armes{
    /** Price of the wooden sword (unused for now). */
    protected int prix = 0;
    /** Paths to weapon sprites (8 directions). */
    protected ArrayList<String> weaponsSprites;

    protected BufferedImage swordup, sworddown, swordright,swordleft,swordupleft,sworddownleft,swordupright,sworddownright;
    /** All sword sprites stored in order. */
    protected ArrayList<BufferedImage> swordImages= new ArrayList<>();

    /**
     * Creates a new wooden sword.
     *
     * @param gp The game panel used for scaling and rendering.
     */
    public EpeeBois(GamePanel gp){
        super(gp, false, "EpeeBois", 1, new ArrayList<String>(Arrays.asList("","")), true, 1,new ArrayList<Integer>(Arrays.asList(100,100,0)));
        this.weaponsSprites = new ArrayList<>(Arrays.asList("/assets/playerattack/swordup.png","/assets/playerattack/sworddown.png",
                "/assets/playerattack/swordright.png","/assets/playerattack/swordleft.png","/assets/playerattack/slashupleft.png","/assets/playerattack/slashdownleft.png",
                "/assets/playerattack/slashupright.png","/assets/playerattack/slashdownright.png"));
        this.getSpriteImage();
    }

    /**
     * Loads all sword sprites (8 directions) into memory.
     * <p>
     * All images are stored in {@link #swordImages} in the following order:
     * up, down, left, right, up-left, down-left, up-right, down-right.
     * </p>
     */
    @Override
    public void getSpriteImage() {
        try {
            this.swordup = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(this.weaponsSprites.get(0))));
            this.sworddown = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(this.weaponsSprites.get(1))));
            this.swordleft = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(this.weaponsSprites.get(3))));
            this.swordright = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(this.weaponsSprites.get(2))));
            this.swordupleft = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(this.weaponsSprites.get(4))));
            this.sworddownleft = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(this.weaponsSprites.get(5))));
            this.swordupright = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(this.weaponsSprites.get(6))));
            this.sworddownright = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(this.weaponsSprites.get(7))));
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.swordImages.add(this.swordup);
        this.swordImages.add(this.sworddown);
        this.swordImages.add(this.swordleft);
        this.swordImages.add(this.swordright);
        this.swordImages.add(this.swordupleft);
        this.swordImages.add(this.sworddownleft);
        this.swordImages.add(this.swordupright);
        this.swordImages.add(this.sworddownright);
    }
    /**
     * Returns all loaded sword sprites.
     *
     * @return A list containing all 8 sword images.
     */
    public ArrayList<BufferedImage> getSwordImages(){
        return this.swordImages;
    }
}
