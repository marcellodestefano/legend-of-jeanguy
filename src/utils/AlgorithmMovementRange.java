package utils;

import entities.players.NonPlayable;
import entities.players.Playable;
import main.GamePanel;
/**
 * Utility class that calculates the movement and attack direction of
 * NonPlayable enemies with ranged attack capabilities relative to the player.
 * <p>
 * This class provides a static method to determine the optimal action for an enemy:
 * either move towards the player, attack in a direction, or remain idle if blocked.
 * It considers distance, enemy attack speed, and collisions.
 */
public class AlgorithmMovementRange {
    /**
     * Determines the movement or attack direction for a NonPlayable enemy with range attacks.
     * <p>
     * The algorithm calculates the horizontal and vertical differences between the enemy
     * and the player. It then chooses the primary direction to move or attack based on:
     * <ul>
     *     <li>If the enemy is in a damage state (`cpdmg != 0`), it returns the damage direction.</li>
     *     <li>If the enemy's attack speed is low (`< 10`), it performs an immediate attack
     *     in the dominant direction ("atk-left", "atk-right", "atk-up", "atk-downds").</li>
     *     <li>If the distance to the player is less than 200 units, the enemy moves towards the player,
     *     checking for collisions before deciding the final movement direction ("up", "down", "left", "right").</li>
     * </ul>
     *
     * Possible return values:
     * <ul>
     *     <li>"atk-left", "atk-right", "atk-up", "atk-downds" – ranged attack in a direction</li>
     *     <li>"left", "right", "up", "down" – movement direction towards the player</li>
     *     <li>"none" – no valid movement or attack possible</li>
     *     <li>enemy's damage direction – if enemy.getCpdmg() != 0</li>
     * </ul>
     *
     * @param gamePanel the main game panel containing all entities and tiles
     * @param enemy the NonPlayable enemy whose action is being calculated
     * @param player the Playable player character that the enemy targets
     * @return a String representing the movement or attack direction
     */
    public static String algorithmMovementRange(GamePanel gamePanel, NonPlayable enemy, Playable player) {
        float diffx = enemy.getPosition().get(0) - player.getPosition().get(0);
        float diffy = enemy.getPosition().get(1) - player.getPosition().get(1);
        double ipten = Math.sqrt(diffx*diffx+diffy*diffy);
        String direction;

        if (enemy.getCpdmg() != 0) {
            enemy.setCpdmg();
            return enemy.getDmgdir();
        }
        if (enemy.getAttackSpeed() < 10) {
            enemy.setKillable(true);
            if (Math.abs(diffx) >= Math.abs(diffy)) {
                if (diffx >= 0) {
                    return "atk-left";
                } else {
                    return "atk-right";
                }
            } else if (Math.abs(diffx) < Math.abs(diffy)) {
                if (diffy >= 0) {
                    return "atk-up";
                } else {
                    return "atk-downds";
                }
            }

        } else if (ipten<200){
            enemy.setKillable(true);
            if (Math.abs(diffx) >= Math.abs(diffy)) {
                if (diffx > 0) {
                    direction = "right";
                    if (Collisions.collisions(gamePanel.getPersonnages(), direction, enemy, gamePanel.getTileSize()).equals("ok")) {
                        return "right";
                    }
                } else {
                    direction = "left";
                    if (Collisions.collisions(gamePanel.getPersonnages(), direction, enemy, gamePanel.getTileSize()).equals("ok")) {
                        return "left";
                    }

                }
            } else if (Math.abs(diffy) > Math.abs(diffx)) {
                if (diffy > 0) {
                    direction = "down";
                    if (Collisions.collisions(gamePanel.getPersonnages(), direction, enemy, gamePanel.getTileSize()).equals("ok")) {
                        return "down";
                    }

                } else {
                    direction = "up";
                    if (Collisions.collisions(gamePanel.getPersonnages(), direction, enemy, gamePanel.getTileSize()).equals("ok")) {
                        return "up";
                    }

                }
            }
        }
        return "none";

    }
}
