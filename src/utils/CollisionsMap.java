package utils;

import entities.players.JeanGuy;
import entities.players.Playable;
import entities.players.Players;
import main.GamePanel;
import tile.Tile;

import java.util.ArrayList;
/**
 * Utility class for handling collision detection between the player and the game map.
 * <p>
 * This class provides a method to check what type of tile or area the player is colliding with,
 * based on their current position, movement direction, and speed.
 */
public class CollisionsMap {
    /**
     * Determines the type of collision the player ({@link Playable}) has with the map.
     * <p>
     * The method calculates the bounding box of the player and determines which tiles
     * (from {@code mapTiles} and {@code tiles}) the player is interacting with. It considers
     * both movement input from the player and damage knockback direction.
     * <p>
     * Possible return values:
     * <ul>
     *     <li>{@code "path"}: the player is on a walkable path tile.</li>
     *     <li>{@code "chunk"}: the player is on a chunk tile or reached the map boundaries.</li>
     *     <li>{@code "merchant"}: the player is on a merchant tile.</li>
     *     <li>{@code "exitmerchant"}: the player is on an exit tile of the merchant.</li>
     *     <li>{@code "block"}: the player is blocked by a non-walkable tile.</li>
     * </ul>
     *
     * @param jeanGuy the {@link Playable} player whose collision is being checked
     * @param pathTiles the list of tiles considered as walkable paths
     * @param chunkTiles the list of tiles considered as map chunks
     * @param mapTiles a 2D array representing the map, where each element is an index into {@code tiles}
     * @param gp the {@link GamePanel} containing screen and tile information
     * @param tiles an array of {@link Tile} objects representing all possible tiles in the game
     * @return a {@link String} indicating the type of collision ("path", "chunk", "merchant", "exitmerchant", or "block")
     */
    public static String collisionsMap(Playable jeanGuy, ArrayList<Tile> pathTiles, ArrayList<Tile> chunkTiles, int[][] mapTiles, GamePanel gp, Tile[] tiles) {
        int posX, posY, posXend, posYend;
        int col, rowEnd, colEnd;


        posX = jeanGuy.getPosition().get(0)+4;
        posY = jeanGuy.getPosition().get(1);
        posXend = jeanGuy.getPosition().get(0)+gp.getTileSize()-4;
        posYend = jeanGuy.getPosition().get(1)+gp.getTileSize();

        col = (posX)/(gp.getTileSize());
        rowEnd = (posYend)/(gp.getTileSize());
        colEnd = (posXend)/(gp.getTileSize());

        if(!jeanGuy.isGettingDamage()){
        if (jeanGuy.getKeyHandler().isUpPressed() ) {
            rowEnd = (posYend- jeanGuy.checkSpeed())/(gp.getTileSize());

        }
        if(jeanGuy.getKeyHandler().isDownPressed() ) {
            rowEnd = (posYend + jeanGuy.checkSpeed())/(gp.getTileSize());

        }
        if(jeanGuy.getKeyHandler().isLeftPressed()) {
            col = (posX- jeanGuy.checkSpeed())/(gp.getTileSize());
            colEnd = (posXend -  jeanGuy.checkSpeed())/(gp.getTileSize());
        }
        if(jeanGuy.getKeyHandler().isRightPressed()) {
            col = (posX + jeanGuy.checkSpeed())/(gp.getTileSize());
            colEnd = (posXend + jeanGuy.checkSpeed())/(gp.getTileSize());
        }}
        else{
            if(jeanGuy.getDmgdir().contains("up")){
                rowEnd = (posYend- jeanGuy.checkSpeed())/(gp.getTileSize());
            }
            if(jeanGuy.getDmgdir().contains("down")){
                rowEnd = (posYend + jeanGuy.checkSpeed())/(gp.getTileSize());
            }
            if(jeanGuy.getDmgdir().contains("left")){
                col = (posX- jeanGuy.checkSpeed())/(gp.getTileSize());
                colEnd = (posXend -  jeanGuy.checkSpeed())/(gp.getTileSize());
            }
            if(jeanGuy.getDmgdir().contains("right")){
                col = (posX + jeanGuy.checkSpeed())/(gp.getTileSize());
                colEnd = (posXend + jeanGuy.checkSpeed())/(gp.getTileSize());
            }
        }




        if(posXend>=gp.getScreenWidth() - gp.getTileSize() || posX<=0 || posY<=0 || posYend>=gp.getScreenHeight() - gp.getTileSize()){
            return "chunk";
        }
        else if(pathTiles.contains(tiles[mapTiles[col][rowEnd]])&&pathTiles.contains(tiles[mapTiles[colEnd][rowEnd]])) {
            return "path";
        } else if(chunkTiles.contains(tiles[mapTiles[col][rowEnd]]) || chunkTiles.contains(tiles[mapTiles[colEnd][rowEnd]])) {
            return "chunk";
        } else if(mapTiles[col][rowEnd]==46 && mapTiles[colEnd][rowEnd]==46){
            return "merchant";

        }else if(mapTiles[col][rowEnd]==339 || mapTiles[colEnd][rowEnd]==340){
            return "exitmerchant";
        }
        return "block";
    }

}
