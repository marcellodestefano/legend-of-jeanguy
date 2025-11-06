package main.java.com.LegendOfJG.entities;

abstract class Equipements {
    protected static int id;
    protected boolean drop;
    protected String name;
    protected int unite;
    protected String spritePath;

    public Equipements() {
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

    public String getSpritePath() {
        return spritePath;
    }
}



