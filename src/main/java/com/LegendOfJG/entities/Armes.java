package main.java.com.LegendOfJG.entities;

abstract class Armes extends Equipements{
    protected boolean isMelee;
    protected int range;

    public boolean getIsMelee(){
        return isMelee;
    }

    public int getRange(){
        return range;
    }
}
