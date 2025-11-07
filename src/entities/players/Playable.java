package entities.players;

import java.util.List;
import entities.equipements.armes.*;
abstract class Playable extends Players{
    protected Armes arme;
    protected int argent;

    public Playable(String name, int damage, List<Integer> position, int range, int hp, float speed, boolean isDead, boolean isMelee, int attackSpeed, boolean killable, List<String> soundPaths, List<String> spritePaths){
        super(name, damage, position, range,  hp,  speed,  isMelee,  attackSpeed,  killable,  soundPaths,spritePaths);
        this.argent = 0;
        this.arme = new EpeeBois(10);
    }

    public void equip(Armes arme){
        this.arme = arme;
    }

    public Armes getArmes(){
        return this.arme;
    }

    public int getArgent(){
        return this.argent;
    }


}
