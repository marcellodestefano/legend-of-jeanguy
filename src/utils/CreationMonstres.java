package utils;

import entities.players.NonPlayable;
import entities.players.Players;
import main.GamePanel;
import tile.Tile;

import java.util.ArrayList;
import java.util.Random;

public class CreationMonstres {
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
