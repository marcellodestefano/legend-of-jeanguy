package utils;

import entities.bullets.Bullets;
import entities.players.Playable;
import main.GamePanel;

public class BulletCollisions {
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
