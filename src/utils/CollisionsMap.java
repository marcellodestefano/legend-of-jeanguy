package utils;

import entities.players.JeanGuy;
import entities.players.Playable;
import entities.players.Players;
import main.GamePanel;
import tile.Tile;

import java.util.ArrayList;

public class CollisionsMap {
    public static String collisionsMap(JeanGuy jeanGuy, ArrayList<Tile> pathTiles, ArrayList<Tile> chunkTiles, int[][] mapTiles, GamePanel gp, Tile[] tiles) {
        int posX, posY, posXend, posYend;
        int row, col, rowEnd, colEnd;

        posX = jeanGuy.getPosition().get(0)+4;
        posY = jeanGuy.getPosition().get(1);
        posXend = jeanGuy.getPosition().get(0)+gp.tileSize-4;
        posYend = jeanGuy.getPosition().get(1)+gp.tileSize;

        row = (posY)/(gp.tileSize);
        col = (posX)/(gp.tileSize);
        rowEnd = (posYend)/(gp.tileSize);
        colEnd = (posXend)/(gp.tileSize);

        if (jeanGuy.getKeyHandler().upPressed ) {
            row = (posY - (int) jeanGuy.getSpeed())/(gp.tileSize);

            rowEnd = (posYend- (int) jeanGuy.getSpeed())/(gp.tileSize);

        }
        if(jeanGuy.getKeyHandler().downPressed) {
            row = (posY + (int) jeanGuy.getSpeed())/(gp.tileSize);

            rowEnd = (posYend + (int) jeanGuy.getSpeed())/(gp.tileSize);

        }
        if(jeanGuy.getKeyHandler().leftPressed) {

            col = (posX- (int) jeanGuy.getSpeed())/(gp.tileSize);

            colEnd = (posXend - (int) jeanGuy.getSpeed())/(gp.tileSize);
        }
        if(jeanGuy.getKeyHandler().rightPressed) {
            col = (posX + (int) jeanGuy.getSpeed())/(gp.tileSize);

            colEnd = (posXend+ (int) jeanGuy.getSpeed())/(gp.tileSize);
        }




        if(posXend>=gp.screenWidth - gp.tileSize || posX<=0 || posY<=0 || posYend>=gp.screenHeight - gp.tileSize){
            return "chunk";
        }
        else if(pathTiles.contains(tiles[mapTiles[col][rowEnd]])&&pathTiles.contains(tiles[mapTiles[colEnd][rowEnd]])) {
            return "path";
        } else if(chunkTiles.contains(tiles[mapTiles[col][rowEnd]]) && chunkTiles.contains(tiles[mapTiles[colEnd][rowEnd]])) {
            return "chunk";
        } else if(mapTiles[col][rowEnd]==46 && mapTiles[colEnd][rowEnd]==46){
            return "merchant";

        }else if(mapTiles[col][rowEnd]==339 && mapTiles[colEnd][rowEnd]==340){
            return "exitmerchant";
        }
        return "block";
    }

}
