package entities.players;

import entities.equipements.Equipements;

import java.awt.*;
import java.util.List;

/**
 * Interface representing the common actions and properties of any character in the game,
 * including both player-controlled and non-player characters.
 * <p>
 * Any class implementing this interface should provide concrete implementations for
 * movement, attack, damage handling, sprite management, and item interactions.
 */
interface Actions {

    // ======================
    // Getters
    // ======================

    /**
     * Gets the character's name.
     *
     * @return The name of the character.
     */
    String getName();

    /**
     * Gets the amount of damage the character deals.
     *
     * @return The damage value.
     */
    int getDamage();

    /**
     * Gets the current position of the character as a list of integers [x, y].
     *
     * @return List containing the x and y coordinates of the character.
     */
    List<Integer> getPosition();

    /**
     * Gets the list of sprite file paths used for this character's animations.
     *
     * @return List of sprite file paths.
     */
    List<String> getSpritePaths();

    /**
     * Gets the attack speed of the character.
     *
     * @return The attack speed value.
     */
    int getAttackSpeed();

    /**
     * Gets the attack range of the character.
     *
     * @return The attack range.
     */
    int getRange();

    /**
     * Gets the current health points of the character.
     *
     * @return The HP value.
     */
    int getHp();

    /**
     * Gets the movement speed of the character.
     *
     * @return The speed value.
     */
    float getSpeed();

    /**
     * Checks if the character uses melee attacks.
     *
     * @return True if the character is melee, false otherwise.
     */
    boolean isMelee();

    /**
     * Checks if the character is dead.
     *
     * @return True if dead, false otherwise.
     */
    boolean isDead();

    /**
     * Checks if the character can be killed.
     *
     * @return True if killable, false otherwise.
     */
    boolean isKillable();

    /**
     * Gets the list of sound file paths for the character's actions.
     *
     * @return List of sound file paths.
     */
    List<String> getSoundPaths();

    /**
     * Gets the current direction the character is facing.
     *
     * @return Direction as a string ("up", "down", "left", "right", etc.).
     */
    String getDirection();


    // ======================
    // Setters
    // ======================

    /**
     * Sets the character's health points.
     *
     * @param hp New HP value.
     */
    void setHp(int hp);

    /**
     * Sets the dead state of the character.
     *
     * @param dead True if the character should be dead, false otherwise.
     */
    void setDead(boolean dead);

    /**
     * Sets whether the character can be killed.
     *
     * @param kill True if killable, false otherwise.
     */
    void setKillable(boolean kill);

    /**
     * Sets the direction the character is facing.
     *
     * @param direction Direction as a string.
     */
    void setDirection(String direction);


    // ======================
    // Actions
    // ======================

    /**
     * Applies damage to the character.
     *
     * @param damage Amount of damage to apply.
     * @param dir Direction from which the damage is received.
     */
    void receiveDamage(int damage, String dir);

    /**
     * Applies damage to the character from another character.
     *
     * @param sender The character causing the damage.
     * @param damage Amount of damage.
     * @param dir Direction from which the damage is received.
     */
    void receiveDamage(Players sender, int damage, String dir);

    /**
     * Loads or updates the character's sprite images.
     */
    void getPlayerImage();

    /**
     * Checks if the character should drop any items.
     *
     * @param players Target player who might receive the drop.
     */
    void checkDrop(Players players);

    /**
     * Drops a specific item into the game world.
     *
     * @param equipements The equipment to drop.
     */
    void dropItem(Equipements equipements);

    /**
     * Updates the character's logic each frame, including movement, attacks, and interactions.
     */
    void update();

    /**
     * Draws the character on the screen.
     *
     * @param g2 Graphics2D object used for drawing.
     */
    void draw(Graphics2D g2);
}
