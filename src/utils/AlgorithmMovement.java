package utils;


import entities.players.NonPlayable;
import entities.players.Playable;
import main.GamePanel;

import java.util.ArrayList;

public class AlgorithmMovement {

    public static String movements(GamePanel gamePanel, NonPlayable enemy, Playable player) {

        float diffx = enemy.getPosition().get(0) - player.getPosition().get(0);
        float diffy = enemy.getPosition().get(1) - player.getPosition().get(1);
        String direction;
        if(enemy.getCpdmg()!=0){
            enemy.setCpdmg();
            return enemy.getDmgdir();
        }
        else{
        if (Math.abs(diffx) >= Math.abs(diffy)) {
            if (diffx > 0) {
                direction = "left";
                if(Collisions.collisions(gamePanel.personnages, direction,enemy , gamePanel.tileSize)=="ok"){
                    return "left";
                }else{
                    return "none";
                }
            } else {
                direction = "right";
                if(Collisions.collisions(gamePanel.personnages, direction, enemy, gamePanel.tileSize)=="ok"){
                    return "right";
                }else{
                    return "none";
                }

            }
        } else if (Math.abs(diffy) > Math.abs(diffx)) {
            if (diffy > 0) {
                direction = "up";
                if(Collisions.collisions(gamePanel.personnages, direction, enemy, gamePanel.tileSize)=="ok"){
                    return "up";
                }else{
                    return "none";
                }

            } else {
                direction = "down";
                if(Collisions.collisions(gamePanel.personnages, direction, enemy, gamePanel.tileSize)=="ok"){
                    return "down";
                }else{
                    return "none";
                }

            }
        }else {
            return "none";
        }}
    }
}




