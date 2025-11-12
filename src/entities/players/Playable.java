package entities.players;

import java.util.ArrayList;
import java.util.List;

import entities.equipements.Equipements;
import entities.equipements.armes.*;
import main.GamePanel;

public abstract class Playable extends Players{

    protected int argent;
    protected ArrayList<Equipements> inventaire = new ArrayList<>();

    public Playable(GamePanel panel, String name, int damage, List<Integer> position, int range, int hp, int speed, boolean isDead, boolean isMelee, int attackSpeed, boolean killable, List<String> soundPaths, List<String> spritePaths){
        super(panel, name, damage, position, range,  hp,  speed, isDead, isMelee,  attackSpeed,  killable,  soundPaths, spritePaths);
        this.argent = 0;
        Equipements arme  = new EpeeBois(gamePanel);
        inventaire.add(arme);
    }


    public ArrayList<Equipements> getInventaire(){
        return this.inventaire;
    }

    public int getArgent(){
        return this.argent;
    }

    public void setArgent(int argent){
        this.argent += argent;
    }

    public void setInventaire(Equipements arme){
        if (inventaire.size()<2) {
            this.inventaire.add(arme);
        }else{
            inventaire.remove(1);
            inventaire.add(arme);
        }
    }


}
