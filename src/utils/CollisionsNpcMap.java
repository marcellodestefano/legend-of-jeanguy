package utils;

import entities.players.JeanGuy;
import entities.players.NonPlayable;
import main.GamePanel;
import tile.Tile;

import java.util.ArrayList;
/**
 * Utility class for handling collision detection between NPCs (NonPlayable characters) and the game map.
 * <p>
 * This class provides a method to check whether an NPC can move in a certain direction based on the map layout.
 */
public class CollisionsNpcMap {
    /**
     * Determines the type of collision an NPC ({@link NonPlayable}) will have on the map when moving in a given direction.
     * <p>
     * The method calculates the NPC's bounding box after moving in the specified direction,
     * and checks which tiles the NPC would occupy. It uses the {@code pathTiles} list to determine
     * walkable tiles.
     * <p>
     * Possible return values:
     * <ul>
     *     <li>{@code "path"}: the NPC can move in the desired direction (walkable path tiles).</li>
     *     <li>{@code "block"}: the NPC's movement is blocked (non-walkable tiles).</li>
     * </ul>
     *
     * @param npc the {@link NonPlayable} NPC whose collision is being checked
     * @param direction the direction the NPC intends to move ("up", "down", "left", "right")
     * @param pathTiles a list of {@link Tile} objects considered as walkable paths
     * @param mapTiles a 2D array representing the map, where each element is an index into {@code tiles}
     * @param gp the {@link GamePanel} containing screen and tile information
     * @param tiles an array of {@link Tile} objects representing all possible tiles in the game
     * @return a {@link String} indicating the type of collision ("path" if the NPC can move, "block" if movement is blocked)
     */
    public static String collisionsNpcMap(NonPlayable npc,String direction, ArrayList<Tile> pathTiles, int[][] mapTiles, GamePanel gp, Tile[] tiles) {
        int posX, posY, posXend, posYend;
        int row, col, rowEnd, colEnd;

        posX = npc.getPosition().get(0)+4;
        posY = npc.getPosition().get(1);
        posXend = npc.getPosition().get(0)+gp.getTileSize()-4;
        posYend = npc.getPosition().get(1)+gp.getTileSize();

        row = (posY)/(gp.getTileSize());
        col = (posX)/(gp.getTileSize());
        rowEnd = (posYend)/(gp.getTileSize());
        colEnd = (posXend)/(gp.getTileSize());

        if (direction.contains("up") ) {
            row = (posY - (int) npc.getSpeed())/(gp.getTileSize());

            rowEnd = (posYend- (int) npc.getSpeed())/(gp.getTileSize());

        }
        if(direction.contains("down") ) {
            row = (posY + (int) npc.getSpeed())/(gp.getTileSize());

            rowEnd = (posYend + (int) npc.getSpeed())/(gp.getTileSize());

        }
        if(direction.contains("left")) {

            col = (posX- (int) npc.getSpeed())/(gp.getTileSize());

            colEnd = (posXend - (int) npc.getSpeed())/(gp.getTileSize());
        }
        if(direction.contains("right")) {
            col = (posX + (int) npc.getSpeed())/(gp.getTileSize());

            colEnd = (posXend+ (int) npc.getSpeed())/(gp.getTileSize());
        }
        if(pathTiles.contains(tiles[mapTiles[col][rowEnd]])&&pathTiles.contains(tiles[mapTiles[colEnd][rowEnd]])) {
            return "path";
        }

        return "block";

    }
}
