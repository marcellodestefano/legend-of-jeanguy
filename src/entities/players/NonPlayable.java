package entities.players;


import main.GamePanel;

import java.util.*;

public abstract class NonPlayable extends Players{
    protected static int id;

    public NonPlayable(GamePanel panel , String name, int damage, List<Integer> position, int range, int hp, int speed, boolean isDead, boolean isMelee, int attackSpeed, boolean killable, List<String> soundPaths, List<String> spritePaths) {
        super (panel, name, damage, position, range,  hp,  speed,  isMelee,  attackSpeed,  killable,  soundPaths,spritePaths);
        id = id++;

    }
    public int getId() {
        return id;
    }

}
