package utils;

import entities.equipements.Equipements;
import entities.equipements.armes.Armes;
import entities.players.Playable;
import entities.players.Players;

import java.util.ArrayList;
/**
 * Utility class for detecting collisions between a player and equipment items.
 * <p>
 * This class provides a method to check if the {@link Playable} player (e.g., JeanGuy)
 * is colliding with any {@link Equipements} in the given inventory.
 */
public class CollisionEquipement {
    /**
     * Checks for a collision between the player and any equipment in the inventory.
     * <p>
     * The method calculates the player's bounding box using the given tile size and
     * compares it with the bounding boxes of all equipment items. If a collision is detected,
     * the corresponding {@link Equipements} object is returned; otherwise, {@code null} is returned.
     *
     * @param inventaire the list of {@link Equipements} to check for collision
     * @param jeanguy the {@link Playable} player whose collision is being checked
     * @param tileSize the size of a tile, used to define the player's bounding box
     * @return the {@link Equipements} object that collides with the player, or {@code null} if none
     */
    public static Equipements collisionEquipement(ArrayList<Equipements> inventaire, Playable jeanguy, int tileSize){
        int posX=jeanguy.getPosition().get(0);
        int posY=jeanguy.getPosition().get(1);
        int posXend= posX+tileSize;
        int posYend=posY+tileSize;;
        for (Equipements arme : inventaire) {


            if (posX < arme.getPosition().get(0) + tileSize && posXend > arme.getPosition().get(0) && posY < arme.getPosition().get(1) + tileSize && posYend > arme.getPosition().get(1)){
                    return arme;
                }
            }

        return null;
    }

}

