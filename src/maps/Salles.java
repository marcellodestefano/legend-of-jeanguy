package maps;
import java.util.*;
import entities.players.NonPlayable;

abstract class Salles{
    protected static int id;
    protected String type;
    protected List<Object> elements;
    protected List<NonPlayable> monstres;
    protected String sound;
    protected String spritePath;

    public Salles(){
            id = id++;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public List<Object> getElements() {
        return elements;
    }

    public List<NonPlayable> getMonstres(){
        return this.monstres;
    }

    public String getSound() {
        return sound;
    }

    public String getSpritePath() {
        return spritePath;
    }




}