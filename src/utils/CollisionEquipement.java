package utils;

import entities.equipements.Equipements;
import entities.equipements.armes.Armes;
import entities.players.Playable;
import entities.players.Players;

import java.util.ArrayList;

public class CollisionEquipement {
    public static Equipements collisionEquipement(ArrayList<Equipements> inventaire, Playable jeanguy, int tileSize){
        int posX=jeanguy.getPosition().get(0);
        int posY=jeanguy.getPosition().get(1);
        int posXend= posX+tileSize;
        int posYend=posY+tileSize;;
        for (Equipements arme : inventaire) {


            if (posX < arme.getPosition().get(0) + tileSize && posXend > arme.getPosition().get(0) && posY < arme.getPosition().get(1) + tileSize && posYend > arme.getPosition().get(1)){
                    return arme;
                }
            }

        return null;
    }

}

