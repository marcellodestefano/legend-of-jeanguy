package utils;


import entities.players.NonPlayable;
import entities.players.Playable;

public class AlgorithmMovement {

    public static String movements(NonPlayable enemy, Playable player) {

        float diffx = enemy.getPosition().get(0) - player.getPosition().get(0);
        float diffy = enemy.getPosition().get(1) - player.getPosition().get(1);

        if (Math.abs(diffx) > Math.abs(diffy)) {
            if (diffx > 0) {
                return "left";
            } else {
                return "right";
            }
        } else if (Math.abs(diffy) > Math.abs(diffx)) {
            if (diffy > 0) {
                return "up";
            } else {
                return "down";
            }
        }else {
            return "none";
        }
    }
}




