package entities.players;

import java.util.List;
import entities.equipements.armes.*;
import main.GamePanel;

public abstract class Playable extends Players{
    protected Armes arme;
    protected int argent;

    public Playable(GamePanel panel, String name, int damage, List<Integer> position, int range, int hp, int speed, boolean isDead, boolean isMelee, int attackSpeed, boolean killable, List<String> soundPaths, List<String> spritePaths){
        super(panel, name, damage, position, range,  hp,  speed,  isMelee,  attackSpeed,  killable,  soundPaths, spritePaths);
        this.argent = 0;
        this.arme = new EpeeBois(gamePanel);
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
