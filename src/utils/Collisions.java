package utils;

import entities.players.Players;

import java.util.*;


public class Collisions {
    public static String collisions(ArrayList<Players> players, String direction, Players toCheck, int tileSize ) {
        int posX = toCheck.getPosition().get(0);
        int posXend = posX + tileSize;
        int posY = toCheck.getPosition().get(1);
        int posYend = posY + tileSize;

        if (direction.equals("up")) {
            for (Players player : players) {
                if (player.getName() != toCheck.getName()) {
                    if ((posY > player.getPosition().get(1) && posY < player.getPosition().get(1)+tileSize )&& ((posXend > player.getPosition().get(0) && posXend <player.getPosition().get(0)+tileSize)||(posX > player.getPosition().get(0) && posX < player.getPosition().get(0)+tileSize))) {
                        if(player.getName().equals("Jean-Guy")){
                            return "up-player";
                        }else{
                            return "enemy";
                        }
                    }
                }
            }
        }else if (direction.equals("down")) {
            for (Players player : players) {
                if (player.getName() != toCheck.getName()) {
                    if ((posYend > player.getPosition().get(1) && posYend < player.getPosition().get(1)+tileSize) && ((posXend > player.getPosition().get(0) && posXend <player.getPosition().get(0)+tileSize)||(posX > player.getPosition().get(0) && posX < player.getPosition().get(0)+tileSize))) {
                        if(player.getName().equals("Jean-Guy")){
                            return "down-player";
                        }else{
                            return "enemy";
                        }

                    }
                }
            }
        }else if (direction.equals("left")) {
            for (Players player : players) {
                if (player.getName() != toCheck.getName()) {
                    if ((posX > player.getPosition().get(0) && posX < player.getPosition().get(0)+tileSize) && ((posYend > player.getPosition().get(1) && posYend <player.getPosition().get(1)+tileSize)||(posY > player.getPosition().get(1) && posY < player.getPosition().get(1)+tileSize))) {
                        if(player.getName().equals("Jean-Guy")){
                            return "left-player";
                        }else{
                            return "enemy";
                        }
                    }
                }
            }
        }else if (direction.equals("right")) {
            for (Players player : players) {
                if (player.getName() != toCheck.getName()) {
                    if ((posXend > player.getPosition().get(0) && posXend < player.getPosition().get(0)+tileSize) && ((posYend > player.getPosition().get(1) && posYend <player.getPosition().get(1)+tileSize)||(posY > player.getPosition().get(1) && posY < player.getPosition().get(1)+tileSize))) {
                        if(player.getName().equals("Jean-Guy")){
                            return "right-player";
                        }else{
                            return "enemy";
                        }
                    }
                }
            }
        }
        return "ok";

    }
}
