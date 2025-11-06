package main.java.com.LegendOfJG.entities;
import java.util.*;

abstract class Equipements {
    protected static int id;
    protected boolean drop;
    protected String name;
    protected int unite;
    protected List<String> spritePath;

    public Equipements(boolean drop, String name, int unite, List<String> spritePath) {
        this.drop = drop;
        this.name = name;
        this.unite = unite;
        this.spritePath = spritePath;
        id = id++;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getUnite() {
        return unite;
    }

    public List<String> getSpritePath() {
        return spritePath;
    }
}



