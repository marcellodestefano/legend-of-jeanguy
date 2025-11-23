package entities.equipements;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.List;

    /**
     * Abstract class representing an equipment item in the game.
     * <p>
     * An {@code Equipements} object corresponds to any interactable or collectible
     * in-game object (e.g., weapons, resources). It stores basic metadata such as
     * its name, unit count, sprite path, and position on the map. Each equipment
     * also maintains a reference to the {@link GamePanel} for drawing and updating.
     * <p>
     * Subclasses are expected to implement custom drawing and update behavior.
     */
public abstract class Equipements {
    /**
     * Global incremental ID shared by all equipment instances.
     */
    protected int id;

    /** Whether the item is currently dropped in the world (visible on the ground). */
    protected boolean drop;
    /** Equipment's name (e.g., "EpeeBois", "BouclierBois", "Coeur"). */
    protected String name;
    /** Quantity associated with this equipment (e.g., stack size). */
    protected int unite;
    /**
     * List of sprite paths used for this equipment.
     * The first element is used as the main image.
     */
    protected List<String> spritePath;
    /** The buffered image representing the equipment's sprite. */
    protected BufferedImage equipementImage;
    /** Reference to the game panel, used for rendering and tile size. */
    protected GamePanel gp;
    /** Equipment's position in the world, as an (x, y) list of integers. */
    protected ArrayList<Integer> position;
    /** Whether the equipment has been picked up by the player. */
    protected boolean ramasser = false;

    protected static int count = 0;
    /**
     * Constructs a new equipment instance.
     *
     * @param gp         The game panel instance.
     * @param drop       Whether the equipment is currently dropped in the world.
     * @param name       The name of the equipment.
     * @param unite      Quantity or unit associated with this equipment.
     * @param spritePath List of sprite file paths.
     * @param position   World position of the equipment.
     */
    public Equipements(GamePanel gp, boolean drop, String name, int unite, List<String> spritePath, ArrayList<Integer> position) {
        this.drop = drop;
        this.name = name;
        this.unite = unite;
        this.spritePath = spritePath;
        this.gp = gp;
        this.position =position;
        id = count;
        count++;

    }

    /**
     * @return The global equipment ID (shared by all instances).
     */
    public int getId() {
        return id;
    }

        /**
         *
         * @return The name of the equipement.
         */
    public String getName() {
        return name;
    }

    /**
     *
     * @return how many units the equipement represents
     */
    public int getUnite() {
        return unite;
    }
    /**
     * @return The list of sprite paths for this equipment.
     */
    public List<String> getSpritePath() {
        return spritePath;
    }
    /**
     * @return True if the player has picked up the equipment.
     */
    public boolean isRamasser() {
        return this.ramasser;
    }
        /**
         * Sets whether the equipment has been moved to another chunk.
         *
         * @param changeChunk True if a chunk change occurred.
         */
    public void setisRamasser(boolean changeChunk) {
        this.ramasser = changeChunk;
    }
        /**
         * Loads the equipment's sprite image based on the first sprite path.
         * <p>
         * If loading fails, the stack trace is printed for debugging.
         */
    public void getSpriteImage() {
        try {
            this.equipementImage = ImageIO.read(getClass().getResourceAsStream(this.spritePath.get(0)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
        /**
         * @return The position of the equipment in world coordinates.
         */
    public ArrayList<Integer> getPosition(){
        return this.position;
    }
        /**
         * Marks the equipment as picked up by the player.
         */
    public void setRamasser() {
        this.ramasser = true;
    }
        /**
         * Draws the equipment on screen.
         * <p>
         * Implemented in subclasses depending on rendering behavior.
         *
         * @param g Graphics2D context to draw onto.
         */
    public void draw(Graphics2D g) {
    }
        /**
         * Updates the equipment's logic each frame.
         * <p>
         * Subclasses override this method to add behavior.
         */
    public void update(){}
}



