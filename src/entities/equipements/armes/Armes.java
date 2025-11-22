package entities.equipements.armes;
import java.util.ArrayList;
import java.util.List;
import entities.equipements.Equipements;
import main.GamePanel;

public abstract class Armes extends Equipements{
    protected int range;


    public Armes(GamePanel gp, boolean drop, String name, int unite, List<String> spritePath, boolean isMelee, int range, ArrayList<Integer> position){
        super(gp, drop, name, unite, spritePath, position);
        this.range = range;

    }

    public int getRange(){
        return range;
    }
}
