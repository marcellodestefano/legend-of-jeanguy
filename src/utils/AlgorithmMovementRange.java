package utils;

import entities.players.NonPlayable;
import entities.players.Playable;
import main.GamePanel;

public class AlgorithmMovementRange {

    public static String algorithmMovementRange(GamePanel gamePanel, NonPlayable enemy, Playable player) {
        float diffx = enemy.getPosition().get(0) - player.getPosition().get(0);
        float diffy = enemy.getPosition().get(1) - player.getPosition().get(1);
        double ipten = Math.sqrt(diffx*diffx+diffy*diffy);
        String direction;

        if (enemy.getCpdmg() != 0) {
            enemy.setCpdmg();
            return enemy.getDmgdir();
        }
        if (enemy.getAttackSpeed() < 10) {
            enemy.setKillable(true);
            if (Math.abs(diffx) >= Math.abs(diffy)) {
                if (diffx >= 0) {
                    return "atk-left";
                } else {
                    return "atk-right";
                }
            } else if (Math.abs(diffx) < Math.abs(diffy)) {
                if (diffy >= 0) {
                    return "atk-up";
                } else {
                    return "atk-downds";
                }
            }

        } else if (ipten<200){
            enemy.setKillable(true);
            if (Math.abs(diffx) >= Math.abs(diffy)) {
                if (diffx > 0) {
                    direction = "right";
                    if (Collisions.collisions(gamePanel.personnages, direction, enemy, gamePanel.tileSize).equals("ok")) {
                        return "right";
                    }
                } else {
                    direction = "left";
                    if (Collisions.collisions(gamePanel.personnages, direction, enemy, gamePanel.tileSize).equals("ok")) {
                        return "left";
                    }

                }
            } else if (Math.abs(diffy) > Math.abs(diffx)) {
                if (diffy > 0) {
                    direction = "down";
                    if (Collisions.collisions(gamePanel.personnages, direction, enemy, gamePanel.tileSize).equals("ok")) {
                        return "down";
                    }

                } else {
                    direction = "up";
                    if (Collisions.collisions(gamePanel.personnages, direction, enemy, gamePanel.tileSize).equals("ok")) {
                        return "up";
                    }

                }
            }
        }
        return "none";

    }
}
