package entities.bullets;

import entities.players.Players;
import main.GamePanel;

import java.util.Arrays;

    /**
     * Subclass of the super class Bullet.
     * It represents the bullet sent by the NPC Octorok.
    */

public class Octorokatk extends Bullets{
        /**
         * Creates a new OctorokAttack bullet.
         * @param gamePanel reference to the main gamepanel.
         * @param sender the entity that sent the bullet (Octorok).
         * @param receiver the entity that the bullet is going to search.
         */
    public Octorokatk(GamePanel gamePanel, Players sender, Players receiver) {
        super(gamePanel, sender, receiver, Arrays.asList("/assets/ennemies/shootingmob/the_bullet.png"),2, 3);

    }
}
