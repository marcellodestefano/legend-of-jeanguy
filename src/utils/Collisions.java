package utils;

import entities.players.Players;

import java.util.*;
/**
 * Utility class for detecting collisions between players.
 * <p>
 * This class provides a method to check if a {@link Players} object (e.g., an enemy or player)
 * would collide with any other players when moving in a specific direction.
 */

public class Collisions {
    /**
     * Checks for collisions between a given player and a list of other players in a specified direction.
     * <p>
     * The method calculates the bounding box of the {@code toCheck} player based on its current position
     * and the {@code tileSize}. It then checks if this bounding box would intersect with any other
     * player's bounding box when moving in the given {@code direction}.
     * <p>
     * The returned string indicates the type of collision:
     * <ul>
     *     <li>{@code "up-player"}, {@code "down-player"}, {@code "left-player"}, {@code "right-player"}:
     *         collision with the player named "Jean-Guy" in the specified direction.</li>
     *     <li>{@code "enemy"}: collision with any other player (not "Jean-Guy").</li>
     *     <li>{@code "ok"}: no collision detected.</li>
     * </ul>
     *
     * @param players the list of all {@link Players} objects to check collisions against
     * @param direction the movement direction ("up", "down", "left", "right") to check
     * @param toCheck the {@link Players} object whose movement is being checked
     * @param tileSize the size of a tile, used to calculate the bounding box for collision
     * @return a {@link String} indicating the type of collision ("up-player", "down-player", "left-player", "right-player", "enemy", or "ok")
     */
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
