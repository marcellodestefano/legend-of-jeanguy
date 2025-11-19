package utils;

import entities.players.JeanGuy;
import entities.players.Playable;
import main.GamePanel;
import tile.Tile;

import java.util.ArrayList;

public class CollisionsMap {
    public static String collisionsMap(JeanGuy jeanGuy, ArrayList<Tile> pathTiles, ArrayList<Tile> chunkTiles, int[][] mapTiles, GamePanel gp, Tile[] tiles) {
        int posX, posY, posXend, posYend;
        int row, col, rowEnd, colEnd;





        posX = jeanGuy.getPosition().get(0);
        posY = jeanGuy.getPosition().get(1);
        posXend = jeanGuy.getPosition().get(0)+gp.tileSize;
        posYend = jeanGuy.getPosition().get(1)+gp.tileSize;

        row = (posY)/(gp.tileSize);
        col = (posX)/(gp.tileSize);
        rowEnd = (posYend)/(gp.tileSize);
        colEnd = (posXend)/(gp.tileSize);

        if (jeanGuy.getKeyHandler().upPressed ) {
            row = (posY - (int) jeanGuy.getSpeed())/(gp.tileSize);
            col = (posX)/(gp.tileSize);
            rowEnd = (posYend- (int) jeanGuy.getSpeed())/(gp.tileSize);
            colEnd = (posXend)/(gp.tileSize);
        }
        if(jeanGuy.getKeyHandler().downPressed) {
            row = (posY + (int) jeanGuy.getSpeed())/(gp.tileSize);
            col = (posX)/(gp.tileSize);
            rowEnd = (posYend + (int) jeanGuy.getSpeed())/(gp.tileSize);
            colEnd = (posXend)/(gp.tileSize);
        }
        if(jeanGuy.getKeyHandler().leftPressed) {
            row = (posY)/(gp.tileSize);
            col = (posX- (int) jeanGuy.getSpeed())/(gp.tileSize);
            rowEnd = (posYend)/(gp.tileSize);
            colEnd = (posXend - (int) jeanGuy.getSpeed())/(gp.tileSize);
        }
        if(jeanGuy.getKeyHandler().rightPressed) {
            row = (posY)/(gp.tileSize);
            col = (posX + (int) jeanGuy.getSpeed())/(gp.tileSize);
            rowEnd = (posYend)/(gp.tileSize);
            colEnd = (posXend+ (int) jeanGuy.getSpeed())/(gp.tileSize);
        }




        System.out.println("piedi : "+mapTiles[col][rowEnd]+ " " +mapTiles[colEnd][rowEnd]+ "\n " + rowEnd + " " + colEnd);
        if (pathTiles.contains(tiles[mapTiles[col][rowEnd]])&&pathTiles.contains(tiles[mapTiles[colEnd][rowEnd]])) {
            return "path";
        } else if (chunkTiles.contains(tiles[mapTiles[col][row]])) {
            return "chunk";
        } else {
            return "block";
        }
    }

}
