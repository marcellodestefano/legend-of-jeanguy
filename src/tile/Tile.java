package tile;

import java.awt.image.BufferedImage;
/**
 * Represents a single tile in the game world.
 * Each tile can have an image and a collision property indicating
 * whether the player or other entities can pass through it.
 */
public class Tile {
    /** The visual representation of the tile. */
    public BufferedImage image;
    /** Determines if the tile blocks movement. True if impassable. */
    public boolean collision = false;
}
