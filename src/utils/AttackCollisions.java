package utils;

import entities.players.JeanGuy;
import entities.players.Players;

import java.util.ArrayList;

public class AttackCollisions {

    public static Players attackCollisions(ArrayList<Players> enemies, String direction, JeanGuy jeanguy, int tileSize){
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
