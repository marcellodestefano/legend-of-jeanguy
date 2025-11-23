package utils;


import entities.players.Playable;
import entities.players.Players;

import java.util.ArrayList;
/**
 * Utility class for checking collision distances between the player and other entities.
 * <p>
 * Specifically, this class checks if the {@link Playable} player (e.g., JeanGuy) is colliding
 * with any "Octorok" entity in the provided list of {@link Players}.
 */
public class CollisionDistance {
    /**
     * Checks whether the given player is at a safe distance from any "Octorok" entities.
     * <p>
     * The method calculates the bounding box of the player using the given tile size and
     * compares it with the bounding boxes of all "Octorok" players. If there is an overlap,
     * it returns {@code false} indicating a collision; otherwise, it returns {@code true}.
     *
     * @param players the list of all {@link Players} entities to check against
     * @param jeanGuy the {@link Playable} player whose collision is being checked
     * @param tileSize the size of a tile, used to define the player's bounding box
     * @return {@code false} if the player collides with any "Octorok", {@code true} otherwise
     */
    public static boolean collisionDistance(ArrayList<Players> players, Playable jeanGuy, int tileSize){
        int posX=jeanGuy.getPosition().get(0);
        int posY=jeanGuy.getPosition().get(1);
        int posXend= posX+tileSize;
        int posYend=posY+tileSize;;

        for (Players player : players) {

            if (player.getName().contains("Octorok")){
                if (posX < player.getPosition().get(0) + tileSize && posXend > player.getPosition().get(0) && posY < player.getPosition().get(1) + tileSize && posYend > player.getPosition().get(1)){
                    return false;
                }
            }
        }
        return true;
    }
}
