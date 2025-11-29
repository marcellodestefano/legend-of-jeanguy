package utils;

import entities.equipements.soins.Coeur;
import entities.equipements.soins.CoeurMax;
import entities.equipements.soins.Soins;
import entities.players.NonPlayable;
import entities.players.Players;
import main.GamePanel;

import java.util.ArrayList;
import java.util.Arrays;
/**
 * Utility class for dropping items in the game.
 * <p>
 * This class provides a method to spawn equipment or healing items at the position of a
 * {@link Players} character (usually a defeated NonPlayable character).
 */
public class DropItems {
    /**
     * Drops an item at the position of the specified {@link Players} character based on the given order.
     * <p>
     * Supported items:
     * <ul>
     *     <li>{@code "coeur"} - drops a {@link Coeur} (healing item)</li>
     *     <li>{@code "coeurmax"} - drops a {@link CoeurMax} (full health item)</li>
     * </ul>
     * The item is placed at the same coordinates as the NPC or player, and added to the game panel's equipment list.
     *
     * @param order a {@link String} indicating the type of item to drop ("coeur" or "coeurmax")
     * @param gp the {@link GamePanel} where the item will be added
     * @param npc the {@link Players} character whose position will be used for the item drop
     */
    public static void dropItems(String order,GamePanel gp, Players npc){
        ArrayList<Integer> position = new ArrayList<Integer>(Arrays.asList(0,0,0));
        if (order == "coeur") {
            Coeur coeur = new Coeur(gp);
            position.set(0, npc.getPosition().get(0));
            position.set(1, npc.getPosition().get(1));
            position.set(2, npc.getPosition().get(2));
            coeur.setPosition(position);
            gp.getEquipements().add(coeur);
        }else if (order == "coeurmax") {
            CoeurMax coeurMax = new CoeurMax(gp);
            position.set(0, npc.getPosition().get(0));
            position.set(1, npc.getPosition().get(1));
            position.set(2, npc.getPosition().get(2));
            coeurMax.setPosition(position);
            gp.getEquipements().add(coeurMax);
        }

    }
}
