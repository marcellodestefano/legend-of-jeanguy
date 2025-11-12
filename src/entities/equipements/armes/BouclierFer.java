package entities.equipements.armes;
import main.GamePanel;

import java.util.*;

public class BouclierFer extends Armes{
    protected int prix = 30;


    public BouclierFer(GamePanel gp){
        super(gp, false, "Bouclier_en_fer", 3, new ArrayList<String>(Arrays.asList("","")), true, 1);
    }

    public int getPrix(){
        return prix;
    }

}
