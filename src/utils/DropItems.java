package utils;

import entities.equipements.soins.Coeur;
import entities.equipements.soins.CoeurMax;
import entities.equipements.soins.Soins;
import entities.players.NonPlayable;
import entities.players.Players;
import main.GamePanel;

import java.util.ArrayList;
import java.util.Arrays;

public class DropItems {

    public static void dropItems(String order,GamePanel gp, Players npc){
        ArrayList<Integer> position = new ArrayList<Integer>(Arrays.asList(0,0,0));
        if (order == "coeur") {
            Coeur coeur = new Coeur(gp);
            position.set(0, npc.getPosition().get(0));
            position.set(1, npc.getPosition().get(1));
            position.set(2, npc.getPosition().get(2));
            coeur.setPosition(position);
            gp.getEquipements().add(coeur);
        }else if (order == "coeurmax") {
            CoeurMax coeurMax = new CoeurMax(gp);
            position.set(0, npc.getPosition().get(0));
            position.set(1, npc.getPosition().get(1));
            position.set(2, npc.getPosition().get(2));
            coeurMax.setPosition(position);
            gp.getEquipements().add(coeurMax);
        }

    }
}
