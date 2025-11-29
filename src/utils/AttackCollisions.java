package utils;

import entities.players.JeanGuy;
import entities.players.Playable;
import entities.players.Players;

import java.util.ArrayList;

/**
 * Utility class to handle attack collision detection between a player and enemies.
 * <p>
 * This class provides a static method to check if a player's attack in a given direction
 * hits any enemy on the map. It works by comparing the player's attack area with the
 * bounding boxes of enemies.
 */
public class AttackCollisions {
    /**
     * Checks if a player's attack collides with any enemy in a specified direction.
     * <p>
     * The method calculates the attack area based on the player's position and the
     * direction of the attack. It then iterates through all enemies and checks for
     * overlapping bounding boxes. Only enemies different from the attacking player are considered.
     *
     * @param enemies an ArrayList of all potential target enemies
     * @param direction the direction of the attack ("up", "down", "left", "right")
     * @param jeanguy the player performing the attack
     * @param tileSize the size of the tiles / hitboxes used for collision detection
     * @return the first enemy Players object that collides with the attack area,
     *         or null if no collision occurs
     */
    public static Players attackCollisions(ArrayList<Players> enemies, String direction, Playable jeanguy, int tileSize){
        int posX=jeanguy.getPosition().get(0);
        int posY=jeanguy.getPosition().get(1);
        int posXend= posX+tileSize;
        int posYend=posY+tileSize;;

        if(direction.contains("up")){
            posY -= tileSize;
            posYend -= tileSize;
        }
        if(direction.contains("down")){
            posY  += tileSize;
            posYend  += tileSize;
        }
        if(direction.contains("left")){
            posX -= tileSize;
            posXend  -= tileSize;
        }
        if(direction.contains("right")){
            posX  += tileSize;
            posXend += tileSize;
        }



        for (Players enemy : enemies) {

            if (enemy.getName()!= jeanguy.getName()){
                if (posX < enemy.getPosition().get(0) + tileSize && posXend > enemy.getPosition().get(0) && posY < enemy.getPosition().get(1) + tileSize && posYend > enemy.getPosition().get(1)){
                    return enemy;
                }
            }
        }
        return null;
     }
}
