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
        posXend = npc.getPosition().get(0)+gp.tileSize-4;
        posYend = npc.getPosition().get(1)+gp.tileSize;

        row = (posY)/(gp.tileSize);
        col = (posX)/(gp.tileSize);
        rowEnd = (posYend)/(gp.tileSize);
        colEnd = (posXend)/(gp.tileSize);

        if (direction.contains("up") ) {
            row = (posY - (int) npc.getSpeed())/(gp.tileSize);

            rowEnd = (posYend- (int) npc.getSpeed())/(gp.tileSize);

        }
        if(direction.contains("down") ) {
            row = (posY + (int) npc.getSpeed())/(gp.tileSize);

            rowEnd = (posYend + (int) npc.getSpeed())/(gp.tileSize);

        }
        if(direction.contains("left")) {

            col = (posX- (int) npc.getSpeed())/(gp.tileSize);

            colEnd = (posXend - (int) npc.getSpeed())/(gp.tileSize);
        }
        if(direction.contains("right")) {
            col = (posX + (int) npc.getSpeed())/(gp.tileSize);

            colEnd = (posXend+ (int) npc.getSpeed())/(gp.tileSize);
        }
        if(pathTiles.contains(tiles[mapTiles[col][rowEnd]])&&pathTiles.contains(tiles[mapTiles[colEnd][rowEnd]])) {
            return "path";
        }

        return "block";

    }
}
