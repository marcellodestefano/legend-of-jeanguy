package entities.equipements.soins;

import entities.equipements.Equipements;
import main.GamePanel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Abstract class representing healing items in the game.
 * Each healing item can have a drop percentage and a position in the game.
 */
public abstract class Soins extends Equipements{
    /** Drop percentage of the item. */
    protected static int dropPercentage;
    /**
     * Constructor for a healing item.
     *
     * @param gp Reference to the GamePanel
     * @param drop True if the item can appear as a drop, false otherwise
     * @param dropPercentage Percentage chance to drop
     * @param name Name of the item
     * @param unite Quantity of the item
     * @param spritePath List of sprite paths for the item
     * @param position Initial position of the item in the game
     */
    public Soins(GamePanel gp, boolean drop, int dropPercentage, String name, int unite, List<String> spritePath, ArrayList<Integer> position){
        super(gp, drop, name, unite, spritePath, position);
        this.dropPercentage = dropPercentage;
    }
    /**
     * Returns the drop percentage of the item.
     * @return drop percentage
     */
    public static int getDropPercentage() {
        return dropPercentage;
    }
    /**
     * Sets the position of the healing item.
     * @param position New position as an ArrayList of integers
     */
    public void setPosition(ArrayList<Integer>position){
        this.position = position;
    }
}
