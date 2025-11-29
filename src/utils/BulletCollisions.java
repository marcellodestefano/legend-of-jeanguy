package utils;

import entities.bullets.Bullets;
import entities.players.Playable;
import main.GamePanel;
/**
 * Utility class to handle collision detection for bullets.
 * <p>
 * This class provides a method to check if a bullet has either:
 * <ul>
 *     <li>Hit the target player</li>
 *     <li>Gone out of the screen bounds</li>
 *     <li>Or is still in a valid state</li>
 * </ul>
 */
public class BulletCollisions {
    /**
     * Checks whether a bullet collides with a target or goes out of screen bounds.
     * <p>
     * The method calculates the bullet's hitbox and compares it with the target player's
     * bounding box. It also checks if the bullet has left the visible screen area.
     *
     * @param gp the GamePanel instance containing game information and tile size
     * @param bullet the bullet to check for collisions
     * @param cible the target player that the bullet might hit
     * @return a String indicating the bullet status:
     *         <ul>
     *             <li>"screen" - bullet is out of screen bounds</li>
     *             <li>"touche" - bullet hit the target player</li>
     *             <li>"ok" - bullet is still in play and no collision occurred</li>
     *         </ul>
     */
    public static String bulletCollisions(GamePanel gp, Bullets bullet, Playable cible){
        int posX=(int) Math.round(bullet.getPosition().get(0));
        int posY=(int) Math.round(bullet.getPosition().get(1));
        int posXend= posX+gp.getTileSize();
        int posYend=posY+gp.getTileSize();
        if(bullet.getPosition().get(0)<0 || bullet.getPosition().get(1)<0 || bullet.getPosition().get(0)>gp.getWidth()|| bullet.getPosition().get(1)>gp.getHeight()){
            return "screen";
        } else if (posX < cible.getPosition().get(0) + gp.getTileSize() && posXend > cible.getPosition().get(0) && posY < cible.getPosition().get(1) + gp.getTileSize() && posYend > cible.getPosition().get(1)) {
            return "touche";
        }else{
            return "ok";
        }
    }
}
