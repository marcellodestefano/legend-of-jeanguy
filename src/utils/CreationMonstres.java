package utils;

import entities.players.NonPlayable;
import entities.players.Players;
import main.GamePanel;
import tile.Tile;

import java.util.ArrayList;
import java.util.Random;
/**
 * Utility class for handling the creation or spawning of monsters (NonPlayable characters) on the game map.
 * <p>
 * This class provides a method to check whether a monster can be safely spawned
 * at its current position, taking into account map tiles and existing characters.
 */
public class CreationMonstres {
    /**
     * Determines if a {@link NonPlayable} monster can be created/spawned at its current position on the map.
     * <p>
     * The method checks two main conditions:
     * <ul>
     *     <li>Whether the monster's position is on walkable path tiles.</li>
     *     <li>Whether the monster's position does not overlap with other {@link Players} on the map.</li>
     * </ul>
     *
     * @param gp the {@link GamePanel} providing the map, tile, and player information
     * @param npc the {@link NonPlayable} monster whose spawn position is being checked
     * @return {@code true} if the monster can be created at its current position (walkable tile and no overlap),
     *         {@code false} otherwise
     */
    public static boolean creationMonstres(GamePanel gp, NonPlayable npc) {
        int posX, posY, posXend, posYend;
        int row, col, rowEnd, colEnd;

        posX = npc.getPosition().get(0)+4;
        posY = npc.getPosition().get(1);
        posXend = npc.getPosition().get(0)+gp.getTileSize()-4;
        posYend = npc.getPosition().get(1)+gp.getTileSize();

        ArrayList<Tile> pathTiles =  gp.getTileM().getPathTiles();
        int[][] mapTiles = gp.getTileM().getMapTiles();
        Tile[] tiles = gp.getTileM().getTiles();

        row = (posY)/(gp.getTileSize());
        col = (posX)/(gp.getTileSize());
        rowEnd = (posYend)/(gp.getTileSize());
        colEnd = (posXend)/(gp.getTileSize());

        if(!(pathTiles.contains(tiles[mapTiles[col][rowEnd]]) && pathTiles.contains(tiles[mapTiles[colEnd][rowEnd]]))){
            return false;
        }
        for (Players p : gp.getPersonnages()){
            if(p!=npc){
                if(p.getPosition().get(0)<npc.getPosition().get(0)+gp.getTileSize() &&
                        p.getPosition().get(0)+gp.getTileSize()>npc.getPosition().get(0) &&
                        p.getPosition().get(1)<npc.getPosition().get(1)+gp.getTileSize() &&
                        p.getPosition().get(1)+gp.getTileSize()>npc.getPosition().get(1)){
                    return false;
                }
            }
        }
        return true;

    }
}
