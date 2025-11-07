package entities.equipements.armes;
import java.util.List;
import entities.equipements.Equipements;

abstract class Armes extends Equipements{
    protected boolean isMelee;
    protected int range;

    public Armes(boolean drop, String name, int unite, List<String> spritePath, boolean isMelee, int range){
        this.isMelee = isMelee;
        this.range = range;
        super(drop, name, unite, spritePath);
    }

    public boolean getIsMelee(){
        return isMelee;
    }

    public int getRange(){
        return range;
    }
}
