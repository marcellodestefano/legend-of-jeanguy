package utils;

import entities.players.JeanGuy;
import entities.players.NonPlayable;
import main.GamePanel;
import tile.Tile;

import java.util.ArrayList;

public class CollisionsNpcMap {
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
