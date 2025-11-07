package entities.players;


import java.util.*;

abstract class NonPlayable extends Players{
    protected static int id;

    public NonPlayable(String name,int damage, List<Integer> position, int range, int hp, float speed, boolean isDead, boolean isMelee, int attackSpeed, boolean killable, List<String> soundPaths, List<String> spritePaths) {
        super (name, damage, position, range,  hp,  speed,  isMelee,  attackSpeed,  killable,  soundPaths,spritePaths);
        id = id++;

    }
    public int getId() {
        return id;
    }

}
