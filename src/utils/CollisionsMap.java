package utils;

import entities.players.JeanGuy;
import entities.players.Playable;
import entities.players.Players;
import main.GamePanel;
import tile.Tile;

import java.util.ArrayList;

public class CollisionsMap {
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
        } else if(chunkTiles.contains(tiles[mapTiles[col][rowEnd]]) && chunkTiles.contains(tiles[mapTiles[colEnd][rowEnd]])) {
            return "chunk";
        } else if(mapTiles[col][rowEnd]==46 && mapTiles[colEnd][rowEnd]==46){
            return "merchant";

        }else if(mapTiles[col][rowEnd]==339 || mapTiles[colEnd][rowEnd]==340){
            return "exitmerchant";
        }
        return "block";
    }

}
