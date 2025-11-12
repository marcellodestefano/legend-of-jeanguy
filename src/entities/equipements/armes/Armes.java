package entities.equipements.armes;
import java.util.List;
import entities.equipements.Equipements;
import main.GamePanel;

public abstract class Armes extends Equipements{
    protected boolean isMelee;
    protected int range;


    public Armes(GamePanel gp, boolean drop, String name, int unite, List<String> spritePath, boolean isMelee, int range){
        super(gp, drop, name, unite, spritePath);
        this.isMelee = isMelee;
        this.range = range;

    }

    public boolean getIsMelee(){
        return isMelee;
    }

    public int getRange(){
        return range;
    }
}
