package utils;


import entities.players.NonPlayable;
import entities.players.Playable;
import main.GamePanel;

import java.util.ArrayList;
/**
 * Utility class that calculates the movement direction of NonPlayable enemies
 * in relation to the player character.
 * <p>
 * This class provides a static method to determine the optimal direction for an enemy
 * to move towards or attack the player, while checking for collisions.
 */
public class AlgorithmMovement {
    /**
     * Determines the movement direction for a NonPlayable enemy towards the player.
     * <p>
     * The algorithm compares the horizontal and vertical distances between the enemy
     * and the player, then chooses the primary direction to move. It also considers
     * whether the enemy is in a damage state (cpdmg) or blocked by collisions.
     * <p>
     * Possible return values:
     * <ul>
     *     <li>"left" – move left</li>
     *     <li>"right" – move right</li>
     *     <li>"up" – move up</li>
     *     <li>"down" – move down</li>
     *     <li>"none" – no valid movement possible</li>
     *     <li>enemy's damage direction – if enemy.getCpdmg() != 0</li>
     * </ul>
     *
     * @param gamePanel the main game panel, containing all game entities and tiles
     * @param enemy the NonPlayable enemy whose movement is being calculated
     * @param player the Playable player character to move towards
     * @return a String representing the direction the enemy should move or attack
     */
    public static String movements(GamePanel gamePanel, NonPlayable enemy, Playable player) {

        float diffx = enemy.getPosition().get(0) - player.getPosition().get(0);
        float diffy = enemy.getPosition().get(1) - player.getPosition().get(1);
        String direction;
        if(enemy.getCpdmg()!=0){
            enemy.setCpdmg();
            return enemy.getDmgdir();
        }
        else{
            enemy.setKillable(true);
            if (Math.abs(diffx) >= Math.abs(diffy)) {
                if (diffx > 0) {
                    direction = "left";
                    if(Collisions.collisions(gamePanel.getPersonnages(), direction,enemy , gamePanel.getTileSize())=="ok"){
                        return "left";
                    }
                } else {
                    direction = "right";
                    if(Collisions.collisions(gamePanel.getPersonnages(), direction, enemy, gamePanel.getTileSize())=="ok"){
                        return "right";
                    }

                }
            } else if (Math.abs(diffy) > Math.abs(diffx)) {
                if (diffy > 0) {
                    direction = "up";
                    if(Collisions.collisions(gamePanel.getPersonnages(), direction, enemy, gamePanel.getTileSize())=="ok"){
                        return "up";
                    }

                } else {
                    direction = "down";
                    if(Collisions.collisions(gamePanel.getPersonnages(), direction, enemy, gamePanel.getTileSize())=="ok"){
                        return "down";
                    }

                }
            }
                return "none";
            }
    }
}




