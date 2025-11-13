package utils;

import entities.players.JeanGuy;
import entities.players.NonPlayable;
import entities.players.Players;

import java.util.ArrayList;

public class CollisionDistance {
    public static boolean collisionDistance(ArrayList<Players> players, JeanGuy jeanGuy, int tileSize){
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
